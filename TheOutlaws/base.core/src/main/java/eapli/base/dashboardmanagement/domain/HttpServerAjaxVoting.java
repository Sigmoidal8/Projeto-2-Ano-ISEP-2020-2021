/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.dashboardmanagement.domain;

import eapli.base.collaboratormanagement.domain.Collaborator;
import eapli.base.dashboardmanagement.application.DashboardController;
import eapli.base.taskmanagement.domain.TaskExecution;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

/**
 *
 * @author migue
 */
public class HttpServerAjaxVoting extends Thread {

    static private final String BASE_FOLDER = "base.core/src/main/java/eapli/base/dashboardmanagement/domain/www";
    static private SSLServerSocket sock;
    static private DashboardController thecontroller;

    @Override
    public void run() {
        SSLSocket cliSock = null;

        accessesCounter = 0;
        for (int i = 0; i < candidatesNumber; i++) {
            candidateName[i] = "Candidate " + i;
            candidateVotes[i] = 0;
        }

        System.setProperty("javax.net.ssl.keyStore", "server.jks");
        System.setProperty("javax.net.ssl.keyStorePassword", "forgotten");

        try {
            SSLServerSocketFactory sslF = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
            sock = (SSLServerSocket) sslF.createServerSocket(50001);
        } catch (IOException ex) {
            System.err.println("Server failed to open local port " + 50001);

        }
        while (true) {

            try {
                cliSock = (SSLSocket) sock.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }
            HttpAjaxVotingRequest req = new HttpAjaxVotingRequest(cliSock, BASE_FOLDER);
            req.start();
        }
    }

    // DATA ACCESSED BY THREADS - LOCKING REQUIRED
    private static final int candidatesNumber = 4;
    private static final String[] candidateName = new String[candidatesNumber];
    private static final int[] candidateVotes = new int[candidatesNumber];
    private static int accessesCounter;
    private static Collaborator colab;
    private static List<String> listOrderByPriority;
    private static List<String> listOrderByCriticality;
    private static List<String> listOrderByRequestDate;

    public void setColab(Collaborator colab) {
        HttpServerAjaxVoting.colab = colab;
    }

    public void changeController(DashboardController controller) {
        this.thecontroller = controller;
    }

    public void setlistOrderByPriority(List<String> listOrderByPriority) {
        HttpServerAjaxVoting.listOrderByPriority = listOrderByPriority;
    }

    public void setlistOrderByCriticality(List<String> listOrderByCriticality) {
        HttpServerAjaxVoting.listOrderByCriticality = listOrderByCriticality;
    }
    
    public void setlistOrderByRequestDate(List<String> listOrderByRequestDate) {
        HttpServerAjaxVoting.listOrderByRequestDate = listOrderByRequestDate;
    }

    private static synchronized void incAccessesCounter() {
        accessesCounter++;
    }

    public static synchronized String getVotesStandingInHTML() {
        String textHtml = "<ul class=\"whiteText\">";
        for (int i = 0; i < candidatesNumber; i++) {
            textHtml = textHtml + "<li><button type=button onclick=voteFor(" + (i + 1)
                    + ")>Vote for " + candidateName[i] + "</button> "
                    + " - " + candidateVotes[i] + " votes </li>";
        }
        textHtml = textHtml + "</ul><p class=\"whiteText\">HTTP server accesses counter: " + accessesCounter + "</p>";
        return textHtml;
    }

    public static synchronized String getColabInfoStandingInHTML() {
        return " <div class=\"topnav\" id=\"personalInfo\">\n"
                + " <a class=\"active\" href=\"#home\">Personal Information</a>\n"
                + " <a href=\"#news\">Name: " + colab.completeName() + "</a>\n"
                + " <a href=\"#about\">Email: " + colab.email() + "</a>\n"
                + " <a href=>Mechanographic Number: " + colab.identity() + "</a>\n"
                + " <a href=>Pending Tasks: " + listOrderByPriority.size() + "</a>\n"
                + " </div>";
    }

    public static synchronized String getTasksByPriorityInHTML() {
        thecontroller.askWorkflowForData();
        String textHtml = "";
        for (String t : listOrderByPriority) {
            String taskElements[] = t.split(",");
            textHtml = textHtml + "<tr class=\"active-row\">"
                    + "<td>" + taskElements[0].toString().substring(1, taskElements[0].length() - 1) + "</td>"
                    + "<td>" + taskElements[1].toString().substring(1, taskElements[1].length() - 1) + "</td>"
                    + "<td>" + taskElements[2].toString().substring(1, taskElements[2].length() - 1) + "</td>"
                    + "<td>" + taskElements[3].toString().substring(1, taskElements[3].length() - 1) + "</td>"
                    + "<td>" + taskElements[4].toString().substring(1, taskElements[4].length() - 1) + "</td>"
                    + "<td>" + taskElements[5].toString().substring(1, taskElements[5].length() - 1) + "</td>"
                    + "<td>" + taskElements[6].toString().substring(1, taskElements[6].length() - 1) + "</td>"
                    + "<td>" + taskElements[7].toString().substring(1, taskElements[7].length() - 1) + "</td>"
                    + " </tr>";
        }
        return textHtml;
    }

    public static synchronized String getTasksByCriticalityInHTML() {
        thecontroller.askWorkflowForData();
        String textHtml = "";
        for (String t : listOrderByCriticality) {
            String taskElements[] = t.split(",");
            textHtml = textHtml + "<tr class=\"active-row\">"
                    + "<td>" + taskElements[0].toString().substring(1, taskElements[0].length() - 1) + "</td>"
                    + "<td>" + taskElements[1].toString().substring(1, taskElements[1].length() - 1) + "</td>"
                    + "<td>" + taskElements[2].toString().substring(1, taskElements[2].length() - 1) + "</td>"
                    + "<td>" + taskElements[3].toString().substring(1, taskElements[3].length() - 1) + "</td>"
                    + "<td>" + taskElements[4].toString().substring(1, taskElements[4].length() - 1) + "</td>"
                    + "<td>" + taskElements[5].toString().substring(1, taskElements[5].length() - 1) + "</td>"
                    + "<td>" + taskElements[6].toString().substring(1, taskElements[6].length() - 1) + "</td>"
                    + "<td>" + taskElements[7].toString().substring(1, taskElements[7].length() - 1) + "</td>"
                    + " </tr>";
        }
        return textHtml;
    }

    public static synchronized String getTasksByRequestDateInHTML() {
        thecontroller.askWorkflowForData();
        String textHtml = "";
        for (String t : listOrderByRequestDate) {
            String taskElements[] = t.split(",");
            textHtml = textHtml + "<tr class=\"active-row\">"
                    + "<td>" + taskElements[0].toString().substring(1, taskElements[0].length() - 1) + "</td>"
                    + "<td>" + taskElements[1].toString().substring(1, taskElements[1].length() - 1) + "</td>"
                    + "<td>" + taskElements[2].toString().substring(1, taskElements[2].length() - 1) + "</td>"
                    + "<td>" + taskElements[3].toString().substring(1, taskElements[3].length() - 1) + "</td>"
                    + "<td>" + taskElements[4].toString().substring(1, taskElements[4].length() - 1) + "</td>"
                    + "<td>" + taskElements[5].toString().substring(1, taskElements[5].length() - 1) + "</td>"
                    + "<td>" + taskElements[6].toString().substring(1, taskElements[6].length() - 1) + "</td>"
                    + "<td>" + taskElements[7].toString().substring(1, taskElements[7].length() - 1) + "</td>"
                    + " </tr>";
        }
        return textHtml;
    }

    public static synchronized void castVote(String i) {
        int cN;
        try {
            cN = Integer.parseInt(i);
        } catch (NumberFormatException ne) {
            return;
        }
        cN--;
        if (cN >= 0 && cN < candidatesNumber) {
            candidateVotes[cN]++;
        }
    }
}
