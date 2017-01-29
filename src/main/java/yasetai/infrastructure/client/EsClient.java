package yasetai.infrastructure.client;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import yasetai.infrastructure.client.response.PostResponse;

import java.io.Closeable;
import java.io.IOException;
import java.util.Collections;

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

  public PostResponse post(Object object) throws IOException {
    HttpEntity entity = new NStringEntity(mapper.writeValueAsString(object), ContentType.APPLICATION_JSON);
    Response rawResponse = client.performRequest("POST", "/yasetai/daily-log", Collections.emptyMap(), entity);
    return mapper.readValue(rawResponse.getEntity().getContent(), PostResponse.class);
  }

  @Override
  public void close() throws IOException {
    client.close();
  }

}
