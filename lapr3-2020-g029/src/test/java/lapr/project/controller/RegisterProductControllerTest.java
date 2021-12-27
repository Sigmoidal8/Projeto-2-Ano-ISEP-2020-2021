/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import lapr.project.data.ProductDB;
import lapr.project.model.Address;
import lapr.project.model.ParkingLot;
import lapr.project.model.Pharmacy;
import lapr.project.model.PharmacyList;
import lapr.project.model.Platform;
import lapr.project.model.Product;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;


public class RegisterProductControllerTest {


    private final RegisterProductController controller;
    
    @Mock
    private final RegisterProductController controller2;
    
    private final Platform plat;
    
    public RegisterProductControllerTest() {
        controller = new RegisterProductController();
        plat = AplicationPOT.getInstance().getPlatform();
        controller2 = new RegisterProductController();
    }

    /**
     * Test of newProduct method, of class RegisterProductController.
     */
    @Test
    public void testNewProduct() {
        AplicationPOT app = new AplicationPOT();
        Address adress = new Address("1","street",1,"postalCode","locality",0,0);
        ParkingLot parkingLot = new ParkingLot(1,1,"drone");
        Pharmacy pharmacy = new Pharmacy("pharmacyID", "designation", "email", "password",adress);
        
        controller.getPharmacyList().addPharmacy(pharmacy); // necessário para inicializar uma farmácia no controller
        
        Product result = controller.newProduct("name", 0, 0, "pharmacyID");
        Product expResult = new Product("name4","name", 0, 0);
        
        assertEquals(result,expResult);
    }

    /**
     * Test of getPharmacyList method, of class RegisterProductController.
     */
    @Test
    public void testGetPharmacyList() {
        PharmacyList expResult = plat.getPharmacyList();
        PharmacyList res = controller.getPharmacyList();
        
        assertEquals(expResult,res);
    }

    /**
     * Test of registerProduct method, of class RegisterProductController.
     */
     @Test
    public void testRegisterProduct() {
        System.out.println("registerProduct");
        
        Address adress = new Address("1","street",1,"postalCode","locality",0,0);
        ParkingLot parkingLot = new ParkingLot(1,1,"drone");
        Pharmacy pharmacy = new Pharmacy("pharmacyID", "designation", "email", "password",adress);
        
        AplicationPOT.getInstance().getPlatform().getPharmacyList().addPharmacy(pharmacy);
        
        ProductDB mockDB = mock(ProductDB.class);
        AplicationPOT.getInstance().getPlatform().setPrdb(mockDB);
        
        Product phar = new Product("name4","name",0,0);
        doNothing().when(mockDB).addProduct(phar);
        
        Product p = controller2.newProduct("name", 1, 1, "pharmacyID");
        
        boolean expResult = true;
        boolean result = controller2.registerProduct(p);
       
        assertEquals(expResult,result);
    }
    
    /**
     * Test of registerProduct method, of class RegisterProductController.
     */
     @Test
    public void testRegisterProduct2() {
        System.out.println("registerProduct2");
        
        Address adress = new Address("1","street",1,"postalCode","locality",0,0);
        ParkingLot parkingLot = new ParkingLot(1,1,"drone");
        Pharmacy pharmacy = new Pharmacy("pharmacyID", "designation", "email", "password",adress);
        
        AplicationPOT.getInstance().getPlatform().getPharmacyList().addPharmacy(pharmacy);
        
        ProductDB mockDB = mock(ProductDB.class);
        AplicationPOT.getInstance().getPlatform().setPrdb(mockDB);
        
        Product phar = new Product("name4","name",-9,0);
        doNothing().when(mockDB).addProduct(phar);
        
        Product p = controller2.newProduct("name", 0, 0, "pharmacyID");
        
        boolean expResult = false;
        boolean result = controller2.registerProduct(p);
       
        assertEquals(expResult,result);
    }
    
    @Test
    public void testRegisterProduct3() {
        System.out.println("registerProduct");
        
        Address adress = new Address("1","street",1,"postalCode","locality",0,0);
        ParkingLot parkingLot = new ParkingLot(1,1,"drone");
        Pharmacy pharmacy = new Pharmacy("pharmacyID", "designation", "email", "password",adress);
        
        AplicationPOT.getInstance().getPlatform().getPharmacyList().addPharmacy(pharmacy);
        
        ProductDB mockDB = mock(ProductDB.class);
        AplicationPOT.getInstance().getPlatform().setPrdb(mockDB);
        
        Product phar = new Product("name4","name",0,0);
        doNothing().when(mockDB).addProduct(phar);
        
        Product p = controller2.newProduct("name", 0, 0, "pharmacyID");
        
        boolean expResult = false;
        boolean result = controller2.registerProduct(p);
       
        assertEquals(expResult,result);
    }
    
    @Test
    public void testRegisterProduct4() {
        System.out.println("registerProduct");
        
        Address adress = new Address("1","street",1,"postalCode","locality",0,0);
        ParkingLot parkingLot = new ParkingLot(1,1,"drone");
        Pharmacy pharmacy = new Pharmacy("pharmacyID", "designation", "email", "password",adress);
        
        AplicationPOT.getInstance().getPlatform().getPharmacyList().addPharmacy(pharmacy);
        
        ProductDB mockDB = mock(ProductDB.class);
        AplicationPOT.getInstance().getPlatform().setPrdb(mockDB);
        
        Product phar = new Product("name4","name",0,0);
        doNothing().when(mockDB).addProduct(phar);
        
        Product p = controller2.newProduct("name", 1, 0, "pharmacyID");
        
        boolean expResult = false;
        boolean result = controller2.registerProduct(p);
       
        assertEquals(expResult,result);
    }
    
    @Test
    public void testRegisterProduct5() {
        System.out.println("registerProduct");
        
        Address adress = new Address("1","street",1,"postalCode","locality",0,0);
        ParkingLot parkingLot = new ParkingLot(1,1,"drone");
        Pharmacy pharmacy = new Pharmacy("pharmacyID", "designation", "email", "password",adress);
        
        AplicationPOT.getInstance().getPlatform().getPharmacyList().addPharmacy(pharmacy);
        
        ProductDB mockDB = mock(ProductDB.class);
        AplicationPOT.getInstance().getPlatform().setPrdb(mockDB);
        
        Product phar = new Product("name4","name",0,0);
        doNothing().when(mockDB).addProduct(phar);
        
        Product p = controller2.newProduct("name", 0, 1, "pharmacyID");
        
        boolean expResult = false;
        boolean result = controller2.registerProduct(p);
       
        assertEquals(expResult,result);
    }
}
