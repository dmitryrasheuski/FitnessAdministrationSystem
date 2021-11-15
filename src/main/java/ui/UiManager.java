package ui;

import com.google.inject.Inject;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;
import model.Client;
import ui.view.client.ViewControllerClients;
import ui.view.client.ViewControllerNewClient;
import ui.view.group.ViewControllerGroupLog;

import javax.inject.Singleton;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;

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

        openGroupLogWorkspace();
        primaryStage.show();
    }

    public void openGroupLogWorkspace() {
        ViewControllerGroupLog controller;
        try {
            controller = viewProvider.create(ViewControllerGroupLog.class);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        switchWorkspace(controller.getServicesView());
    }

    public void openClientWorkspace() {
        ViewControllerClients controller;
        try {
            controller = viewProvider.create(ViewControllerClients.class);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        switchWorkspace(controller.getServicesView());
    }

    public void openNewClientDialog(CompletableFuture<Client> closeCallback) {
        ViewControllerNewClient controller;
        try {
            controller = viewProvider.create(ViewControllerNewClient.class);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Stage stage = new Stage();
        stage.setTitle("Создание нового клиента");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(controller.getServicesView()));

        closeCallback.thenAccept(client -> stage.hide());
        controller.init(closeCallback);

        stage.show();
    }

    private void switchWorkspace(Pane workspace) {
        workspaceContainer.getChildren().clear();
        workspaceContainer.getChildren().add(workspace);
    }
}
