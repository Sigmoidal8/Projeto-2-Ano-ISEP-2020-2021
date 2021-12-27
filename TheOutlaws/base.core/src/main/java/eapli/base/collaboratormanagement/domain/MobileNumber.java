/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.collaboratormanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;
import javax.persistence.Embeddable;

/**
 *
 * @author migue
 */
@Embeddable
public class MobileNumber implements ValueObject, Comparable<MobileNumber> {

    private static final long serialVersionUID = 1L;

    private String mobileNumber;

    public MobileNumber(String mobileNumber) {
        if (StringPredicates.isNullOrEmpty(mobileNumber)) {
            throw new IllegalArgumentException(
                    "O número de contacto inserido não é válido");
        }
        // TODO validate invariants, i.e., mecanographic number regular
        // expression
        this.mobileNumber = mobileNumber;
    }

    protected MobileNumber() {
        // for ORM
    }

    public static MobileNumber valueOf(final String mobileNumber) {
        return new MobileNumber(mobileNumber);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MobileNumber)) {
            return false;
        }

        final MobileNumber that = (MobileNumber) o;
        return this.mobileNumber.equals(that.mobileNumber);
    }

    @Override
    public int hashCode() {
        return this.mobileNumber.hashCode();
    }

    @Override
    public int compareTo(final MobileNumber arg0) {
        return mobileNumber.compareTo(arg0.mobileNumber);
    }

    public String toString() {
        return String.format("%s", mobileNumber);
    }

    
}
