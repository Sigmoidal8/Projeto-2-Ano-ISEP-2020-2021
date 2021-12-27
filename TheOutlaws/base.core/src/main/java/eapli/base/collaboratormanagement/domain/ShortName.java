/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.collaboratormanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;
import eapli.framework.validations.Preconditions;
import javax.persistence.Embeddable;

/**
 *
 * @author migue
 */
@Embeddable
public class ShortName implements ValueObject, Comparable<ShortName> {

    private static final long serialVersionUID = 1L;

    private String shortName;

    public ShortName(String shortName) {
        if (StringPredicates.isNullOrEmpty(shortName) || shortName.length()>30) {
            throw new IllegalArgumentException(
                    "O Nome Curto não deve ser null nem empty e só pode ter um número" +
                            "máximo de 30 caracteres");
        }
        // TODO validate invariants, i.e., mecanographic number regular
        // expression
        this.shortName = shortName;
    }

    protected ShortName() {
        // for ORM
    }

    public static ShortName valueOf(final String shortName) {
        return new ShortName(shortName);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ShortName)) {
            return false;
        }

        final ShortName that = (ShortName) o;
        return this.shortName.equals(that.shortName);
    }

    @Override
    public int hashCode() {
        return this.shortName.hashCode();
    }

    @Override
    public int compareTo(final ShortName arg0) {
        return shortName.compareTo(arg0.shortName);
    }

    public String toString() {
        return String.format("%s", shortName);
    }

}
