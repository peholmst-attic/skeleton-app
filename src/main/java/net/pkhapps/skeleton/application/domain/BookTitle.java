package net.pkhapps.skeleton.application.domain;

import net.pkhapps.skeleton.application.domain.base.AbstractAggregateRoot;
import net.pkhapps.skeleton.application.domain.constraints.ISBN;
import org.springframework.lang.NonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * TODO Documentm me!
 */
@Entity
public class BookTitle extends AbstractAggregateRoot {

    @Column(unique = true, nullable = false)
    @NotEmpty(message = "Please specify an ISBN")
    private String isbn;
    @Column(nullable = false)
    @NotEmpty(message = "Please specify a title")
    @ISBN
    private String title;
    @ManyToMany
    @Size(min = 1, message = "Please specify at least one author")
    private Set<Author> authors = new HashSet<>();

    public BookTitle() {
    }

    public BookTitle(@NonNull BookTitle origin) {
        super(origin);
        isbn = origin.isbn;
        title = origin.title;
        authors = new HashSet<>(origin.authors);
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @NonNull
    public Set<Author> getAuthors() {
        return Collections.unmodifiableSet(authors);
    }

    public void addAuthor(@NonNull Author author) {
        authors.add(Objects.requireNonNull(author));
    }

    public void removeAuthor(@NonNull Author author) {
        authors.remove(Objects.requireNonNull(author));
    }
}
