package yasetai.infrastructure.diet;

import yasetai.domain.model.dailylog.DailyLog;
import yasetai.domain.model.diet.DailyDiet;
import yasetai.domain.model.diet.DailyDietRepository;
import yasetai.infrastructure.client.EsClient;
import yasetai.infrastructure.client.EsHost;

import javax.enterprise.context.ApplicationScoped;
import java.io.IOException;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@ApplicationScoped
public class EsDailyDietRepository implements DailyDietRepository {

  @Override
  public DailyDiet find(String id) {
    // TODO この際 EsClient はシングルトンにしちゃってもいい?
    try (EsClient client = EsClient.create(new EsHost("localhost", 9200))) {

      return client.getDailyDiet(id);

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public DailyDiet create(DailyDiet dailyDiet) {
    dailyDiet.setTimestamp(ZonedDateTime.now(ZoneOffset.UTC).toString());

    // TODO この際 EsClient はシングルトンにしちゃってもいい?
    try (EsClient client = EsClient.create(new EsHost("localhost", 9200))) {

      return client.post(dailyDiet);

    } catch (IOException e) {
      throw new RuntimeException(e);
    }

  }

}
