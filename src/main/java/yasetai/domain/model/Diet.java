package yasetai.domain.model;

import lombok.Data;

import java.util.List;

@Data
public class Diet {

  private String timestamp;
  private float weight;
  private float calorie;
  private float fat;
  private List<String> foods;

}
