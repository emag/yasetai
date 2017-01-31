package yasetai.domain.model;

public interface DailyLogRepository {

  DailyLog find(String id);
  DailyLog create(DailyLog dailyLog);

}
