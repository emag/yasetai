package yasetai.api.diet;

import yasetai.domain.model.diet.DailyDiet;
import yasetai.domain.model.diet.DailyDietRepository;

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
@Path("/daily-diet")
public class DailyDietController {

  private DailyDietRepository repository;

  @Inject
  public DailyDietController(DailyDietRepository repository) {
    this.repository = repository;
  }

  public DailyDietController() {}

  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public DailyDiet get(@PathParam("id") String id) {
    return repository.find(id);
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response post(@Context UriInfo uriInfo, DailyDiet dailyDiet) {
    DailyDiet created = repository.create(dailyDiet);

    return Response
      .created(
        uriInfo.getAbsolutePathBuilder()
          .path(String.valueOf(created.getId()))
          .build()
      ).build();
  }

}
