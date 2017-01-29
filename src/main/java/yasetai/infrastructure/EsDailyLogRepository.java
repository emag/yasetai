package yasetai.infrastructure;

import yasetai.domain.model.DailyLog;
import yasetai.domain.model.DailyLogRepository;
import yasetai.infrastructure.client.EsClient;
import yasetai.infrastructure.client.EsHost;
import yasetai.infrastructure.client.response.PostResponse;

import javax.enterprise.context.ApplicationScoped;
import java.io.IOException;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@ApplicationScoped
public class EsDailyLogRepository implements DailyLogRepository {

  public DailyLog find(Integer id) {
    return new DailyLog();
  }

  @Override
  public DailyLog create(DailyLog dailyLog) {
    dailyLog.setTimestamp(ZonedDateTime.now(ZoneOffset.UTC).toString());

    // TODO この際 EsClient はシングルトンにしちゃってもいい?
    try (EsClient client = EsClient.create(new EsHost("localhost", 9200))) {

      PostResponse response = client.post(dailyLog);

      dailyLog.setId(response.getId());
      return dailyLog;

    } catch (IOException e) {
      throw new RuntimeException(e);
    }

  }

}
