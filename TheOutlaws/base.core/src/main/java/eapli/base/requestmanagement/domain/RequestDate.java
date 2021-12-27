/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.requestmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import java.util.Date;
import javax.persistence.Embeddable;

/**
 *
 * @author José Forno
 */
@Embeddable
public class RequestDate implements ValueObject, Comparable<RequestDate>{
    
    private static final long serialVersionUID = 1L;

    private Date requestDate;

    public RequestDate(Date requestDate) {
        Preconditions.noneNull(requestDate);
        this.requestDate = requestDate;
    }

    protected RequestDate() {
        // for ORM
    }

    public static RequestDate valueOf(final Date requestDate) {
        return new RequestDate(requestDate);
    }
    
    public Date date(){
        return this.requestDate;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RequestDate)) {
            return false;
        }

        final RequestDate that = (RequestDate) o;
        return this.requestDate.equals(that.requestDate);
    }

    @Override
    public int hashCode() {
        return this.requestDate.hashCode();
    }

    @Override
    public int compareTo(final RequestDate arg0) {
        return requestDate.compareTo(arg0.requestDate);
    }

    public String toString() {
        return String.format("%s", requestDate);
    }

    
}
