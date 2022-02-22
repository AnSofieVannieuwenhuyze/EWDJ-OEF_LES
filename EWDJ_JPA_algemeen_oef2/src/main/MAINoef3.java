package main;

import domein.Campus;
import domein.Docent;
import domein.Werkruimte;

import java.math.BigDecimal;
import javax.persistence.EntityManager;
import util.JPAUtil;

public class MAINoef3 {

    public static void main(String args[]) {
        
    	Docent a  = new Docent(123, "Jan", "Baard", new BigDecimal(8000));
        Docent b  = new Docent(456, "Piet", "Baard", new BigDecimal(10000));
        Docent c  = new Docent(789, "Joris", "ZonderBaard", new BigDecimal(12000));
        Campus gent = new Campus("Gent");
        Campus aalst = new Campus("Aalst");
        Werkruimte r1 = new Werkruimte("SCH123", "zolder", 12, 6);
        Werkruimte r2 = new Werkruimte("SCH555", "kelder", 4, 4);
        Werkruimte r3 = new Werkruimte("AA222", "dak", 10, 2);
    	
        //vraag aan de factory een entityManager
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        
        ////start een transactie
        entityManager.getTransaction().begin();
        
        ////persisteer de 3 objecten
        entityManager.persist(a);
        entityManager.persist(b);
        entityManager.persist(c);
        entityManager.persist(gent);
        entityManager.persist(aalst);
        entityManager.persist(r1);
        entityManager.persist(r2);
        entityManager.persist(r3);

        //commit
        entityManager.getTransaction().commit();
        
        //sluit de entityManager
        entityManager.close();
        
        //sluit de factory
        JPAUtil.getEntityManagerFactory().close();
    }

}