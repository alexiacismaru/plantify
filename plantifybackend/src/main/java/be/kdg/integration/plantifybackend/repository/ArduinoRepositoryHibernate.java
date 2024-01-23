package be.kdg.integration.plantifybackend.repository;

import be.kdg.integration.plantifybackend.domain.Arduino;
import be.kdg.integration.plantifybackend.Util.RGBColor;
import be.kdg.integration.plantifybackend.domain.hibernate.ArduinoDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ArduinoRepositoryHibernate implements ArduinoRepository {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;



    private Arduino daoToArduino(ArduinoDao arduinoDao){
        return new Arduino(arduinoDao.getSeries(), arduinoDao.getLedSetting(),
                arduinoDao.getPhysicalIdentifier(), new RGBColor(arduinoDao.getRedCode(), arduinoDao.getBlueCode(),
                arduinoDao.getGreenCode()),arduinoDao.isConfigured());
    }

    private ArduinoDao arduinoToDao(Arduino arduino){
        return new ArduinoDao(arduino.getPhysicalIdentifier(), arduino.getSeries(),
                arduino.getLedSetting(), arduino.getRed(), arduino.getGreen(), arduino.getBlue(), arduino.isConfigured());
    }

    @Override
    public void setLedSetting(int physicalId, boolean base) {
        logger.debug("changing led setting");
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        ArduinoDao arduinoDao = em.find(ArduinoDao.class, physicalId);
        arduinoDao.setLedSetting(base);
        logger.debug("leds setting changed");
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void changeColor(int physicalId, RGBColor color) {
        logger.debug("Setting color of "+physicalId +" to " + color);
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        ArduinoDao arduinoDao = em.find(ArduinoDao.class, physicalId);
        arduinoDao.setRedCode(color.getRed());
        arduinoDao.setGreenCode(color.getGreen());
        arduinoDao.setBlueCode(color.getBlue());

        logger.debug("Set color of "+physicalId +" to " + color);
        em.getTransaction().commit();
        em.close();

    }

    @Override
    public Arduino saveArduino(Arduino arduino) throws SQLException {
        logger.debug("saving arduino to database");
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        ArduinoDao arduinoDao = arduinoToDao(arduino);
        em.persist(arduinoDao);
        logger.debug("added arduino to database");
        try {
            em.getTransaction().commit();
        }catch (Exception e){
            em.close();
            throw new SQLException("This arduino is already in use");
        }

        return arduino;
    }

    @Override
    public List<Arduino> getArduinoList() {
        logger.debug("retrieving arduinoList");
        List<Arduino> arduinoList = new ArrayList<>();

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        List<ArduinoDao> daoList = em.createQuery("select a from ArduinoDao a",
                ArduinoDao.class).getResultList();
        daoList.forEach(arduinoDao -> arduinoList.add(daoToArduino(arduinoDao)));

        em.getTransaction().commit();
        em.close();
        return arduinoList;
    }

    @Override
    public void deleteArduino(int physicalId){
        logger.debug("removing arduino from database and arduino list");
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        ArduinoDao arduinoDao = em.find(ArduinoDao.class, physicalId);
        em.remove(arduinoDao);
        logger.debug("deletion successful");
        em.getTransaction().commit();
        em.close();


    }

    @Override
    public void configureArduino(int physicalId, boolean b) {
        logger.debug("configuring arduino");
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        ArduinoDao arduinoDao = em.find(ArduinoDao.class, physicalId);
//        em.remove(arduinoDao);
        arduinoDao.setConfiguration(b);
        logger.debug("configured");
        em.getTransaction().commit();
        em.close();
    }


}
