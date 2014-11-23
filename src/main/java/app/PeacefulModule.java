package app;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

public class PeacefulModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(ConfigurationManager.class).in(Singleton.class);
    }
}
