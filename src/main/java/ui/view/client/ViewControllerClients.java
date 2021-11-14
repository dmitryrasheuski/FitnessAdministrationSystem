package ui.view.client;

import javafx.fxml.FXML;
import lombok.RequiredArgsConstructor;
import ui.UiManager;
import ui.view.ViewController;

import javax.inject.Inject;

@RequiredArgsConstructor(onConstructor_ = @Inject)
public class ViewControllerClients implements ViewController {
    private final UiManager uiManager;

    @Override
    public String getViewPath() {
        return "view/client/clients.fxml";
    }

    @FXML
    public void showNewClientDialog() {
        uiManager.openNewClientDialog();
    }
}
