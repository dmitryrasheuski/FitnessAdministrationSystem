package model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class Workout {
    private int id;
    @Getter
    private LocalDateTime date;
    @Getter @Setter
    private WorkoutState state;
}
