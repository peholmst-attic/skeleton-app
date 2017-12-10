package net.pkhapps.skeleton.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
class ReturnServiceBean implements ReturnService { // TODO Secure

    @Override
    public void returnBook(String barcode) {

    }
}
