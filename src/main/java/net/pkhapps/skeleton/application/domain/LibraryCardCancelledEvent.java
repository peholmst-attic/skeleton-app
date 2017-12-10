package net.pkhapps.skeleton.application.domain;

import org.springframework.util.Assert;

public class LibraryCardCancelledEvent extends LibraryCardEvent {

    public LibraryCardCancelledEvent(LibraryCard libraryCard) {
        super(libraryCard);
        Assert.state(libraryCard.getState() == LibraryCardState.CANCELLED, "libraryCard state must be CANCELLED");
    }
}
