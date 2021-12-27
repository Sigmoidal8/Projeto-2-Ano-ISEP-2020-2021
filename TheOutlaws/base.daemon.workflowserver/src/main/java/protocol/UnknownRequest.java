package protocol;

/**
 * @author Raúl Coelho
 */
public class UnknownRequest extends BaseErrorRequest{

    public UnknownRequest(final int inputLine) {
        super(inputLine);
    }

    @Override
    protected String messageType() {
        return "UNKNOWN_REQUEST";
    }
}
