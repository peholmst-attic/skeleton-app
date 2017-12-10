package net.pkhapps.skeleton.application.security;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.lang.NonNull;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * An immutable reference to a user of the system. Feel free to tweak this class according to the user database
 * that you are using.
 *
 * @see UserRefConverter
 * @see UserLookupService
 */
public class UserRef implements Serializable {

    private final String userId;
    private final String userName;

    /**
     * Creates a new {@code UserRef}.
     *
     * @param userId   the user ID (for uniquely identifying the user).
     * @param userName the name of the user (for displaying in UIs etc.)
     */
    public UserRef(@NonNull Long userId, @NonNull String userName) {
        this(Objects.requireNonNull(userId, "userId must not be null").toString(), userName);
    }

    /**
     * Creates a new {@code UserRef}.
     *
     * @param userId   the user ID (for uniquely identifying the user).
     * @param userName the name of the user (for displaying in UIs etc.)
     */
    public UserRef(@NonNull UUID userId, @NonNull String userName) {
        this(Objects.requireNonNull(userId, "userId must not be null").toString(), userName);
    }

    /**
     * Creates a new {@code UserRef}.
     *
     * @param userId   the user ID (for uniquely identifying the user).
     * @param userName the name of the user (for displaying in UIs etc.)
     */
    public UserRef(@NonNull String userId, @NonNull String userName) {
        this.userId = Objects.requireNonNull(userId, "userId must not be null");
        this.userName = Objects.requireNonNull(userName, "userName must not be null");
    }

    /**
     * Returns the ID of the user. This ID can be used to uniquely identify the user within the user database.
     *
     * @return the user ID.
     */
    @NonNull
    public String getUserId() {
        return userId;
    }

    /**
     * Returns the name of the user. This name should not be used inside the system for identifying the user but to help
     * humans to identify the user ("Joe Cool" is easier to understand than "3498293420").
     *
     * @return the name of the user.
     */
    @NonNull
    public String getUserName() {
        return userName;
    }

    @Override
    public String toString() {
        return String.format("%s[userId=%s, userName=%s]", getClass().getSimpleName(), userId, userName);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        UserRef other = (UserRef) obj;

        return new EqualsBuilder().append(userId, other.userId).append(userName, other.userName).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(userId).append(userName).toHashCode();
    }
}
