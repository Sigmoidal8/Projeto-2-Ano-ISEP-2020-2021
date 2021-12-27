/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.dashboardmanagement.domain;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author migue
 */
public class HttpAjaxVotingRequest extends Thread {

    String baseFolder;
    Socket sock;
    DataInputStream inS;
    DataOutputStream outS;

    public HttpAjaxVotingRequest(Socket s, String f) {
        baseFolder = f;
        sock = s;
    }

    @Override
    public void run() {
        try {
            outS = new DataOutputStream(sock.getOutputStream());
            inS = new DataInputStream(sock.getInputStream());
        } catch (IOException ex) {
            System.out.println("Thread error on data streams creation");
        }
        try {
            HTTPmessage request = new HTTPmessage(inS);
            HTTPmessage response = new HTTPmessage();
            // System.out.println(request.getURI());

            if (request.getMethod().equals("GET")) {
                if (request.getURI().equals("/votes")) {
                    response.setContentFromString(HttpServerAjaxVoting.getVotesStandingInHTML(), "text/html");
                    response.setResponseStatus("200 Ok");
                } else if (request.getURI().equals("/personalInfo")) {
                    response.setContentFromString(HttpServerAjaxVoting.getColabInfoStandingInHTML(), "text/html");
                    response.setResponseStatus("200 Ok");
                } else if (request.getURI().equals("/tasksOrderByPriority")) {
                    response.setContentFromString(HttpServerAjaxVoting.getTasksByPriorityInHTML(), "text/html");
                    response.setResponseStatus("200 Ok");
                } else if (request.getURI().equals("/tasksOrderByCriticality")) {
                    response.setContentFromString(HttpServerAjaxVoting.getTasksByCriticalityInHTML(), "text/html");
                    response.setResponseStatus("200 Ok");
                } else if (request.getURI().equals("/tasksOrderByRequestDate")) {
                    response.setContentFromString(HttpServerAjaxVoting.getTasksByRequestDateInHTML(), "text/html");
                    response.setResponseStatus("200 Ok");
                } else {
                    String fullname = baseFolder + "/";
                    if (request.getURI().equals("/")) {
                        fullname = fullname + "index.html";
                    } else {
                        fullname = fullname + request.getURI();
                    }
                    if (response.setContentFromFile(fullname)) {
                        response.setResponseStatus("200 Ok");
                    } else {
                        response.setContentFromString("<html><body><h1>404 File not found</h1></body></html>", "text/html");
                        response.setResponseStatus("404 Not Found");
                    }
                }
                response.send(outS);
            } else { // NOT GET
                if (request.getMethod().equals("PUT") && request.getURI().startsWith("/votes/")) {
                    HttpServerAjaxVoting.castVote(request.getURI().substring(7));
                    response.setResponseStatus("200 Ok");
                } else {
                    response.setContentFromString("<html><body><h1>ERROR: 405 Method Not Allowed</h1></body></html>", "text/html");
                    response.setResponseStatus("405 Method Not Allowed");
                }
                response.send(outS);
            }
        } catch (IOException ex) {
            ex.toString();
        }
        try {
            sock.close();
        } catch (IOException ex) {
            System.out.println("CLOSE IOException");
        }
    }
}
