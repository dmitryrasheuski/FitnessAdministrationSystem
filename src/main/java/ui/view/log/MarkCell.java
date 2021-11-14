package ui.view.log;

import javafx.geometry.Insets;
import javafx.scene.control.TableCell;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import model.Workout;
import model.WorkoutState;

class MarkCell extends TableCell<RowModel, Workout> {
    private static final Background VISITED = new Background(new BackgroundFill(Color.GREENYELLOW, CornerRadii.EMPTY, Insets.EMPTY));
    private static final Background CANCELED = new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY));
    private static final Background NONE = Background.EMPTY;

    @Override
    protected void updateItem(Workout workout, boolean empty) {
        if (workout == getItem() && empty == isEmpty()) {
            return;
        }

        super.updateItem(workout, empty);

        if (empty) {
            setBackground(Background.EMPTY);
            setOnMouseClicked(null);
        } else {
            updateView();
            setOnMouseClicked(e -> changeValue());
        }
    }

    private void updateView() {
        WorkoutState workoutState = getItem().getState();

        Background background;
        if (workoutState == WorkoutState.VISITED) background = VISITED;
        else if (workoutState == WorkoutState.CANCELED) background = CANCELED;
        else background = NONE;

        setBackground(background);
    }

    private void changeValue() {
        Workout workout = getItem();
        RowModel rowModel = getTableRow().getItem();

        rowModel.changeWorkoutState(workout);
        updateView();
    }
}
