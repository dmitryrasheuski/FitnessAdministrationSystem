package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Subscription {
    private int id;
    private WorkoutType type;
    private YearMonth period;
    private int orderedCount;
    private int price;
    private Set<Workout> workouts;

    public void markWorkout(LocalDate date, WorkoutState state) throws IllegalArgumentException {
        workouts.stream()
                .filter(workout -> workout.getDate().equals(date))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("unable to find workout by date"))
                .setState(state);
    }
}
