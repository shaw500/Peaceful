package app;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.restlet.Component;
import org.restlet.Context;
import org.restlet.data.Protocol;
import org.restlet.routing.Router;
import restlet.RestletUtils;
import restlet.jackson.JacksonConverter;
import router.RootRouter;

public class Peaceful {

    private Component component;

    public Peaceful() {
        component = new Component();
        component.getServers().add(Protocol.HTTP, 80);
        component.getClients().add(Protocol.FILE);

        Injector injector = Guice.createInjector(new PeacefulModule());

        Context childContext = component.getContext().createChildContext();

        component.setStatusService(new PeacefulStatusService());

        Router router = new RootRouter(injector, childContext);
        component.getDefaultHost().attach(router);

        RestletUtils.replaceConverter(org.restlet.ext.jackson.JacksonConverter.class, new JacksonConverter());
    }

    public static void main(String[] args) throws Exception {
        new Peaceful().run();
    }

    private void run() throws Exception {
        component.start();
        System.out.println("Server has started.");
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {
                try {
                    component.stop();
                    System.out.println("Server has stopped.");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
