/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import java.util.Scanner;
import lapr.project.controller.LoginController;


public class LoginWindowUI {


    private final LoginController appController;

    public LoginWindowUI(MainWindowUI main) {
        appController =  main.getLoginController();
        Scanner sc = new Scanner(System.in);
        boolean login = false;
        while (!login) {
            System.out.println("========================================");
            System.out.println("\t\tLogin");
            System.out.println("========================================");
            System.out.println("Email:");
            String email = sc.next();
            System.out.println("Password:");
            String password = sc.next();

            login = appController.doLogin(email, password);
            if (login) {
                System.out.println("Sucesseful Login");
            }else{
                System.out.println("Bad Login,");
            }
        }
    }
    
    public String getUserRole(){
        return appController.getUserRole();
    }
}
