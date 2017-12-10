package net.pkhapps.skeleton.application.security.auditing;

import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.temporal.TemporalAccessor;
import java.util.Optional;

/**
 * Default implementation of {@link DateTimeProvider} that uses the {@link Clock} to return the current time.
 */
@Service
class DefaultDateTimeProvider implements DateTimeProvider {

    private final Clock clock;

    DefaultDateTimeProvider(@NonNull Clock clock) {
        this.clock = clock;
    }

    @Override
    public Optional<TemporalAccessor> getNow() {
        return Optional.of(clock.instant());
    }
}
