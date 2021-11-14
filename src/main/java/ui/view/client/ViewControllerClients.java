package ui.view.client;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import lombok.RequiredArgsConstructor;
import model.Client;
import service.IClientService;
import ui.UiManager;
import ui.view.ViewController;

import javax.inject.Inject;

@RequiredArgsConstructor(onConstructor_ = @Inject)
public class ViewControllerClients implements ViewController {
    @FXML private TextField searchField;
    @FXML private ListView<Client> clientList;

    private final UiManager uiManager;
    private final IClientService clientService;

    @FXML
    private void initialize() {
        clientList.setCellFactory(listView -> new ClientListCell());
        clientList.getItems().addAll(clientService.getClients());
    }

    @Override
    public String getViewPath() {
        return "view/client/clients.fxml";
    }

    @FXML
    public void showNewClientDialog() {
        uiManager.openNewClientDialog();
    }
}
