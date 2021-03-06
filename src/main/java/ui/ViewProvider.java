package ui;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import lombok.RequiredArgsConstructor;
import ui.view.ViewController;

import java.io.IOException;

@Singleton
@RequiredArgsConstructor(onConstructor_ = @Inject)
class ViewProvider {
    private final Injector injector;

    public Pane getRootView() throws IOException {
        FXMLLoader loader = new FXMLLoader(ClassLoader.getSystemResource("view/root.fxml"));
        loader.setControllerFactory(injector::getInstance);
        return loader.load();
    }

    public <T extends ViewController> T create(Class<T> viewControllerClazz) throws IOException {
        T viewController = injector.getInstance(viewControllerClazz);
        FXMLLoader loader = new FXMLLoader(ClassLoader.getSystemResource(viewController.getViewPath()));
        loader.setControllerFactory(c -> viewController);
        loader.load();
        return viewController;
    }
}
