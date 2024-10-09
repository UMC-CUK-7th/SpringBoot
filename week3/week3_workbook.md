# 🎯 핵심 키워드

- API Endpoint
    
    : API가 서버에서 자원(resource)에 접근할 수 있도록 하는 URL
    
    💡HTTP 메소드가 같은 URL들에 대해서도 다른 요청을 하게끔 구별해주는 항목
<br/>
    
- REST(Representational State Transfer)
    
    : 네트워크를 통해서 컴퓨터들끼리 통신할 수 있게 해주는 아키텍처 스타일
    
    REST의 특징
    
    - **Sever - Client (서버 - 클라이언트 구조)**
        - 자원이 있는 쪽 : Server (API 제공, 비즈니스 로직 처리 및 저장)
        자원을 요청하는 쪽 : Client (사용자 인증 정보 관리)
        - 서로 간의 의존성 ⬇️
    - **Stateless (무상태)**
        - HTTP 프로토콜은 Stateless 프로토콜 이므로 REST 또한 무상태성을 가짐
        - Client의 사용자 인증 정보를 Server에 저장하지 않음
        → 구현 단순화, 서비스 자유도 ⬆️, 유연한 아키텍쳐 적응이 가능
        - Server는 각각의 요청을 완전히 별개의 것으로 인식하고 처리함
    - **Cacheable (캐시 처리 기능)**
        - HTTP 프로토콜을 사용하므로 웹에서 사용하는 기존의 인프라를 그대로 사용 가능
        - 대량의 요청을 효율적으로 처리하기 위해 캐시 요구
        캐시 사용 → 응답 시간이 빨라지짐
        REST Server 트랜잭션 발생❌ → 전체 응답 시간, 성능, 서버의 자원 이용률 향상
    - **Layered System (계층화)**
        - Client는 REST API Server만 호출
        - REST Server는 다중 계층으로 구성될 수 있음
        - API Server는 순수 비즈니스 로직을 수행하고 그 앞단에 보안, 로드밸런싱, 암호화, 사용자 인증 등을 추가 가능 → 구조상 유연성과 확장성, 보안성 향상
        - PROXY, 게이트웨이 같은 네트워크 기반의 중간 매체 사용 가능
    - **Uniform Inerface (인터페이스의 일관성)**
        - URI로 지정한 Resource에 대한 조작을 통일되고 한정적인 인터페이스로 수행
        - HTTP 표준 프로토콜을 따르는 모든 플랫폼에서 사용이 가능하며, 특정 언어나 기술에 종속되지 않음
    
    REST의 구성 요소 : resource, method, message
    
    REST에서 자원(resource)에 접근할 때 **URL** 사용
    
    URL : 자원의 위치를 나타내는 일종의 식별자
    
    → **REST API** : REST를 통해서 서비스 API를 구현한 것
    
    REST API에서 API Endpoint는 해당 API를 호출하기 위한 HTTP 메소드, URL을 포함
    
    [[Web] API 그리고 EndPoint](https://velog.io/@kho5420/Web-API-그리고-EndPoint)
    
    [RESTful API ?](https://ji-musclecode.tistory.com/57)
<br/>

- 로그인 GET vs POST
    
    GET은 모든 사람에게 표시되는 쿼리 스트링으로 URL 요청이 보내지기에 **로그인은 POST를 쓴다.**
    
    POST로 로그인 요청 시, ID와 PW가 Request Body에 담겨서 보내지는데
    HTTPS를 적용하면 암호화가 된다
    
    GET 요청은 서버 데이터의 상태를 변경하지 않기 때문에 쿼리를 적극적으로 캐싱할 수 있다.
    아이디와 비밀번호와 같은 정보를 쿼리에 담아 보내게 되면 보안에 취약할 수 있다.
    (다음 페이지가 로딩되거나 다른 페이지로 이동할 때까지 로그 파일이나 사용자 브라우저에 일반텍스트로 표시됨)
    
    POST 요청은 서버의 상태를 변경시키고 동일한 응답을 주며 서버를 동일한 상태로 유지하기 위해 캐싱을 하지 않기 때문에 GET보다는 보안에 용이하다.
    
    예를 들면 5번의 로그인에 실패했을 때 6번째 요청은 "당신의 IP는 30분동안 차단되었습니다." 라고 응답할 수 있다.
    
    [[REST API] LogIn GET vs POST](https://velog.io/@jch9537/REST-API-LogIn-GET-vs-POST)
<br/>

# 📢 학습 후기

---

- 이번 주차 워크북을 해결해보면서 어땠는지 회고해봅시다.
- 핵심 키워드에 대해 완벽하게 이해했는지? 혹시 이해가 안 되는 부분은 뭐였는지?

<aside>
💡 처음 공부했을 땐 이게 무슨 말이지😵‍💫 싶었는데, API 개발을 해본 후 다시 개념을 살펴보니까 이해가 잘 되었다! 프로젝트 세팅을 하면서 스프링 부트를 처음 사용해봤는데, 아직 쩜 낯설어서 따로 공부를 더 해볼 생각이다

</aside>
<br/>

# ✅ 실습 체크리스트

---

- [x]  Spring Initializr로 Spring Boot 프로젝트 생성
- [x]  build.gradle `dependencies`  수정
- [x]  Gradle 변경 내용 로드 후 실행
<br/>

# ☑️ 실습 인증

---

<img width="50%" alt="3주차 워크북 1" src="https://github.com/user-attachments/assets/db1e96ca-85b8-44e4-8206-69186ee8b689">

<img width="50%" alt="3주차 워크북 2" src="https://github.com/user-attachments/assets/9e1374fd-07a5-4e43-9904-5c7618e5dc69">

<img width="50%" alt="3주차 워크북 4" src="https://github.com/user-attachments/assets/31f1dccd-597e-4674-981c-47e87884ced8">

<img width="50%" alt="3주차 워크북 5" src="https://github.com/user-attachments/assets/82ae9257-52ce-4d3c-bad0-e6e766981643">