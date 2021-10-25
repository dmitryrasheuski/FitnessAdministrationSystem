package model;

import lombok.Getter;

import java.util.Collections;
import java.util.Date;
import java.util.Set;

public class Subscription {
    private int id;
    @Getter
    private WorkoutType type;
    @Getter
    private Date period;
    @Getter
    private int orderedCount;
    @Getter
    private int price;
    private Set<Workout> workouts;

    public Set<Workout> getWorkouts() {
        return Collections.unmodifiableSet(workouts);
    }

    public void markWorkout(Date date, WorkoutState state) throws IllegalArgumentException {
        workouts.stream()
                .filter(workout -> workout.getDate().equals(date))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("unable to find workout by date"))
                .setState(state);
    }
}
