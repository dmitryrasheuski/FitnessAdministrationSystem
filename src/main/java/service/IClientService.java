package service;

import model.Client;

import java.io.IOException;

public interface IClientService {
    boolean isUnique(Client client);
    void addClient(Client client) throws IOException;
}
