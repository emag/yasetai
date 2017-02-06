package yasetai.domain.model.diet;

public interface DailyDietRepository {

  DailyDiet find(String id);
  DailyDiet create(DailyDiet dailyDiet);

}
