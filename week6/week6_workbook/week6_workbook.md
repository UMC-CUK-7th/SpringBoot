# 🎯 핵심 키워드

- **지연로딩과 즉시로딩의 차이**
    
    ### 즉시 로딩
    
    데이터를 요청할 때 즉시 모든 관련 데이터를 가져오는 방식
    
    데이터를 한 번에 가져오기 때문에 데이터 접근 시 추가적인 데이터베이스 쿼리가 발생하지 않는다.
    → 모든 데이터를 미리 로딩
    
    ### 지연 로딩
    
    실제로 데이터가 필요할 때 데이터를 가져오는 방식
    
    데이터 접근 시 추가적인 데이터베이스 쿼리가 발생한다.
    
    지연 로딩은 DB가 아닌 **프록시**에서 데이터를 가져온다.

    <br>

    <aside>
    💡
    
    **지연 로딩** → 데이터 접근 빈도⬇️ 메모리 사용량⬇️
    
    **즉시 로딩** → 데이터 접근 빈도⬆️ 성능 최적화
    
    </aside>

    <br>
    https://f-lab.kr/insight/understanding-lazy-and-eager-loading-20240702
    
    https://engineerinsight.tistory.com/148#google_vignette
    
    https://tecoble.techcourse.co.kr/post/2022-10-17-jpa-hibernate-proxy/

    <br>

- **Fetch Join**
    - SQL 조인의 종류는 아님
    - JPQL에서 성능 최적화를 위해 제공하는 기능
    - 연관된 엔티티나 컬렉션을 한 번에 같이 조회하는 기능
    - `JOIN FETCH` 명령어로 사용
    
    <br>
    `[LEFT [OUTER] | INNER] JOIN FETCH 조인경로`
    
    - JPQL
    `SELECT m FROM Member m JOIN FETCH m.team`
    - SQL
    `SELECT m.*, t.* FROM Member m INNER JOIN Team t ON m.team_id=t.id`
    
    `JOIN FETCH` 로 설정된 연관관계 엔티티의 정보까지 한 번의 쿼리로 다 가져온다.
    → **프록시 객체로 두지 않음**
    이에 따라 모든 연관관계 엔티티의 정보들이 한번에 조회되고, 영속성 컨텍스트 1차 캐시에 저장되어 쿼리 전송 없이 바로 활용될 수 있다.
    
    <br>

    <aside>
    💡
    
    **특징**
    
    글로벌 로딩 전략: 페치 타입 설정과 같이 엔티티에 직접 적용하는 로딩 전략
    **페치 조인**은 글로벌 로딩 전략 보다 우선시된다.
    → 페치 타입을 `LAZY`로 설정하더라도 페치 조인을 사용하면 데이터가 즉시 조회된다.
    
    </aside>
    <br>
    <aside>
    💡
    
    **한계**
    
    페치 조인 대상에는 **별칭을 줄 수 없다.**
    → `SELECT`, `WHERE`, `서브 쿼리`에 페치 조인 대상을 사용할 수 없다. 
    (잘못된 별칭 사용으로 인해 데이터 무결성이 깨질 수 있으므로 걸어둔 제약)
    
    JPA 표준에서는 지원하지 않지만 하이버네이트를 포함한 일부 구현체들은 별칭을 지원한다.
    
    데이터가 증폭되는 문제로 인하여 둘 이상의 컬렉션을 페치할 수 없다.
    
    컬렉션을 페치 조인하면 `페이징 API(setFirstResult, setMaxResults)`를 사용할 수 없다. 일대일, 다대일과 같은 단일값 연관 필드는 페이징 API를 사용할 수 있습니다.
    
    </aside>
    <br>
    https://woo-chang.tistory.com/38
    
    https://medium.com/sjk5766/fetch-join-%ED%8A%B9%EC%A7%95-%EB%B0%8F-%EB%8B%A8%EC%A0%90-75095d1ede21
    
    https://cobbybb.tistory.com/18

    <br>
    
- **@EntityGraph**
    
    **@EntityGraph:** 스프링 데이터 JPA에서 fect 조인을 어노테이션으로 사용할 수 있도록 만들어 준 기능
    
    - 페치 조인을 사용하려면 매번 JPQL을 작성해야 하는데, @EntityGraph를 사용하면 추가적인 JPQL 없이 페치 조인을 사용할 수 있다.
    - JPQL과 같이 사용할 수 있다.
    - 메서드 이름으로 쿼리 생성 기능에도 사용할 수 있다.
    
    연관관계가 있는 엔티티를 조회할 경우 지연 로딩으로 설정되어 있으면 연관관계에서 종속된 엔티티는 쿼리 실행 시 select 되지 않고 proxy 객체를 만들어 엔티티가 적용시킨다. 그 후 해당 proxy 객체를 호출할 때마다 select 쿼리가 실행된다.
    
    위 같은 연관관계가 지연 로딩으로 되어있을 경우 fetch 조인을 사용하여 여러 번의 쿼리를 한 번에 해결할 수 있다.
    
    https://wonin.tistory.com/496
    
    https://devhan.tistory.com/206
    
    https://docs.spring.io/spring-data/jpa/docs/current/api/org/springframework/data/jpa/repository/EntityGraph.html
    
    https://docs.spring.io/spring-data/jpa/docs/current/api/org/springframework/data/jpa/repository/EntityGraph.EntityGraphType.html

    <br>
    
- **JPQL**
    - SQL을 추상화한 JPQL이라는 객체 지향 쿼리 언어를 제공한다.
    - 테이블을 대상으로 쿼리 하는 것이 아닌 엔티티 객체를 대상으로 쿼리한다.
    - SQL을 추상화했기 때문에 특정 데이터베이스 SQL에 의존하지 않는다.
    - SQL과 문법이 유사하며, SELECT, FROM, WHERE, GROUP BY, HAVING, JOIN을 지원한다.
    - JPA는 JPQL을 분석하여 SQL을 생성한 후 DB에서 조회한다.
    
    <br>
    <aside>
    
    💡
    
    **특징**
    
    - 엔티티 클래스 이름, 엔티티 필드의 대소문자 일치
    - JPQL 키워드는 대소문자 구분 ❌(SELECT=select, From=from)
    - 엔티티 객체를 대상으로 하는 쿼리이므로 엔티티 이름을 사용  테이블 이름 X )
    - 별칭은 필수(as는 생략가능)
    - 에러가 런타임 시점에 발생
    </aside>
    
    https://ittrue.tistory.com/270
    
    https://hstory0208.tistory.com/entry/JPA-JPQL%EC%9D%B4%EB%9E%80-%EC%82%AC%EC%9A%A9%EB%B0%A9%EB%B2%95-%EA%B8%B0%EB%B3%B8-%EB%AC%B8%EB%B2%95-%EC%B4%9D-%EC%A0%95%EB%A6%AC
    
    https://adjh54.tistory.com/479

    <br>
    
- **QueryDSL**
    
    : **타입 안전성을 보장**하는 **자바 기반의 쿼리 빌더 라이브러리**
    
    - 하이버네이트 쿼리 언어(HQL)의 쿼리를 타입에 안전하게 생성 및 관리해주는 프레임워크
    - 정적 타입을 이용하여 SQL과 같은 쿼리를 생성할 수 있게 해준다.
    - 복잡한 쿼리, 동적 쿼리를 구현하는 한계를 해결한다.
    - 문자가 아닌 **코드**로 쿼리를 작성할 수 있어 컴파일 시점에 문법 오류를 확인할 수 있다.
    - 인텔리제이와 같은 IDE의 자동 완성 기능의 도움을 받을 수 있다.
    - 복잡한 쿼리나 동적 쿼리 작성이 편리하다.
    - 쿼리 작성 시 제약 조건 등을 메서드 추출을 통해 재사용할 수 있다.
    - JPQL 문법과 유사한 형태로 작성할 수 있어 쉽게 적응할 수 있다.
    
    ### QClass
    
    QueryDSL은 컴파일 단계에서 Entitiy를 기반으로 QClass를 생성한다.
    
    JPA AnnotationProcessor가 컴파일 시점에 작동해서@Entity 등의 어노테이션을 찾아 해당 파일들을 분석해서 QClass를 만든다. Entity 클래스와 매핑되는 QClass를 기반으로 쿼리를 실행한다.
    
    https://ittrue.tistory.com/292
    
    https://ittrue.tistory.com/296
    
    https://hstory0208.tistory.com/entry/JPA-QueryDSL%EC%9D%B4%EB%9E%80-%EA%B7%B8%EB%A6%AC%EA%B3%A0-%EC%84%A4%EC%A0%95-%EB%B0%A9%EB%B2%95
    
    <br>

💡추가 키워드

- N+1 문제
    
    https://dev-coco.tistory.com/165
<br>

# 📢 학습 후기

---

- 이번 주차 워크북을 해결해보면서 어땠는지 회고해봅시다.
- 핵심 키워드에 대해 완벽하게 이해했는지? 혹시 이해가 안 되는 부분은 뭐였는지?

<aside>
💡 엔티티 설정을 잘못해서 테이블도 몇 개는 안 만들어지고🥹 QueryDSL 하면서 계속 에러 발생하고 Q 클래스도 안 만들어져서 정말 울 뻔 했지만 , , 이겨냈따 , , 화이띵

</aside>
<br>

# ✅ 실습 체크리스트

---

- [x]  QueryDSL 기본 설정
- [x]  Q 클래스 생성
- [x]  QueryDSL 설정파일 만들기
- [x]  QueryDSL 쿼리 작성
<br>

# ☑️ 실습 인증

---

- build.gradle
    
    ```java
    plugins {
        id 'java'
        id 'org.springframework.boot' version '3.3.5'
        id 'io.spring.dependency-management' version '1.1.6'
        id 'com.ewerk.gradle.plugins.querydsl' version '1.0.10'
    }
    
    group = 'umc'
    version = '0.0.1-SNAPSHOT'
    
    java {
        toolchain {
            languageVersion = JavaLanguageVersion.of(17)
        }
    }
    
    configurations {
        compileOnly {
            extendsFrom annotationProcessor
        }
        querydsl.extendsFrom compileClasspath
    }
    
    repositories {
        mavenCentral()
    }
    
    dependencies {
        implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
        implementation 'org.springframework.boot:spring-boot-starter-web'
        implementation 'org.hibernate.orm:hibernate-core:6.0.2.Final'  // Hibernate 6.0.2 이상
        implementation 'mysql:mysql-connector-java:8.0.33'  // MySQL 드라이버 추가
    
        // queryDSL
        implementation 'com.querydsl:querydsl-jpa:5.0.0:jakarta'
        annotationProcessor "com.querydsl:querydsl-apt:5.0.0:jakarta"
        annotationProcessor "jakarta.annotation:jakarta.annotation-api"
        annotationProcessor "jakarta.persistence:jakarta.persistence-api"
    
        implementation 'com.fasterxml.jackson.core:jackson-databind:2.17.2'
        implementation 'com.fasterxml.jackson.core:jackson-annotations:2.17.2'
        implementation 'com.fasterxml.jackson.core:jackson-core:2.17.2'
        implementation 'org.springdoc:springdoc-openapi-ui:1.6.9'
        implementation 'org.springdoc:springdoc-openapi-data-rest:1.6.9'
    
        compileOnly 'org.projectlombok:lombok'
        runtimeOnly 'com.mysql:mysql-connector-j'
        annotationProcessor 'org.projectlombok:lombok'
        testImplementation 'org.springframework.boot:spring-boot-starter-test'
        testRuntimeOnly 'org.junit.platform:junit-platform-launcher'
    }
    
    tasks.named('test') {
        useJUnitPlatform()
    }
    
    // Querydsl 설정부
    def generated = 'src/main/generated'
    
    sourceSets {
        main {
            java {
                srcDirs = ['src/main/java', 'src/main/resources', generated]
            }
        }
    }
    
    querydsl {
        jpa = true
        querydslSourcesDir = generated
    }
    
    compileQuerydsl{
        options.annotationProcessorPath = configurations.querydsl
    }
    ```
    
- Q클래스 생성

    <img width="50%" alt="6주차 워크북 1" src="https://github.com/user-attachments/assets/1e4bf599-6669-41e1-856b-89ad14ae56de">
    
- QueryDSLConfig
    
    <img width="50%" alt="6주차 워크북 2" src="https://github.com/user-attachments/assets/ddc540f2-f3db-4a08-b2f5-23e2492a86a9">
    
- 실행 결과
    
    <img width="50%" alt="6주차 워크북 3" src="https://github.com/user-attachments/assets/eea825cb-2c6c-4e4d-9457-9a25e011ff54">

## ⚡ 트러블 슈팅

---

<aside>
💡 실습하면서 생긴 문제들에 대해서, **이슈 - 문제 - 해결** 순서로 작성해주세요.

</aside>

<aside>
💡 스스로 해결하기 어렵다면? 스터디원들에게 도움을 요청하거나 **너디너리의 지식IN 채널에 질문**해보세요!

</aside>

- ⚡이슈 작성 예시 (이슈가 생기면 아래를 복사해서 No.1, No.2, No3 … 으로 작성해서 트러블 슈팅을 꼭 해보세요!)
    
    **`이슈`**
    
    👉 앱 실행 중에 노래 다음 버튼을 누르니까 앱이 종료되었다.
    
    **`문제`**
    
    👉 노래클래스의 데이터리스트의 Size를 넘어서 NullPointException이 발생하여 앱이 종료된 것이었다. 
    
    **`해결`**
    
    👉  노래 다음 버튼을 눌렀을 때 데이터리스트의 Size를 검사해 Size보다 넘어가려고 하면 다음으로 넘어가는 메서드를 실행시키지 않고, 첫 노래로 돌아가게끔 해결
    
    **`참고레퍼런스`**
    
    - 링크
- ⚡이슈 No.1
    
    **`이슈`**
    
    👉 build.gradle에 QueryDSL 기본 설정을 하고 build를 하였더니
    Q 클랙스 디렉토리가 생성되지 않고, `error: Attempt to recreate a file for type QBaseEntity` 에러가 발생하였다.
    
    **`문제`**
    
    👉 build.gradle 파일 코드를 워크북의 일부만 작성하고 빌드하였는데 Q 클래스 디렉토리가 생성되지 않았다. 워크북의 코드대로 다시 수정하고 빌드하였더니 이미 Q 클래스가 생성되었던건지 `recreate` 할 수 없다는 에러가 발생하였다.
    
    **`해결`**
    
    👉  Gradle에서 build-clean 으로 빌드해서 기존 파일을 삭제한 후
    other-compileQuerydsl 을 실행했더니 main에 generated 디렉토리와 Q 클래스가 생성되었다.
    
    <img width="50%" alt="6주차 워크북 4" src="https://github.com/user-attachments/assets/b73b5f89-fe2d-4bca-974a-289b270da34b">
    
    **`참고레퍼런스`**
    
    - https://lahezy.tistory.com/94
- ⚡이슈 No.2
    
    **`이슈`**
    
    👉 `StoreQueryServiceImpl.java` 파일에서 `@Transactional(readOnly = true)` 코드에 에러가 발생한다.
    
    **`문제`**
    
    👉 `import jakarta.transaction.Transactional;` 코드를 사용하였다.
    `javax.transaction.Transactional` 로 import되어 있는 경우  `@Transactional` Annotation에 대하여 옵션을 허용하지 않아 오류가 발생한다고 한다.
    
    **`해결`**
    
    👉 `import org.springframework.transaction.annotation.Transactional;` 로 수정하였다.
    
    **`참고레퍼런스`**
    
    - https://velog.io/@be_have98/Spring-Boot-Cannot-resolve-method-readOnly-%EC%98%A4%EB%A5%98
- ⚡이슈 No.3
    
    **`이슈`**
    
    👉 `StoreRepositoryImpl.java` 파일에서 Q클래스 파일을 인식하지 못해서 에러가 발생한다.
    
    <img width="50%" alt="6주차 워크북 5" src="https://github.com/user-attachments/assets/6f2653cd-f12c-4d3a-be8e-9d0848603c15">
    
    **`문제`**
    
    👉 generated 패키지가 일반 패키지로 인식하고, source로 인식하지 못해서 import가 되지 않았다.
    
    **`해결`**
    
    👉 generated 패키지를 Mark Directory as source root 로 설정하였다.
    
    <img width="50%" alt="6주차 워크북 6" src="https://github.com/user-attachments/assets/e55fd4b7-7ff9-474f-896e-a939dc905836">
    
    **`참고레퍼런스`**
    
    - https://velog.io/@hyeonny/Spring-Querydsl-%EC%82%AC%EC%9A%A9-%EC%A4%91-Qclass-%EC%9D%B8%EC%8B%9D%EC%9D%B4-%EB%90%98%EC%A7%80-%EC%95%8A%EC%9D%84-%EB%95%8C