package team.domain;

import javax.persistence.*;

@Entity
public class Member {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    //객체지향 모델링을 위해 Team의 외래 키를 포함시키는 대신에
    //아래와 같이 연관관계 매핑한다. (ORM 매핑)
//    @Column(name = "TEAM_ID")
//    private Long teamId;

    //Member와 Team은 다 대 1 관계
    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    //연관관계 편의 메소드
    public void changeTeam(Team team){
        this.team = team;
        team.getMembers().add(this);
    }
}
