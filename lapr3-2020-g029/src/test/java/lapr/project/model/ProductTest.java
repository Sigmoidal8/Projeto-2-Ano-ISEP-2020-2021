/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import lapr.project.controller.AplicationPOT;
import lapr.project.data.ProductDB;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Ignore;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

public class ProductTest {
    
    @Mock
    private final Product productDB;
    
    public ProductTest() {
        productDB = new Product ("12345", "Name", 2.0f, 0.5f);
    }

    /**
     * Test of getID method, of class Product.
     */
    @Test
    public void testGetID() {
        System.out.println("getID");
        Product instance = new Product ("12345", "Name", 2.0f, 0.5f);
        String expResult = "12345";
        String result = instance.getID();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNome method, of class Product.
     */
    @Test
    public void testGetNome() {
        System.out.println("getNome");
        Product instance = new Product ("12345", "Name", 2.0f, 0.5f);
        String expResult = "Name";
        String result = instance.getNome();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPrice method, of class Product.
     */
    @Test
    public void testGetPrice() {
        System.out.println("getPrice");
        Product instance = new Product ("12345", "Name", 2.0f, 0.5f);
        float expResult = 2.0F;
        float result = instance.getPrice();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getWeight method, of class Product.
     */
    @Test
    public void testGetWeight() {
        System.out.println("getWeight");
        Product instance = new Product ("12345", "Name", 2.0f, 0.5f);
        float expResult = 0.5F;
        float result = instance.getWeight();
        assertEquals(expResult, result, 0.0);
    }
    
    /**
     * Test of setID method, of class Product.
     */
    @Test
    public void testSetID() {
        System.out.println("setID");
        Product instance = new Product ("12345", "Name", 2.0f, 0.5f);
        String ID = "1111";
        instance.setID(ID);
        assertEquals(ID, instance.getID());
    }

    /**
     * Test of setName method, of class Product.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "OtherName";
        Product instance = new Product ("12345", "Name", 2.0f, 0.5f);
        instance.setName(name);
        assertEquals(name, instance.getNome());
    }

    /**
     * Test of setPrice method, of class Product.
     */
    @Test
    public void testSetPrice() {
        System.out.println("setPrice");
        Product instance = new Product ("12345", "Name", 2.0f, 0.5f);
        float price = 5.0F;
        instance.setPrice(price);
        assertEquals(price, instance.getPrice(), 0.1);
    }

    /**
     * Test of setWeight method, of class Product.
     */
    @Test
    public void testSetWeight() {
        System.out.println("setWeight");
        float weight = 0.2F;
        Product instance = new Product ("12345", "Name", 2.0f, 0.5f);
        instance.setWeight(weight);
        assertEquals(weight, instance.getWeight(), 0.1);
    }
    
    /**
     * Test of equals method, of class Client.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object otherObjeto = new Product("12345", "Name", 2.0f, 0.5f);
        Product instance = new Product("12345", "Name", 2.0f, 0.5f);
        boolean expResult = true;
        boolean result = instance.equals(otherObjeto);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of equals method, of class Client.
     */
    @Test
    public void testEquals2() {
        System.out.println("not equals");
        Object otherObjeto = new Product("12345", "Name", 2.0f, 0.5f);
        Product instance = new Product("1111", "Name", 2.0f, 0.5f);
        boolean expResult = false;
        boolean result = instance.equals(otherObjeto);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of equals method, of class Client.
     */
    @Test
    public void testEquals3() {
        System.out.println("equals");
        Object otherObjeto = new Product("12345", "Name", 2.4f, 0.7f);
        Product instance = new Product("12345", "Name2",  2.4f, 0.7f);
        boolean expResult = false;
        boolean result = instance.equals(otherObjeto);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of equals method, of class Client.
     */
    @Test
    public void testEquals4() {
        System.out.println("not equals");
        Object otherObjeto = null;
        Product instance = new Product("1111", "Name2", 1.0f, 0.5f);
        boolean expResult = false;
        boolean result = instance.equals(otherObjeto);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of equals method, of class Client.
     */
    @Test
    public void testEquals5() {
        System.out.println("not equals");
        Object otherObjeto = new Scooter(1,20,20,1);
        Product instance = new Product("1111", "Name2", 1.0f, 0.5f);
        boolean expResult = false;
        boolean result = instance.equals(otherObjeto);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testEquals6() {
        System.out.println("not equals");
        Object otherObjeto = new Product("12345", "Name", 2.0f, 0.5f);
        Product instance = new Product("12345", "Name", 7.0f, 0.5f);
        boolean expResult = false;
        boolean result = instance.equals(otherObjeto);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testEquals8() {
        System.out.println("not equals");
        Object otherObjeto = new Product("12345", "Name", 2.0f, 0.5f);
        Product instance = new Product("12345", "Name", 2.0f, 1.5f);
        boolean expResult = false;
        boolean result = instance.equals(otherObjeto);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getProduct method, of class Product.
     */
    @Test
    public void testGetProduct() {
        System.out.println("getProduct");
        String id = "Moderna7";
        
        Product product = new Product(id, "name", 2.0f, 0.5f);
        ProductDB dbMock = mock(ProductDB.class);
        AplicationPOT.getInstance().getPlatform().setPrdb(dbMock);
        when(dbMock.getProduct(id)).thenReturn(product);
        
        Product result = productDB.getProduct(id);
        
        assertEquals(result,product);
    }

    /**
     * Test of save method, of class Product.
     */
    @Test
    public void testSave() {
        System.out.println("save");
        
        Product product = new Product("id", "name", 2.0f, 0.5f);
        
        ProductDB dbMock = mock(ProductDB.class);
        AplicationPOT.getInstance().getPlatform().setPrdb(dbMock);
        doNothing().when(dbMock).addProduct(product);
        when(dbMock.getProduct("id")).thenReturn(product);
        
        productDB.save();
        
        Product result = productDB.getProduct("id");
        
        assertEquals(result,product);
    }
    
    /**
     * Test of save method, of class Product.
     */
    @Test
    public void testSave2() {
        System.out.println("save2");
        
        ProductDB dbMock = mock(ProductDB.class);
        AplicationPOT.getInstance().getPlatform().setPrdb(dbMock);
        doNothing().when(dbMock).addProduct(productDB);
        when(dbMock.getProduct("12345")).thenThrow(new IllegalArgumentException());
        
        productDB.save();
        assertThrows(IllegalArgumentException.class, ()-> 
                {productDB.getProduct("12345");
        });
    }
    
    /**
     * Test of update method, of class Product.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        
        Product product = new Product("id", "name", 2.0f, 0.5f);
        
        ProductDB dbMock = mock(ProductDB.class);
        AplicationPOT.getInstance().getPlatform().setPrdb(dbMock);
        doNothing().when(dbMock).addProduct(product);
        when(dbMock.getProduct("id")).thenReturn(product);
        productDB.update();
        
        Product result = productDB.getProduct("id");
        
        assertEquals(result,product);
    }

    /**
     * Test of toString method, of class Product.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Product instance = new Product ("12345", "Name", 2.0f, 0.5f);
        String expResult = "12345, Name, 2.00, 0.50";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
}
