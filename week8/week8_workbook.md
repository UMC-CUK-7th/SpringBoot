# 🎯 핵심 키워드

- java의 Exception 종류들
    
    `NullPointerException`
    
    `ConstraintViolationException` 
    
    `MethodArgumentNotValidException`
    
    `IllegalArgumentException`
    
    `IOException`
    
    `FileNotFoundException`
    
    `SQLException`
    
    `StackOverflowError`
<br>

- @Valid
    - 빈 검증기(Bean Validator)를 이용해 객체의 제약 조건을 검증하도록 지시하는 어노테이션
    - @Valid는 기본적으로 컨트롤러에서만 동작하며, 기본적으로 다른 계층에서는 검증 ❌
    - 유효성 검증에 실패할 경우, MethodArgumentNotValidException 예외 발생
    - 주로 request body를 검증할 때 사용
    
    <br>

    **@Valid vs @Validated**
    
    - @Valid : 자바 표준 스펙
    @Validated : 스프링에서 제공하는 어노테이션
    - @Validated를 통해 그룹 유효성 검사나 Controller가 아닌 다른 계층에서 유효성 검증 가능
    - @Validated는 ConstraintViolationException 예외 발생
    - 쿼리 스트링이나 쿼리 파라미터를 검증할 때 사용
    
    https://medium.com/sjk5766/valid-vs-validated-%EC%A0%95%EB%A6%AC-5665043cd64b
<br>

# 💪 미션 기록

---

<aside>
🍀 미션 기록의 경우, 아래 미션 기록 토글 속에 작성하시거나, 페이지를 새로 생성하여 해당 페이지에 기록하여도 좋습니다!

하지만, 결과물만 올리는 것이 아닌, **중간 과정 모두 기록하셔야 한다는 점!** 잊지 말아주세요.

</aside>

- **미션 기록**
    
    <img width="50%" alt="8주차 워크북 1" src="https://github.com/user-attachments/assets/321a4c07-b49d-45c6-946a-ce9ad03f5ed9">
    
    <img width="50%" alt="8주차 워크북 2" src="https://github.com/user-attachments/assets/310dcc6b-397c-4eec-be3b-5153b6821afa">

    <img width="50%" alt="8주차 워크북 3" src="https://github.com/user-attachments/assets/7c303d8e-86a1-4a11-922e-21267ebafaf1">