package net.pkhapps.skeleton.application.domain;

import net.pkhapps.skeleton.application.domain.base.AbstractEntity;
import net.pkhapps.skeleton.application.security.UserRef;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.time.Instant;
import java.util.Objects;
import java.util.Optional;

/**
 * TODO Document me!
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
public class BookStateChangedEvent extends AbstractEntity {

    @CreatedDate
    @Column(nullable = false)
    private Instant stateChanged;

    @CreatedBy
    @Column(nullable = false)
    private UserRef stateChangedBy;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookState stateChangedTo;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private Book book;

    /**
     * Used by JPA only.
     */
    BookStateChangedEvent() {
    }

    public BookStateChangedEvent(@NonNull BookState stateChangedTo, @NonNull Book book) {
        this.stateChangedTo = Objects.requireNonNull(stateChangedTo, "stateChangedToMust not be null");
        this.book = Objects.requireNonNull(book, "book must not be null");
    }

    @NonNull
    public Optional<Instant> getStateChanged() {
        return Optional.ofNullable(stateChanged);
    }

    @NonNull
    public Optional<UserRef> getStateChangedBy() {
        return Optional.ofNullable(stateChangedBy);
    }

    @NonNull
    public BookState getStateChangedTo() {
        return stateChangedTo;
    }

    @NonNull
    public Book getBook() {
        return book;
    }
}
