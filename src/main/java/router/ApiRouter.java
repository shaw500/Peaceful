package router;

import com.google.inject.Injector;
import controllers.HelloWorld;
import org.restlet.Application;
import org.restlet.Context;
import org.restlet.Restlet;
import org.restlet.resource.Directory;
import org.restlet.routing.Router;
import restlet.guice.GuiceRouter;

public class ApiRouter extends GuiceRouter {

    public ApiRouter(Injector injector, Context context) {
        super(injector, context);
    }

    @Override
    protected void attachRoutes() {
        attach("/hello", HelloWorld.class);
        attach("/echo", HelloWorld.class);
    }
}
