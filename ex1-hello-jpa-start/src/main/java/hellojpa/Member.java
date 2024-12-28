package hellojpa;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

// JPA가 관리하는 클래스 @Entity
@Entity
//@Table(name="MBR") // 매핑할 때 사용됨. 실제 db 명
public class Member {

    @Id
    private Long id;

    // DB 컬럼명이 name
    @Column(name = "name", nullable = false)
    private String username;

    private Integer age;

    // @Enumerated = enum 타입 매핑
    // ** EnumType.ORDINAL: enum 순서를 데이터베이스에 저장 -> 사용X **
    // EnumType.STRING: enum 이름을 데이터베이스에 저장
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    // 구 버전
    // @Temporal = 날짜 타입 매핑
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    // 최신 버전
    private LocalDate testLocalDate;
    private LocalDateTime testLocalDateTime;

    // @Lob = BLOB, CLOB 매핑
    // String -> CLOB 나머지 BLOB
    @Lob
    private String description;

    // 특정 필드를 컬럼에 매핑하지 않음(매핑 무시)
    @Transient
    private int temp;

    public Member() {
    }

    public Member(Long id, String username, Integer age, RoleType roleType, Date createdDate, Date lastModifiedDate, String description) {
        this.id = id;
        this.username = username;
        this.age = age;
        this.roleType = roleType;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
        this.description = description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return username;
    }
}
