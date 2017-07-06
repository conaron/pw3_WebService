package teste;

import control.CidadeUtil;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.Cidade;

public class Teste {

    public static void main(String[] args) {
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pww3PU");
//        CidadeUtil dao = new CidadeUtil(emf);

        CidadeUtil dao = new CidadeUtil();
//
        for (Cidade c : dao.findCidadeEntities()) {
            System.out.println(c.getCidade());
        }
        System.out.println("teste");
    }

}
