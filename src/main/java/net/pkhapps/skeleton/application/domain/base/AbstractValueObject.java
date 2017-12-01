package net.pkhapps.skeleton.application.domain.base;

import org.springframework.lang.NonNull;

import java.io.Serializable;
import java.util.Objects;

/**
 * Base class for value objects.
 *
 * @see AbstractEntity
 * @see AbstractAggregateRoot
 */
public abstract class AbstractValueObject implements Serializable {

    /**
     * Default constructor.
     */
    public AbstractValueObject() {
    }

    /**
     * Copy constructor for creating an exact copy of the original value object.
     *
     * @param original the value object to copy from.
     * @see #copy()
     */
    public AbstractValueObject(@NonNull AbstractValueObject original) {
        Objects.requireNonNull(original, "original must not be null");
    }

    /**
     * Helper method for invoking the {@link #AbstractValueObject(AbstractValueObject) copy constructor}. If a copy
     * constructor does not exist or is not accessible, this method will try to invoke the {@link #clone()} method.
     * If that also fails, an {@link Error} is thrown. Developers should not attempt to catch the error but make sure
     * that there is a copy constructor or that the value object is clonable instead.
     *
     * @return a copy of this value object.
     */
    @NonNull
    public AbstractValueObject copy() {
        try {
            return getClass().getConstructor(getClass()).newInstance(this);
        } catch (Exception e) {
            try {
                return (AbstractValueObject) clone();
            } catch (CloneNotSupportedException ex) {
                throw new Error("An accessible copy constructor was not found and the value object could not be cloned", e);
            }
        }
    }

    /**
     * Calculates and returns a hash code based on the value of this value object. This will be returned by
     * {@link #hashCode()}. This method is abstract to ensure that value objects implement the hash code properly.
     *
     * @return the hash code.
     */
    protected abstract int valueHashCode();

    /**
     * Checks if this value object has the same value as the {@code other} value object. The other object is guaranteed
     * to be of the same type as this value object and be non-null, so those need not be checked by the implementation.
     * This method will be used by {@link #equals(Object)} and is abstract to ensure that value objects implement the
     * equality check properly.
     *
     * @param other the other value object to compare with.
     * @return whether this value object has the same value as the other value object.
     */
    protected abstract boolean hasSameValue(@NonNull AbstractValueObject other);

    @Override
    public boolean equals(Object obj) {
        return obj == this || obj != null && obj.getClass() == getClass() && hasSameValue((AbstractValueObject) obj);
    }

    @Override
    public int hashCode() {
        return valueHashCode();
    }
}
