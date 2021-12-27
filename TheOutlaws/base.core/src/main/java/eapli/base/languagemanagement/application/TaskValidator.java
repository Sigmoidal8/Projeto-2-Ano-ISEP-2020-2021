package eapli.base.languagemanagement.application;

import eapli.base.languagemanagement.domain.form.FormErrorHandling;
import eapli.base.languagemanagement.domain.task.AutomaticTaskLanguageLexer;
import eapli.base.languagemanagement.domain.task.AutomaticTaskLanguageParser;
import eapli.base.languagemanagement.domain.task.AutomaticTaskVisitor;
import eapli.base.requestmanagement.domain.Request;
import eapli.base.servicemanagement.domain.Script;
import eapli.base.taskmanagement.domain.AutomaticTask;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.misc.ParseCancellationException;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.tree.ParseTree;

/**
 * @author Raúl Coelho
 */
public class TaskValidator {

    public static boolean validateScript(Script script) {
        try {
            if (script.content() == null) {
                System.out.println("This script doesn't exist");
                return false;
            } else {
                CharStream charStream = CharStreams.fromString(script.content());
                AutomaticTaskLanguageLexer taskLexer = new AutomaticTaskLanguageLexer(charStream);
                taskLexer.removeErrorListeners();
                taskLexer.addErrorListener(FormErrorHandling.INSTANCE);
                CommonTokenStream commonTokenStream = new CommonTokenStream(taskLexer);
                AutomaticTaskLanguageParser taskParser = new AutomaticTaskLanguageParser(commonTokenStream);
                taskParser.removeErrorListeners();
                taskParser.addErrorListener(FormErrorHandling.INSTANCE);

                ParserRuleContext tree = taskParser.prog();
                return true;
            }
        } catch (ParseCancellationException ex) {
            System.out.println("This script is not valid");
            return false;
        }
    }

    public static boolean executeScript(Request request) {
        Script script = null;
        if (request.taskExecutions().size() == 1) {
            script = ((AutomaticTask) request.taskExecutions().get(0).task()).script();
        } else {
            script = ((AutomaticTask) request.taskExecutions().get(1).task()).script();
        }
        try {
            if (script.content().isBlank()) {
                return true;
            }

            AutomaticTaskLanguageLexer lexer = new AutomaticTaskLanguageLexer(CharStreams.fromString(script.content()));
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            AutomaticTaskLanguageParser parser = new AutomaticTaskLanguageParser(tokens);
            ParseTree tree = parser.prog();
            AutomaticTaskVisitor tskVisitor = new AutomaticTaskVisitor(request, new ArrayList<>(), new ArrayList<>());
            Object value = tskVisitor.visit(tree);

            if ((Boolean) value) {
                return true;
            } else {
                System.out.println("An error has ocurred");
                return false;
            }

        } catch (Exception ex) {
            System.out.println("Exception ocurred");
            return false;
        }

    }
}
