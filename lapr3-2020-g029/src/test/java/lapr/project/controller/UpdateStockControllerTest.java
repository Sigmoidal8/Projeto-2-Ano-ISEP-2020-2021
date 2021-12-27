/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import lapr.project.data.StockDB;
import lapr.project.model.Address;
import lapr.project.model.ParkingLot;
import lapr.project.model.Pharmacy;
import lapr.project.model.PharmacyList;
import lapr.project.model.Platform;
import lapr.project.model.Product;
import lapr.project.model.ProductList;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class UpdateStockControllerTest {


    private final UpdateStockController controller;
    
    @Mock
    private final UpdateStockController controllerDB;
    
    private final Platform plat;
    
    public UpdateStockControllerTest() {
        controller = new UpdateStockController();
        plat = AplicationPOT.getInstance().getPlatform();
        controllerDB = new UpdateStockController();
    }
    /**
     * Test of getPharmacyList method, of class UpdateStockController.
     */
    @Test
    public void testGetPharmacyList() {
        PharmacyList expResult = plat.getPharmacyList();
        PharmacyList res = controller.getPharmacyList();
        
        assertEquals(expResult,res);
    }

    /**
     * Test of getPharmacy method, of class UpdateStockController.
     */
    @Test
    public void testGetPharmacy() {
        Address adress = new Address("1","street",1,"postalCode","locality",0,0);
        ParkingLot parkingLot = new ParkingLot(1,1,"drone");
        Pharmacy expResult = new Pharmacy("id", "designation", "email", "password",adress);
        
        controller.getPharmacyList().addPharmacy(expResult);
        Pharmacy result = controller.getPharmacy("id");
        
        assertEquals(expResult,result);
    }

    /**
     * Test of getProductList method, of class UpdateStockController.
     */
    @Test
    public void testGetProductList() {
        Address adress = new Address("1","street",1,"postalCode","locality",0,0);
        ParkingLot parkingLot = new ParkingLot(1,1,"drone");
        Pharmacy pharmacy = new Pharmacy("id", "designation", "email", "password",adress);
        
        controller.getPharmacyList().addPharmacy(pharmacy);
        Pharmacy ControllerPharmacy = controller.getPharmacy("id");
        
        ProductList expResult = ControllerPharmacy.getProductList();
        
        ProductList result = controller.getProductList();
        
        assertEquals(expResult,result);
    }

    /**
     * Test of getProduct method, of class UpdateStockController.
     */
    @Test
    public void testGetProduct() {
        Address adress = new Address("1","street",1,"postalCode","locality",0,0);
        ParkingLot parkingLot = new ParkingLot(1,1,"drone");
        Pharmacy pharmacy = new Pharmacy("id", "designation", "email", "password",adress);
        
        controller.getPharmacyList().addPharmacy(pharmacy); // necessário para inicializar uma farmácia no controller
        
        Product expResult = new Product("id","nome",1,1);
        controller.getPharmacy("id");
        controller.getProductList().addProduct(expResult);
        
        Product p = controller.getProduct("id");
        assertEquals(p,expResult);
    }

    /**
     * Test of setStock method, of class UpdateStockController.
     */
    @Test
    public void testSetStock() {
        System.out.println("setStock");
        
        int stock = 0;
        String idProduct = "1";
        String idPharmacy = "1";
        
        StockDB dbMock = mock(StockDB.class);
        AplicationPOT.getInstance().getPlatform().setStdb(dbMock);
        
        when(dbMock.setStock("1", "1", 0)).thenReturn(true); 
        when(dbMock.setStock("2", "2", 0)).thenReturn(false);
       
        UpdateStockController instance = new UpdateStockController();
        boolean result =  instance.setStock(stock, idProduct, idPharmacy);
        assertEquals(true, result);
    }
    
     /**
     * Test of setStock method, of class UpdateStockController.
     */
    @Test
    public void testSetStock2() {
        System.out.println("setStock");
        
        int stock = 0;
        String idProduct = "2";
        String idPharmacy = "2";
        
        StockDB dbMock = mock(StockDB.class);
        AplicationPOT.getInstance().getPlatform().setStdb(dbMock);
        
        when(dbMock.setStock("1", "1", 0)).thenReturn(true); 
        when(dbMock.setStock("2", "2", 0)).thenReturn(false);
       
        UpdateStockController instance = new UpdateStockController();
        boolean result =  instance.setStock(stock, idProduct, idPharmacy);
        assertEquals(false, result);
    }

    /**
     * Test of getStock method, of class UpdateStockController.
     */
    @Test
    public void testGetStock() {
        System.out.println("getStock");
        String idProduct = "1";
        String idPharmacy = "1";
        
        StockDB dbMock = mock(StockDB.class);
        AplicationPOT.getInstance().getPlatform().setStdb(dbMock);
        when(dbMock.getStock(idPharmacy, idProduct)).thenReturn(1);
        
        UpdateStockController instance = new UpdateStockController();
        
        int expResult = 1;
        int result = instance.getStock(idProduct, idPharmacy);
        assertEquals(expResult, result);
    }

}
