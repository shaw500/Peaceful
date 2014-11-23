package restlet.guice;

import app.ConfigurationManager;
import com.google.inject.Injector;
import org.restlet.Context;
import org.restlet.resource.Finder;
import org.restlet.resource.ServerResource;
import org.restlet.routing.Router;

public abstract class GuiceRouter extends Router {
    private Injector injector;
    private ConfigurationManager configurationManager;

    public GuiceRouter(Injector injector, Context context) {
        super(context);

        this.injector = injector;
        configurationManager = injector.getInstance(ConfigurationManager.class);

        attachRoutes();
    }

    @Override
    public Finder createFinder(Class<? extends ServerResource> targetClass) {
        return new GuiceFinder(injector, getContext(), targetClass);
    }

    protected abstract void attachRoutes();

    protected Injector getInjector() {
        return injector;
    }

    public ConfigurationManager getConfigurationManager() {
        return configurationManager;
    }
}

