package yasetai.infrastructure.client;

import org.junit.Test;
import yasetai.domain.model.DailyLog;

import static org.assertj.core.api.Assertions.assertThat;

public class EsClientTest {

  @Test
  public void create_daily_log() throws Exception {
    try (EsClient client = EsClient.create(new EsHost("localhost", 9200))) {
      DailyLog response = client.post(new DailyLog());

      assertThat(response.getId()).isNotNull();
    }
  }

  @Test
  public void get_a_daily_log() throws Exception {
    try (EsClient client = EsClient.create(new EsHost("localhost", 9200))) {
      DailyLog newDailyLog = client.post(new DailyLog());

      DailyLog dailyLog = client.get(newDailyLog.getId());
      assertThat(dailyLog.getId()).isNotNull();
      assertThat(dailyLog.getId()).isEqualTo(dailyLog.getId());
    }
  }

}
