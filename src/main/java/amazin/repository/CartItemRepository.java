package amazin.repository;

import amazin.model.Book;
import amazin.model.Cart;
import amazin.model.CartItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface CartItemRepository extends CrudRepository<CartItem, Long>{

}
