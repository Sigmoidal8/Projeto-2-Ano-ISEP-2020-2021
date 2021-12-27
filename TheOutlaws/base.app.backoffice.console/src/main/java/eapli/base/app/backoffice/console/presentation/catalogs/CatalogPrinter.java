/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.app.backoffice.console.presentation.catalogs;

import eapli.base.catalogmanagement.domain.Catalog;
import eapli.framework.visitor.Visitor;

/**
 *
 * @author José Forno
 */
public class CatalogPrinter implements Visitor<Catalog>{
    
    @Override
    public void visit(final Catalog visitee) {
        System.out.printf("Title: %s  Brief Description:%s", visitee.title(), visitee.briefDescription());
    }
}
