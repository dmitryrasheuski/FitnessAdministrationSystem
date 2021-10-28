package ui;

import com.google.inject.Inject;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;

import javax.inject.Singleton;
import java.io.IOException;

@Singleton
@RequiredArgsConstructor(onConstructor_ = @Inject)
public class UiManager {
    private final ViewProvider viewProvider;
    private Pane workspaceContainer;

    public void start(Stage primaryStage) throws IOException {
        Pane rootView = viewProvider.getRootView();

        workspaceContainer = (Pane) rootView.getChildren().stream()
                .filter(node -> "workspaceContainer".equals(node.getId()))
                .findFirst().orElseThrow(() -> new IllegalArgumentException("Unable to find workspaceContainer"));

        primaryStage.setScene(new Scene(rootView));
        primaryStage.setTitle("Fitness");

        primaryStage.show();
    }

    private void switchWorkspace(Pane workspace) {
        workspaceContainer.getChildren().clear();
        workspaceContainer.getChildren().add(workspace);
    }
}
