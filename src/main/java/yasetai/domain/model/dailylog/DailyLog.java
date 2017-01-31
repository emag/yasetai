package yasetai.domain.model.dailylog;

import lombok.Data;

import java.util.List;

@Data
public class DailyLog {

  private String id;
  private String timestamp;
  private float weight;
  private float calorie;
  private float fat;
  private List<String> foods;

}
