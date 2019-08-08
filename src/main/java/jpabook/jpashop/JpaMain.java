package jpabook.jpashop;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            /*
            - 현재 방식은 객체 설계를 테이블 설계에 맞춘 방식
            - 테이블의 외래키를 객체에 그대로 가져오기 때문에 객체 그래프 탐색이 불가능하다.
            - 객체지향적으로 코딩할 수 없다.
            => 객체와 테이블 간의 연관관계 매핑이 필요!
             */

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
