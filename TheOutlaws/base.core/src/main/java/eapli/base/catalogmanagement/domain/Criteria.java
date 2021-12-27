/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.catalogmanagement.domain;

import eapli.base.teammanagement.domain.Team;
import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import java.util.List;

/**
 *
 * @author raulcoelho
 */
@Entity
public class Criteria implements ValueObject{
    
    private static final long serialVersion = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToMany(cascade=CascadeType.ALL)
    private List<Team> teams;

    public Criteria(List<Team> teams){
        Preconditions.nonEmpty(teams);
        this.teams=teams;
    }

    protected Criteria(){
        //ORM only
    }
    
    public List<Team> teams(){
        return teams;
    }
    
}
