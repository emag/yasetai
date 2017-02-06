package yasetai.domain.model.dailylog;

import lombok.Data;

@Data
public class DailyLog {

  private String id;
  private String timestamp;
  private float weight;
  private float fat;

}
