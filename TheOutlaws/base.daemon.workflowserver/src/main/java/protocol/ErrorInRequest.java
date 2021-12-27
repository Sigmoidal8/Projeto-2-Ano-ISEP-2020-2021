package protocol;

/**
 * @author Raúl Coelho
 */
public class ErrorInRequest extends BaseErrorRequest{

    public ErrorInRequest(final int request, final String errorDescription) {
        super(request, errorDescription);
    }

    @Override
    protected String messageType() {
        return "ERROR_IN_REQUEST";
    }
}

