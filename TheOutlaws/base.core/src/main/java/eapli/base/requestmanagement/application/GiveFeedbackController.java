package eapli.base.requestmanagement.application;

import eapli.base.collaboratormanagement.domain.Collaborator;
import eapli.base.collaboratormanagement.repositories.CollaboratorRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.requestmanagement.domain.Feedback;
import eapli.base.requestmanagement.domain.Request;
import eapli.base.requestmanagement.repositories.RequestRepository;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author Raúl Coelho
 */
public class GiveFeedbackController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final RequestRepository requestRepository = PersistenceContext.repositories().requests();
    private final CollaboratorRepository collaboratorRepository = PersistenceContext.repositories().collaborators();

    private static final int days = 7;

    public Iterable<Request> availableRequestsFeedback(){
        Collaborator collab = null;
        collab = collaboratorRepository.findByEmail(authz.session().get().authenticatedUser().email()).get();

        if(collab == null){
            return null;
        }

        Iterable<Request> availableRequest = requestRepository.findRequestAvailableFeedback(collab);

        List<Request> aux = new ArrayList<>();

        Calendar c=Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE,days);

        for(Request r : availableRequest){
            if(c.getTime().compareTo(r.conclusionDate().date())>=0){
                aux.add(r);
            }
        }

        return aux;
    }

    public void giveFeedback(Request request, int value){
        Feedback feedback = new Feedback(value);
        request = request.giveFeedback(feedback);
        requestRepository.save(request);
    }
}
