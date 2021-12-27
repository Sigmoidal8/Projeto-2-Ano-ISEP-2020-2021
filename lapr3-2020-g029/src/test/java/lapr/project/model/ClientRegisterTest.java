/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.ArrayList;
import java.util.List;
import lapr.project.controller.AplicationPOT;
import lapr.project.data.AdressDB;
import lapr.project.data.ClientDB;
import lapr.project.utils.Graph;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

public class ClientRegisterTest {

    @Mock
    private final ClientRegister clientRegisterDB;

    public ClientRegisterTest() {
        clientRegisterDB = new ClientRegister();
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test of getClientList method, of class ClientRegister.
     */
    @Test
    public void testGetClientList() {
        System.out.println("getClientList");
        Address s1 = new Address("street", 34, "postalCode", "locality", 42.365, -85.236);
        Address s2 = new Address("street2", 34, "postalCode2", "locality2", 42.365, -85.236);
        Client c1 = new Client("id", "name", "password", "email", "12", s1);
        Client c2 = new Client("id2", "name2", "password2", "email2", "12", s2);
        ClientRegister instance = new ClientRegister();
        List<Client> expResult = new ArrayList<>();
        expResult.add(c1);
        expResult.add(c2);
        instance.addClient(c1);
        instance.addClient(c2);
        List<Client> result = instance.getClientList();
        assertEquals(expResult, result);
    }

    /**
     * Test of newClient method, of class ClientRegister.
     */
    @Test
    public void testNewClient() {
        System.out.println("newClient");
        String name = "name";
        String password = "password";
        String email = "email";
        String NIF = "34";
        String street = "street";
        int doorNumber = 23;
        String postalCode = "postalCode";
        String locality = "locality";
        double decimalDegree1 = 23.435;
        double decimalDegree2 = -64.435;
        ClientRegister instance = new ClientRegister();
        Address s1 = new Address("street", 23, "postalCode", "locality", 23.435, -64.435);
        Address s2 = new Address(street, doorNumber, postalCode, locality, decimalDegree1, decimalDegree2);
        Client expResult = new Client("n34", "name", "password", "email", "34", s1);
        Client result = instance.newClient(name, password, email, NIF, s2);
        assertEquals(expResult, result);
    }

    /**
     * Test of validateClientByEmail method, of class ClientRegister.
     */
    @Test
    public void testValidateClientByEmail() {
        System.out.println("validateClientByEmailtrue");
        String email = "miguelsilva@gmail.com";
        ClientRegister instance = new ClientRegister();
        boolean expResult = false;
        boolean result = instance.validateClientByEmail(email);
        assertEquals(expResult, result);
    }

    /**
     * Test of validateClientByEmail method, of class ClientRegister.
     */
    @Test
    public void testValidateClientByEmail2() {
        System.out.println("validateClientByEmailFalse");
        String email = "email";
        ClientRegister instance = new ClientRegister();
        Address s1 = new Address("street", 23, "postalCode", "locality", 23.435, -64.435);
        Client c = new Client("n12345", "name", "pass", "email", "12345", s1);
        instance.addClient(c);
        boolean expResult = true;
        boolean result = instance.validateClientByEmail(email);
        assertEquals(expResult, result);
    }

    /**
     * Test of validateClientByEmail method, of class ClientRegister.
     */
    @Test
    public void testValidateClientByEmail3() {
        System.out.println("validateClientByEmailFalse3");
        String email = "email2";
        ClientRegister instance = new ClientRegister();
        Address s1 = new Address("street", 23, "postalCode", "locality", 23.435, -64.435);
        Client c = new Client("n12345", "name", "pass", "email", "12345", s1);
        instance.addClient(c);
        boolean expResult = false;
        boolean result = instance.validateClientByEmail(email);
        assertEquals(expResult, result);
    }


    /**
     * Test of validateClient method, of class ClientRegister.
     */
    @Test
    public void testValidateClient() {
        System.out.println("validateClient");

        Address s1 = new Address("street", 34, "postalCode", "locality", 42.365, -85.236);
        Client client = new Client("id", "name", "password", "email", "12", s1);

        ClientRegister instance = new ClientRegister();
        boolean expResult = false;
        boolean result = instance.validateClient(client);
        assertEquals(expResult, result);
    }

    /**
     * Test of addClient method, of class ClientRegister.
     */
    @Test
    public void testAddClient() {
        System.out.println("addClient");
        Address s1 = new Address("street", 34, "postalCode", "locality", 42.365, -85.236);
        Client client = new Client("id", "name", "password", "email", "12", s1);
        ClientRegister instance = new ClientRegister();
        boolean expResult = true;
        boolean result = instance.addClient(client);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of generateId method, of class ClientRegister.
     */
    @Test
    public void testGenerateId() {
        System.out.println("generateId");
        String name = "Miguel Silva";
        String nif = "nif";
        ClientRegister instance = new ClientRegister();
        String expResult = "Mnif";
        String result = instance.generateId(name, nif);
        assertEquals(expResult, result);
    }

    /**
     * // * Test of getClientByEmail method, of class ClientRegister. //
     */
    @Test
    public void testGetClientByEmail() {
        System.out.println("getClientByEmail");

        String email = "email";
        Address s1 = new Address("street", 34, "postalCode", "locality", 42.365, -85.236);
        Client expResult = new Client("id", "name", "password", "email", "12", s1);
        clientRegisterDB.addClient(expResult);

        Client result = clientRegisterDB.getClientByEmail(email);
        assertEquals(expResult, result);
    }

    /**
     * // * Test of getClientByEmail method, of class ClientRegister. //
     */
    @Test
    public void testGetClientByEmail2() {
        System.out.println("getClientByEmail");

        String email = "email";
        ClientRegister instance = new ClientRegister();
        Client expResult = null;

        Client result = instance.getClientByEmail(email);
        assertEquals(expResult, result);
    }

    /**
     * // * Test of getClientByEmail method, of class ClientRegister. //
     */
    @Test
    public void testGetClientByEmail3() {
        System.out.println("getClientByEmail3");

        String email = "email2";
        Address s1 = new Address("street", 34, "postalCode", "locality", 42.365, -85.236);
        Client client = new Client("id", "name", "password", "email", "12", s1);
        clientRegisterDB.addClient(client);
        Client expResult = null;
        Client result = clientRegisterDB.getClientByEmail(email);
        assertEquals(expResult, result);
    }

    /**
     * Test of registerClientWithRole method, of class ClientRegister.
     */
    @Test
    public void testRegisterClientWithRole() {
        System.out.println("registerClientWithRole");

        Address s1 = new Address("street", 34, "postalCode", "locality", 42.365, -85.236);
        Client c1 = new Client("id", "name", "password", "email", "12", s1);
        Platform plat = new Platform();

        ClientRegister instance = new ClientRegister();
        instance.registerClientWithRole(c1, plat);
    }
    
    /**
     * Test of registerClientWithRole method, of class ClientRegister.
     */
    @Test
    public void testRegisterClient() {
        System.out.println("registerClientWithRole");

        Address s1 = new Address("street", 34, "postalCode", "locality", 42.365, -85.236);
        Client c1 = new Client("id", "name", "password", "email", "12", s1);
        Platform plat = AplicationPOT.getInstance().getPlatform();

        ClientRegister instance = AplicationPOT.getInstance().getPlatform().getClientRegister();
        
        ClientDB dbMock1 = mock(ClientDB.class);
        AplicationPOT.getInstance().getPlatform().setCdb(dbMock1);
        doNothing().when(dbMock1).addClient(c1);
        
        AdressDB dbMock2 = mock(AdressDB.class);
        AplicationPOT.getInstance().getPlatform().setAdb(dbMock2);
        doNothing().when(dbMock2).addAdress(s1);
        
        boolean isRegistered = instance.registerClient(c1, plat);
        
        boolean result = AplicationPOT.getInstance().getPlatform().getAutorizationFacade().hasUser("email");
        assertTrue(plat.getClientRegister().getClientList().contains(c1));
        assertTrue(result);
        assertTrue(isRegistered);
    }
    
    /**
     * Test of registerClientWithRole method, of class ClientRegister.
     */
    @Test
    public void testRegisterClientFalse() {
        System.out.println("registerClientWithRole");

        Address s1 = new Address("street", 34, "postalCode", "locality", 42.365, -85.236);
        Client c1 = new Client("id", "name", "password", "email", "12", s1);
        Platform plat = new Platform();

        ClientRegister instance = plat.getClientRegister();
        
        ClientDB dbMock1 = mock(ClientDB.class);
        AplicationPOT.getInstance().getPlatform().setCdb(dbMock1);
        doNothing().when(dbMock1).addClient(c1);
        
        AdressDB dbMock2 = mock(AdressDB.class);
        AplicationPOT.getInstance().getPlatform().setAdb(dbMock2);
        doNothing().when(dbMock2).addAdress(s1);
        
        instance.registerClient(c1, plat);
        boolean isRegistered = instance.registerClient(c1, plat);
        
        assertFalse(isRegistered);
    }

    /**
     * Test of addClientAdressToGraph method, of class ClientRegister.
     */
    @Test
    public void testAddClientAdressToGraph() {
        System.out.println("addClientAdressToGraph");
        AplicationPOT app = new AplicationPOT();
        
        Address adress = new Address("1", "street", 0, "postalCode", "locality", 41.35195190070675, -8.49001219205514);
        Client client = new Client("n22222", "name", "pass", "email", "22222", adress);
        
        Address adress2 = new Address("2", "street2", 1, "postalCode", "locality", 41.35195190070675, -8.49001219205514);
        Client client2 = new Client("n222", "name", "pass", "email", "22222", adress2);
        
        Address adress3 = new Address("3", "street2", 1, "postalCode", "locality", 41.35195190070675, -8.49001219205514);
        Client client3 = new Client("n22", "name", "pass", "email", "22222", adress3);
        
        Graph grafo = AplicationPOT.getInstance().getPlatform().getScooterMap();
        grafo.insertVertex(adress2);
        grafo.insertVertex(adress3);
        
        Graph grafo2 = AplicationPOT.getInstance().getPlatform().getDroneMap();
        grafo2.insertVertex(adress2);
        grafo2.insertVertex(adress3);
        
        ClientRegister instance = new ClientRegister();
        
        ArrayList<String> list = new ArrayList<>();
        list.add(adress2.getId());
        
        ArrayList<String> list2 = new ArrayList<>();
        list2.add(adress2.getId());
        
        ArrayList<String> list3 = new ArrayList<>();
        list3.add(adress2.getId());
        
        app.getPlatform().getListRestrictionsScooter().put("1", list);
        app.getPlatform().getListRestrictionsScooter().put("2", list2);
        app.getPlatform().getListRestrictionsScooter().put("3", list3);
        
        app.getPlatform().getListRestrictionsDrone().put("1", list);
        app.getPlatform().getListRestrictionsDrone().put("2", list2);
        app.getPlatform().getListRestrictionsDrone().put("3", list3);
        
        boolean expResult = true;
        boolean result = instance.addClientAdressToGraph(client);
        assertEquals(expResult, result);
    }
    
    
}
