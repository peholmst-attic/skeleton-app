package net.pkhapps.skeleton.application.domain.base;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.data.domain.Persistable;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.ClassUtils;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Objects;

/**
 * Base class for entities.
 *
 * @see AbstractValueObject
 * @see AbstractAggregateRoot
 */
@MappedSuperclass
public abstract class AbstractEntity implements Persistable<Long> {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    /**
     * Default constructor.
     */
    public AbstractEntity() {
    }

    /**
     * Copy constructor for creating an exact copy of the original entity.
     *
     * @param origin the entity to copy from.
     */
    public AbstractEntity(@NonNull AbstractEntity origin) {
        Objects.requireNonNull(origin, "origin must not be null");
        this.id = origin.id;
    }

    @Nullable
    @Override
    public final Long getId() {
        return id;
    }

    /**
     * Sets the ID of this entity. The ID is auto-generated so developers should rarely need to call this method.
     *
     * @param id the ID to set.
     */
    protected final void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean isNew() {
        return id == null;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !getClass().equals(ClassUtils.getUserClass(obj))) {
            return false;
        } else if (obj == this) {
            return true;
        }
        return id != null && id.equals(((AbstractEntity) obj).id);
    }

    @Override
    public int hashCode() {
        HashCodeBuilder hashCodeBuilder = new HashCodeBuilder().append(getClass());
        if (id == null) {
            hashCodeBuilder.append(super.hashCode());
        } else {
            hashCodeBuilder.append(id);
        }
        return hashCodeBuilder.toHashCode();
    }

    @Override
    public String toString() {
        return String.format("%s[id=%d]", getClass().getSimpleName(), id);
    }
}
