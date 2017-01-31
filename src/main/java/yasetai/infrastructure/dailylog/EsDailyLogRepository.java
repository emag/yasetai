package yasetai.infrastructure.dailylog;

import yasetai.domain.model.dailylog.DailyLog;
import yasetai.domain.model.dailylog.DailyLogRepository;
import yasetai.infrastructure.client.EsClient;
import yasetai.infrastructure.client.EsHost;

import javax.enterprise.context.ApplicationScoped;
import java.io.IOException;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@ApplicationScoped
public class EsDailyLogRepository implements DailyLogRepository {

  public DailyLog find(String id) {
    // TODO この際 EsClient はシングルトンにしちゃってもいい?
    try (EsClient client = EsClient.create(new EsHost("localhost", 9200))) {

      return client.get(id);

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public DailyLog create(DailyLog dailyLog) {
    dailyLog.setTimestamp(ZonedDateTime.now(ZoneOffset.UTC).toString());

    // TODO この際 EsClient はシングルトンにしちゃってもいい?
    try (EsClient client = EsClient.create(new EsHost("localhost", 9200))) {

      return client.post(dailyLog);

    } catch (IOException e) {
      throw new RuntimeException(e);
    }

  }

}
