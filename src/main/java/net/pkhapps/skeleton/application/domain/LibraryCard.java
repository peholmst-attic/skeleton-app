package net.pkhapps.skeleton.application.domain;

import net.pkhapps.skeleton.application.domain.base.AbstractAggregateRoot;
import net.pkhapps.skeleton.application.domain.base.DefaultJpaConstructor;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * A library card is used by customers to easily identify themselves while borrowing books from the library. All library
 * cards have a unique bar code. The bar codes are pre-printed on the cards and entered into the system at the moment
 * the card is issued to a customer. A customer can have multiple library cards.
 */
@Entity
@Table(name = LibraryCard.TABLE_NAME)
public class LibraryCard extends AbstractAggregateRoot {
    public static final String TABLE_NAME = "library_cards";

    @Column(unique = true, nullable = false)
    @NotEmpty
    private String barcode;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull
    private LibraryCardState state;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    @NotNull
    private Customer customer;

    @DefaultJpaConstructor
    LibraryCard() {
    }

    public LibraryCard(@NonNull String barcode, @NonNull Customer customer) {
        this.barcode = Objects.requireNonNull(barcode, "barcode must not be null");
        this.customer = Objects.requireNonNull(customer, "customer must not be null");
        state = LibraryCardState.ACTIVE;
    }

    @NonNull
    public Customer getCustomer() {
        return customer;
    }

    @NonNull
    public LibraryCardState getState() {
        return state;
    }

    @NonNull
    public String getBarcode() {
        return barcode;
    }

    public void cancel() {
        if (state != LibraryCardState.CANCELLED) {
            state = LibraryCardState.CANCELLED;
            registerEvent(new LibraryCardCancelledEvent(this));
        }
    }

    public void markAsMissing() {
        if (state == LibraryCardState.ACTIVE) {
            state = LibraryCardState.MISSING;
        }
    }
}
