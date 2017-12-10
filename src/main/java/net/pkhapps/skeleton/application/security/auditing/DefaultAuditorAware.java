package net.pkhapps.skeleton.application.security.auditing;

import net.pkhapps.skeleton.application.security.UserLookupService;
import net.pkhapps.skeleton.application.security.UserRef;
import org.springframework.data.domain.AuditorAware;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Default implementation of {@link AuditorAware} that uses the {@link SecurityContextHolder} and the
 * {@link UserLookupService} to return the current auditor as a {@link UserRef}.
 */
@Service
class DefaultAuditorAware implements AuditorAware<UserRef> {

    private final UserLookupService userLookupService;

    DefaultAuditorAware(@NonNull UserLookupService userLookupService) {
        this.userLookupService = userLookupService;
    }

    @Override
    public Optional<UserRef> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return Optional.ofNullable(authentication).flatMap(userLookupService::createUserReference);
    }
}
