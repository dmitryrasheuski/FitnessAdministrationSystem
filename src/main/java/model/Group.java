package model;

import lombok.Getter;

import java.util.Collections;
import java.util.Date;
import java.util.Set;

public class Group {
    private int id;
    @Getter
    private Date period;
    private Set<Date> plan;
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

    public Set<Date> getPlan() {
        return Collections.unmodifiableSet(plan);
    }

    public boolean addToPlan(Date date) {
        return plan.add(date);
    }

    public boolean removeFromPlan(Date date) {
        return plan.remove(date);
    }
}
