/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.infrastructure.bootstrapers;

import eapli.base.catalogmanagement.domain.Catalog;
import eapli.base.catalogmanagement.domain.Identifier;
import eapli.base.catalogmanagement.repositories.CatalogRepository;
import eapli.base.collaboratormanagement.domain.Collaborator;
import eapli.base.collaboratormanagement.domain.MecanographicNumber;
import eapli.base.collaboratormanagement.repositories.CollaboratorRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.servicemanagement.application.SpecifyServiceController;
import eapli.base.servicemanagement.domain.*;
import eapli.base.taskmanagement.domain.TaskType;
import eapli.base.taskmanagement.domain.TypeCollaboratorApproval;
import eapli.base.teammanagement.domain.Team;
import eapli.base.teammanagement.domain.UniqueCode;
import eapli.base.teammanagement.repositories.TeamRepository;
import eapli.base.workflowmanagement.domain.Workflow;
import eapli.base.workflowmanagement.domain.WorkflowStatus;
import eapli.framework.actions.Action;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.general.domain.model.Description;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author migue
 */
public class ServiceBootstrapper implements Action {

    private static final Logger LOGGER = LogManager.getLogger(ServiceBootstrapper.class);
    private final CatalogRepository catalogRepo = PersistenceContext.repositories().catalogs();
    private final CollaboratorRepository CollaboratorRepo = PersistenceContext.repositories().collaborators();
    private final TeamRepository TeamRepo = PersistenceContext.repositories().teams();

    private Catalog getCatalog(final Identifier identifier) {
        return catalogRepo.ofIdentity(identifier).orElseThrow(IllegalStateException::new);
    }

    private Collaborator getCollaborator(final MecanographicNumber mechanographicNumber) {
        return CollaboratorRepo.ofIdentity(mechanographicNumber).orElseThrow(IllegalStateException::new);
    }
    
     private Team getTeam(final UniqueCode uniqueCode) {
        return TeamRepo.ofIdentity(uniqueCode).orElseThrow(IllegalStateException::new);
    }

    @Override
    public boolean execute() {
        final Team team1 = getTeam(TestDataConstants.TEAM_1);
        final Team team2 = getTeam(TestDataConstants.TEAM_2);

        final List<Team> teams1 = new ArrayList<>();
        teams1.add(team1);
        teams1.add(team2);
        final List<Team> teams2 = new ArrayList<>();
        teams2.add(team1);

        final Catalog Catalog1 = getCatalog(TestDataConstants.CATALOG_1);
        final Catalog Catalog2 = getCatalog(TestDataConstants.CATALOG_2);

        final Collaborator colab1 = getCollaborator(TestDataConstants.COLLABORATOR_1);
        final Collaborator colab2 = getCollaborator(TestDataConstants.COLLABORATOR_2);
        final Collaborator colab3 = getCollaborator(TestDataConstants.COLLABORATOR_3);
        final Collaborator colab4 = getCollaborator(TestDataConstants.COLLABORATOR_4);
        final Collaborator colab5 = getCollaborator(TestDataConstants.COLLABORATOR_5);
        final Collaborator colab6 = getCollaborator(TestDataConstants.COLLABORATOR_6);

        final List<Collaborator> colabs1 = new ArrayList<>();
        colabs1.add(colab1);
        colabs1.add(colab2);

        final List<Collaborator> colabs2 = new ArrayList<>();
        colabs2.add(colab3);
        colabs2.add(colab4);

        final Attribute atribute1 = new Attribute(new Label("Código"), new Name("Código Interno do Cliente"), Description.valueOf("3 consoantes + 3 dígitos, Obrigatório"), DataType.String, new RegularExpression("[B-DF-HJ-NP-TV-Z]{3}[0-9]{3}"));
        final Attribute atribute18 = new Attribute(new Label("Nome"), new Name("Nome"), Description.valueOf("Obrigatório"), DataType.String, new RegularExpression("[A-Za-z ]+"));
        final Attribute atribute11 = new Attribute(new Label("Tipo"), new Name("Tipo de Desconto"), Description.valueOf("Obrigatório"), DataType.String, new RegularExpression("[A-Za-z ]+"));
        final Attribute atribute12 = new Attribute(new Label("Recorrência"), new Name("Recorrência"), Description.valueOf("Obrigatório, Unica ou Ate Data Limite"), DataType.String, new RegularExpression("Unica|Ate Data Limite"));
        final Attribute atribute13 = new Attribute(new Label("Percentagem"), new Name("Percentagem de Desconto"), Description.valueOf("Não por nada se quiser pôr o valor do desconto"), DataType.Integer, new RegularExpression("[0-9]*(.[0-9]+)?"));
        final Attribute atribute14 = new Attribute(new Label("Valor"), new Name("Valor de Desconto"), Description.valueOf("Não por nada se tiver posto a percentagem"), DataType.Integer, new RegularExpression("[0-9]*(.[0-9]+)?"));
        final Attribute atribute15 = new Attribute(new Label("Identificação"), new Name("Identificação da Fatura"), Description.valueOf("Apenas se a recorrência for única"), DataType.Integer, new RegularExpression("[0-9]*"));
        final Attribute atribute16 = new Attribute(new Label("Data"), new Name("Data Limite"), Description.valueOf("Apenas se a recorrência for até data limite"), DataType.Date, new RegularExpression("(([0-2][0-9]|(3)[0-1])(\\/)(((0)[1-9])|((1)[0-2]))(\\/)\\d{4})?"));
        final Attribute atribute17 = new Attribute(new Label("Fundamentação"), new Name("Fundamentação do pedido"), Description.valueOf("Obrigatório"), DataType.String, new RegularExpression("[a-zA-Z0-9 ]+"));
        final Attribute atribute3 = new Attribute(new Label("Texto"), new Name("Texto fundamentando a decisão"), Description.valueOf("Obrigatório"), DataType.String, new RegularExpression("[a-zA-Z0-9 ]+"));
        final Attribute atribute31 = new Attribute(new Label("Percentagem"), new Name("Percentagem de Desconto"), Description.valueOf("Não por nada se quiser pôr o valor do desconto"), DataType.Integer, new RegularExpression("[0-9]*(.[0-9]+)?"));
        final Attribute atribute32 = new Attribute(new Label("Valor"), new Name("Valor de Desconto"), Description.valueOf("Não por nada se tiver posto a percentagem"), DataType.Integer, new RegularExpression("[0-9]*(.[0-9]+)?"));
        final Attribute atribute4 = new Attribute(new Label("Codigo"), new Name("Código do Produto"), Description.valueOf("Obrigatório"), DataType.Integer, new RegularExpression("[0-9]+"));
        final Attribute atribute41 = new Attribute(new Label("Quantidade"), new Name("Quantidade Pretendida"), Description.valueOf("Obrigatório"), DataType.Integer, new RegularExpression("[0-9]+"));
        final Attribute atribute42 = new Attribute(new Label("Tipo"), new Name("Tipo de Cliente"), Description.valueOf("Nacional, Europeu ou Resto do Mundo, Obrigatório"), DataType.String, new RegularExpression("Nacional|Europeu|Resto do Mundo"));
        final Attribute atribute5 = new Attribute(new Label("labe5"), new Name("name1"), Description.valueOf("description"), DataType.String, new RegularExpression("[A-Za-z]{2}"));
        final Attribute atribute6 = new Attribute(new Label("Data"), new Name("Data de Início da Ausência"), Description.valueOf("Data de quando a Ausência começam"), DataType.Date, new RegularExpression("([0-2][0-9]|(3)[0-1])(\\/)(((0)[1-9])|((1)[0-2]))(\\/)\\d{4}"));
        final Attribute atribute61 = new Attribute(new Label("Data"), new Name("Data de fim das Ausência"), Description.valueOf("Data de quando a Ausência terminam, tem de ser superior à data de início"), DataType.Date, new RegularExpression("([0-2][0-9]|(3)[0-1])(\\/)(((0)[1-9])|((1)[0-2]))(\\/)\\d{4}"));
        final Attribute atribute62 = new Attribute(new Label("Tipo"), new Name("Tipo de ausência"), Description.valueOf("Ferias, Justificada, Nao Justificada"), DataType.String, new RegularExpression("Ferias|Justificada|Nao Justificada"));
        final Attribute atribute63 = new Attribute(new Label("Justificação"), new Name("Justificação para ausência"), Description.valueOf("Caso seja uma ausência justificada tem de apresentar a justificação"), DataType.String, new RegularExpression("[a-zA-Z0-9 ]*"));
        final Attribute atribute7 = new Attribute(new Label("Fundamentação"), new Name("Fundamentação da decisão"), Description.valueOf("Obrigatório"), DataType.String, new RegularExpression("[a-zA-Z0-9 ]+"));
        final Attribute atribute8 = new Attribute(new Label("Dias"), new Name("Dias de férias já gozados no ano"), Description.valueOf("Obrigatório"), DataType.Integer, new RegularExpression("[0-9]+"));
        final Attribute atribute81 = new Attribute(new Label("Dias"), new Name("Dias de férias gozados do período solicitado"), Description.valueOf("Obrigatório"), DataType.Integer, new RegularExpression("[0-9]+"));
        final Attribute atribute82 = new Attribute(new Label("Dias"), new Name("Dias de férias totais"), Description.valueOf("Obrigatório"), DataType.Integer, new RegularExpression("[0-9]+"));
        final Attribute atribute83 = new Attribute(new Label("Dias"), new Name("Dias de falta justificadas já ocorridas no ano"), Description.valueOf("Obrigatório"), DataType.Integer, new RegularExpression("[0-9]+"));
        final Attribute atribute84 = new Attribute(new Label("Dias"), new Name("Dias de faltas justificadas do período solicitado"), Description.valueOf("Obrigatório"), DataType.Integer, new RegularExpression("[0-9]+"));
        final Attribute atribute85 = new Attribute(new Label("Dias"), new Name("Dias de faltas justificadas totais"), Description.valueOf("Obrigatório"), DataType.Integer, new RegularExpression("[0-9]+"));
        final Attribute atribute86 = new Attribute(new Label("Dias"), new Name("Dias de falta não justificadas já ocorridas no ano"), Description.valueOf("Obrigatório"), DataType.Integer, new RegularExpression("[0-9]+"));
        final Attribute atribute87 = new Attribute(new Label("Dias"), new Name("Dias de faltas não justificadas do período solicitado"), Description.valueOf("Obrigatório"), DataType.Integer, new RegularExpression("[0-9]+"));
        final Attribute atribute88 = new Attribute(new Label("Dias"), new Name("Dias de faltas não justificadas totais"), Description.valueOf("Obrigatório"), DataType.Integer, new RegularExpression("[0-9]+"));
        final Attribute atribute89 = new Attribute(new Label("Comentário"), new Name("Comentário"), Description.valueOf("Não Obrigatório"), DataType.Integer, new RegularExpression("[a-zA-Z0-9 ]*"));
        final Attribute atribute9 = new Attribute(new Label("Rua"), new Name("Rua"), Description.valueOf("Obrigatório"), DataType.String, new RegularExpression("[a-zA-Z0-9 ]+"));
        final Attribute atribute91 = new Attribute(new Label("Código"), new Name("Código Postal"), Description.valueOf("Obrigatório"), DataType.String, new RegularExpression("[0-9]{4}-[0-9]{3}"));
        final Attribute atribute10 = new Attribute(new Label("Texto"), new Name("Texto de Observações"), Description.valueOf("Obrigatório"), DataType.String, new RegularExpression("[a-zA-Z0-9 ]+"));

        final List<String> keywords1 = new ArrayList<>();
        keywords1.add("desconto");
        keywords1.add("aplicacao");

        final List<String> keywords2 = new ArrayList<>();
        keywords2.add("cotacao");
        keywords2.add("venda");

        final List<String> keywords3 = new ArrayList<>();
        keywords3.add("ferias");
        keywords3.add("férias");
        keywords3.add("descanso");

        final List<String> keywords4 = new ArrayList<>();
        keywords4.add("residencia");
        keywords4.add("alteracao");
        
        final List<String> keywords5 = new ArrayList<>();
        keywords4.add("servidor");
        keywords4.add("analise");
        
        final List<String> keywords6 = new ArrayList<>();
        keywords4.add("servidor");
        keywords4.add("limpeza");

        final List<Attribute> attibutes = new ArrayList<>();
        attibutes.add(atribute1);
        attibutes.add(atribute18);
        attibutes.add(atribute11);
        attibutes.add(atribute12);
        attibutes.add(atribute13);
        attibutes.add(atribute14);
        attibutes.add(atribute15);
        attibutes.add(atribute16);
        attibutes.add(atribute17);

        final List<Attribute> attibutes2 = new ArrayList<>();
        attibutes2.add(atribute3);
        attibutes2.add(atribute31);
        attibutes2.add(atribute32);

        final List<Attribute> attibutes3 = new ArrayList<>();
        attibutes3.add(atribute4);
        attibutes3.add(atribute41);
        attibutes3.add(atribute42);

        final List<Attribute> attibutes4 = new ArrayList<>();
        attibutes4.add(atribute5);

        final List<Attribute> attibutes5 = new ArrayList<>();
        attibutes5.add(atribute6);
        attibutes5.add(atribute61);
        attibutes5.add(atribute62);
        attibutes5.add(atribute63);

        final List<Attribute> attibutes6 = new ArrayList<>();
        attibutes6.add(atribute7);

        final List<Attribute> attibutes7 = new ArrayList<>();
        attibutes7.add(atribute8);
        attibutes7.add(atribute81);
        attibutes7.add(atribute82);
        attibutes7.add(atribute83);
        attibutes7.add(atribute84);
        attibutes7.add(atribute85);
        attibutes7.add(atribute86);
        attibutes7.add(atribute87);
        attibutes7.add(atribute88);
        attibutes7.add(atribute89);

        final List<Attribute> attibutes8 = new ArrayList<>();
        attibutes8.add(atribute9);
        attibutes8.add(atribute91);

        final List<Attribute> attibutes9 = new ArrayList<>();
        attibutes9.add(atribute10);

        final List<Attribute> attibutes10 = new ArrayList<>();

        final Form form1 = new Form(new Name("name1"), new Script("scripts/serv2solicitar.txt"), attibutes);
        final Form form2 = new Form(new Name("name2"), new Script("scripts/serv2aprovar.txt"), attibutes2);
        final Form form3 = new Form(new Name("name3"), new Script("scripts/serv4solicitar.txt"), attibutes3);
        final Form form4 = new Form(new Name("name4"), new Script("scripts/script1.txt"), attibutes4);
        final Form form5 = new Form(new Name("name5"), new Script("scripts/serv1solicitar.txt"), attibutes5);
        final Form form6 = new Form(new Name("name6"), new Script("scripts/serv1aprovar.txt"), attibutes6);
        final Form form7 = new Form(new Name("name7"), new Script("scripts/serv1realizar.txt"), attibutes7);
        final Form form8 = new Form(new Name("name8"), new Script("scripts/serv3solicitar.txt"), attibutes8);
        final Form form9 = new Form(new Name("name9"), new Script("scripts/serv3realizar.txt"), attibutes9);
        final Form form10 = new Form(new Name("name10"), new Script("scripts/script1.txt"), attibutes10);

        final Date date1 = new Date();

        final Script script1 = new Script("scripts/serv2tarefa.txt");
        final Script script2 = new Script("scripts/serv4tarefa.txt");
        final Script script3 = new Script("scripts/script2.txt");
        final Script script4 = new Script("scripts/script2.txt");

        registerServiceAutomatic(colab1, "code1", "Desconto", "Autorização para Aplicação de Desconto", "Autorização para Aplicação de Desconto", Type.Automatic, keywords1, "icone1", form1, form2, Catalog1, date1, WorkflowStatus.ACTIVE, script1, TypeCollaboratorApproval.CatalogResponsibleCollaborator);
        registerServiceAutomatic(colab2, "code2", "Cotação", "Requerer cotação para venda por grosso", "Requerer cotação para venda por grosso", Type.Automatic, keywords2, "icone2", form3, form4, Catalog1, date1, WorkflowStatus.ACTIVE, script2, null);
        registerServiceManual(colab1, "code3", "Ausência", "Pedido de Ausência", "Os colaboradores podem pedir uma ausência", Type.Manual, keywords3, "icone3", form5, form6, form7, Catalog2, date1, WorkflowStatus.ACTIVE,colabs1,null, TypeCollaboratorApproval.HierarchyCollaborator);
        registerServiceManual(colab2, "code4", "Residência", "Comunicação de Alteração de Residência ", "Os colaboradores podem alterar a residência", Type.Manual, keywords4, "icone4", form8, form9, form9, Catalog1, date1, WorkflowStatus.ACTIVE,colabs2,null,null);
        //registerServiceManual(null, "code5", "Reparacao", "Reparo ao servidor", "Reparação efetuada na servidor devido a danos causados", Type.Manual, keywords5, "icone5", form8, form9, form10, Catalog2, date1, WorkflowStatus.ACTIVE,null,teams2,null);
        //registerServiceManual(null, "code6", "Reparacao", "Reparo ao servidor", "Reparação efetuada na servidor devido a danos causados", Type.Manual, keywords6, "icone6", form8, form9, form10, Catalog1, date1, WorkflowStatus.ACTIVE,null,teams1,null);
        registerServiceIncomplete("code7", "Reparacao", Catalog1);
        registerServiceIncomplete("code8", "Reparacao", Catalog2);

        return false;
    }

    private void registerServiceAutomatic(final Collaborator colab, final String uniqueCode, final String title, final String briefDescription, final String completeDescription,
            final Type type, final List<String> keywords, final String icone, final Form forms, final Form forms2, final Catalog catalog, final Date startDate,
            final WorkflowStatus status, final Script script, final TypeCollaboratorApproval tca) {

        final SpecifyServiceController controller = new SpecifyServiceController();
        try {
            controller.specifyCollaborator(colab);
            controller.specifyUniqueCode(uniqueCode);
            controller.specifyTitle(title);
            controller.specifyBriefDescription(briefDescription);
            controller.specifyCompleteDescription(completeDescription);
            controller.specifyType(type);
            controller.specifyKeywords(keywords);
            controller.specifyIcone(icone);
            controller.specifyForms(forms);
            Workflow w = controller.createWorkflow(startDate, status);
            if(tca == null){
                w.tasks().add(controller.createAutomaticTask(TaskType.Realization, script, 1));
            }else {
                w.tasks().add(controller.createApprovalTask(TaskType.Approval, 0, forms2, tca));
                w.tasks().add(controller.createAutomaticTask(TaskType.Realization, script, 1));
            }
            controller.specifyWorkflow(w);
            Service service= controller.saveService();
            controller.specifyCatalog(catalog, service);


        } catch (final IntegrityViolationException | ConcurrencyException ex) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            LOGGER.warn("Assuming {} already exists (activate trace log for details)", ex.toString());
            LOGGER.trace("Assuming existing record", ex);
        }
    }

    private void registerServiceManual(final Collaborator colab, final String uniqueCode, final String title, final String briefDescription, final String completeDescription,
            final Type type, final List<String> keywords, final String icone, final Form forms, final Form forms2, final Form forms3, final Catalog catalog, final Date startDate,
            final WorkflowStatus status, final List<Collaborator> collaborators, final List<Team> teams, TypeCollaboratorApproval collabType) {

        final SpecifyServiceController controller = new SpecifyServiceController();
        try {
            controller.specifyCollaborator(colab);
            controller.specifyUniqueCode(uniqueCode);
            controller.specifyTitle(title);
            controller.specifyBriefDescription(briefDescription);
            controller.specifyCompleteDescription(completeDescription);
            controller.specifyType(type);
            controller.specifyKeywords(keywords);
            controller.specifyIcone(icone);
            controller.specifyForms(forms);
            Workflow w = controller.createWorkflow(startDate, status);
            if(collabType != null) {
                w.tasks().add(controller.createApprovalTask(TaskType.Approval, 0, forms2, collabType));
                if (teams == null) {
                    w.tasks().add(controller.createManualTask(TaskType.Realization, forms3, collaborators, 1));
                }
                if (collaborators == null) {
                    w.tasks().add(controller.createManualTask(TaskType.Realization, forms3, 1, teams));
                }
            }else{
                if (teams == null) {
                    w.tasks().add(controller.createManualTask(TaskType.Realization, forms3, collaborators, 0));
                }
                if (collaborators == null) {
                    w.tasks().add(controller.createManualTask(TaskType.Realization, forms3, 0, teams));
                }
            }
            controller.specifyWorkflow(w);
            Service service = controller.saveService();
            controller.specifyCatalog(catalog, service);


        } catch (final IntegrityViolationException | ConcurrencyException ex) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            LOGGER.warn("Assuming {} already exists (activate trace log for details)", ex.toString());
            LOGGER.trace("Assuming existing record", ex);
        }
    }

    private void registerServiceIncomplete(final String uniqueCode, final String title, final Catalog catalog) {

        final SpecifyServiceController controller = new SpecifyServiceController();
        try {
            controller.specifyUniqueCode(uniqueCode);
            controller.specifyTitle(title);
            Service service =  controller.saveServiceAsDraft();
            controller.specifyCatalog(catalog, service);

        } catch (final IntegrityViolationException | ConcurrencyException ex) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            LOGGER.warn("Assuming {} already exists (activate trace log for details)", ex.toString());
            LOGGER.trace("Assuming existing record", ex);
        }
    }
}
