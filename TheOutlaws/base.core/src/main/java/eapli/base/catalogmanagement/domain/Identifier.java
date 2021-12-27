package eapli.base.catalogmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;

/**
 * @author Raúl Coelho
 */
@Embeddable
public class Identifier implements ValueObject, Comparable<Identifier> {

    private static final long serialVersionUID = 1L;

    private Long identifier;

    public Identifier(Long identifier){
        Preconditions.nonNull(identifier);

        this.identifier = identifier;
    }

    protected Identifier(){
        //ORM only
    }

    public static Identifier valueOf(final Long identifier){
        return new Identifier(identifier);
    }

    public boolean equals(final Object o){
        if(this == o){
            return true;
        }
        if(!(o instanceof Identifier)){
            return false;
        }

        final Identifier that = (Identifier) o;
        return this.identifier.equals(that.identifier);
    }

    public int hashCode(){
        return this.identifier.hashCode();
    }

    @Override
    public int compareTo(Identifier o) {
        return identifier.compareTo(o.identifier);
    }

    public String toString(){
        return String.format("%d", identifier);
    }
}
