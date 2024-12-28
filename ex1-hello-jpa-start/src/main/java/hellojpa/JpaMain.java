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
        tx.begin();

        try {
            // [1] 멤버 저장
            // 비영속 상태
//            Member member = new Member();
//            member.setId(2L);
//            member.setName("Dan");
            // 영속 상태
//            em.persist(member);

            // [2] 멤버 조회
//            Member findMember = em.find(Member.class, 1L);
//            System.out.println("findMember.getId() = " + findMember.getId());
//            System.out.println("findMember.getName() = " + findMember.getName());

            // [3] 멤버 삭제
//            em.remove(findMember);

            // [4] 멤버 수정
            // persist 안해도 JPA가 자동으로 update 쳐준다.
//            findMember.setName("DK");

            // [JPQL 예시]
//            List<Member> result = em.createQuery("select m from Member as m", Member.class)
//                    .setFirstResult(1) // pagination
//                    .setMaxResults(10) // pagination
//                    .getResultList();
//
//            for (Member member : result) {
//                System.out.println("member = " + member.getName());
//            }

            // 실제로 쿼리를 날리는 부분
            tx.commit();
            // transaction end
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
            emf.close();
        }
    }
}