package net.pkhapps.skeleton.application.security;

import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Objects;
import java.util.Optional;

/**
 * A service for looking up detailed user information.
 */
public interface UserLookupService extends UserDetailsService {

    /**
     * Loads the user details of the given {@code userRef}.
     *
     * @param userRef the user reference whose details should be loaded.
     * @return the user details.
     */
    @NonNull
    Optional<UserDetails> loadByReference(@NonNull UserRef userRef);

    /**
     * Creates a new {@link UserRef} for the given {@code userDetails} if possible.
     *
     * @param userDetails the user details.
     * @return the user reference.
     */
    @NonNull
    Optional<UserRef> createUserReference(@NonNull UserDetails userDetails);

    /**
     * Creates a new {@link UserRef} for the given {@code authentication} token if possible. The default implementation
     * will simply take the {@link Authentication#getDetails() details} of the token and pass them straight to
     * {@link #createUserReference(UserDetails)}. If the system does not support this, you have to override this method.
     *
     * @param authentication the authentication token.
     * @return the user reference.
     */
    @NonNull
    default Optional<UserRef> createUserReference(@NonNull Authentication authentication) {
        Objects.requireNonNull(authentication, "authentication must not be null");
        Object details = authentication.getDetails();
        if (!(details instanceof UserDetails)) {
            throw new IllegalArgumentException("The given authentication token did not contain UserDetails");
        }
        return createUserReference((UserDetails) details);
    }
}
