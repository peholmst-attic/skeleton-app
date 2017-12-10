package net.pkhapps.skeleton.application.domain;

import net.pkhapps.skeleton.application.domain.base.AbstractAggregateRoot;
import net.pkhapps.skeleton.application.domain.base.DefaultJpaConstructor;
import org.springframework.lang.NonNull;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import java.util.Objects;

@Entity
public class Book extends AbstractAggregateRoot {

    @ManyToOne
    private BookTitle title;

    private String barcode;

    @ManyToOne
    private Library library;

    private String shelf;

    @Enumerated(EnumType.STRING)
    private BookState state;

    @DefaultJpaConstructor
    Book() {
    }

    public Book(@NonNull BookTitle title, @NonNull Library library) {
        this.title = Objects.requireNonNull(title, "title must not be null");
        this.library = Objects.requireNonNull(library, "library must not be null");
        this.state = BookState.REGISTERED;
        // TODO Barcode
        // TODO Default shelf
    }

    @NonNull
    public BookState getState() {
        return state;
    }
}
