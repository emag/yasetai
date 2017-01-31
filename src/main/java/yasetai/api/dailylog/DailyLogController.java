package yasetai.api.dailylog;

import yasetai.domain.model.dailylog.DailyLog;
import yasetai.domain.model.dailylog.DailyLogRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@ApplicationScoped
@Path("/daily-log")
public class DailyLogController {

  private DailyLogRepository repository;

  @Inject
  public DailyLogController(DailyLogRepository repository) {
    this.repository = repository;
  }

  public DailyLogController() {}

  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public DailyLog get(@PathParam("id") String id) {
    return repository.find(id);
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response post(@Context UriInfo uriInfo, DailyLog dailyLog) {
    DailyLog created = repository.create(dailyLog);

    return Response
      .created(
        uriInfo.getAbsolutePathBuilder()
          .path(String.valueOf(created.getId()))
          .build()
      ).build();
  }

}
