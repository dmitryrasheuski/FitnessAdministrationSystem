package ui.view;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import lombok.RequiredArgsConstructor;
import ui.UiManager;

import javax.inject.Inject;

@RequiredArgsConstructor(onConstructor_ = @Inject)
public class ViewControllerMenu implements ViewController{
    @FXML private RadioButton groupButton;
    @FXML private RadioButton clientsButton;

    private final UiManager uiManager;

    @FXML
    private void initialize() {
        ToggleGroup group = new ToggleGroup();

        group.getToggles().add(groupButton);
        group.getToggles().addAll(clientsButton);

        group.getToggles().stream().map(RadioButton.class::cast).forEach(toggle -> toggle.getStyleClass().remove("radio-button"));

        group.selectToggle(groupButton);
    }

    @Override
    public final String getViewPath() {
        return "view/menu.fxml";
    }

    @FXML
    public void showGroupLog() {
        uiManager.openGroupLogWorkspace();
    }

    @FXML
    public void showClients() {
        uiManager.openClientWorkspace();
    }
}
