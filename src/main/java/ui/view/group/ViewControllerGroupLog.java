package ui.view.group;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import lombok.RequiredArgsConstructor;
import model.*;
import service.IGroupService;
import ui.view.ViewController;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor(onConstructor_ = @Inject)
public class ViewControllerGroupLog implements ViewController {
    @FXML private Pane servicedView;
    @FXML private TableView<RowModel> logTable;
    @FXML private ComboBox<WorkoutType> workoutType;
    @FXML private ComboBox<YearMonth> date;

    private final IGroupService groupService;

    @FXML
    private void initialize() {
        workoutType.getItems().addAll(WorkoutType.values());
        date.getItems().addAll(groupService.getDateList());
    }

    @Override
    public String getViewPath() {
        return "view/group/group_log.fxml";
    }

    @Override
    public Pane getServicesView() {
        return servicedView;
    }

    public void onSetPeriod() {
        if (workoutType.getSelectionModel().getSelectedItem() == null)
            return;

        updateGroupView();
    }

    public void onSetWorkoutType() {
        if (date.getSelectionModel().getSelectedItem() == null)
            return;

        updateGroupView();
    }

    private void updateGroupView() {
        logTable.getColumns().clear();
        logTable.getItems().clear();

        WorkoutType workoutType = this.workoutType.getSelectionModel().getSelectedItem();
        YearMonth date = this.date.getSelectionModel().getSelectedItem();

        Group group = groupService.getGroup(workoutType, date);

        logTable.getColumns().addAll(createColumns(group));
        logTable.getItems().addAll(createRows(group));
    }

    private List<TableColumn<RowModel, ?>> createColumns(Group group) {
        List<TableColumn<RowModel, ?>> columns = new ArrayList<>();

        TableColumn<RowModel, String> fioColumn = createFioColumn();
        List<TableColumn<RowModel, Workout>> markColumns = group.getPlan().stream().sorted(LocalDateTime::compareTo).map(this::createMarkColumn).collect(Collectors.toList());

        columns.add(fioColumn);
        columns.addAll(markColumns);

        return columns;
    }

    private TableColumn<RowModel, String> createFioColumn() {
        TableColumn<RowModel, String> column = new TableColumn<>("ФИО");
        column.setCellValueFactory(data -> data.getValue().getFio());
        column.setSortable(false);
        column.setReorderable(false);
        return column;
    }

    private TableColumn<RowModel, Workout> createMarkColumn(LocalDateTime dateTime) {
        String date = dateTime.format(DateTimeFormatter.ofPattern("dd.MM"));
        TableColumn<RowModel, Workout> column = new TableColumn<>(date);
        column.setCellFactory(col -> new MarkCell());
        column.setCellValueFactory(data -> data.getValue().getMarkProperty(dateTime));
        column.setSortable(false);
        column.setReorderable(false);
        return column;
    }

    private List<RowModel> createRows(Group group) {
        Client client = group.getClients().stream().findFirst().get();
        Subscription subscription = client.getSubscription(group.getPeriod()).get();

        return Collections.singletonList(new RowModel(client, subscription));
    }
}
