package yasetai.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import yasetai.domain.model.DailyLog;
import yasetai.domain.model.DailyLogRepository;

import javax.enterprise.context.ApplicationScoped;
import java.io.IOException;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Collections;

@ApplicationScoped
public class EsDailyLogRepository implements DailyLogRepository {

  public DailyLog find(Integer id) {
    return new DailyLog();
  }

  @Override
  public DailyLog create(DailyLog dailyLog) {
    ObjectMapper mapper = new ObjectMapper();

    // TODO この際シングルトンにしちゃってもいい?
    try (RestClient client = RestClient.builder(new HttpHost("localhost", 9200)).build()) {
      dailyLog.setTimestamp(ZonedDateTime.now(ZoneOffset.UTC).toString());

      HttpEntity entity = new NStringEntity(mapper.writeValueAsString(dailyLog), ContentType.APPLICATION_JSON);

      Response response = client.performRequest("POST", "/dailyLog/log", Collections.emptyMap(), entity);

      // TODO ES のレスポンスをモデルに変換する
      return null;

    } catch (IOException e) {
      throw new RuntimeException(e);
    }

  }

}
