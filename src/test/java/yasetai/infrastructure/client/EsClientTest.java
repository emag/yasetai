package yasetai.infrastructure.client;

import org.junit.Test;
import yasetai.domain.model.DailyLog;
import yasetai.infrastructure.client.response.PostResponse;

import static org.assertj.core.api.Assertions.assertThat;

public class EsClientTest {

  @Test
  public void create_daily_log() throws Exception {
    try (EsClient client = EsClient.create(new EsHost("localhost", 9200))) {
      PostResponse response = client.post(new DailyLog());

      assertThat(response.getIndex()).isNotNull();
      assertThat(response.getType()).isNotNull();
      assertThat(response.getId()).isNotNull();
    }
  }

}
