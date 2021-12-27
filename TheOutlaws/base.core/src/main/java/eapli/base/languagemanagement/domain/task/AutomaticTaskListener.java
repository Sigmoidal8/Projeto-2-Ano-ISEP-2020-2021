/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.languagemanagement.domain.task;

import eapli.base.collaboratormanagement.aplication.SendEmailService;
import eapli.base.requestmanagement.domain.Request;
import eapli.base.requestmanagement.domain.RequestStatus;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author migue
 */
public class AutomaticTaskListener extends AutomaticTaskLanguageBaseListener {

    private Request request = null;
    private List<String> dataSearched;
    private List<Double> calculations;
    private final Stack<Integer> stack = new Stack<>();
    private final Stack<Double> stackDouble = new Stack<>();
    private final Stack<Boolean> stackBoolean = new Stack<>();
    private final Stack<String> stackString = new Stack<>();

    public AutomaticTaskListener(Request request, List<String> dataSearched, List<Double> calculations) {
        this.request = request;
        this.dataSearched = dataSearched;
        this.calculations = calculations;
    }

    @Override
    public void exitProg(AutomaticTaskLanguageParser.ProgContext ctx) {
        try {
            Boolean prog = stackBoolean.pop();
            Boolean email = stackBoolean.pop();
            Boolean file = stackBoolean.pop();
            Boolean calculation = stackBoolean.pop();

            if (prog != null) {
                if (prog == false) {
                    stackBoolean.push(false);
                }
            }

            if (email != null) {
                if (email == false) {
                    stackBoolean.push(false);
                }
            }

            if (file != null) {
                if (file == false) {
                    stackBoolean.push(false);
                }
            }

            if (calculation != null) {
                if (calculation == false) {
                    stackBoolean.push(false);
                }
            }

            stackBoolean.push(true);

        } catch (Exception ex) {
            ex.printStackTrace();
            stackBoolean.push(false);
        }
    }

    @Override
    public void exitSearchfile(AutomaticTaskLanguageParser.SearchfileContext ctx) {
        try {
            String fileLocation = stackString.pop();

            Document document = getDocument(fileLocation);

            String path = stackString.pop();

            path = "/" + path;

            String endPath = stackString.pop();

            path = path + '/' + endPath;

            // Create XPathFactory object
            XPathFactory xpathFactory = XPathFactory.newInstance();

            // Create XPath object
            XPath xpath = xpathFactory.newXPath();

            List<String> values = new ArrayList<>();
            try {
                // Create XPathExpression object
                XPathExpression expr = xpath.compile(path);

                // Evaluate expression result on XML document
                NodeList nodes = (NodeList) expr.evaluate(document, XPathConstants.NODESET);

                for (int i = 0; i < nodes.getLength(); i++) {
                    values.add(nodes.item(i).getTextContent());
                }

                StringBuilder stringBuilder = new StringBuilder();
                for (String s : values) {
                    stringBuilder.append(s);
                }

                String string = stringBuilder.toString();
                string.trim();

                dataSearched.add(string);
            } catch (XPathExpressionException e) {
                e.printStackTrace();
                stackBoolean.push(false);
            }

            stackBoolean.push(true);

        } catch (Exception ex) {
            ex.printStackTrace();
            stackBoolean.push(false);
        }
    }

    @Override
    public void exitCalculationArithmetic(AutomaticTaskLanguageParser.CalculationArithmeticContext ctx) {
        try {
            Double calculation = stackDouble.pop();
            calculations.add(calculation);
            stackBoolean.push(true);
        } catch (Exception e) {
            e.printStackTrace();
            stackBoolean.push(false);
        }
    }

    @Override
    public void exitNumber(AutomaticTaskLanguageParser.NumberContext ctx) {
        try {
            stackDouble.push(Double.parseDouble(ctx.Number().toString()));
        } catch (Exception e) {
            e.printStackTrace();
            stackDouble.push(null);
        }
    }

    @Override
    public void exitParentheses(AutomaticTaskLanguageParser.ParenthesesContext ctx) {
        try {
            double result = stackDouble.pop();
            stackDouble.push(result);
        } catch (Exception e) {
            e.printStackTrace();
            stackDouble.push(null);
        }
    }

    @Override
    public void exitTextinfoArithmetic(AutomaticTaskLanguageParser.TextinfoArithmeticContext ctx) {
        try {
            String data = stackString.pop();
            Double number = Double.parseDouble(data);
            stackDouble.push(number);
        } catch (Exception e) {
            e.printStackTrace();
            stackDouble.push(null);
        }
    }

    @Override
    public void exitMultiplicationOrDivision(AutomaticTaskLanguageParser.MultiplicationOrDivisionContext ctx) {
        try {
            double result1 = stackDouble.pop();
            double result2 = stackDouble.pop();
            if (ctx.operator.getText().equals("*")) {
                stackDouble.push(result1 * result2);
            }
            stackDouble.push(result1 / result2);
        } catch (Exception e) {
            e.printStackTrace();
            stackDouble.push(null);
        }
    }

    @Override
    public void exitAdditionOrSubtraction(AutomaticTaskLanguageParser.AdditionOrSubtractionContext ctx) {
        try {
            double result1 = stackDouble.pop();
            double result2 = stackDouble.pop();
            if (ctx.operator.getText().equals("+")) {
                stackDouble.push(result1 + result2);
            }

            stackDouble.push(result1 - result2);
        } catch (Exception e) {
            e.printStackTrace();
            stackDouble.push(null);
        }
    }

    @Override
    public void exitPower(AutomaticTaskLanguageParser.PowerContext ctx) {
        try {
            double result1 = stackDouble.pop();
            double result2 = stackDouble.pop();
            stackDouble.push(Math.pow(result1, result2));
        } catch (Exception e) {
            e.printStackTrace();
            stackDouble.push(null);
        }
    }

    @Override
    public void exitFileXmlWithoutSlash(AutomaticTaskLanguageParser.FileXmlWithoutSlashContext ctx) {
        String path = stackString.pop();
        path = path + ".xml";
        stackString.push(path);
    }

    @Override
    public void exitFileXmlWithSlash(AutomaticTaskLanguageParser.FileXmlWithSlashContext ctx) {
        String path = stackString.pop();
        path = path + "/";
        String pathname = stackString.pop();
        path = path + pathname + ".xml";
        stackString.push(path);
    }

    @Override
    public void exitFileNameWord(AutomaticTaskLanguageParser.FileNameWordContext ctx) {
        stackString.push(stackString.pop());
    }

    @Override
    public void exitFileNameWithWord(AutomaticTaskLanguageParser.FileNameWithWordContext ctx) {
        String string = stackString.pop();
        String name = ctx.Word().toString();

        string = string + name;
        stackString.push(string);
    }

    @Override
    public void exitFileNameWithNumber(AutomaticTaskLanguageParser.FileNameWithNumberContext ctx) {
        String string = stackString.pop();
        String number = ctx.Number().toString();

        string += number;
        stackString.push(string);
    }

    @Override
    public void exitFileNameNumber(AutomaticTaskLanguageParser.FileNameNumberContext ctx) {
        stackString.push(stackString.pop());
    }

    @Override
    public void exitFiletextFileTextText(AutomaticTaskLanguageParser.FiletextFileTextTextContext ctx) {
        String path = stackString.pop();
        String string = stackString.pop();
        path = path + string;
        stackString.push(path);
    }

    @Override
    public void exitFiletextDiv(AutomaticTaskLanguageParser.FiletextDivContext ctx) {
        String path = stackString.pop();
        path = path + "/";
        stackString.push(path);
    }

    @Override
    public void exitFiletextFormValidation(AutomaticTaskLanguageParser.FiletextFormValidationContext ctx) {
        String path = stackString.pop();

        String value1 = stackString.pop();
        String value2 = stackString.pop();

        String string = "[" + value1 + "=" + value2 + "]";
        path = path + string;
        stackString.push(path);
    }

    @Override
    public void exitEmailTextWithTextInfo(AutomaticTaskLanguageParser.EmailTextWithTextInfoContext ctx) {
        try {
            String string = stackString.pop();
            String word = stackString.pop();

            string = string + " " + word;
            stackString.push(string);
        } catch (Exception e) {
            e.printStackTrace();
            stackDouble.push(null);
        }
    }

    @Override
    public void exitEmailTextTextInfo(AutomaticTaskLanguageParser.EmailTextTextInfoContext ctx) {
        try {
           stackString.push(stackString.pop());
        } catch (Exception e) {
            e.printStackTrace();
            stackDouble.push(null);
        }
    }

    @Override
    public void exitTextInfoFormResponse(AutomaticTaskLanguageParser.TextInfoFormResponseContext ctx) {
        try {
            if (ctx.formResponse().approval() != null) {
                int index = Integer.parseInt(ctx.formResponse().approval().Number().toString());
                String awnser = request.taskExecutions().get(0).formResponse().get(index - 1);
                stackString.push(awnser);
            }

            if (ctx.formResponse().request() != null) {
                int index = Integer.parseInt(ctx.formResponse().request().Number().toString());
                String awnser = request.formResponse().get(index - 1);
                stackString.push(awnser);
            }
            stackDouble.push(null);
        } catch (Exception e) {
            e.printStackTrace();
            stackDouble.push(null);
        }
    }

    @Override
    public void exitTextInfoFileSearch(AutomaticTaskLanguageParser.TextInfoFileSearchContext ctx) {
        try {
            int index = Integer.parseInt(ctx.fileSearch().Number().toString());
            String awnser = dataSearched.get(index - 1);
            stackString.push(awnser);
        } catch (Exception e) {
            e.printStackTrace();
            stackDouble.push(null);
        }
    }

    @Override
    public void exitTextInfoCalculationSearch(AutomaticTaskLanguageParser.TextInfoCalculationSearchContext ctx) {
        try {
            int index = Integer.parseInt(ctx.calculationSearch().Number().toString());
            String awnser = calculations.get(index - 1).toString();
            stackString.push(awnser);
        } catch (Exception e) {
            e.printStackTrace();
            stackDouble.push(null);
        }
    }

    @Override
    public void exitTextInfoText(AutomaticTaskLanguageParser.TextInfoTextContext ctx) {
        try {
            stackString.push(stackString.pop());
        } catch (Exception e) {
            e.printStackTrace();
            stackDouble.push(null);
        }
    }

    @Override
    public void exitFiletextText(AutomaticTaskLanguageParser.FiletextTextContext ctx) {
        String word = stackString.pop();
        stackString.push(word);
    }

    @Override
    public void exitTextDecision(AutomaticTaskLanguageParser.TextDecisionContext ctx) {
        System.out.println("hipotese1");
        System.out.println(request.status().toString());
        if (request.status().equals(RequestStatus.Aproved)) {
            stackString.push("approved");
        } else if (request.status().equals(RequestStatus.Rejected)) {
            stackString.push("rejected");
        }
        stackString.push("empty");
    }

    @Override
    public void exitTextNumber(AutomaticTaskLanguageParser.TextNumberContext ctx) {
        stackString.push(stackString.pop());
    }

    @Override
    public void exitTextTextDecision(AutomaticTaskLanguageParser.TextTextDecisionContext ctx) {
        String text = stackString.pop();
        String approval = null;
        System.out.println("Hipotese2");
        System.out.println(request.status().toString());
        if (request.status().equals(RequestStatus.Aproved)) {
            approval = "approved";
        } else if (request.status().equals(RequestStatus.Rejected)) {
            approval = "rejected";
        }

        text = text + " " + approval;
       stackString.push(text);
    }

    @Override
    public void exitTextTextWord(AutomaticTaskLanguageParser.TextTextWordContext ctx) {
        String text = stackString.pop();
        String word = ctx.Word().toString();

        text = text + " " + word;
        stackString.push(text);
    }

    @Override
    public void exitTextWord(AutomaticTaskLanguageParser.TextWordContext ctx) {
        String word = ctx.Word().toString();
        stackString.push(word);
    }

    @Override
    public void exitTextTextNumber(AutomaticTaskLanguageParser.TextTextNumberContext ctx) {
        String text = stackString.pop();
        String number = ctx.Number().toString();

        text = text + " " + number;
         stackString.push(text);
    }

    public void exitFileXmlFileName(AutomaticTaskLanguageParser.FileXmlFileNameContext ctx) {
        stackString.push(stackString.pop());
    }

    @Override
    public void exitSendEmail(AutomaticTaskLanguageParser.SendEmailContext ctx) {
        try {
            String email = request.collaborator().email().toString();
            String header = stackString.pop();
            String text = stackString.pop();

            SendEmailService sendEmailService = new SendEmailService();
            sendEmailService.sendEmail(email, header, text);
            stackBoolean.push(true);

        } catch (Exception ex) {
            ex.printStackTrace();
            stackBoolean.push(false);
        }
    }

    private static Document getDocument(String fileName) throws Exception {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(fileName);
            return doc;
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
