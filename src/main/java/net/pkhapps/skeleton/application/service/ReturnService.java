package net.pkhapps.skeleton.application.service;

import org.springframework.lang.NonNull;

public interface ReturnService {

    void returnBook(@NonNull String barcode);
}
