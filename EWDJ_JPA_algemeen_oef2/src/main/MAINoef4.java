package main;

import domein.Campus;
import domein.Docent;
import domein.Werkruimte;

import java.math.BigDecimal;
import javax.persistence.EntityManager;
import util.JPAUtil;

public class MAINoef4 {

    public static void main(String args[]) {
    	Docent jan  = new Docent(123, "Jan", "Baard", new BigDecimal(8000));
        Docent piet  = new Docent(456, "Piet", "Baard", new BigDecimal(10000));
        Docent joris  = new Docent(789, "Joris", "ZonderBaard", new BigDecimal(12000));
        Campus gent = new Campus("Gent");
        Campus aalst = new Campus("Aalst");
        Werkruimte zolder = new Werkruimte("SCH123", "zolder", 12, 6);
        Werkruimte kelder = new Werkruimte("SCH555", "kelder", 4, 4);
        Werkruimte dak = new Werkruimte("AA222", "dak", 10, 2);
    	
        //vraag aan de factory een entityManager
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        
        ////start een transactie
        entityManager.getTransaction().begin();
        
        // Docent Jan, Piet en Joris werken op campus Gent
    	jan.addCampus(gent);
    	piet.addCampus(gent);
    	joris.addCampus(gent);
    	// Docent Jan en Joris werken op campus Aalst
    	jan.addCampus(aalst);
    	joris.addCampus(aalst);
    	// Docent Jan heeft als werkruimte SCH123
    	jan.setWerkruimte(zolder);
    	// Docent Piet heeft als werkruimte SCH123
    	piet.setWerkruimte(zolder);
    	// Docent Joris heeft als werkruimte AA222
    	joris.setWerkruimte(dak);
    	
    	entityManager.persist(jan);
        entityManager.persist(piet);
        entityManager.persist(joris);
        entityManager.persist(gent);
        entityManager.persist(aalst);
        entityManager.persist(zolder);
        entityManager.persist(kelder);
        entityManager.persist(dak);
    	
        //commit
        entityManager.getTransaction().commit();
        
        //sluit de entityManager
        entityManager.close();
        
        //sluit de factory
        JPAUtil.getEntityManagerFactory().close();
    }

}