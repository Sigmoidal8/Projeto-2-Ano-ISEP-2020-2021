package eapli.base.languagemanagement.application;

import eapli.base.languagemanagement.domain.form.FormErrorHandling;
import eapli.base.languagemanagement.domain.form.FormValidationLexer;
import eapli.base.languagemanagement.domain.form.FormValidationParser;
import eapli.base.languagemanagement.domain.form.FormVisitor;
import eapli.base.servicemanagement.domain.Attribute;
import eapli.base.servicemanagement.domain.Script;
import javassist.compiler.SyntaxError;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;
import java.util.List;

/**
 * @author Raúl Coelho
 */
public class FormValidator {

    public static boolean validateScript(Script script)  {
        try {
            if(script.content() == null){
                System.out.println("This script doesn't exist");
                return false;
            }else {
                CharStream charStream = CharStreams.fromString(script.content());
                FormValidationLexer formLexer = new FormValidationLexer(charStream);
                formLexer.removeErrorListeners();
                formLexer.addErrorListener(FormErrorHandling.INSTANCE);
                CommonTokenStream commonTokenStream = new CommonTokenStream(formLexer);
                FormValidationParser formParser = new FormValidationParser(commonTokenStream);
                formParser.removeErrorListeners();
                formParser.addErrorListener(FormErrorHandling.INSTANCE);

                ParserRuleContext tree = formParser.prog();
                return true;
            }
        } catch (ParseCancellationException ex){
            System.out.println("This script is not valid");
            return false;
        }
    }

    public static boolean verifyData(Script script, List<String> requestData, List<Attribute> requestAttribute){
        try{
            if(script.content().isBlank()){
                return true;
            }
            FormValidationLexer lexer = new FormValidationLexer(CharStreams.fromString(script.content()));
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            FormValidationParser parser = new FormValidationParser(tokens);
            ParseTree tree = parser.prog(); // parse
            FormVisitor form = new FormVisitor(requestData, requestAttribute);
            Object value = form.visit(tree);


            if((Boolean) value){
                return true;
            }else{
                System.out.println("There was invalid awnsers, insert the data again");
                return false;
            }
        }catch(Exception ex){
            System.out.println("There was an error");
            return false;
        }
    }


}
