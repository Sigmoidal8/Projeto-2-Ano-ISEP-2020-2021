/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.servicemanagement.application;

import eapli.base.catalogmanagement.domain.Catalog;
import eapli.base.catalogmanagement.repositories.CatalogRepository;
import eapli.base.collaboratormanagement.domain.Collaborator;
import eapli.base.collaboratormanagement.repositories.CollaboratorRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.servicemanagement.domain.*;
import eapli.base.servicemanagement.repositories.ServiceRepository;
import eapli.base.taskmanagement.domain.*;
import eapli.base.teammanagement.domain.Team;
import eapli.base.teammanagement.repositories.TeamRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.base.workflowmanagement.domain.Workflow;
import eapli.base.workflowmanagement.domain.WorkflowStatus;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Utilizador
 */
public class SpecifyServiceController {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final CatalogRepository catalogRepository = PersistenceContext.repositories().catalogs();
    private final CollaboratorRepository collaboratorRepository = PersistenceContext.repositories().collaborators();
    private final ServiceRepository serviceRepository = PersistenceContext.repositories().services();
    private final TeamRepository teamRepository = PersistenceContext.repositories().teams();
    private final ServiceBuilder serviceBuilder = new ServiceBuilder();

    public ServiceBuilder specifyCollaborator(){
        return serviceBuilder.withCollaborator(collaboratorRepository.findByEmail(authz.session().get().authenticatedUser().email()));
    }
    
    public ServiceBuilder specifyCollaborator(Collaborator colab){
        return serviceBuilder.withCollaborator(colab);
    }
    
    public void specifyCatalog(Catalog theCatalog, Service service){
         theCatalog.services().add(service);
         catalogRepository.save(theCatalog);
    }

    public ServiceBuilder specifyUniqueCode(String uniqueCode){
        return serviceBuilder.withUniqueCode(new UniqueCode(uniqueCode));
    }

    public ServiceBuilder specifyTitle(String title){
        return serviceBuilder.withTitle(new Title(title));
    }

    public ServiceBuilder specifyBriefDescription(String briefDescription){
        return serviceBuilder.withBriefDescription(new BriefDescription(briefDescription));
    }

    public ServiceBuilder specifyCompleteDescription(String completeDescription){
        return serviceBuilder.withCompleteDescription(new CompleteDescription(completeDescription));
    }

    public ServiceBuilder specifyType(Type type){
        return serviceBuilder.withType(type);
    }

    public ServiceBuilder specifyKeywords(List<String> keywords){
        List<Keyword> keywords2 = new ArrayList<>();
        for(String keyword:keywords){
            keywords2.add(new Keyword (keyword));
        }
        
        return serviceBuilder.withKeywords(keywords2);
    }

    public ServiceBuilder specifyIcone(String icone){
        return serviceBuilder.withIcone(new Icone (icone));
    }

    public ServiceBuilder specifyForms(Form forms){
        return serviceBuilder.withForms(forms);
    }

    public ServiceBuilder specifyWorkflow(Workflow workflows){
        return serviceBuilder.withWorkflow(workflows);
    }


    public ServiceBuilder addKeywords(List<String> keywords){
        List<Keyword> keywords2 = new ArrayList<>();
        for(String keyword:keywords){
            keywords2.add(new Keyword (keyword));
        }
        return serviceBuilder.addKeywords(keywords2);
    }

    public Workflow createWorkflow(Date startDate, WorkflowStatus status){
        Workflow workflow = new Workflow(startDate, status);
        return workflow;
    }

    public Task createAutomaticTask(TaskType type, Script script, int index){
        return new AutomaticTask(type,index,script);
    }

    public Task createApprovalTask(TaskType type,int index, Form form, TypeCollaboratorApproval collaboratorApproval){
        return new ApprovalTask(type,index,form, collaboratorApproval);
    }

    public Task createManualTask(TaskType type, Form form,List<Collaborator> collaborators, int index){
        return  new ManualTask(type, index,collaborators,form);
    }

    public Task createManualTask(TaskType type, Form form, int index, List<Team> teams){
        return  new ManualTask(type, index,form,teams);
    }

    public Script createScript(String scriptLocation){
        return new Script(scriptLocation);
    }

    public Form createForm(String name, Script script, List<Attribute> attributes){
        return new Form(new Name(name), script, attributes);
    }

    public Catalog catalogOfSelectedService(Service service){
        return catalogRepository.findByService(service);
    }

    public Type verifyServiceType(){
        return serviceBuilder.verifyServiceType();
    }

    public List<String> verifyService(){
        return serviceBuilder.missingData();
    }

    public Service saveService(){
        serviceBuilder.isStillDraft(false);
        return serviceRepository.save(serviceBuilder.build());
    }

    public Service saveServiceAsDraft(){
        serviceBuilder.isStillDraft(true);
        return serviceRepository.save(serviceBuilder.build());
    }


    public void continueServiceSpecification(Service service){
        serviceBuilder.withUniqueCode(service.uniqueCode());
        serviceBuilder.withTitle(service.title());
        serviceBuilder.withBriefDescription(service.briefDescription());
        serviceBuilder.withCompleteDescription(service.completeDescription());
        serviceBuilder.withForms(service.forms());
        serviceBuilder.withIcone(service.icone());
        serviceBuilder.isStillDraft(service.isDraft());
        serviceBuilder.withKeywords(service.keywords());
        serviceBuilder.withCollaborator(service.collaboratorWhoDefined());
        serviceBuilder.withWorkflow(service.workflows());
        serviceBuilder.withType(service.type());
    }

    public Iterable<Catalog> catalogs(){
        return catalogRepository.findAll();
    }

    public Iterable<Collaborator> collaborators(){
        return collaboratorRepository.findAll();
    }

    public Iterable<Team> teams(){
        return teamRepository.findAll();
    }

    public Iterable<Service> uncompletedServices(){
        return serviceRepository.findUnfinishedServiceByCollaborator(collaboratorRepository.findByEmail(authz.session().get().authenticatedUser().email()).get());
    }
    
}
