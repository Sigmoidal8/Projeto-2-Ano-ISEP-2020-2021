/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.collaboratormanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;
import eapli.framework.validations.Preconditions;
import java.util.Date;
import javax.persistence.Embeddable;

/**
 *
 * @author migue
 */
@Embeddable
public class BirthDate implements ValueObject, Comparable<BirthDate> {

    private static final long serialVersionUID = 1L;

    private Date birthDate;

    public BirthDate(Date birthDate) {
        Preconditions.noneNull(birthDate);
        this.birthDate = birthDate;
    }

    protected BirthDate() {
        // for ORM
    }

    public static BirthDate valueOf(final Date birthDate) {
        return new BirthDate(birthDate);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BirthDate)) {
            return false;
        }

        final BirthDate that = (BirthDate) o;
        return this.birthDate.equals(that.birthDate);
    }

    @Override
    public int hashCode() {
        return this.birthDate.hashCode();
    }

    @Override
    public int compareTo(final BirthDate arg0) {
        return birthDate.compareTo(arg0.birthDate);
    }

    public String toString() {
        return String.format("%s", birthDate);
    }

    
}
