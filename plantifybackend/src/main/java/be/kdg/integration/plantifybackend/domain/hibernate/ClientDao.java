package be.kdg.integration.plantifybackend.domain.hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * class for usage in JPA hibernate, communicates with client table
 */
@Entity
@Table(name = "client")
public class ClientDao {


    @Id
    @Column(name = "email", nullable = false, updatable = false)
    private String email;

    @Column(name = "password", nullable = false, updatable = false)
    private String password;

    public ClientDao(String email, String password) {
        this.email = email;
        this.password = password;
    }
    public ClientDao() {

    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

}
