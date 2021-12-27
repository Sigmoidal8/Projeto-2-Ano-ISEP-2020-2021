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
public class CompleteName implements ValueObject, Comparable<CompleteName> {

    private static final long serialVersionUID = 1L;

    private String completeName;

    public CompleteName(String completeName) {
        if (StringPredicates.isNullOrEmpty(completeName) || completeName.length()>80) {
            throw new IllegalArgumentException(
                    "O Nome Completo não deve ser null nem empty e só pode ter um número" +
                            "máximo de 80 caracteres");
        }
        // TODO validate invariants, i.e., mecanographic number regular
        // expression
        this.completeName = completeName;
    }

    protected CompleteName() {
        // for ORM
    }

    public static CompleteName valueOf(final String completeName) {
        return new CompleteName(completeName);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CompleteName)) {
            return false;
        }

        final CompleteName that = (CompleteName) o;
        return this.completeName.equals(that.completeName);
    }

    @Override
    public int hashCode() {
        return this.completeName.hashCode();
    }

    @Override
    public int compareTo(final CompleteName arg0) {
        return completeName.compareTo(arg0.completeName);
    }

    public String toString() {
        return String.format("%s", completeName);
    }

    
}
