package control;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Dao {

    public EntityManagerFactory emf() {
        String dbUrl = System.getenv("JDBC_DATABASE_URL");
        String username = System.getenv("JDBC_DATABASE_USERNAME");
        String password = System.getenv("JDBC_DATABASE_PASSWORD");

        Map<String, String> propriedades = new HashMap<>();
        propriedades.put("javax.persistence.jdbc.url", dbUrl);
        propriedades.put("javax.persistence.jdbc.user", username);
        propriedades.put("javax.persistence.jdbc.password", password);

        return Persistence.createEntityManagerFactory("pww3PU", propriedades);
    }

}
