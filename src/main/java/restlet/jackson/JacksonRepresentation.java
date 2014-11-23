package restlet.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.restlet.data.MediaType;
import org.restlet.representation.Representation;

import java.io.IOException;

public class JacksonRepresentation<T> extends org.restlet.ext.jackson.JacksonRepresentation<T> {
    public JacksonRepresentation(MediaType mediaType, T object) {
        super(mediaType, object);
    }

    public JacksonRepresentation(Representation representation, Class<T> objectClass) {
        super(representation, objectClass);
    }

    public JacksonRepresentation(T object) {
        super(object);
    }

    @Override
    protected ObjectMapper createObjectMapper() {
        ObjectMapper objectMapper = super.createObjectMapper();
        configure(objectMapper);
        return objectMapper;
    }

    private void configure(ObjectMapper objectMapper) {
    }

    public <O> O getObject(Class<O> objectClass) throws IOException {
        setObjectClass((Class<T>) objectClass);
        return (O) getObject();
    }
}