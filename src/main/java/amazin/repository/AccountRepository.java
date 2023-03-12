package amazin.repository;

import amazin.model.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import java.util.Optional;

@RepositoryRestResource
public interface AccountRepository extends CrudRepository<Account, Long> {
	public Optional<Account> findAccountByUserName(String userName);
}