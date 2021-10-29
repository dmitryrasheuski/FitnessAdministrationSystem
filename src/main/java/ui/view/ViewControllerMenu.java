package ui.view;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import lombok.RequiredArgsConstructor;
import ui.UiManager;

import javax.inject.Inject;

@RequiredArgsConstructor(onConstructor_ = @Inject)
public class ViewControllerMenu implements ViewController{
    @FXML
    private RadioButton groupButton;

    private final UiManager uiManager;

    @FXML
    private void initialize() {
        ToggleGroup group = new ToggleGroup();
        group.selectToggle(groupButton);
        groupButton.getStyleClass().remove("radio-button");
    }

    @Override
    public final String getViewPath() {
        return "view/menu.fxml";
    }

    @FXML
    public void showGroupLog() {
        uiManager.openGroupLogWorkspace();
    }
}
