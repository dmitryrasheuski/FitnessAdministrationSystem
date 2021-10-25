package model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class Workout {
    private int id;
    @Getter
    private Date date;
    @Getter @Setter
    private WorkoutState state;
}
