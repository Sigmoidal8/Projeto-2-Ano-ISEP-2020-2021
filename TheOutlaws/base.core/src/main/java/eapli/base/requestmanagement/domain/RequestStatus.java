/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.requestmanagement.domain;

/**
 *
 * @author José Forno
 */
public enum RequestStatus {
     /**
     * Request submited
     */
    Submited,

    /**
     * Request in aproval
     */
    InAproval,
    
    /**
     * Request aproved
     */
    Aproved,
    
     /**
     * Request rejected
     */
    Rejected,
    
    /**
     * Request resolving
     */
    Resolving,
    
    /**
     * Request concluded
     */
    Concluded,
    
}
