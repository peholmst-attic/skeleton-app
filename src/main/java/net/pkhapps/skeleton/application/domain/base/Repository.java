package net.pkhapps.skeleton.application.domain.base;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Base interface for {@link AbstractAggregateRoot aggregate root} repositories.
 *
 * @param <T> the aggregate root type.
 */
@NoRepositoryBean
public interface Repository<T extends AbstractAggregateRoot> extends CrudRepository<T, Long>,
        JpaSpecificationExecutor<T> {
}
