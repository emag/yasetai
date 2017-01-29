package yasetai.domain.model;

public interface DailyLogRepository {

  DailyLog find(Integer id);
  DailyLog create(DailyLog dailyLog);

}
