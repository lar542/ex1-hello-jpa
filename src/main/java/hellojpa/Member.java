package hellojpa;

import javax.persistence.*;
import java.util.Date;

//@Entity
public class Member {

//    @Id //직접 할당할 때 사용
    //자동 생성
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    //컬럼명은 name으로
    //컬럼 값이 절대로 변경되지 않아야 한다면 updatable = false
    //not null로 설정하려면 nullable = false
    //유니크 제약 조건 생성하려면 unique = true 단, 제약조건 아이디가 랜덤 값이기 때문에 잘 쓰지 않음
    //보통 @Table을 통해 제약 조건 생성함
    @Column(name="name", columnDefinition = "varchar(100) default 'EMPTY'")
    private String username;

    private Integer age;

    //기본 값이 EnumType.ORDINAL이며 절대로 이를 설정해서는 안 된다.
    //DB에 enum의 순서 값이 저장되기 때문에 만약 추후에 enum이 새로 추가 앞에 된다면 문제가 되기 때문
    @Enumerated(EnumType.STRING) //enum 타입을 사용하기 위한 설정
    private RoleType rolType;

    //최신 하이버네이트에서는 LocalDate(date 타입)나 LocalDateTime(timestamp 타입) 타입을 사용하고 @Temporal 생략
    @Temporal(TemporalType.TIMESTAMP) //날짜와 시간
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @Lob //VARCHAR를 넘는 굉장히 큰 타입
    private String description;

    @Transient //컬럼으로 생성하지 않고 메모리에서만 사용하기 위한 설정
    private int temp;

    public Member() {
    }
}