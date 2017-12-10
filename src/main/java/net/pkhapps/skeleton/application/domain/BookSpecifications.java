package net.pkhapps.skeleton.application.domain;

import org.springframework.data.jpa.domain.Specification;

public class BookSpecifications {

    public static Specification<Book> byBarcode(String barcode) {
        return (Specification<Book>) (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(Book_.barcode), barcode);
    }
}
