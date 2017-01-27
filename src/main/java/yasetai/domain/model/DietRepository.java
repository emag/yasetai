package yasetai.domain.model;

public interface DietRepository {

  Diet find(Integer id);
  Diet create(Diet diet);

}
