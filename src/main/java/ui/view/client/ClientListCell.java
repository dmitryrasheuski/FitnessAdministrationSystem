package ui.view.client;

import javafx.scene.control.ListCell;
import model.Client;

class ClientListCell extends ListCell<Client> {
    @Override
    protected void updateItem(Client item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || item == null) {
            setText(null);
        } else {
            setText(
                    String.format("%s\n%s\n%s", item.getSurname(), item.getName(), item.getPatronymic())
            );
        }
    }
}
