package eapli.base.catalogmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.Embeddable;

/**
 * @author Raúl Coelho
 */
@Embeddable
public class Title implements ValueObject, Comparable<Title> {

    private static final long serialVersionUID = 1L;

    private String title;

    public Title(String title){
        if(StringPredicates.isNullOrEmpty(title)){
            throw new IllegalArgumentException("The title should not be null nor empty");
        }
        this.title = title;
    }

    protected Title(){
        //ORM only
    }

    public static Title valueOf(final String title){
        return new Title(title);
    }

    public boolean equals(final Object o){
        if(this == o){
            return true;
        }
        if(!(o instanceof Title)){
            return false;
        }

        final Title that = (Title) o;
        return this.title.equals(that.title);
    }

    public int hashCode(){
        return this.title.hashCode();
    }

    @Override
    public int compareTo(Title o) {
        return title.compareTo(o.title);
    }

    public String toString(){
        return String.format("%s", title);
    }
}
