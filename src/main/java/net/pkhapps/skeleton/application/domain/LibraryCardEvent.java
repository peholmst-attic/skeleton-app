package net.pkhapps.skeleton.application.domain;

import org.springframework.lang.NonNull;

import java.util.Objects;

public abstract class LibraryCardEvent {

    private final LibraryCard libraryCard;

    public LibraryCardEvent(@NonNull LibraryCard libraryCard) {
        this.libraryCard = Objects.requireNonNull(libraryCard);
    }

    @NonNull
    public LibraryCard getLibraryCard() {
        return libraryCard;
    }
}
