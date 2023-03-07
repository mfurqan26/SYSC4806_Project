package amazin.repository;

import amazin.model.StoreOwner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface StoreOwnerRepository extends CrudRepository<StoreOwner, Long> {
    StoreOwner findStoreOwnerById(long id);

}