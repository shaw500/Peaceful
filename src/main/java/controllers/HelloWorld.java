package controllers;

import models.Contact;
import models.Message;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import java.io.IOException;


public class HelloWorld extends ServerResource{

    @Get("json")
    public Contact sayHello() {
        return new Contact("Matthew");
    }

    @Post("json")
    public Message echo(Message message) throws IOException {
        return new Message(message.getMessage());
    }
}
