package router;

import app.ConfigurationManager;
import com.google.inject.Injector;
import org.restlet.Context;
import org.restlet.resource.Directory;
import restlet.guice.GuiceRouter;

public class RootRouter extends GuiceRouter {

    public RootRouter(Injector injector, Context context) {
        super(injector, context);
    }

    @Override
    protected void attachRoutes() {
        attach("/api", new ApiRouter(getInjector(), getContext()));

        Directory dir = getStaticContentDirectory();
        attach(dir);
    }

    private Directory getStaticContentDirectory() {
        String root = getConfigurationManager().getWebappRoot();

        final Directory dir = new Directory(getContext(), root);
        dir.setDeeplyAccessible(true);
        dir.setListingAllowed(false);
        dir.setNegotiatingContent(false);
        return dir;
    }
}
