package be.kdg.integration.plantifybackend.service;

import be.kdg.integration.plantifybackend.domain.Client;

public interface ClientService {
    Client addClient(String email, String password);

    boolean checkClient(Client client);

    void removeClient(Client client);

}
