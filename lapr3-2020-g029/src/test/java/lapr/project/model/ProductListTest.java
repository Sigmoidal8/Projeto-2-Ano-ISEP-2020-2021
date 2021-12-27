/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.ArrayList;
import java.util.List;
import lapr.project.controller.AplicationPOT;
import lapr.project.data.ProductDB;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

public class ProductListTest {
    
    @Mock
    private final ProductList producListDB;

    public ProductListTest() {
        producListDB = new ProductList();
    }

    /**
     * Test of addProduct method, of class ProductList.
     */
    @Test
    public void testAddProduct() {
        System.out.println("addProduct");
        Product p = new Product("Name4", "Name", 2.0f, 0.5f);
        ProductList instance = new ProductList();
        boolean expResult = true;
        boolean result = instance.addProduct(p);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of addProduct method, of class ProductList.
     */
    @Test
    public void testAddProductFalse() {
        System.out.println("addProduct");
        Product p = new Product("Name4", "Name", 2.0f, 0.5f);
        ProductList instance = new ProductList();
        instance.addProduct(p);
        boolean expResult = false;
        boolean result = instance.addProduct(p);
        assertEquals(expResult, result);
    }

    /**
     * // * Test of getProductList method, of class ProductList. //
     */
    @Test
    public void testGetProductList() {
        System.out.println("getProductList");
        ProductList instance = new ProductList();
        Product p = new Product("1", "nome",2,2);
        instance.addProduct(p);
        @SuppressWarnings("unchecked")
        List<Product> expResult = new ArrayList();
        expResult.add(p);
        List<Product> result = instance.getProductList();
        assertEquals(expResult, result);
    }
    /**
     * Test of getProduct method, of class ProductList.
     */
    @Test
    public void testGetProduct() {
        System.out.println("getProduct");
        String ID = "Name4";
        ProductList instance = new ProductList();
        Product p = new Product("Name4", "Name", 2.0f, 0.5f);
        instance.addProduct(p);
        Product expResult = p;
        Product result = instance.getProduct(ID);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getProduct method, of class ProductList.
     */
    @Test
    public void testGetProduct2() {
        System.out.println("getProduct");
        String ID = "Name6";
        ProductList instance = new ProductList();
        Product p = new Product("Name4", "Name", 2.0f, 0.5f);
        instance.addProduct(p);
        Product expResult = null;
        Product result = instance.getProduct(ID);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getProduct method, of class ProductList.
     */
    @Test
    public void testGetProduct3() {
        System.out.println("getProduct");
        String ID = "Name4";
        ProductList instance = new ProductList();
        Product expResult = null;
        Product result = instance.getProduct(ID);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of newProduct method, of class ProductList.
     */
    @Test
    public void testNewProduct() {
        System.out.println("newProduct");
        String name = "Name";
        float price = 2.0f;
        float weight = 0.5f;
        ProductList instance = new ProductList();
        Product p = new Product ("Name4", "Name", 2.0f, 0.5f);
        Product expResult = p;
        Product result = instance.newProduct(name, price, weight);
        assertEquals(expResult, result);
    }
    
    
    /**
     * Test of registerProduct method, of class ProductList.
     */
    @Test
    public void testRegisterProduct() {
        System.out.println("registerProduct");
        
        Product prod = new Product ("Name4", "Name", 2.0f, 0.5f);
        
        ProductDB dbMock = mock(ProductDB.class);
        AplicationPOT.getInstance().getPlatform().setPrdb(dbMock);
        doNothing().when(dbMock).addProduct(prod);
        
        boolean expResult = true;
        boolean result = producListDB.registerProduct(prod);
        
        assertEquals(expResult, result);
    }
    
    /**
     * Test of registerProduct method, of class ProductList.
     */
    @Test
    public void testRegisterProduct2() {
        System.out.println("registerProduct2");
        
        Product prod = new Product ("Name4", null, 2.0f, 0.5f);
        
        ProductDB dbMock = mock(ProductDB.class);
        AplicationPOT.getInstance().getPlatform().setPrdb(dbMock);
        doNothing().when(dbMock).addProduct(prod);
        
        boolean expResult = false;
        boolean result = producListDB.registerProduct(prod);
        
        assertEquals(expResult, result);
    }
    
    
    /**
     * Test of validateScooter method, of class ProductList.
     */
    @Test
    public void testValidateProduct() {
        System.out.println("validateScooter");
        Product prod = new Product("12345", "Name", 2.0f, 0.5f);
        ProductList instance = new ProductList();
        instance.addProduct(prod);
        boolean expResult = false;
        boolean result = instance.validateProduct(prod);
        assertEquals(expResult, result);
    }

    /**
     * Test of validateScooter method, of class ProductList.
     */
    @Test
    public void testValidateProduct2() {
        System.out.println("validateScooter");
        Product prod = new Product (null, null, 2.0f, 0.5f);
        ProductList instance = new ProductList();
        boolean expResult = false;
        boolean result = instance.validateProduct(prod);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of validateScooter method, of class ProductList.
     */
    @Test
    public void testValidateProduct3() {
        System.out.println("validateScooter");
        Product prod = new Product("Name4", "Name", -2.0f, 0.5f);
        ProductList instance = new ProductList();
        boolean expResult = false;
        boolean result = instance.validateProduct(prod);
        assertEquals(expResult, result);
    }

    /**
     * Test of validateScooter method, of class ProductList.
     */
    @Test
    public void testValidateProduct4() {
        System.out.println("validateScooter");
        Product prod = new Product("Name4", "Name", 2.0f, -0.5f);
        ProductList instance = new ProductList();
        boolean expResult = false;
        boolean result = instance.validateProduct(prod);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of validateScooter method, of class ProductList.
     */
    @Test
    public void testValidateProduct5() {
        System.out.println("validateScooter");
        Product prod = new Product("Name5", null, -2.0f, -0.5f);
        ProductList instance = new ProductList();
        boolean expResult = false;
        boolean result = instance.validateProduct(prod);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of validateScooter method, of class ProductList.
     */
    @Test
    public void testValidateProduct6() {
        System.out.println("validateScooter");
        Product prod = new Product("Name6", "Name", 2.0f, 0.5f);
        ProductList instance = new ProductList();
        boolean expResult = true;
        boolean result = instance.validateProduct(prod);
        assertEquals(expResult, result);
    }

    /**
     * Test of generateId method, of class ProductList.
     */
    @Test
    public void testGenerateId() {
        System.out.println("generateId");
        String name = "Name";
        ProductList instance = new ProductList();
        String expResult = "Name4";
        String result = instance.generateId(name);
        assertEquals(expResult, result);
    }

}
