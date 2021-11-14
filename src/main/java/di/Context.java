package di;

import com.google.inject.Guice;
import com.google.inject.Injector;
import ui.UiManager;

public class Context {
    private final Injector injector =  Guice.createInjector();

    public UiManager getUiManage() {
        return injector.getInstance(UiManager.class);
    }
}
