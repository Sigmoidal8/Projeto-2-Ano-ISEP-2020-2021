/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.teammanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.util.HashCoder;
import eapli.framework.validations.Preconditions;
import javax.persistence.Embeddable;

/**
 *
 * @author raulcoelho
 */
@Embeddable
public class Color implements ValueObject{
    private static final long serialVersionUID = 1L;
    
    private final int red;
    
    private final int green;
    
    private final int blue;
    
    private final String colorName;
    
    public Color(int red, int green, int blue, String colorName){
        Preconditions.ensure(red <= 255 && green <= 255 && blue <= 255 && red >= 0 && green >= 0 && blue >= 0);

        this.red=red;
        this.green=green;
        this.blue=blue;
        this.colorName=colorName;
    }
    
    protected Color(){
        red = green = blue = 0;
        colorName = "default";
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Color)) {
            return false;
        }

        final Color that = (Color) o;
        return red == that.red && green == that.green && blue == that.blue
                && colorName.equalsIgnoreCase(that.colorName);
    }
    
    @Override
    public int hashCode() {
        return new HashCoder().with(red).with(green).with(blue).with(colorName).code();
    }
    
    @Override
    public String toString() {
        return this.colorName+":"+this.red + ", " + this.green+", "+this.blue;
    }

}
