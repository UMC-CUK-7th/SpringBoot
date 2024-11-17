# 🔥 미션

---

1. 위의 링크, 그리고 워크북을 보며 API 응답 통일과 에러 핸들러를 숙지하기.
2. **반드시** 본인 손으로 처음부터 끝까지 다 해보고 새 리포지토리 혹은 7주차 리포지토리에 새 브랜치에 push 후 해당 링크를 미션 기록지에 제출할 것.
3. 미션 진행 시 반드시 중간 중간 **과정 인증샷**을 남길 것.
4. ❗**필수**❗ ****RestControllerAdvice의 장점, 그리고 없을 경우 어떤 점이 불편한지도 조사하여 **미션 기록란**에 수록할 것.
5. ❗**필수**❗ **미션 목록 조회(진행중, 진행 완료) API 명세서** 작성하기 (이미 작성되어 있으면 상관 없음!)
<br>

# 💪 미션 기록

---

<aside>
🍀 미션 기록의 경우, 아래 미션 기록 토글 속에 작성하시거나, 페이지를 새로 생성하여 해당 페이지에 기록하여도 좋습니다!

하지만, 결과물만 올리는 것이 아닌, **중간 과정 모두 기록하셔야 한다는 점!** 잊지 말아주세요.

</aside>

- **미션 기록**
    
    ### RestControllerAdvice
    
    > RestControllerAdvice = ControllerAdvice + ResponseBody
    > 
    <br>

    **`RestControllerAdvice`**  vs **`ControllerAdvice`**
    
    @Controller 어노테이션, @RestController 어노테이션이 붙은 컨트롤러에서 발생하는 예외(@ExceptionHandler)를 AOP를 적용해 **예외를 전역적으로 처리**할 수 있는 어노테이션

    <br>
    
    `RestControllerAdvice` 로 선언하면 컨트롤러에서 리턴하는 값이 응답 값의 body로 세팅되어 클라이언트에게 전달된다.
    일반 컨트롤러에서도 script message를 보내주고 싶은 경우 script를 Data로 보내줄 수 있다고 한다.
    
    `ControllerAdvice`로 선언하였을 경우, 리턴 값을 기준으로 동일한 이름의 view를 찾는다.
    → 해당하는 view가 없을 경우 에러 발생
    
    ❗여러 ControllerAdvice가 있는 경우 @Order 로 순서를 지정하지 않는다면 Spring이 ControllerAdvice를 임의의 순서로 처리할 수 있다.
    
    <br>

    https://medium.com/sjk5766/restcontrolleradvice-vs-controlleradvice-%EC%A0%95%EB%A6%AC-1591010ce0b0
    
    https://moon-meteor.tistory.com/27
    
    https://goodfriends-team.tistory.com/23
    
    <br>
    
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