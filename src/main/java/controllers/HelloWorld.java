package controllers;

import models.Contact;
import models.Message;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
import restlet.jackson.JacksonRepresentation;

import java.io.IOException;


public class HelloWorld extends ServerResource{

    @Get("json")
    public Contact sayHello() {
        return new Contact("Matthew");
    }

    @Post("json")
    public JacksonRepresentation echo(JacksonRepresentation<Message> payload) throws IOException {
        Message message = payload.getObject(Message.class);
        return new JacksonRepresentation<>(new Message(message.getMessage()));
    }
}
