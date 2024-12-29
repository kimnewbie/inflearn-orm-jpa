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
            //팀 저장
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);
            //회원 저장
            Member member = new Member();
            member.setName("member1");
            member.setTeamId(team.getId()); // em.persist(team); 여기서 세팅

            // ## 객체를 테이블에 맞추어 모델링 한 부분
            // ## 식별자로 다시 조회 -> 객체 지향적인 방법이 아님
            Member findMember = em.find(Member.class, member.getId());

            Long findTeamId = findMember.getTeamId();
            Team findTeam = em.find(Team.class, findTeamId);

            em.persist(member);
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