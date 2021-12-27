package eapli.base.catalogmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.Embeddable;

/**
 * @author Raúl Coelho
 */
@Embeddable
public class Icone implements ValueObject, Comparable<Icone>{
    private static final long serialVersionUID = 1L;

    private String location;

    public Icone(String location){
        if(StringPredicates.isNullOrEmpty(location)){
            throw new IllegalArgumentException("The title should not be null nor empty");
        }
        this.location = location;
    }

    protected Icone(){
        //ORM only
    }

    public static Icone valueOf(final String location){
        return new Icone(location);
    }

    public boolean equals(final Object o){
        if(this == o){
            return true;
        }
        if(!(o instanceof Icone)){
            return false;
        }

        final Icone that = (Icone) o;
        return this.location.equals(that.location);
    }

    public int hashCode(){
        return this.location.hashCode();
    }

    @Override
    public int compareTo(Icone o) {
        return location.compareTo(o.location);
    }

    public String toString(){
        return String.format("%s", location);
    }
}
