
# README.MD (작성중)

## Package 구성
기능별로 (Service, Repository, Entity 등) 구성하면 패키지 내부 클래스 파일이 많아져서 큰 기능 안에 Service, repository  

IDEA에서는 빈 패키지는 commit이 되지 않기 때문에 빈 클래스를 두고 있습니다.
나중에 클래스 만들면 삭제해주시면 됩니다

<strong>Packages</strong>

## Database
MySQL 사용<br>
- 접속 주소: Application.properties 파일 확인

## Test
test directory 안의 패키지 구조는 main\java와 똑같이 해주세요


<br><br>

<strong>DB 종류</strong>
### User
- 사용자 정보
### Toilet
- 화장실 정보
### Review
- 리뷰 내용 포함
### Report
- 신고 정보


```java
User
private String userId;
private String username;
private String password;
private Integer userPoint;
private Integer userTrust;



//

Toilet

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long toiletId;

@Column(name="road_address")
private String roadAddress;

@Column(name="lot_address")
private String lotAddress;

@Column(nullable = false, precision = 11, scale = 2, name = "latitude")
private BigDecimal latitude;

@Column(nullable = false, precision = 11, scale = 2, name = "longitude")
private BigDecimal longitude;

@OneToMany(cascade = CascadeType.ALL,mappedBy = "toilet")
@JsonIgnore
private Set<Report> report;

@Column(name="building_name")
private BigDecimal longitude;

@Column(name="gu_name")
private String guName;

@Column(name="tel_no")
private String telNo;

@Column(name="toilet_type")
private String toiletType;

@Column(name="open_time")
private String openTime;

@Column(name="toilet_usage")
private String toiletUsage;

@Column(name="toilet_status")
private String toiletStatus;

@Column(name="location_detail")
private String locationDetail;


@Lob
@Column(name="facilities")
private String facilities;

@Column(name="sign_info")
private String signInfo;

@Lob
@Column(name="note")
private String note;

@Column(precision = 3, scale = 2, name = "rating")
private BigDecimal rating;

@Column(name="has_diaper_table")
private Boolean hasDiaperTable;

@Column(name="has_handicap_access")
private Boolean hasHandicapAccess;

@Column(name="has_bidet")
private Boolean hasBidet;

@Column(name="has_tissue")
private Boolean hasTissue;



```


## API







dependencies {

	implementation 'mysql:mysql-connector-java:8.0.33'
	implementation 'org.springframework.boot:spring-boot-starter-web'
	implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
	implementation 'org.springframework.boot:spring-boot-starter-data-rest'
	compileOnly 'org.projectlombok:lombok'
	annotationProcessor 'org.projectlombok:lombok'
	testImplementation 'org.springframework.boot:spring-boot-starter-test'
	testRuntimeOnly 'org.junit.platform:junit-platform-launcher'

	// implementation group: 'org.springdoc', name: 'springdoc-openapi-starter-webmvc-ui', version:'2.0.2'
	// implementation 'org.springdoc:springdoc-openapi-starter-webmvc-ui:2.5.0'
	implementation 'org.springdoc:springdoc-openapi-starter-webmvc-ui:2.7.0'

	implementation 'org.springframework.boot:spring-boot-starter-security'
	testImplementation 'org.springframework.security:spring-security-test'

	implementation 'io.jsonwebtoken:jjwt-api:0.11.5'
	runtimeOnly 'io.jsonwebtoken:jjwt-impl:0.11.5', 'io.jsonwebtoken:jjwt-jackson:0.11.5'

	// runtimeOnly 'com.mysql:mysql-connector-j'
}










<style>

h1 {
    color: gray;
}

h2 {
    color: greenyellow;
    font-size: 24px;
}

h3 {
    color: deepskyblue;
    font-size: 20px;
}

p {
    font-size: 16px;
    padding: 0;
}

strong {
    font-size: 16px;
    color: aquamarine;
}

li {
    font-size:16px;
    font-weight: bold;
}

</style>
