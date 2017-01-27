package yasetai.api;

import yasetai.domain.model.Diet;
import yasetai.domain.model.DietRepository;

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
public class DietController {

  private DietRepository repository;

  @Inject
  public DietController(DietRepository repository) {
    this.repository = repository;
  }

  public DietController() {}

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Diet get(Integer id) {
    return repository.find(id);
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response post(Diet diet) {
    repository.create(diet);
    return null;
  }

}
