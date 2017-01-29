package yasetai.api;

import yasetai.domain.model.DailyLog;
import yasetai.domain.model.DailyLogRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/")
public class DailyLogController {

  private DailyLogRepository repository;

  @Inject
  public DailyLogController(DailyLogRepository repository) {
    this.repository = repository;
  }

  public DailyLogController() {}

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public DailyLog get(Integer id) {
    return repository.find(id);
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response post(DailyLog dailyLog) {
    repository.create(dailyLog);
    return null;
  }

}
