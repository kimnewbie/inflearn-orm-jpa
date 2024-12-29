package hellojpa;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

// JPA가 관리하는 클래스 @Entity
@Entity
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    private int age;

    // 이 어노테이션으로 JPA에 관계를 알려줘야해
    // 외래 키가 있는 곳이 주인
    // 연관관계의 주인O
    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    public Team getTeam() {
        return team;
    }

    public void changeTeam(Team team) {
        this.team = team;

        // TIP! 양방향에 넣어주는 것을 잊는 경우가
        // 많아서 여기에 넣어주면 편하다.
        team.getMembers().add(this);
    }
//    @ManyToOne
//    @JoinColumn(name = "TEAM_ID")
//    private Team team;

    public Member() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
