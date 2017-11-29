package com.roi.goliath.device;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ExceptionHandlerController implements ExceptionMapper<Throwable> {

    private static final Logger LOG = Logger.getAnonymousLogger();

    /**
     *
     * @param e message error
     * @return
     */
    @Override
    public Response toResponse(Throwable e) {
        JsonMessage message = new JsonMessage(e.toString());
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        String stackTrace = sw.toString();
        LOG.log(Level.WARNING, () -> stackTrace);
        return Response.serverError()
                .entity(message)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

}
