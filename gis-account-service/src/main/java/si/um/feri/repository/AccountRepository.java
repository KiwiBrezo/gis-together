package si.um.feri.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import si.um.feri.models.AccountEntity;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AccountRepository implements PanacheRepositoryBase<AccountEntity, Integer> {
}
