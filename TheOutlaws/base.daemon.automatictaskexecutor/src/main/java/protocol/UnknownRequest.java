package protocol;

public class UnknownRequest extends BaseErrorRequest{

    public UnknownRequest(final int inputLine) {
        super(inputLine);
    }

    @Override
    protected String messageType() {
        return "UNKNOWN_REQUEST";
    }
}
