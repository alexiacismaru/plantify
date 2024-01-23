package be.kdg.integration.plantifybackend.repository;

import be.kdg.integration.plantifybackend.domain.Client;
import be.kdg.integration.plantifybackend.domain.hibernate.ArduinoDao;
import be.kdg.integration.plantifybackend.domain.hibernate.ClientDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

@Repository
public class ClientRepositoryHibernate implements ClientRepository{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    private ClientDao clientToDao(Client cLient){
        return new ClientDao(cLient.getEmail(), cLient.getPassword());
    }

    private Client daoToClient(ClientDao clientDao){
        return new Client(clientDao.getEmail(), clientDao.getPassword());
    }


    @Override
    public Client saveClient(Client client) {
        logger.debug("saving client to database");
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(clientToDao(client));
        logger.debug("client saved to database");
        em.getTransaction().commit();
        em.close();

        return client;
    }


    @Override
    public Client searchClient(String email) {
        logger.debug("searching client");
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        ClientDao clientDao = em.find(ClientDao.class, email);
        logger.debug("client found");
        em.getTransaction().commit();
        em.close();

        return daoToClient(clientDao);
    }


    @Override
    public void deleteClient(Client client){
        logger.debug("deleting client");
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        ClientDao clientDao = em.find(ClientDao.class, client.getEmail());
        em.remove(clientDao);
        logger.debug("deletion successful");
        em.getTransaction().commit();
        em.close();
    }
}
