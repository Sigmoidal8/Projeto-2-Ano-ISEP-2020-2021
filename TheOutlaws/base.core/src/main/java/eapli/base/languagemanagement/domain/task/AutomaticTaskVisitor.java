/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.languagemanagement.domain.task;

import eapli.base.collaboratormanagement.aplication.SendEmailService;
import eapli.base.requestmanagement.domain.Request;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import eapli.base.requestmanagement.domain.RequestStatus;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author José Forno
 */
public class AutomaticTaskVisitor extends AutomaticTaskLanguageBaseVisitor {
    
    private Request request = null;

    private List<String> dataSearched;

    private List<Double> calculations;
    
    public AutomaticTaskVisitor(Request request, List<String>  dataSearched, List<Double> calculations){
        this.request= request;
        this.dataSearched = dataSearched;
        this.calculations = calculations;
    }
    
    @Override
    public Boolean visitProg(AutomaticTaskLanguageParser.ProgContext ctx){
        try{
            if(ctx.prog() != null){
                if((Boolean) this.visit(ctx.prog()) == false){
                    return false;
                }
            }

            if(ctx.email() != null){
               if((Boolean) this.visit(ctx.email()) == false){
                   return false;
               }
            }

            if(ctx.file() != null){
                if((Boolean) this.visit(ctx.file()) == false){
                    return false;
                }
            }

            if(ctx.calculation() != null){
                if((Boolean) this.visit(ctx.calculation()) == false){
                    return false;
                }
            }
            
            return true;
            
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean visitSearchfile(AutomaticTaskLanguageParser.SearchfileContext ctx){
        try{
            String fileLocation = (String) this.visit(ctx.filexml());

            Document document = getDocument(fileLocation);

            String path = (String) this.visit(ctx.filetext().get(0));

            path = "/" + path;

            String endPath = (String) this.visit(ctx.filetext().get(1));

            path = path + '/' + endPath;

            // Create XPathFactory object
            XPathFactory xpathFactory = XPathFactory.newInstance();

            // Create XPath object
            XPath xpath = xpathFactory.newXPath();

            List<String> values = new ArrayList<>();
            try
            {
                // Create XPathExpression object
                XPathExpression expr = xpath.compile(path);

                // Evaluate expression result on XML document
                NodeList nodes = (NodeList) expr.evaluate(document, XPathConstants.NODESET);

                for (int i = 0; i < nodes.getLength(); i++) {
                    values.add(nodes.item(i).getTextContent());
                }

                StringBuilder stringBuilder = new StringBuilder();
                for(String s : values){
                    stringBuilder.append(s);
                }

                String string = stringBuilder.toString();
                string.trim();

                dataSearched.add(string);
            } catch (XPathExpressionException e) {
                e.printStackTrace();
                return false;
            }

            return true;

        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean visitCalculationArithmetic(AutomaticTaskLanguageParser.CalculationArithmeticContext ctx){
        try {
            Double calculation = (Double) this.visit(ctx.arithmetic());
            calculations.add(calculation);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Double visitNumber(AutomaticTaskLanguageParser.NumberContext ctx){
        try{
            return Double.parseDouble(ctx.Number().toString());
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Double visitParentheses(AutomaticTaskLanguageParser.ParenthesesContext ctx){
        try{
            return (Double) this.visit(ctx.inner);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Double visitTextinfoArithmetic(AutomaticTaskLanguageParser.TextinfoArithmeticContext ctx){
        try{
            String data = (String) this.visit(ctx.textinfo());
            Double number = Double.parseDouble(data);
            return number;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Double visitMultiplicationOrDivision(AutomaticTaskLanguageParser.MultiplicationOrDivisionContext ctx){
        try{
            if (ctx.operator.getText().equals("*")) {
                return (Double) this.visit(ctx.left) * (Double) this.visit(ctx.right);
            }

            return (Double) this.visit(ctx.left) / (Double) this.visit(ctx.right);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Double visitAdditionOrSubtraction(AutomaticTaskLanguageParser.AdditionOrSubtractionContext ctx) {
        try {
            if (ctx.operator.getText().equals("+")) {
                return (Double) this.visit(ctx.left) + (Double) this.visit(ctx.right);
            }

            return (Double) this.visit(ctx.left) - (Double) this.visit(ctx.right);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Double visitPower(AutomaticTaskLanguageParser.PowerContext ctx){
        try{
            return Math.pow((Double) this.visit(ctx.left), (Double) this.visit(ctx.right));
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }



    @Override
    public String visitFileXmlWithoutSlash(AutomaticTaskLanguageParser.FileXmlWithoutSlashContext ctx){
        String path = (String) this.visit(ctx.fileName());
        path = path + ".xml";
        return path;
    }

    @Override
    public String visitFileXmlWithSlash(AutomaticTaskLanguageParser.FileXmlWithSlashContext ctx){
        String path = (String) this.visit(ctx.filexml());
        path = path + "/";
        String pathname = (String) this.visit(ctx.fileName());
        path = path + pathname + ".xml";
        return path;
    }

    @Override
    public String visitFileNameWord(AutomaticTaskLanguageParser.FileNameWordContext ctx){
        return ctx.Word().toString();
    }

    @Override
    public String visitFileNameWithWord(AutomaticTaskLanguageParser.FileNameWithWordContext ctx){
        String string = (String) this.visit(ctx.fileName());
        String name = ctx.Word().toString();

        string = string + name;
        return string;
    }

    @Override
    public String visitFileNameWithNumber(AutomaticTaskLanguageParser.FileNameWithNumberContext ctx){
        String string = (String) this.visit(ctx.fileName());
        String number = ctx.Number().toString();

        string +=number;
        return number;
    }

    @Override
    public String visitFileNameNumber(AutomaticTaskLanguageParser.FileNameNumberContext ctx){
        return ctx.Number().toString();
    }

    @Override
    public String visitFiletextFileTextText(AutomaticTaskLanguageParser.FiletextFileTextTextContext ctx){
        String path = (String) this.visit(ctx.filetext());
        String string = (String) this.visit(ctx.text());
        path = path + string;
        return path;
    }

    @Override
    public String visitFiletextDiv(AutomaticTaskLanguageParser.FiletextDivContext ctx){
        String path = (String) this.visit(ctx.filetext());
        path = path+"/";
        return path;
    }

    @Override
    public String visitFiletextFormValidation(AutomaticTaskLanguageParser.FiletextFormValidationContext ctx){
        String path = (String) this.visit(ctx.filetext());

        String value1 = (String) this.visit(ctx.textinfo().get(0));
        String value2 = (String) this.visit(ctx.textinfo().get(1));

        String string = "["+value1+"="+value2+"]";
        path = path + string;
        return path;
    }

    @Override
    public String visitEmailTextWithTextInfo(AutomaticTaskLanguageParser.EmailTextWithTextInfoContext ctx){
        try{
            String string = (String) this.visit(ctx.emailtext());
            String word = (String) this.visit(ctx.textinfo());

            string = string + " " + word;
            return string;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String visitEmailTextTextInfo(AutomaticTaskLanguageParser.EmailTextTextInfoContext ctx){
        try{
            return (String) this.visit(ctx.textinfo());
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String visitTextInfoFormResponse(AutomaticTaskLanguageParser.TextInfoFormResponseContext ctx){
        try{
            if(ctx.formResponse().approval()!= null){
                int index = Integer.parseInt(ctx.formResponse().approval().Number().toString());
                String awnser = request.taskExecutions().get(0).formResponse().get(index - 1);
                return awnser;
            }

            if(ctx.formResponse().request() != null){
                int index = Integer.parseInt(ctx.formResponse().request().Number().toString());
                String awnser = request.formResponse().get(index - 1);
                return awnser;
            }
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String visitTextInfoFileSearch(AutomaticTaskLanguageParser.TextInfoFileSearchContext ctx){
        try{
            int index = Integer.parseInt(ctx.fileSearch().Number().toString());
            String awnser = dataSearched.get(index - 1);
            return awnser;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String visitTextInfoCalculationSearch(AutomaticTaskLanguageParser.TextInfoCalculationSearchContext ctx){
        try{
            int index = Integer.parseInt(ctx.calculationSearch().Number().toString());
            String awnser = calculations.get(index-1).toString();
            return awnser;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String visitTextInfoText(AutomaticTaskLanguageParser.TextInfoTextContext ctx){
        try{
            return (String) this.visit(ctx.text());
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String visitFiletextText(AutomaticTaskLanguageParser.FiletextTextContext ctx){
        String word = (String) this.visit(ctx.text());
        return word;
    }

    @Override
    public String visitTextDecision(AutomaticTaskLanguageParser.TextDecisionContext ctx){
        System.out.println("hipotese1");
        System.out.println(request.status().toString());
        if(request.status().equals(RequestStatus.Aproved)){
            return "approved";
        }else if(request.status().equals(RequestStatus.Rejected)){
            return "rejected";
        }
        return "empty";
    }

    @Override
    public String visitTextNumber(AutomaticTaskLanguageParser.TextNumberContext ctx){
        return ctx.Number().toString();
    }

    @Override
    public String visitTextTextDecision(AutomaticTaskLanguageParser.TextTextDecisionContext ctx){
        String text = (String) this.visit(ctx.text());
        String approval = null;
        System.out.println("Hipotese2");
        System.out.println(request.status().toString());
        if(request.status().equals(RequestStatus.Aproved)){
            approval = "approved";
        }else if(request.status().equals(RequestStatus.Rejected)){
            approval = "rejected";
        }

        text = text + " " + approval;
        return text;
    }

    @Override
    public String visitTextTextWord(AutomaticTaskLanguageParser.TextTextWordContext ctx){
        String text = (String) this.visit(ctx.text());
        String word = ctx.Word().toString();

        text = text + " " + word;
        return text;
    }

    @Override
    public String visitTextWord(AutomaticTaskLanguageParser.TextWordContext ctx){
        String word = ctx.Word().toString();
        return word;
    }

    @Override
    public String visitTextTextNumber(AutomaticTaskLanguageParser.TextTextNumberContext ctx){
        String text = (String) this.visit(ctx.text());
        String number = ctx.Number().toString();

        text = text + " " + number;
        return text;
    }

    public String visitFileXmlFileName(AutomaticTaskLanguageParser.FileXmlFileNameContext ctx){
        return (String) this.visit(ctx.fileName());
    }

    @Override
    public Boolean visitSendEmail(AutomaticTaskLanguageParser.SendEmailContext ctx) {
        try{
            String email= request.collaborator().email().toString();
            String header= (String) this.visit(ctx.text());
            String text = (String) this.visit(ctx.emailtext());

            SendEmailService sendEmailService = new SendEmailService();
            sendEmailService.sendEmail(email,header,text);
            return true;

        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    private static Document getDocument(String fileName) throws Exception
    {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(fileName);
            return doc;
        }catch (ParserConfigurationException | SAXException | IOException e){
            e.printStackTrace();
            return null;
        }
    }
    
    
}
