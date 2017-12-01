package net.pkhapps.skeleton.application.domain.base;

import net.pkhapps.skeleton.application.security.UserRef;
import org.springframework.data.domain.AfterDomainEventPublication;
import org.springframework.data.domain.Auditable;
import org.springframework.data.domain.DomainEvents;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Base class for aggregate roots.
 *
 * @see AbstractEntity
 * @see AbstractValueObject
 * @see Repository
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractAggregateRoot extends AbstractEntity implements Auditable<UserRef, Long, Instant> {

    @Transient
    private final List<Object> domainEvents = new ArrayList<>();
    @Version
    @Column(name = "version")
    private Long version;
    @Column(name = "created_by")
    private UserRef createdBy;
    @Column(name = "created_date")
    private Instant createdDate;
    @Column(name = "last_modified_by")
    private UserRef lastModifiedBy;
    @Column(name = "last_modified_date")
    private Instant lastModifiedDate;

    /**
     * Default constructor.
     */
    public AbstractAggregateRoot() {
    }

    /**
     * Copy constructor for creating an exact copy of the original aggregate root.
     *
     * @param origin the aggregate root to copy from.
     */
    public AbstractAggregateRoot(@NonNull AbstractAggregateRoot origin) {
        super(origin);
        version = origin.version;
        createdBy = origin.createdBy;
        createdDate = origin.createdDate;
        lastModifiedBy = origin.lastModifiedBy;
        lastModifiedDate = origin.lastModifiedDate;
    }

    /**
     * Registers the specified {@code domainEvent} for publication when this aggregate root has been saved.
     *
     * @param domainEvent the domain event to register.
     * @return the registered domain event.
     */
    @NonNull
    protected final <T> T registerEvent(@NonNull T domainEvent) {
        domainEvents.add(Objects.requireNonNull(domainEvent, "domainEvent must not be null"));
        return domainEvent;
    }

    /**
     * Registers the specified {@code domainEvent} for publication when this aggregate root has been saved, deleting
     * any previously registered events of the same class. This ensures that only one instance of the type of this
     * domain event is published.
     *
     * @param domainEvent the domain event to register.
     * @return the registered domain event.
     */
    @NonNull
    protected final <T> T registerSingletonEvent(@NonNull T domainEvent) {
        domainEvents.removeIf(registered -> registered.getClass().equals(domainEvent.getClass()));
        return registerEvent(domainEvent);
    }

    /**
     * Returns a list of domain events to publish when this aggregate root has been saved. Developers should never need
     * to invoke this method, Spring Data takes care of that automatically.
     *
     * @see #registerEvent(Object)
     */
    @DomainEvents
    protected final List<Object> domainEvents() {
        return domainEvents;
    }

    /**
     * Clears the list of registered {@link #domainEvents() domain events}. Developers should never need to invoke this
     * method, Spring Data takes care of that automatically.
     *
     * @see #registerEvent(Object)
     */
    @AfterDomainEventPublication
    protected final void clearDomainEvents() {
        domainEvents.clear();
    }

    /**
     * Returns the version number of this aggregate root.
     *
     * @return the version number.
     */
    public final Optional<Long> getVersion() {
        return Optional.of(version);
    }

    @Override
    public final Optional<UserRef> getCreatedBy() {
        return Optional.of(createdBy);
    }

    @Override
    public final void setCreatedBy(UserRef createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public final Optional<Instant> getCreatedDate() {
        return Optional.of(createdDate);
    }

    @Override
    public final void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public final Optional<UserRef> getLastModifiedBy() {
        return Optional.of(lastModifiedBy);
    }

    @Override
    public final void setLastModifiedBy(UserRef lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    @Override
    public final Optional<Instant> getLastModifiedDate() {
        return Optional.of(lastModifiedDate);
    }

    @Override
    public final void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
