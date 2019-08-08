package hellojpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {
        //애플리케이션 로딩 시점에 DB 당 하나만 생성되어 애플리케이션 전체에서 공유된다.
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        //요청이 올 때마다 계속 쓰고 버리면서 동작
        //쓰레드 간에 공유해선 안 된다. 사용하고 버려야 한다.
        EntityManager em = emf.createEntityManager();
        //트랜잭션 시작 : JPA의 모든 데이터 변경은 트랜잭션 안에서 실행
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            //등록
			/*Member member = new Member();
			member.setId(2L);
			member.setName("HelloB");
			em.persist(member);*/

            //조회
			/*Member member = em.find(Member.class, 1L);
			System.out.println("아이디 : " + member.getId());
			System.out.println("이름 : " + member.getName());*/

            //삭제
			/*Member member = em.find(Member.class, 2L);
			em.remove(member);*/

            //수정
			/*Member member = em.find(Member.class, 1L);
			member.setName("HelloJPA");*/ //마치 자바 컬렉션을 다루는 것처럼 setter만 호출하면 update됨
            //JPA를 통해 데이터를 가져오면 이를 JPA가 관리하게 되고 변경된 값이 있는지 트랜잭션 커밋 전에 체크한다.
            //바뀐 게 있으면 update 쿼리를 만들어 날린 후에 커밋한다.

            //전체 조회 : 대상이 테이블이 아니라 객체를 조회
			/*List<Member> result = em.createQuery("select m from Member as m", Member.class)
					.setFirstResult(5)
					.setMaxResults(8)
					.getResultList();

			for (Member member : result) {
				System.out.println("member name : " + member.getName());
			}*/
			/*
			select m from Member as m
			↓
			select
	            member0_.id as id1_0_,
	            member0_.name as name2_0_
	        from
	            MEMBER member0_

			데이터베이스 방언 맞춰서 페이징 쿼리가 만들어짐
			select
	            member0_.id as id1_0_,
	            member0_.name as name2_0_
	        from
	            MEMBER member0_ limit ? offset ?

	        ANSI SQL이 지원하는 대부분의 쿼리가 지원됨

	        JPQL
	        	검색할 때 테이블이 아닌 엔티티 객체를 대상으로 검색할 수 있는 문법을 지원함
			 */

            //영속성 컨텍스트
            //비영속 상태 (new)
//			Member member = new Member();
//			member.setId(101L);
//			member.setName("Hello!!");
            //영속 상태 : 영속성 컨텍스트에 관리되는 상태 (managed)
//			em.persist(member);
            //준영속 상태 : 엔티티를 영속성 컨텍스트에서 지운다. (detached)
//			em.detach(member);

			/*
			1차 캐시 : 큰 도움은 안됨. 엔티티 매니저는 DB 트랜잭션 단위이기 때문에
			트랜잭션이 종료되면 해당 영속 컨텍스트가 지워지기 때문에 1차 캐시도 다 날라간다.
			즉 DB 트랜잭션 한 사이클 안 에서만 이점을 얻을 수 있기 때문에 큰 효과를 느낄 순 없다.
			성능적 이점보다는 좀 더 객체지향적으로 설계할 수 있다.
			 */
//			Member member = new Member();
//			member.setId(101L);
//			member.setName("회원1");

            //영속 상태 : 영속 컨텍스트의 1차 캐시에 저장된다.
//			em.persist(member);

            //1차 캐시에 저장된 값을 조회하여 반환한다.
            //그렇기 때문에 해당 쿼리가 DB로 보내지지 않는다.
//			Member findMember = em.find(Member.class, 101L);
            //1차 캐시에 저장된 값이 있는 지 확인 후 없으면 DB에서 조회하여 1차 캐시에 저장한 후 반환한다.
//			Member findMember2 = em.find(Member.class, 102L);

			/*
			영속 엔티티의 동일성 보장 : 마치 자바 컬렉션에서 같은 레퍼런스의 객체를 비교할 때와 같음
			 */
//			Member findMember1 = em.find(Member.class, 101L);
//			Member findMember2 = em.find(Member.class, 101L);
//			System.out.println(findMember1 == findMember2); //동일성 비교 true

			/*
			엔티티를 등록할 때 트랜잭션을 지원하는 쓰기 지연
			 */

            tx.commit();

        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close(); //애플리케이션 종료 시 닫아주어야 한다.
    }
}