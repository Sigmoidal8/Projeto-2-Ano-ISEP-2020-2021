package eapli.base.languagemanagement.domain.form;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.ParseCancellationException;

import java.util.BitSet;

/**
 * @author Raúl Coelho
 */
public class FormErrorHandling extends BaseErrorListener {

    public static final FormErrorHandling INSTANCE = new FormErrorHandling();

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object o, int i, int i1, String s, RecognitionException e)
            throws ParseCancellationException {
        throw new ParseCancellationException("line " + i + ":" + i1 + " " + s);
    }
}

