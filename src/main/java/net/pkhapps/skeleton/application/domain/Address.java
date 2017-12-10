package net.pkhapps.skeleton.application.domain;

import net.pkhapps.skeleton.application.domain.base.AbstractValueObject;
import net.pkhapps.skeleton.application.domain.base.DefaultJpaConstructor;

import javax.persistence.Embeddable;

@Embeddable
public class Address extends AbstractValueObject {

    private String street;
    private String postalCode;
    private String postOffice;
    private String country;

    @DefaultJpaConstructor
    Address() {
    }

    public Address(String street, String postalCode, String postOffice, String country) {
        this.street = street;
        this.postalCode = postalCode;
        this.postOffice = postOffice;
        this.country = country;
    }

    public Address(Address original) {
        super(original);
        street = original.street;
        postalCode = original.postalCode;
        postOffice = original.postOffice;
        country = original.country;
    }

    public String getStreet() {
        return street;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getPostOffice() {
        return postOffice;
    }

    public String getCountry() {
        return country;
    }

    @Override
    protected int valueHashCode() {
        return 0; // TODO
    }

    @Override
    protected boolean hasSameValue(AbstractValueObject other) {
        return false; // TODO
    }
}
