package si.um.feri.mappers;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import si.um.feri.models.Account;
import si.um.feri.models.AccountEntity;

import javax.decorator.Decorator;
import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@Mapper(componentModel = "cdi")
public interface AccountMapper {
    Account toDomain(AccountEntity account);

    List<Account> toDomainList(List<AccountEntity> entities);

    @InheritInverseConfiguration(name = "toDomain")
    AccountEntity toEntity(Account account);

    void updateEntityFromDomain(Account domain, @MappingTarget AccountEntity entity);

    void updateDomainFromEntity(AccountEntity entity, @MappingTarget Account domain);
}
