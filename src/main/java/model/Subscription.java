package model;

import lombok.Getter;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Collections;
import java.util.Set;

public class Subscription {
    private int id;
    @Getter
    private WorkoutType type;
    @Getter
    private YearMonth period;
    @Getter
    private int orderedCount;
    @Getter
    private int price;
    private Set<Workout> workouts;

    public Set<Workout> getWorkouts() {
        return Collections.unmodifiableSet(workouts);
    }

    public void markWorkout(LocalDate date, WorkoutState state) throws IllegalArgumentException {
        workouts.stream()
                .filter(workout -> workout.getDate().equals(date))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("unable to find workout by date"))
                .setState(state);
    }
}
