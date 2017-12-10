package net.pkhapps.skeleton.application.domain;

import net.pkhapps.skeleton.application.domain.base.AbstractAggregateRoot;

import java.time.Instant;

public class Reservation extends AbstractAggregateRoot {

    private BookTitle title;
    private Customer customer;
    private Instant reservationDate;
    private Library library;

}
