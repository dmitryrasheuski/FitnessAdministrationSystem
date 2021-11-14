package ui.view.client;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import lombok.RequiredArgsConstructor;
import model.Client;
import service.IClientService;
import ui.view.ViewController;

import javax.inject.Inject;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor(onConstructor_ = @Inject)
public class ViewControllerNewClient implements ViewController {
    @FXML private Pane servicedView;
    @FXML private Text errorMessage;
    @FXML private TextField surnameField;
    @FXML private TextField nameField;
    @FXML private TextField patronymicField;
    @FXML private TextField phoneField;

    private final IClientService clientService;

    private CompletableFuture<Client> closeCallback;

    public void init(CompletableFuture<Client> closeCallback) {
        this.closeCallback = closeCallback;
    }

    @Override
    public String getViewPath() {
        return "view/client/new_client.fxml";
    }

    @Override
    public Pane getServicesView() {
        return servicedView;
    }

    @FXML
    public void onCreateNewClient() {
        Client client = new Client();

        client.setSurname(surnameField.getText());
        client.setName(nameField.getText());
        client.setPatronymic(patronymicField.getText());
        client.setPhone(phoneField.getText());

        boolean isUnique = clientService.isUnique(client);

        if (!isUnique) {
            errorMessage.setText("Пользователь с указанными данными уже существует!");
            return;
        }

        try {
            clientService.addClient(client);
        } catch (IOException e) {
            e.printStackTrace();
            errorMessage.setText("При сохранении произошла ошибка!");
            return;
        }

        closeCallback.complete(client);
    }
}
