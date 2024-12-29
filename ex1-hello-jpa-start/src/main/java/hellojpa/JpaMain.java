package hellojpa;

import jakarta.persistence.*;

import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        // 고객의 요청이 있을 때 '마다' 생성 후 폐기
        // ** thread 같에 공유하지 말고 사용하고 버려야한다.
        EntityManager em = emf.createEntityManager();

        // code
        // transaction 필요
        EntityTransaction tx = em.getTransaction();
        tx.begin(); // [트랜잭션] 시작

        try {
            
            tx.commit(); // [트랜잭션] 커밋
            // transaction end
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
            emf.close();
        }
    }
}