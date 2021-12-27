/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.criticitymanagement.aplication;

import eapli.base.catalogmanagement.domain.Catalog;
import eapli.base.catalogmanagement.repositories.CatalogRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.requestmanagement.domain.Request;
import eapli.base.requestmanagement.repositories.RequestRepository;
import eapli.base.taskmanagement.domain.TaskExecution;
import eapli.base.taskmanagement.domain.TaskType;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author migue
 */
public class ShowSlaController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final RequestRepository requestRepository = PersistenceContext.repositories().requests();
    private final CatalogRepository catalogRepository = PersistenceContext.repositories().catalogs();
    private List<Request> requestsWithApprovedApprovalSla = new ArrayList<>();
    private List<Request> requestsWithoutApprovedResolutionSla = new ArrayList<>();

    public List<Request> searchRequestsBetweenDates(Date beginDate, Date endDate) throws ParseException {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.SERVICES_MANAGER);

        Iterable<Request> requestsAux = requestRepository.findAll();
        List<Request> request = new ArrayList<>();

        for (Request r : requestsAux) {
            if ((r.conclusionDate() != null) && (r.dateOfRequestt().date().before(endDate) && r.dateOfRequestt().date().after(beginDate)) && (r.conclusionDate().date().before(endDate) && r.conclusionDate().date().after(beginDate))) {
                request.add(r);
            }
        }

        verfySla(request);

        return request;
    }

    private void verfySla(List<Request> request) throws ParseException {
        for (Request r : request) {
            //Catalog catalog = catalogRepository.findByService(r.service());
            List<TaskExecution> tasks = r.taskExecutions();
            for (TaskExecution te : tasks) {
                String datestring = te.metrics().resolutionTime().toString();
                SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");//take a look at MM
                Date date = dt.parse(datestring);
                Instant instant = Instant.ofEpochMilli(date.getTime());
                LocalTime resolutionTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalTime();
                if (te.task().type() == TaskType.Approval) {
                    if (resolutionTime.isBefore(r.service().criticality().objectives().tempoMaxAprovacao())) {
                        requestsWithApprovedApprovalSla.add(r);
                    }
                } else if (te.task().type() == TaskType.Realization) {
                    if (resolutionTime.isBefore(r.service().criticality().objectives().tempoMaxResolucao())) {
                        requestsWithoutApprovedResolutionSla.add(r);
                    }
                }

            }
        }
    }

    public List<Request> requestsWithoutApprovedResolutionSlas() {
        return requestsWithoutApprovedResolutionSla;
    }

    public List<Request> requestsWithApprovedApprovalSlas() {
        return requestsWithApprovedApprovalSla;
    }

}
