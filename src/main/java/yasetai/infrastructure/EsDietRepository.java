package yasetai.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import yasetai.domain.model.Diet;
import yasetai.domain.model.DietRepository;

import javax.enterprise.context.ApplicationScoped;
import java.io.IOException;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Collections;

@ApplicationScoped
public class EsDietRepository implements DietRepository {

  public Diet find(Integer id) {
    return new Diet();
  }

  @Override
  public Diet create(Diet diet) {
    ObjectMapper mapper = new ObjectMapper();

    // TODO この際シングルトンにしちゃってもいい?
    try (RestClient client = RestClient.builder(new HttpHost("localhost", 9200)).build()) {
      diet.setTimestamp(ZonedDateTime.now(ZoneOffset.UTC).toString());

      HttpEntity entity = new NStringEntity(mapper.writeValueAsString(diet), ContentType.APPLICATION_JSON);

      Response response = client.performRequest("POST", "/diet/log", Collections.emptyMap(), entity);

      // TODO ES のレスポンスをモデルに変換する
      return null;

    } catch (IOException e) {
      throw new RuntimeException(e);
    }

  }

}
