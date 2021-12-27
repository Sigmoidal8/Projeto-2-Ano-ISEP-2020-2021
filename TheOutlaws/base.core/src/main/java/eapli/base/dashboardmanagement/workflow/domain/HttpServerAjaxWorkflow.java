package eapli.base.dashboardmanagement.workflow.domain;



import eapli.base.collaboratormanagement.domain.Collaborator;
import eapli.base.dashboardmanagement.workflow.application.WorkflowDashboardController;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import java.io.IOException;
import java.util.List;

/**
 * @author Raúl Coelho
 */
public class HttpServerAjaxWorkflow extends Thread{

    static private final String BASE_FOLDER = "base.core/src/main/java/eapli/base/dashboardmanagement/workflow/domain/www";
    static private SSLServerSocket sock;
    static private WorkflowDashboardController controller;

    @Override
    public void run() {
        SSLSocket cliSock = null;

        System.setProperty("javax.net.ssl.keyStore", "server.jks");
        System.setProperty("javax.net.ssl.keyStorePassword", "forgotten");

        try {
            SSLServerSocketFactory sslF = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
            sock = (SSLServerSocket) sslF.createServerSocket(50002);
        } catch (IOException ex) {
            System.err.println("Server failed to open local port " + 50002);

        }
        while (true) {

            try {
                cliSock = (SSLSocket) sock.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }
            HttpAjaxWorkflowRequest req = new HttpAjaxWorkflowRequest(cliSock, BASE_FOLDER);
            req.start();
        }
    }

    // DATA ACCESSED BY THREADS - LOCKING REQUIRED
    private static Collaborator colab;
    private static List<String> requests;

    public void changeController(WorkflowDashboardController controller){
        this.controller = controller;
    }

    public void changeCollaborator(Collaborator colab) {
        HttpServerAjaxWorkflow.colab = colab;
    }

    public void changeRequests(List<String> requests){
        HttpServerAjaxWorkflow.requests = requests;
    }

    public static synchronized String getColabInfoStandingInHTML() {
        return " <div class=\"topnav\" id=\"personalInfo\">\n"
                + " <a class=\"active\" href=\"#home\">Personal Information</a>\n"
                + " <a href=\"#news\">Name: " + colab.completeName() + "</a>\n"
                + " <a href=\"#about\">Email: " + colab.email() + "</a>\n"
                + " <a href=>Mechanographic Number: " + colab.identity() + "</a>\n"
                + " <a href=>Number of Requests: " + requests.size()+ "</a>\n"
                + " </div>";
    }

    public static synchronized String getRequestInHTML() {
        controller.askWorkflowForData();
        String textHtml = "";
        for (String t : requests) {
            String taskElements[] = t.split(",");
            textHtml = textHtml + "<tr class=\"active-row\">"
                    + "<td>" + taskElements[0].toString().substring(1, taskElements[0].length()-1) + "</td>"
                    + "<td>" + taskElements[1].toString().substring(1, taskElements[1].length()-1) + "</td>"
                    + "<td>" + taskElements[2].toString().substring(1, taskElements[2].length()-1) + "</td>"
                    + "<td>" + taskElements[3].toString().substring(1, taskElements[3].length()-1) + "</td>"
                    + "<td>" + taskElements[4].toString().substring(1, taskElements[4].length()-1) + "</td>"
                    + "<td>" + taskElements[5].toString().substring(1, taskElements[5].length()-1) + "</td>"
                    + "<td>" + taskElements[6].toString().substring(1, taskElements[6].length()) + "</td>"
                    + "<td>" + taskElements[8].toString().substring(1, taskElements[8].length()-1) + "</td>"
                    + " </tr>";
        }
        return textHtml;
    }

}
