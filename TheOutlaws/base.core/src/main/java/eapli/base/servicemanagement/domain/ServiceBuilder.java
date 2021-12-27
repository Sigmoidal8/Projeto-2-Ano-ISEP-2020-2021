package eapli.base.servicemanagement.domain;

import eapli.base.collaboratormanagement.domain.Collaborator;
import eapli.base.workflowmanagement.domain.Workflow;
import eapli.framework.domain.model.DomainFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ServiceBuilder implements DomainFactory<Service> {

    private UniqueCode uniqueCode;
    private Title title;
    private BriefDescription briefDescription;
    private CompleteDescription completeDescription;
    private Type type;
    private List<Keyword> keywords;
    private Icone icone;
    private Form forms;
    private int isDraft;
    private Collaborator collaborator;
    private Workflow workflow;

    public ServiceBuilder withUniqueCode(final UniqueCode uniqueCode) {
        this.uniqueCode = uniqueCode;
        return this;
    }

    public ServiceBuilder withTitle(final Title title) {
        this.title = title;
        return this;
    }

    public ServiceBuilder withBriefDescription(final BriefDescription briefDescription) {
        this.briefDescription = briefDescription;
        return this;
    }

    public ServiceBuilder withCompleteDescription(final CompleteDescription completeDescription) {
        this.completeDescription = completeDescription;
        return this;
    }

    public ServiceBuilder withType(final Type type) {
        this.type = type;
        return this;
    }

    public ServiceBuilder withKeywords(final List<Keyword> keywords) {
        this.keywords = keywords;
        return this;
    }

    public ServiceBuilder withIcone(final Icone icone) {
        this.icone = icone;
        return this;
    }

    public ServiceBuilder withForms(final Form forms) {
        this.forms = forms;
        return this;
    }

    public ServiceBuilder withCollaborator(final Optional<Collaborator> collaborator) {
        if (collaborator.get() != null) {
            this.collaborator = collaborator.get();
            return this;
        } else {
            return null;
        }
    }

    public ServiceBuilder withCollaborator(final Collaborator collaborator) {
        this.collaborator = collaborator;
        return this;
    }

    public ServiceBuilder withWorkflow(final Workflow workflow){
        this.workflow = workflow;
        return this;
    }

    public ServiceBuilder isStillDraft(final boolean isDraft) {
        if (isDraft) {
            this.isDraft = 1;
        } else {
            this.isDraft = 0;
        }
        return this;
    }

    public ServiceBuilder addKeywords(final List<Keyword> keywords) {
        keywords.forEach(this.keywords::add);
        return this;
    }

    public Type verifyServiceType(){
        return type;
    }

    public List<String> missingData(){
        List<String> incompleteData = new ArrayList<>();
        if(uniqueCode == null){
            incompleteData.add("Unique code");
        }
        if(title == null){
            incompleteData.add("Title");
        }
        if(briefDescription == null){
            incompleteData.add("Brief description");
        }
        if(completeDescription == null){
            incompleteData.add("Complete description");
        }
        if(type == null){
            incompleteData.add("Type");
        }
        if(keywords == null){
            incompleteData.add("Keywords");
        }
        if(icone == null){
            incompleteData.add("Icone");
        }
        if(forms == null){
            incompleteData.add("Soliciting form");
        }
        if(collaborator == null){
            incompleteData.add("Collaborator who added the service");
        }
        if(workflow == null){
            incompleteData.add("Workflow");
        }
        return incompleteData;
    }

    @Override
    public Service build() {
        return new Service(this.uniqueCode, this.title, this.briefDescription, this.completeDescription, this.type,
                this.keywords, this.icone, this.forms, this.isDraft, this.collaborator, this.workflow);
    }
}
