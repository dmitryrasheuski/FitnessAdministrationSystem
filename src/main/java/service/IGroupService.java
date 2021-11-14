package service;

import model.Group;
import model.WorkoutType;

import java.time.YearMonth;
import java.util.List;

public interface IGroupService {
    List<YearMonth> getDateList();
    Group getGroup(WorkoutType type, YearMonth period);
}
