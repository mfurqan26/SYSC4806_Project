package amazin.repository;

import amazin.model.Cart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface CartRepository extends CrudRepository<Cart, Long> {
	Optional<Cart> findById(long id);
}