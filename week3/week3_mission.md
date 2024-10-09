### 미션 인증

<aside>
💡 **간단한 명세서** 예시

### API Endpoint

PATCH /users/{userId}

### Request Body

```json
	{
	"nickname" : "ddol"
}
```

### Request Header

Authorization : accessToken (String)

### Query String

GET /users/articles**?name=umc&owner=ddol**

</aside>
<br/>

### 간단한 명세서
  - 홈 화면
      
      ### API Endpoint
      
      GET /home
      
      ### Request Body
      
      필요 없음
      
      ### Request Header
      
      Authorization : accessToken (String)
      
      ### Query String
      
      필요 없음
<br/>

  - 마이 페이지 리뷰 작성
      
      💡마이페이지에서 포인트 내역 클릭하면 가게 페이지로 이동하는 로직으로 가정
      
      ### API Endpoint
      
      POST /stores/**{storeId}**/reviews
      
      ### Request Body
      
      ```json
      {
        "content" : "리뷰 내용"
        "rating" : 4.5
        "image_url" : "리뷰 사진 url"
      }
      ```
      
      ### Request Header
      
      Authorization : accessToken (String)
      
      ### Query String
      
      필요 없음
<br/>

  - 미션 목록 조회(진행중, 진행완료)
      
      ### API Endpoint
      
      GET /missions
      
      ### Request Body
      
      필요 없음
      
      ### Request Header
      
      Authorization : accessToken (String)
      
      ### Query String
      
      진행 중인 미션 목록 조회
      
      <aside>
      🔑 GET /missions**?status=0**
      
      </aside>
      
      진행 완료한 미션 목록 조회
      
      <aside>
      🔑 GET /missions**?status=1**
      
      </aside>
<br/>

  - 미션 성공 누르기
      
      ### API Endpoint
      
      PATCH /missions/**{missionId}**
      
      ### Request Body
      
      ```json
      {
        "status" : 1
      }
      ```
      
      ### Request Header
      
      Authorization : accessToken (String)
      
      ### Query String
      
      필요 없음
<br/>

  - 회원 가입 하기(소셜 로그인 고려 X)
      
      ### API Endpoint
      
      POST /users
      
      ### Request Body
      
      ```json
      {
        "name" : "이서연",
        "nickname" : "리브",
        "phone" : "010-0000-0000",
        "gender" : "여",
        "birth" : "2002-06-08",
        "email" : "seoyeoneel02@gmail.com",
        "address" : "경기도 부천시 원미구",
        "spec_address" : "지봉로43 가톨릭대학교"	
      }
      ```
      
      나머지 칼럼은 기본값 설정
      
      ### Request Header
      
      Authorization : accessToken (String)
      
      ### Query String
      
      필요 없음