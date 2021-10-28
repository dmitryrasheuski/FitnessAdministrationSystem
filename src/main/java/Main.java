import com.google.inject.Guice;
import javafx.application.Application;
import javafx.stage.Stage;
import ui.UiManager;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Guice.createInjector()
                .getInstance(UiManager.class)
                .start(primaryStage);
    }
}
