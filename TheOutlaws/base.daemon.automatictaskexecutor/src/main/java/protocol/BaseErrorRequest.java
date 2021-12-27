package protocol;

import eapli.framework.csv.CsvRecord;

public abstract class BaseErrorRequest extends TaskExecutorProtocolRequest{
    private final String errorDescription;

    protected BaseErrorRequest(final int request, final String errorDescription) {
        super((TaskExecutorController) null, request);
        this.errorDescription = errorDescription;
    }

    protected BaseErrorRequest(final int request) {
        super((TaskExecutorController) null, request);
        this.errorDescription = null;
    }

    @Override
    public String execute() {
        // nothing to do, just build the response
        return buildResponse();
    }

    protected String buildResponse() {
        final Object[] fields = {
                messageType(),
                request,
                errorDescription
        };
        final boolean[] mask = { false, true, true };
        return CsvRecord.valueOf(fields, mask).toString() + "\n";
    }

    protected abstract String messageType();
}
