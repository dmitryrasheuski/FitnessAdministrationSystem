package service;

import model.Client;

import java.io.IOException;
import java.util.List;

public interface IClientService {
    List<Client> getClients();
    void addClient(Client client) throws IOException;
    void updateClient(Client client) throws IOException;
    boolean isUnique(Client client);
}
