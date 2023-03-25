package amazin.repository;

import amazin.model.Customer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CustomerRepository extends ReactiveCrudRepository<Customer, Integer> {
    Customer findCustomerById(Integer id);
}
