/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import lapr.project.data.AdressDB;
import lapr.project.data.ClientDB;
import lapr.project.model.Address;
import lapr.project.model.Client;
import lapr.project.utils.Graph;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

/**
 *
 * @author Miguel
 */
public class RegisterClientControllerTest {

    @Mock
    private final RegisterClientController controllerDB = new RegisterClientController();

    public RegisterClientControllerTest() {
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
     * Test of newClient method, of class RegisterClientController.
     */
    @Test
    public void testNewClient() {
        System.out.println("newClient");
        AplicationPOT app = new AplicationPOT();
        String name = "name";
        String password = "pass";
        String email = "email";
        String NIF = "222222";
        String street = "street";
        int doorNumber = 1;
        String postalCode = "postalCode";
        String locality = "locality";
        double decimalDegree1 = 0.0;
        double decimalDegree2 = 0.0;
        RegisterClientController instance = new RegisterClientController();
        boolean expResult = true;
        boolean result = instance.newClient(name, password, email, NIF, street, doorNumber, postalCode, locality, decimalDegree1, decimalDegree2);
        assertEquals(expResult, result);
    }

    /**
     * Test of newClient method, of class RegisterClientController.
     */
    @Test
    public void testNewClient2() {
        System.out.println("newClient2");
        AplicationPOT app = new AplicationPOT();

        Address adress = new Address("1", "street", 0, "postalCode", "locality", 0, 0);
        Client client = new Client("n22222", "name", "pass", "email", "222222", adress);
        String name = "name";
        String password = "pass";
        String email = "email";
        String NIF = "222222";
        String street = "street";
        int doorNumber = 1;
        String postalCode = "postalCode";
        String locality = "locality";
        double decimalDegree1 = 0.0;
        double decimalDegree2 = 0.0;
        RegisterClientController instance = new RegisterClientController();
        instance.newClient("name", "pass", "email", "222222", "street", 1, "postalCode", "locality", 0.0, 0.0);

        AdressDB dbMock2 = mock(AdressDB.class);
        AplicationPOT.getInstance().getPlatform().setAdb(dbMock2);
        doNothing().when(dbMock2).addAdress(adress);

        ClientDB dbMock = mock(ClientDB.class);
        AplicationPOT.getInstance().getPlatform().setCdb(dbMock);
        doNothing().when(dbMock).addClient(client);
        instance.registerClient();

        boolean expResult = false;
        boolean result = instance.newClient(name, password, email, NIF, street, doorNumber, postalCode, locality, decimalDegree1, decimalDegree2);
        assertEquals(expResult, result);
    }

    /**
     * Test of getClient method, of class RegisterClientController.
     */
    @Test
    public void testGetClient() {
        System.out.println("getClient");
        AplicationPOT app = new AplicationPOT();
        RegisterClientController instance1 = new RegisterClientController();
        instance1.newClient("name", "pass", "email", "22222", "street", 0, "postalCode", "locality", 0, 0);

        Address adress = new Address("1", "street", 0, "postalCode", "locality", 0, 0);
        Client expResut = new Client("n22222", "name", "pass", "email", "22222", adress);
        Client result = instance1.getClient();
        assertEquals(expResut, result);
    }

    /**
     * Test of existsEmail method, of class RegisterClientController.
     */
    @Test
    public void testExistsEmail() {
        System.out.println("existsEmail");
        String email = "";

        RegisterClientController instance = new RegisterClientController();
        boolean expResult = false;
        boolean result = instance.existsEmail(email);
        assertEquals(expResult, result);
    }

    /**
     * Test of existsEmail method, of class RegisterClientController.
     */
    @Test
    public void testExistsEmail2() {
        System.out.println("existsEmail");
        String email = "email";

        AplicationPOT.getInstance().getPlatform().getAutorizationFacade().registerUserWithRole("name", "email", "pass", "role");

        RegisterClientController instance = new RegisterClientController();
        boolean expResult = true;
        boolean result = instance.existsEmail(email);
        assertEquals(expResult, result);
    }

    /**
     * Test of registerClient method, of class RegisterClientController.
     */
    @Test
    public void testRegisterClient() {
        System.out.println("registerClient");
        AplicationPOT app = new AplicationPOT();
        Address adress = new Address("1", "street", 0, "postalCode", "locality", 0, 0);
        Client client = new Client("n22222", "name", "pass", "email", "22222", adress);
        RegisterClientController controller = new RegisterClientController();
        ClientDB dbMock = mock(ClientDB.class);
        AdressDB dbMock2 = mock(AdressDB.class);
        app.getInstance().getPlatform().setCdb(dbMock);
        app.getInstance().getPlatform().setAdb(dbMock2);
        doNothing().when(dbMock).addClient(client);
        doNothing().when(dbMock2).addAdress(adress);

        controller.newClient("name", "pass", "email", "22222", "street", 0, "postalCode", "locality", 0, 0);

        boolean expResult = true;
        boolean result = controller.registerClient();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of registerClient method, of class RegisterClientController.
     */
    @Test
    public void testRegisterClient2() {
        System.out.println("registerClient2");
        AplicationPOT app = new AplicationPOT();
        Address adress = new Address("1", "street", 0, "postalCode", "locality", 0, 0);
        Client client = new Client("n22222", "name", "pass", "email", "22222", adress);
        RegisterClientController controller = new RegisterClientController();

        controller.newClient("name", "pass", "email", "22222", "street", 0, "postalCode", "locality", 0, 0);
        app.getPlatform().getClientRegister().addClient(client);
        boolean expResult = false;
        boolean result = controller.registerClient();
        assertEquals(expResult, result);
    }

    /**
     * Test of addClientToGraph method, of class RegisterClientController.
     */
    @Test
    public void testAddClientToGraphTrue() {
        System.out.println("addClientToGraph");
        AplicationPOT app = new AplicationPOT();
        Address adress = new Address("1", "street", 0, "postalCode", "locality", 41.35195190070675, -8.49001219205514);
        Client client = new Client("n22222", "name", "pass", "email", "22222", adress);
        RegisterClientController controller = new RegisterClientController();
        ClientDB dbMock = mock(ClientDB.class);
        AdressDB dbMock2 = mock(AdressDB.class);
        app.getInstance().getPlatform().setCdb(dbMock);
        app.getInstance().getPlatform().setAdb(dbMock2);
        doNothing().when(dbMock).addClient(client);
        doNothing().when(dbMock2).addAdress(adress);
        
        Graph grafo = AplicationPOT.getInstance().getPlatform().getScooterMap();
        grafo.insertVertex(new Address("Street", 2, "locality321", "postalCode312", 41.35229504778268, -8.486986406671033));
        
        Graph grafo2 = AplicationPOT.getInstance().getPlatform().getDroneMap();
        grafo2.insertVertex(new Address("Street", 2, "locality321", "postalCode312", 41.35229504778268, -8.486986406671033));
        
        
        controller.newClient("name", "pass", "email", "22222", "street", 0, "postalCode", "locality", 41.35195190070675, -8.49001219205514);
        
        controller.registerClient();
        
        boolean expResult = true;
        boolean result = controller.addClientToGraph();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of addClientToGraph method, of class RegisterClientController.
     */
    @Test
    public void testAddClientToGraphFalse() {
        System.out.println("addClientToGraph");
        AplicationPOT app = new AplicationPOT();
        Address adress = new Address("1", "street", 0, "postalCode", "locality", 1.0,1.0);
        Client client = new Client("n22222", "name", "pass", "email", "22222", adress);
        RegisterClientController controller = new RegisterClientController();
        ClientDB dbMock = mock(ClientDB.class);
        AdressDB dbMock2 = mock(AdressDB.class);
        app.getInstance().getPlatform().setCdb(dbMock);
        app.getInstance().getPlatform().setAdb(dbMock2);
        doNothing().when(dbMock).addClient(client);
        doNothing().when(dbMock2).addAdress(adress);
        
        Graph grafo = AplicationPOT.getInstance().getPlatform().getScooterMap();
        grafo.insertVertex(new Address("Street", 2, "locality321", "postalCode312", 2.0,2.0));
        
        Graph grafo2 = AplicationPOT.getInstance().getPlatform().getDroneMap();
        grafo2.insertVertex(new Address("Street", 2, "locality321", "postalCode312", 2.0,2.0));
        
        
        controller.newClient("name", "pass", "email", "22222", "street", 0, "postalCode", "locality", 1.0,1.0);
        
        controller.registerClient();
        
        boolean expResult = false;
        boolean result = controller.addClientToGraph();
        assertEquals(expResult, result);
    }
}
