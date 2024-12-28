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
            // [1] 멤버 저장
            // 비영속 상태
//            Member member = new Member();
//            member.setId(2L);
//            member.setName("Dan");
            // 영속 상태
            // 1차 캐시에 저장됨
//            em.persist(member);

            // [2] 멤버 조회
            // 1차 캐시에서 조회 (db 조회 전에)
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

            // [영속 엔티티의 동일성 보장]
            // 1차 캐시로 반복 가능한 읽기(reapeatable read) 등급의
            // 트랜잭션 격리 수준을 db가 아닌 애플리케이션 차원에서 제공
            Member findMember1 = em.find(Member.class, 1L);
            Member findMember2 = em.find(Member.class, 1L);
            System.out.println("result = " + (findMember1 == findMember2));


            // 영속
            Member member1 = new Member(150L, "A");
            Member member2 = new Member(160L, "B");

            // [영속성 :: 엔티티 등록 -> 트랜잭션을 지원하는 쓰기 지연]
            em.persist(member1);
            em.persist(member2);
            
            // 여기까지 insert sql을 db에 보내지 않음

            // 커밋하는 순간 db에 insert sql을 보냄
            // 실제로 쿼리를 날리는 부분
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