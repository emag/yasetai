package yasetai.domain.model.dailylog;

public interface DailyLogRepository {

  DailyLog find(String id);
  DailyLog create(DailyLog dailyLog);

}
