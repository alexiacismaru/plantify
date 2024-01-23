package be.kdg.integration.plantifybackend.service;


import be.kdg.integration.plantifybackend.domain.Client;
import be.kdg.integration.plantifybackend.domain.hibernate.ClientDao;
import be.kdg.integration.plantifybackend.repository.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * service class for handling user data
 */
@Component
public class ClientServiceImplementation implements ClientService {
    private final ClientRepository clientRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public ClientServiceImplementation(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    /**
     * adds a user to the database, used on signups
     * @param email email of the user
     * @param password password of the user
     * @return function from the repository
     */
    @Override
    public Client addClient(String email, String password) {
        Client client = new Client(email, password);
        clientRepository.saveClient(client);
        return client;
    }

    /**
     * checks if a user exists
     * @param client user that needs to be checked
     * @return boolean if password of the user is correct
     */
    @Override
    public boolean checkClient(Client client) {
        logger.debug("Checking password");
        if(clientRepository.searchClient(client.getEmail()).getPassword().equals(client.getPassword())){
            logger.debug("Correct password");
            return true;
        }
        else{
            logger.debug("False password");
            return false;
        }
    }

    @Override
    public void removeClient(Client client){
        clientRepository.deleteClient(client);
    }
}
