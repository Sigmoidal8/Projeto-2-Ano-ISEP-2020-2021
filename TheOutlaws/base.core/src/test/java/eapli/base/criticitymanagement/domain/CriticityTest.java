/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.criticitymanagement.domain;

import eapli.base.teammanagement.domain.Color;
import org.junit.Test;

import java.time.LocalTime;

/**
 *
 * @author migue
 */
public class CriticityTest {
    
    private final Color color = new Color(255,255,255,"black");
    
    private final Objective obj = new Objective(LocalTime.of(0,3,0),LocalTime.of(0,4,0),LocalTime.of(0,5,0),LocalTime.of(0,6,0));

    @Test(expected = IllegalArgumentException.class)
    public void ensureCriticityCantHaveNullLabel() {
        System.out.println("Ensure Team Type Cant Have Null Label");
        Criticity instance = new Criticity(null,Value.valueOf(1), color,obj);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void ensureCriticityCantHaveNullvalues() {
        System.out.println("Ensure Team Type Cant Have Null value");
        Criticity instance = new Criticity(Label.valueOf("label"),Value.valueOf(0), color,obj);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void ensureCriticityCantHaveNullcolor() {
        System.out.println("Ensure Team Type Cant Have Null color");
        Criticity instance = new Criticity(Label.valueOf("label"),Value.valueOf(1), null,obj);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void ensureCriticityCantHaveNullobejective() {
        System.out.println("Ensure Criticity Cant Have Null Label");
        Criticity instance = new Criticity(Label.valueOf("label"),Value.valueOf(1), color,null);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void ensureCriticityeCantHaveRedOver255() {
        System.out.println("Ensure Criticity Cant Have Red Over 255");
        Criticity instance = new Criticity(Label.valueOf("label"), Value.valueOf(1), new Color(265, 255,255,"colorName"),obj);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureCriticityCantHaveGreenOver255() {
        System.out.println("Ensure Criticity Cant Have Red Over 255");
        Criticity instance = new Criticity(Label.valueOf("label"), Value.valueOf(2), new Color(255, 265,255,"colorName"),obj);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureCriticityCantHaveBluedOver255() {
        System.out.println("Ensure Team Type Cant Have Red Over 255");
        Criticity instance = new Criticity(Label.valueOf("label"), Value.valueOf(3),new Color(255, 255,265,"colorName"),obj);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void ensureCriticityCantHaveRedLower0() {
        System.out.println("Ensure Criticity Cant Have Red under 0");
        Criticity instance = new Criticity(Label.valueOf("label"), Value.valueOf(4), new Color(-1, 255,255,"colorName"),obj);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureCriticityCantHaveGreenLower0() {
        System.out.println("Ensure Criticity Cant Have Green under 0");
        Criticity instance = new Criticity(Label.valueOf("label"), Value.valueOf(6), new Color(255, -1,255,"colorName"),obj);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureCriticityCantHaveBlueLower0() {
        System.out.println("Ensure Criticity Cant Have Blue under 0");
        Criticity instance = new Criticity(Label.valueOf("label"), Value.valueOf(6), new Color(255, 255,-1,"colorName"),obj);
    }
    
}
