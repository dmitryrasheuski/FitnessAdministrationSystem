package model;

import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.Date;
import java.util.Set;

public class Client {
    private int id;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String surname;
    @Getter
    @Setter
    private String patronymic;
    @Getter
    private Wallet wallet;
    private Set<Subscription> subscriptions;

    public boolean addSubscription(Subscription subscription) {
        return subscriptions.add(subscription);
    }

    public Set<Subscription> getSubscriptions() {
        return Collections.unmodifiableSet(subscriptions);
    }

    public Subscription getSubscription(Date date) {
        return subscriptions.stream()
                .filter(subscription -> subscription.getPeriod().equals(date))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unable to find subscription by date"));
    }



}
