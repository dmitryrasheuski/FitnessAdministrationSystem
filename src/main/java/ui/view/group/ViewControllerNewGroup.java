package ui.view.group;

import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.Pane;
import model.WorkoutType;
import ui.view.ViewController;

import java.time.MonthDay;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ViewControllerNewGroup implements ViewController {
    @FXML private Pane servicedView;
    @FXML private ComboBox<YearMonth> periodComboBox;
    @FXML private ComboBox<WorkoutType> workoutComboBox;
    @FXML private ListView<MonthDay> planList;
    @FXML private Button createButton;

    @FXML
    private void initialize() {
        periodComboBox.getItems().setAll(getPeriods());
        workoutComboBox.getItems().setAll(WorkoutType.values());
        planList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        planList.getSelectionModel().getSelectedItems().addListener((ListChangeListener<? super MonthDay>) c -> updateCreateAvailability());
    }

    @Override
    public String getViewPath() {
        return "view/group/new_group.fxml";
    }

    @Override
    public Pane getServicesView() {
        return servicedView;
    }

    public void onSetPeriod() {
        List<MonthDay> days = getMonthDays(periodComboBox.getValue());
        planList.getItems().setAll(days);
        updateCreateAvailability();
    }

    public void onSetWorkoutType() {
        updateCreateAvailability();
    }

    public void onCreateNewGroup() {

    }

    private void updateCreateAvailability() {
        createButton.setDisable(
                periodComboBox.getSelectionModel().isEmpty() || workoutComboBox.getSelectionModel().isEmpty() || planList.getSelectionModel().isEmpty()
        );
    }

    private List<YearMonth> getPeriods() {
        return IntStream.rangeClosed(1, 5)
                .mapToObj(i -> YearMonth.now().plus(i, ChronoUnit.MONTHS))
                .collect(Collectors.toList());
    }

    private List<MonthDay> getMonthDays(YearMonth month) {
        return IntStream.rangeClosed(1, month.lengthOfMonth())
                .mapToObj(i -> MonthDay.of(month.getMonth(), i))
                .collect(Collectors.toList());
    }
}
