/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.servicemanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import javax.persistence.Embeddable;

/**
 *
 * @author Utilizador
 */
@Embeddable
public class Label implements ValueObject, Comparable<Label> {

    private static final long serialVersionUID = 1L;

    private String label;

    public Label(String label) {
        Preconditions.noneNull(label);
        this.label = label;
    }

    protected Label() {
        // for ORM
    }

    public static Label valueOf(final String label) {
        return new Label(label);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Label)) {
            return false;
        }

        final Label that = (Label) o;
        return this.label ==  that.label;
    }

    @Override
    public int compareTo(Label o) {
        return label.compareTo(o.label); 
    }
    
    public String toString() {
        return String.format("%d", label);
    }
}
