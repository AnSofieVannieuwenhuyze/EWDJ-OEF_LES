package main;

import domein.Campus;
import domein.Docent;
import domein.Werkruimte;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import util.JPAUtil;

public class MAINoef6 {

    public static void main(String args[]) {
    	
    	
        
        //vraag aan de factory een entityManager
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        
        ////start een transactie
        entityManager.getTransaction().begin();
        
        // campussen opvragen omdat de query van queryD een object verwacht, waardoor je niet zomaar "aalst" kan typen
        Campus campusAalst = entityManager.createNamedQuery("Campus.findByName", Campus.class)
        		.setParameter("naam", "Aalst").getSingleResult();
        Campus campusGent = entityManager.createNamedQuery("Campus.findByName", Campus.class)
        		.setParameter("naam", "Gent").getSingleResult();
        
        // de werkplaats opvragen om daarna te gebruiken om aan een docent toe te wijzen
        Werkruimte kelder = entityManager.find( Werkruimte.class, "SCH555");
        
        // Als er een campusAalst en een campusGent is en een kelder dan moet onderstaande code uitgevoerd worden
        if (campusAalst != null && campusGent != null && kelder != null) {
        	
        	TypedQuery<Docent> queryD = entityManager.createNamedQuery("Docent.docentenInTweeCampussen", Docent.class);
            queryD.setParameter("campusA", campusAalst);
            queryD.setParameter("campusB", campusGent);
            List<Docent> docenten = queryD.getResultList();
            
            // voor die docenten de werkruimte naar de kelder instellen
            docenten.forEach(d -> d.setWerkruimte(kelder));
        }
        
        
        //commit
        entityManager.getTransaction().commit();
        
        //sluit de entityManager
        entityManager.close();
        
        //sluit de factory
        JPAUtil.getEntityManagerFactory().close();
    }

}