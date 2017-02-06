package yasetai.domain.model.diet;

import lombok.Data;

import java.util.List;

@Data
public class DailyDiet {

  private String id;
  private String timestamp;
  private List<Food> foods;

}
