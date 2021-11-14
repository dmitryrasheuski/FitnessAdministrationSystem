package ui.view.client;

import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
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
    @FXML private Pane clientInfoContainer;
    @FXML private TextField surnameField;
    @FXML private TextField nameField;
    @FXML private TextField patronymicField;
    @FXML private TextField phoneField;

    private final UiManager uiManager;
    private final IClientService clientService;

    @FXML
    private void initialize() {
        clientList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        clientList.getSelectionModel().getSelectedItems().addListener((ListChangeListener<? super Client>) change -> {
            change.next();
            showClientInfo(change.getAddedSubList().get(0));
        });
        clientList.setCellFactory(listView -> new ClientListCell());
        clientList.getItems().addAll(clientService.getClients());
    }

    @Override
    public String getViewPath() {
        return "view/client/clients.fxml";
    }

    public void showNewClientDialog() {
        uiManager.openNewClientDialog();
    }

    public void showClientInfo(Client client) {
        surnameField.setText(client.getSurname());
        nameField.setText(client.getName());
        patronymicField.setText(client.getPatronymic());
        phoneField.setText(client.getPhone());
        clientInfoContainer.setVisible(true);
    }
}
