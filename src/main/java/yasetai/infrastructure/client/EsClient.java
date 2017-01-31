package yasetai.infrastructure.client;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import yasetai.domain.model.DailyLog;

import java.io.Closeable;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

public class EsClient implements Closeable {

  private final RestClient client;
  private final ObjectMapper mapper;

  private EsClient(EsHost... esHosts) {
    HttpHost[] httpHosts = new HttpHost[esHosts.length];
    for (int i = 0; i < esHosts.length; i++) {
      httpHosts[i] = new HttpHost(esHosts[i].toHttpHost());
    }

    this.client = RestClient.builder(httpHosts).build();

    this.mapper = new ObjectMapper()
      .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

  public static EsClient create(EsHost... esHosts) {
    return new EsClient(esHosts);
  }

  public DailyLog get(String id) throws IOException {
    Response response = client.performRequest("GET", "/yasetai/daily-log/" + id + "/_source");
    DailyLog target = mapper.readValue(response.getEntity().getContent(), DailyLog.class);
    target.setId(id);
    return target;
  }

  public DailyLog post(DailyLog dailyLog) throws IOException {
    HttpEntity entity = new NStringEntity(mapper.writeValueAsString(dailyLog), ContentType.APPLICATION_JSON);
    Response rawResponse = client.performRequest("POST", "/yasetai/daily-log", Collections.emptyMap(), entity);
    Map<String, Object> responseMap = mapper.readValue(rawResponse.getEntity().getContent(), Map.class);

    DailyLog registered = new DailyLog();
    registered.setId(responseMap.get("_id").toString());
    registered.setTimestamp(dailyLog.getTimestamp());
    registered.setWeight(dailyLog.getWeight());
    registered.setFat(dailyLog.getFat());
    registered.setCalorie(dailyLog.getCalorie());
    registered.setFoods(dailyLog.getFoods());

    return registered;
  }

  @Override
  public void close() throws IOException {
    client.close();
  }

}
