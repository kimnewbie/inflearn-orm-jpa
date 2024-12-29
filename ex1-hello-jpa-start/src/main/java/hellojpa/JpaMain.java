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
            // 팀 저장
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);
            // 회원 저장
            Member member = new Member();
            member.setUsername("member1");

            // 연관관계의 주인 값 설정
            // team.setMembers(member); = null
            member.changeTeam(team); // **

            // 역방향(주인이 아닌 방향)만 연관관계 설정
//            team.getMembers().add(member);

            em.persist(member);

            em.flush();
            em.clear();

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