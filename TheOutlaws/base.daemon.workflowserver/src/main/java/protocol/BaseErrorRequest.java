package protocol;

import eapli.base.dashboardmanagement.application.DashboardController;
import eapli.framework.csv.CsvRecord;

/**
 * @author Raúl Coelho
 */
public abstract class BaseErrorRequest extends RequestingProtocolRequest{
    private final String errorDescription;

    protected BaseErrorRequest(final int request, final String errorDescription) {
        super((DashboardController) null, request);
        this.errorDescription = errorDescription;
    }

    protected BaseErrorRequest(final int request) {
        super((DashboardController) null, request);
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
