package app;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.restlet.Application;
import org.restlet.Component;
import org.restlet.data.Protocol;
import org.restlet.routing.Router;
import restlet.jackson.JacksonConverter;
import restlet.RestletUtils;
import router.RootRouter;

public class Peaceful {

    private Component component;

    public Peaceful() {
        component = new Component();
        component.getServers().add(Protocol.HTTP, 80);
        component.getClients().add(Protocol.FILE);
        component.getClients().add(Protocol.CLAP);

        Injector injector = Guice.createInjector(new PeacefulModule());
        Router rootRouter = new RootRouter(injector, component.getContext().createChildContext());

        component.getDefaultHost().attach(rootRouter);

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
