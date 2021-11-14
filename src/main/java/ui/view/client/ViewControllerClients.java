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
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@RequiredArgsConstructor(onConstructor_ = @Inject)
public class ViewControllerClients implements ViewController {
    @FXML private Pane servicedView;
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
            if (change.wasAdded()) {
                showClientInfo(change.getAddedSubList().get(0));
            }
        });
        clientList.setCellFactory(listView -> new ClientListCell());
        clientList.getItems().addAll(clientService.getClients());
    }

    @Override
    public String getViewPath() {
        return "view/client/clients.fxml";
    }

    @Override
    public Pane getServicesView() {
        return servicedView;
    }

    public void showNewClientDialog() {
        CompletableFuture<Client> updateCallback = new CompletableFuture<>();
        updateCallback.thenAccept(client -> {
            searchField.clear();
            filterClient();
            clientList.getSelectionModel().select(client);
        });
        uiManager.openNewClientDialog(updateCallback);
    }

    public void showClientInfo(Client client) {
        surnameField.setText(client.getSurname());
        nameField.setText(client.getName());
        patronymicField.setText(client.getPatronymic());
        phoneField.setText(client.getPhone());
        clientInfoContainer.setVisible(true);
    }

    public void filterClient() {
        String pattern = searchField.getText();

        List<Client> clients = clientService.getClients().stream()
                .filter(client -> getFio(client).contains(pattern))
                .collect(Collectors.toList());

        clientList.getItems().setAll(clients);
    }

    public void updateClientInfo() {
        Client client = clientList.getSelectionModel().getSelectedItem();
        client.setSurname(surnameField.getText());
        client.setName(nameField.getText());
        client.setPatronymic(patronymicField.getText());
        client.setPhone(phoneField.getText());

        //TODO должна быть проверка на уникальность ФИО

        try {
            clientService.updateClient(client);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //TODO после изменения данных необходимо обновить отображение клиента в списке
    }

    private String getFio(Client client) {
        return String.format("%s %s %s", client.getSurname(), client.getName(), client.getPatronymic());
    }
}
