/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.collaboratormanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.util.HashCoder;
import eapli.framework.validations.Preconditions;
import javax.persistence.Embeddable;

/**
 *
 * @author migue
 */
@Embeddable
public class Address implements ValueObject {

    private static final long serialVersionUID = 1L;

    private String country;

    private String district;

    private String county;

    private String address;

    public Address(String country, String district, String county, String address) {
        Preconditions.noneNull(country, district, county, address);

        this.country = country;
        this.district = district;
        this.county = county;
        this.address = address;
    }

    protected Address() {
        country=district=county=address="Default";
    }


    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Address)) {
            return false;
        }

        final Address that = (Address) o;
        return  country.equalsIgnoreCase(that.country) && district.equalsIgnoreCase(that.district) && county.equalsIgnoreCase(that.county) && address.equalsIgnoreCase(that.address);
    }

    @Override
    public int hashCode() {
        return new HashCoder().with(country).with(district).with(county).with(address).code();
    }

    @Override
    public String toString(){
        return String.format("Country: %s, District: %s, County: %s, Address: %s", country, district, county, address);
    }
}
