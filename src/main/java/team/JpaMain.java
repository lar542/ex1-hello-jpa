package team;

import team.domain.Member;
import team.domain.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            /*
            - 객체를 테이블에 맞추어 모델링한 경우 외래 키 식별자를 직접 다뤄야 한다. (객체지향적 X)

            - 테이블과 객체의 패러다임의 차이!
                - 테이블은 외래 키로 조인을 사용해 연관 테이블을 찾는다.
                - 객체는 참조를 통해 연관된 객체를 찾는다.
             */
            //팀과 멤버를 저장하는 경우
            /*Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.setTeamId(team.getId());
            em.persist(member);

            //조회할 때
            Member findMember = em.find(Member.class, member.getId());
            Team findTeam = em.find(Team.class, team.getId());*/

            //객체지향 모델링 후 연관관계 저장하기
            /*Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.setTeam(team); //단방향 연관관계 설정, 참조 저장!
            em.persist(member);*/

            //양방향 매핑시 많이 하는 실수
            //연관관계의 주인에 값을 입력하지 않음
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");

            //순수 객체 상태를 고려해서 항상 양쪽에 값을 설정한다.
            //역방향(주인이 아닌 방향)만 연관관계 설정하면 fk에 null이 들어간다.
//            team.getMembers().add(member);
            //양방향 매핑시 연관관계의 주인에 값을 입력해야 한다.
//            member.setTeam(team);
            //연관관계 편의 메소드 이용해도 된다. 단, 둘 중에 하나만 정해야한다.
//            member.changeTeam(team);
//            team.addMember(member);

            em.persist(member);


            em.flush();
            em.clear();

            //참조로 연관관계 조회하기 - 객체 그래프 탐색
            /*Member findMember = em.find(Member.class, member.getId());
            Team findTeam = findMember.getTeam(); //참조를 통해 연관관계 조회
            System.out.println("findTeam = " + findTeam.getName());*/

            //수정
            /*Team teamB = new Team();
            teamB.setName("teamB");
            em.persist(teamB);

            findMember.setTeam(teamB);*/

            //양방향 매핑 : 반대 방향으로 객체 그래프 탐색
            /*Member findMember = em.find(Member.class, member.getId());
            List<Member> members = findMember.getTeam().getMembers();

            for(Member m : members){
                System.out.println("m = " + m.getUsername());
            }*/



            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
