package service;

import model.*;

import javax.inject.Singleton;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Singleton
public class GroupService implements IGroupService {
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("HH:mm dd.MM.yy");

    private final List<Group> groups = new ArrayList<>();

    @Override
    public List<YearMonth> getDateList() {
        return Arrays.asList(
                YearMonth.of(2021, 1),
                YearMonth.of(2021, 2),
                YearMonth.of(2021, 11)
        );
    }

    @Override
    public Group getGroup(WorkoutType type, YearMonth period) {
        Subscription subscription = new Subscription();
        subscription.setPeriod(YearMonth.of(2021, 11));
        subscription.setType(WorkoutType.AEROBICS);
        subscription.setWorkouts(getWorkouts());

        Client client = new Client();
        client.setName("Dima");
        client.setSurname("Rashevskiy");
        client.setPatronymic("Viacheslavovich");
        client.setSubscriptions(Collections.singleton(subscription));

        Group group = new Group();
        group.setClients(Collections.singleton(client));
        group.setPeriod(YearMonth.of(2021, 11));
        group.setPlan(getDates());

        return group;
    }

    @Override
    public void addGroup(Group group) {
        groups.add(group);
    }

    private Set<LocalDateTime> getDates() {
        return new HashSet<>(Arrays.asList(
                LocalDateTime.of(2021, 11, 1, 17, 0),
                LocalDateTime.of(2021, 11, 3, 17, 0),
                LocalDateTime.of(2021, 11, 6, 17, 0),
                LocalDateTime.of(2021, 11, 9, 17, 0),
                LocalDateTime.of(2021, 11, 12, 17, 0),
                LocalDateTime.of(2021, 11, 15, 17, 0),
                LocalDateTime.of(2021, 11, 18, 17, 0),
                LocalDateTime.of(2021, 11, 21, 17, 0),
                LocalDateTime.of(2021, 11, 24, 17, 0),
                LocalDateTime.of(2021, 11, 27, 17, 0),
                LocalDateTime.of(2021, 11, 30, 17, 0)
        ));
    }

    private Set<Workout> getWorkouts() {
        return getDates().stream()
                .map(date -> new Workout(-1, date, WorkoutState.NONE))
                .collect(Collectors.toSet());
    }
}
