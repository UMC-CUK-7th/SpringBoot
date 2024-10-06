# 🎯 핵심 키워드

💡추가 키워드

- JOIN
    
    서로 다른 테이블의 데이터를 동시에 보여줄 때 사용하는 SQL문
    두 테이블의 조인을 위해서는 기본키(PK)와 외래키(FK) 관계로 맺어져야 함
    
    ### **Inner Join**(내부 조인)
    
    두 테이블을 연결할 때 가장 많이 사용
    그냥 조인이라고 부르면 Inner Join을 의미
    조인하는 테이블의 ON 절의 조건이 일치하는 결과만 출력
    조인에 부합되지 않는 레코드는 모두 삭제
    
    ```sql
    SELECT <열 목록>
    FROM <첫 번째 테이블>
        INNER JOIN <두 번째 테이블>
        ON <조인 조건>
    [WHERE 검색 조건]
    
    #INNER JOIN을 JOIN이라고만 써도 INNER JOIN으로 인식함
    ```
    
    ```sql
    select u.userid, name 
    from usertbl as u inner join buytbl as b 
    on u.userid=b.userid 
    where u.userid="111" -- join을 완료하고 그다음 조건을 따진다.
    ```
    
    Inner Join 함축 구문
    
    ```sql
    select u.userid, name 
    from usertbl u, buytbl b //단순히 from 절에 콤마 쓰면 inner join 으로 치부됨
    where u.userid=b.userid and u.userid="111"
    ```
    
    <img width="50%" alt="2주차 워크북 1" src="https://github.com/user-attachments/assets/63a650c0-f758-480b-b712-6fb985187b30">
    
    ### **Outer Join**(외부 조인)
    
    ```sql
    SELECT <열 목록>
    FROM <첫 번째 테이블>
    <LEFT | RIGHT | FULL> OUTER JOIN <두 번째 테이블> ON <조인 조건>
    [WHERE 검색 조건]
    ```
    
    - **LEFT** OUTER JOIN: 왼쪽 테이블의 모든 값이 출력되는 조인
        
        ❗LEFT JOIN을 여러번 할 때 주의할 점
        
        - INNER JOIN과는 달리 LEFT JOIN은 조인하는 테이블의 순서가 상당히 중요함
        어떤 순서로 테이블을 조인하는 지에 따라 결과 테이블에 조회 되는 행의 개수며 구성 등이 달라질 수 있다. 따라서 JOIN 문을 작성할 때, 만약 LEFT JOIN을 할 거라면 가장 첫 번째의 테이블로 SELECT문에 가장 많은 열을 가져와야 할 테이블을 우선으로 적어준다.
        - 조인을 여러 번 해야 하는데 시작을 LEFT JOIN으로 했다면 나머지 조인도 LEFT JOIN을 이어나간다.
        
        ```sql
        SELECT * FROM A
        LEFT JOIN B ON A.key = B.key
        ```
        
    - **RIGHT** OUTER JOIN: 오른쪽 테이블의 모든 값이 출력되는 조인
        - **3중 조인**
            
            만일 원하는 정보가 테이블 3개로 흩어져 있을 때, 이 세 개의 테이블을 모아야 할 때 outer join을 연속으로 3번 사용
            
            ```sql
            select A.Name, A.CountryCode 
            from city A 
                left join country B 
                on A.countrycode = B.code -- 테이블 2개 조인 완료
                left join countrylanguage C 
                on B.code = C.countrycode -- 테이블 3개 조인 완료
            where A.countrycode in ('KOR');
            ```
            
        
        ```sql
        SELECT * FROM A
        RIGHT JOIN B ON A.key = B.key
        ```
        
    - **FULL** OUTER JOIN: 왼쪽 또는 오른쪽 테이블의 모든 값이 출력되는 조인
        
        ```sql
        select * 
        from topic FULL OUTER JOIN autor 
        on topic.auther_id = authoer.id
        ```
        
    
    ❗대부분 DB는 FULL OUTER JOIN을 지원❌ → 간접적으로 구현
    LEFT 조인 한 테이블과 RIGHT조인 한 테이블을 UNION 합집합
    *UNION은 (DISTICT 자동 포함이라) 자동으로 중복 제거
    
    ```sql
    (select * from topic LEFT JOIN autor on topic.auther_id = authoer.id)) 
    UNION 
    (select * from topic RIGHT JOIN autor on topic.auther_id = authoer.id))
    ```
    
    내부 조인은 두 테이블에 모두 데이터가 있어야만 결과가 나오지만,
    외부 조인은 한쪽에만 데이터가 있어도 결과가 나옴
    
    <img width="50%" alt="2주차 워크북 2" src="https://github.com/user-attachments/assets/ca79e08c-3666-4885-ba51-9ee989d9d87a">
    
    ### **Cross Join**(상호 조인)
    
    한쪽 테이블의 모든 행과 다른 쪽 테이블의 모든 행을 조인시키는 기능
    상호 조인 결과의 전체 행 개수 = 두 테이블의 각 행의 개수를 곱한 수
    **카티션 곱(CARTESIAN PRODUCT)**이라고도 함
    
    표준 SQL과는 달리 MySQL에서는 **JOIN, INNER JOIN, CROSS JOIN**이 모두 같은 의미로 사용
    
    ```sql
    SELECT *
    FROM <첫 번째 테이블>
        CROSS JOIN <두 번째 테이블>
    ```
    
    <img width="50%" alt="2주차 워크북 3" src="https://github.com/user-attachments/assets/52da5349-6107-4de1-a546-51c61a2f3a6d">
    
    ### **Self Join**(자체 조인)
    
    자체 조인은 자기 자신과 조인하므로 1개의 테이블을 사용
    별도의 문법❌ → 1개로 조인하면 자체 조인
    
    ```sql
    SELECT <열 목록>
    FROM <테이블> 별칭A
        INNER JOIN <테이블> 별칭B
    [WHERE 검색 조건]
    ```
    
    <img width="50%" alt="2주차 워크북 4" src="https://github.com/user-attachments/assets/a384a09f-3eab-4323-b09b-9d7d86f0b36b">
<br/>

- 서브쿼리
    
    ### Subquery(서브쿼리)
    
    : 하나의 SQL문 안에 포함되어 있는 또다른 SQL문
    다른 쿼리 내부에 포함되어 있는 SELETE문
    괄호 ( ) 안에 있는 쿼리
    
    메인쿼리(외부쿼리, outer query)가 서브쿼리(내부쿼리, inner query)를 포함하는 **종속적**인 관계
    
    - 서브쿼리 실행 → 메인쿼리 실행
    - 서브쿼리 : 메인쿼리 필드 사용 ⭕
    메인쿼리 : 서브쿼리 필드 사용 ❌
    - 서브쿼리 장점
        - 서브쿼리는 쿼리를 구조화 시키므로, 쿼리의 각 부분을 명확히 구분할 수 있게 해줌
        - 복잡한 JOIN이나 UNION과 같은 동작을 수행할 수 있는 또 다른 방법을 제공
        - 복잡한 JOIN이나 UNION 보다 읽기 편함 (가독성이 좋음)
<br/>

- 페이징
    
    ### Paging(페이징)
    
    Database 자체에서 끊어서 가져오는 것
    ex) 목록 조회 시 많은 데이터를 전부 가져오면 렉 발생
    
    - Offset based Paging
    : 직접 페이지 번호를 찾아내어 이동
        
        ```sql
        select*
        from book
        order by likes desc
        limit y offset(x - 1) * y; //페이지 x번에서 y개씩 보여줌
        ```
        
        **limit** + 한 페이지에서 보여줄 데이터의 개수
        **offset** + 건너뛰는 개수
        
        - 문제점
            
            ⚠️ 다른 페이지로 넘어가는 도중, 게시글이 추가된다면?
            → 1페이지에서 본 게시글이 2페이지로 넘어왔는데도 보일 수 있음
            
    - Cursor based Paging
    : 커서(마지막으로 조회한 컨텐츠)로 무언가를 가리켜서 페이징
    → 마지막으로 조회한 대상의 다음부터 가져옴
        
        ```sql
        select * from book where book.likes < 
        		(select likes from book where id = 4)
        			order by likes desc limit 15;
        ```
<br/>

## 📢 학습 후기

<aside>
💡 프로젝트를 진행하면서 JOIN 연산만 사용하느라 서브쿼리를 잊고 있었는데, 워크북을 진행하면서 오랜만에 작성해보았다! JOIN 보다 더 쉽게 작성할 수 있는 부분도 있어서 앞으로 번갈아가며 쓰게 될 것 같다😮 페이징도 커서 기반만 사용했었는데, 오프셋도 사용해보면서 어떤 차이점이 있는지 자세히 알게 된 것 같당

</aside>
<br/>

### 미션 인증

[미션 인증](https://www.notion.so/afec6060a29944de940cc63bf524b319?pvs=21)