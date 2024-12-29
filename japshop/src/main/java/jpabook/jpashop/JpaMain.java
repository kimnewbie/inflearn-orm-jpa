package jpabook.jpashop;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;

import static jakarta.persistence.Persistence.*;

public class JpaMain {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin(); // [트랜잭션] 시작

        try {

            Order order = new Order();

            /**
             * [이런식으로 양방향으로 안짜도 사용 가능!!]
             em.persist(order);
             OrderItem orderItem = new OrderItem();
             orderItem.setOrder(order);
             em.persist(orderItem);
             */

            order.addOrderItem(new OrderItem());


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
