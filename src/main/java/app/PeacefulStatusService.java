package app;

import Exceptions.ExceptionView;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.data.MediaType;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.service.StatusService;
import restlet.jackson.JacksonRepresentation;

public class PeacefulStatusService extends StatusService {

    @Override
    public Status getStatus(Throwable throwable, Request request, Response response) {
        return new Status(Status.CLIENT_ERROR_BAD_REQUEST, throwable, "Some peaceful error has occurred...");
    }

    @Override
    public Representation getRepresentation(Status status, Request request, Response response) {
        ExceptionView view = new ExceptionView(status.getThrowable());
        return new JacksonRepresentation<>(MediaType.APPLICATION_JSON, view);
    }
}
