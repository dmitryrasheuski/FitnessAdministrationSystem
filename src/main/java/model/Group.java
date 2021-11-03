package model;

import lombok.Getter;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.Collections;
import java.util.Set;

public class Group {
    private int id;
    @Getter
    private YearMonth period;
    private Set<LocalDateTime> plan;
    private Set<Client> clients;

    public Set<Client> getClients() {
        return Collections.unmodifiableSet(clients);
    }

    public void addClient(Client client) {
        clients.add(client);
    }

    public void removeClient(Client client) {
        clients.remove(client);
    }

    public Set<LocalDateTime> getPlan() {
        return Collections.unmodifiableSet(plan);
    }

    public boolean addToPlan(LocalDateTime date) {
        return plan.add(date);
    }

    public boolean removeFromPlan(LocalDateTime date) {
        return plan.remove(date);
    }
}
