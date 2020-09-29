/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbwithtransactionjpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.Account;

/**
 *
 * @author sarun
 */
public class DBWithTransactionJPA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        transfer(1,2,300);
    }

     public static void transfer(int id1, int id2, int balance) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DBWithTransactionJPAPU");
        EntityManager em = emf.createEntityManager();
        Account target1 = em.find(Account.class, id1);
        Account target2 = em.find(Account.class, id2);
        em.getTransaction().begin();
        try {
            target1.setBalance(target1.getBalance()-balance);
            em.persist(target1);
            int i = 1/0;
            target2.setBalance(target2.getBalance()+balance);
            em.persist(target2);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
}
