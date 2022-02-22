package main;

import domein.Docent;
import java.math.BigDecimal;
import javax.persistence.EntityManager;
import util.JPAUtil;

public class MAINoef2 {

    public static void main(String args[]) {
        

        //vraag aan de factory een entityManager
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        
        ////start een transactie
        entityManager.getTransaction().begin();
        
        ////persisteer de 3 objecten
        Docent d = entityManager.find(Docent.class, 2L);
        if(d !=null) {
        	d.opslag(new BigDecimal(200));
        } else {
        	System.out.println("docent 2 niet gevonden");
        }
        
        //commit
        entityManager.getTransaction().commit();
        
        //sluit de entityManager
        entityManager.close();
        
        //sluit de factory
        JPAUtil.getEntityManagerFactory().close();
    }

}