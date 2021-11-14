package ui.view.log;

import javafx.beans.property.Property;
import javafx.beans.property.ReadOnlyProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleObjectProperty;
import lombok.Getter;
import model.Client;
import model.Subscription;
import model.Workout;
import model.WorkoutState;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
class RowModel {
    private final Client client;
    private final Subscription subscription;

    private final ReadOnlyProperty<String> fio;
    private final List<Property<Workout>> markColumns;

    public RowModel(Client client, Subscription subscription) {
        this.client = client;
        this.subscription = subscription;
        this.fio = new ReadOnlyStringWrapper(String.format("%s %s %s", client.getSurname(), client.getName(), client.getPatronymic()));
        this.markColumns = subscription.getWorkouts().stream().map(SimpleObjectProperty::new).collect(Collectors.toList());
    }

    public Property<Workout> getMarkProperty(LocalDateTime dateTime) {
        return markColumns.stream().filter(prop -> prop.getValue().getDate().equals(dateTime)).findFirst().get();
    }

    public void changeWorkoutState(Workout workout) {
        WorkoutState currentState = workout.getState();

        int length = WorkoutState.values().length;
        int ordinal = currentState.ordinal() + 1;

        WorkoutState nextState;
        if (ordinal < length) {
            nextState = WorkoutState.values()[ordinal];
        } else {
            nextState = WorkoutState.values()[0];
        }

        workout.setState(nextState);
    }
}
