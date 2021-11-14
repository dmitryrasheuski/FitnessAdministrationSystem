package service;

import model.Client;

import javax.inject.Singleton;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class ClientService implements IClientService{
    private final List<Client> clients = new ArrayList<>();

    @Override
    public List<Client> getClients() {
        return clients;
    }

    @Override
    public void addClient(Client client) throws IOException {
        clients.add(client);
    }

    @Override
    public void updateClient(Client client) throws IOException {

    }

    @Override
    public boolean isUnique(Client client) {
        return !clients.contains(client);
    }
}
