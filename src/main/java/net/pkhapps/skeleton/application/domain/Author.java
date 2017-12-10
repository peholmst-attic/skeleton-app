package net.pkhapps.skeleton.application.domain;

import net.pkhapps.skeleton.application.domain.base.AbstractAggregateRoot;
import org.springframework.lang.NonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

/**
 * TODO Document me!
 */
@Entity
public class Author extends AbstractAggregateRoot {

    @Column(nullable = false)
    @NotEmpty(message = "Please enter a first name")
    private String firstName;

    @Column(nullable = false)
    @NotEmpty(message = "Please enter a last name")
    private String lastName;

    public Author() {
    }

    public Author(@NonNull Author origin) {
        super(origin);
        firstName = origin.firstName;
        lastName = origin.lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
