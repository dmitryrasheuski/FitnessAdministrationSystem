package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.YearMonth;
import java.util.Optional;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {
    private int id;
    private String name;
    private String surname;
    private String patronymic;
    private Wallet wallet;
    private Set<Subscription> subscriptions;

    public Optional<Subscription> getSubscription(YearMonth date) {
        return subscriptions.stream()
                .filter(subscription -> subscription.getPeriod().equals(date))
                .findFirst();
    }
}
