/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.requestmanagement.application;

import eapli.base.collaboratormanagement.domain.Collaborator;
import eapli.base.collaboratormanagement.repositories.CollaboratorRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.requestmanagement.domain.Request;
import eapli.base.requestmanagement.domain.RequestStatus;
import eapli.base.requestmanagement.repositories.RequestRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author migue
 */
public class ShowRequestsController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final RequestRepository requestRepository = PersistenceContext.repositories().requests();
    private final CollaboratorRepository collaboratorRepository = PersistenceContext.repositories().collaborators();

    public List<Request> showRequests(int option) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.HR_MANAGER, BaseRoles.COLLABORATOR, BaseRoles.SERVICES_MANAGER);

        Collaborator collaborator = null;
        Optional<Collaborator> optional = collaboratorRepository.findByEmail(authz.session().get().authenticatedUser().email());

        if (optional.get() != null) {
            collaborator = optional.get();
        }
        Iterable<Request> request = requestRepository.findColaboratorRequests(collaborator);
        List<Request> request2 = new ArrayList<>();

        for (Request r : request) {
            request2.add(r);
        }

        if (request2.isEmpty()) {
            return null;
        }
        if (option == 1) {
            List<Request> ongoingRequests = showOngoingRequests(request2);
            if (ongoingRequests.isEmpty()) {
                return null;
            }
            return orderByDate(ongoingRequests);

        } else if (option == 2) {
            List<Request> doneRequests = showRequestsDone(request2);

            if (doneRequests.isEmpty()) {
                return null;
            }
            return orderByDate(doneRequests);
        }
        return null;
    }

    public List<Request> showOngoingRequests(List<Request> requests) {
        List<Request> ongoingRequests = new ArrayList<>();

        for (Request r : requests) {
            if (!r.status().equals(RequestStatus.Concluded)) {
                ongoingRequests.add(r);
            }
        }
        return ongoingRequests;
    }

    public List<Request> showRequestsDone(List<Request> requests) {
        List<Request> doneRequests = new ArrayList<>();

        for (Request r : requests) {
            if (r.status().equals(RequestStatus.Concluded)) {
                doneRequests.add(r);
            }
        }
        return doneRequests;
    }

    public List<Request> orderByDate(List<Request> requests) {

        List<Request> requestAux = requests;
        requestAux.sort(Comparator.comparing((Request r) -> r.dateOfRequestt()).reversed());
        return requestAux;
    }
}
