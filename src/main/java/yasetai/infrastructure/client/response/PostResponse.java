package yasetai.infrastructure.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PostResponse {

  @JsonProperty("_index")
  private String index;

  @JsonProperty("_type")
  private String type;

  @JsonProperty("_id")
  private String id;

}
