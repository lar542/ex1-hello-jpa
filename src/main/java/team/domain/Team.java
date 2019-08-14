package team.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//@Entity
public class Team {

    @Id @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;

    private String name;

    //회원에서 팀을 참조할 뿐만 아니라
    //팀에서도 회원을 참조할 수 있도록 팀에 멤버 리스트를 추가(양방향 매핑)
    @OneToMany(mappedBy = "team") //팀과 멤버는 1대다 관계이고, team은 Member의 team 변수로 매핑되어 있음을 설정
    private List<Member> members = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Member> getMembers() {
        return members;
    }

    //연관관계 편의 메소드
    public void addMember(Member member){
        member.setTeam(this);
        members.add(member);
    }
}
