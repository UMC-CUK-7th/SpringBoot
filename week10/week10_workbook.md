## 🎯 핵심 키워드

<aside>
💡 주요 내용들을 정리하고, 여러분만의 생각을 통해 정리해보세요!

- 레퍼런스를 참고하여 정의, 속성, 장단점 등을 적어주셔도 괜찮습니다.
- 조사는 공식 홈페이지 **Best**, 블로그(최신 날짜) **Not Bad**
</aside>

<br>

- **Spring Security**
    
    : 인증, 권한 관리, 데이터 보호 기능을 포함하여 웹 개발 과정에서 필수적인 **사용자 관리 기능**을 구현하는데 도움을 주는 Spring의 강력한 **프레임워크**
    
    보안 기능을 추가할 때 Spring Security 사용하는 이유는 Spring Security가 **Spring의 생태계에서 보안에 필요한 기능들을 제공**하기 때문이다. Spring Security는 개발 구조가 Spring이라는 프레임워크 안에서 활용하기 적합한 구조로 설계되어 있어, 보안 기능을 추가할 때 활용하기 좋다.
    
    프레임워크를 사용하지 않고 코드를 직접 작성할 경우 Spring에서 추구하는 IoC/DI 패턴과 같은 확장 패턴을 염두 해서 인증/인가 부분을 직접 개발하기는 쉽지 않은데, Spring Security에서는 이와 같은 기능들을 제공해 주기 때문에 개발 작업 효율을 높일 수 있다.
    
    **→** Spring을 사용할 경우에는 Spring Security를 활용하여 **보안 기능을 추가**
    
    <br>

    https://www.elancer.co.kr/blog/detail/235
    
    https://hello-judy-world.tistory.com/216
    
<br>

- **인증(Authentication)과 인가(Authorization)**
    
    ### 인증
    
    - '증명하다'라는 의미로 사용자의 신원을 검증하는 프로세스
    - 유저 아이디와 비밀번호를 이용하여 로그인하는 과정
    
    ### 인가
    
    - 인증 이후의 프로세스
    - 인증된 사용자가 어떠한 자원에 접근할 수 있는지를 확인하는 절차
    - '권한부여'나 '허가'와 같은 의미로 사용
    - 어떤 대상이 특정 목적을 실현하도록 허용(Access) 하는 것을 의미
    
    <br>

    https://dev.gmarket.com/45
    
<br>

## 💪 미션 기록

<aside>
🍀 미션 기록의 경우, 아래 미션 기록 토글 속에 작성하시거나, 페이지를 새로 생성하여 해당 페이지에 기록하여도 좋습니다!

하지만, 결과물만 올리는 것이 아닌, **중간 과정 모두 기록하셔야 한다는 점!** 잊지 말아주세요.

</aside>

- **미션 기록**
    - java
        - config
            
            ```java
            package umc.spring.config.security;
            
            import lombok.RequiredArgsConstructor;
            import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
            import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
            import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
            import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
            import org.springframework.security.oauth2.core.user.OAuth2User;
            import org.springframework.stereotype.Service;
            import umc.spring.domain.enums.Role;
            
            import java.util.HashMap;
            import java.util.Map;
            import java.util.UUID;
            
            @Service
            @RequiredArgsConstructor
            public class CustomOAuth2UserService extends DefaultOAuth2UserService {
            
                private final MemberRepository memberRepository;
                private final PasswordEncoder passwordEncoder;
            
                @Override
                public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
                    OAuth2User oAuth2User = super.loadUser(userRequest);
            
                    Map<String, Object> attributes = oAuth2User.getAttributes();
                    Map<String, Object> properties = (Map<String, Object>) attributes.get("properties");
            
                    String nickname = (String) properties.get("nickname");
                    String email = nickname + "@kakao.com"; // 임시 이메일 생성
            
                    // 사용자 정보 저장 또는 업데이트
                    Member member = saveOrUpdateUser(email, nickname);
            
                    // 이메일을 Principal로 사용하기 위해 attributes 수정
                    Map<String, Object> modifiedAttributes = new HashMap<>(attributes);
                    modifiedAttributes.put("email", email);
            
                    return new DefaultOAuth2User(
                            oAuth2User.getAuthorities(),
                            modifiedAttributes,
                            "email"  // email Principal로 설정
                    );
                }
            
                private Member saveOrUpdateUser(String email, String nickname) {
                    Member member = memberRepository.findByEmail(email)
                            .orElse(Member.builder()
                                    .email(email)
                                    .name(nickname)
                                    .password(passwordEncoder.encode("OAUTH_USER_" + UUID.randomUUID()))
                                    .gender(Gender.NONE)  // 기본값 설정
                                    .address("소셜로그인")  // 기본값 설정
                                    .specAddress("소셜로그인")  // 기본값 설정
                                    .role(Role.USER)
                                    .build());
            
                    return memberRepository.save(member);
                }
            }
            ```
            
            ```java
            package umc.spring.config.security;
            
            import org.springframework.context.annotation.Bean;
            import org.springframework.context.annotation.Configuration;
            
            @EnableWebSecurity
            @Configuration
            public class SecurityConfig {
            
                @Bean
                public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                    http
                            .authorizeHttpRequests((requests) -> requests
                                    .requestMatchers("/", "/home", "/signup", "/css/**").permitAll()
                                    .requestMatchers("/admin/**").hasRole("ADMIN")
                                    .anyRequest().authenticated()
                            )
                            .formLogin((form) -> form
                                    .loginPage("/login")
                                    .defaultSuccessUrl("/home", true)
                                    .permitAll()
                            )
                            .logout((logout) -> logout
                                    .logoutUrl("/logout")
                                    .logoutSuccessUrl("/login?logout")
                                    .permitAll()
                            )
                            .oauth2Login(oauth2 -> oauth2
                                    .loginPage("/login")
                                    .defaultSuccessUrl("/home", true)
                                    .permitAll()
                            );
            
                    return http.build();
                }
            
                @Bean
                public PasswordEncoder passwordEncoder() {
                    return new BCryptPasswordEncoder();
                }
            }
            ```
            
        - web
            
            ```java
            package umc.spring.web.controller;
            
            import org.springframework.stereotype.Controller;
            import org.springframework.ui.Model;
            import org.springframework.web.bind.annotation.GetMapping;
            import umc.spring.web.dto.MemberRequestDTO;
            
            @Controller
            public class MemberViewController {
            
                @GetMapping("/login")
                public String loginPage() {
                    return "login";
                }
            
                @GetMapping("/signup")
                public String signupPage(Model model) {
                    model.addAttribute("memberJoinDto", new MemberRequestDTO.JoinDto());
                    return "signup";
                }
            
                @GetMapping("/home")
                public String home() {
                    return "home";
                }
            
                @GetMapping("/admin")
                public String admin() {
                    return "admin";
                }
            }
            
            ```
            
            ```java
            package umc.spring.web.dto;
            
            import jakarta.validation.constraints.Email;
            import jakarta.validation.constraints.NotBlank;
            import jakarta.validation.constraints.NotNull;
            import jakarta.validation.constraints.Size;
            import lombok.Getter;
            import lombok.Setter;
            import umc.spring.domain.enums.Role;
            
            import java.util.List;
            
            public class MemberRequestDTO {
                @Getter
                @Setter   // thymeleaf에서 사용하기 위해 추가
                public static class JoinDto {
                    @NotBlank
                    String name;
                    @NotBlank
                    @Email
                    String email;    // 이메일 필드 추가
                    @NotBlank
                    String password;    // 비밀번호 필드 추가
                    @NotNull
                    Integer gender;
                    @NotNull
                    Integer birthYear;
                    @NotNull
                    Integer birthMonth;
                    @NotNull
                    Integer birthDay;
                    @Size(min = 5, max = 12)
                    String address;
                    @Size(min = 5, max = 12)
                    String specAddress;
                    List<Long> preferCategory;
                    @NotNull
                    Role role;    // 역할 필드 추가
                }
            }
            
            ```
            
    - resources
        
        ```html
        <!DOCTYPE html>
        <html xmlns:th="http://www.thymeleaf.org">
        <head>
            <title>Admin Page</title>
        </head>
        <body>
        <h2>Admin Page</h2>
        <p>관리자만 접근할 수 있는 페이지입니다.</p>
        </body>
        </html>
        ```
        
        ```html
        <!DOCTYPE html>
        <html xmlns:th="http://www.thymeleaf.org">
        <head>
            <title>Home</title>
        </head>
        <body>
        <h2>Welcome to Home Page!</h2>
        <p th:text="'반가워, ' + ${#authentication.name} + '!'"></p>
        <!-- 관리자 권한이 있을 때만 관리자 페이지 버튼 표시 -->
        <div th:if="${#authorization.expression('hasRole(''ADMIN'')')}">
            <a th:href="@{/admin}">관리자 페이지로 이동</a>
        </div>
        <!-- 로그아웃 버튼 추가 -->
        <form th:action="@{/logout}" method="post">
            <button type="submit">Logout</button>
        </form>
        </body>
        ```
        
        ```html
        <!DOCTYPE html>
        <html xmlns:th="http://www.thymeleaf.org">
        <head>
            <title>Login</title>
        </head>
        <body>
        <h2>Login</h2>
        <form th:action="@{/login}" method="post">
            <div>
                <label for="username">Email:</label>
                <input type="text" id="username" name="username" required>
            </div>
            <div>
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required>
            </div>
            <button type="submit">Login</button>
        </form>
        <p th:if="${param.error}">사용자 이름 또는 비밀번호가 잘못되었습니다.</p>
        <p th:if="${param.logout}">로그아웃되었습니다.</p>
        <!-- 회원가입 링크 수정 -->
        <p>계정이 없나요? <a th:href="@{/signup}">Sign up</a></p>
        
        <a th:href="@{/oauth2/authorization/kakao}">카카오로 로그인</a>
        
        </body>
        </html>
        ```
        
        ```html
        <!DOCTYPE html>
        <html xmlns:th="http://www.thymeleaf.org">
        <head>
            <title>회원가입</title>
            <style>
                /* 기존 스타일 유지 */
            </style>
        </head>
        <body>
        <h2>회원가입</h2>
        <form th:action="@{/members/signup}" th:object="${memberJoinDto}" method="post">
            <div th:if="${error}" th:text="${error}" style="color: red;"></div>
            <div>
                <label for="name">이름:</label>
                <input type="text" id="name" th:field="*{name}" required>
            </div>
            <div>
                <label for="email">이메일:</label>
                <input type="email" id="email" th:field="*{email}" required>
            </div>
            <div>
                <label for="password">비밀번호:</label>
                <input type="password" id="password" th:field="*{password}" required>
            </div>
            <div>
                <label for="gender">성별:</label>
                <select id="gender" th:field="*{gender}">
                    <option value="1">남성</option>
                    <option value="2">여성</option>
                    <option value="3">선택안함</option>
                </select>
            </div>
            <div>
                <label for="birthYear">출생년도:</label>
                <input type="number" id="birthYear" th:field="*{birthYear}" required>
            </div>
            <div>
                <label for="birthMonth">출생월:</label>
                <input type="number" id="birthMonth" th:field="*{birthMonth}" min="1" max="12" required>
            </div>
            <div>
                <label for="birthDay">출생일:</label>
                <input type="number" id="birthDay" th:field="*{birthDay}" min="1" max="31" required>
            </div>
            <div>
                <label for="address">주소:</label>
                <input type="text" id="address" th:field="*{address}" required>
            </div>
            <div>
                <label for="specAddress">상세주소:</label>
                <input type="text" id="specAddress" th:field="*{specAddress}" required>
            </div>
            <div>
                <label>선호 카테고리:</label>
                <div class="preferCategory">
                    <label><input type="checkbox" name="preferCategory" value="1"> 한식</label>
                    <label><input type="checkbox" name="preferCategory" value="2"> 중식</label>
                    <label><input type="checkbox" name="preferCategory" value="3"> 일식</label>
                </div>
            </div>
            <div>
                <label for="role">역할:</label>
                <select id="role" th:field="*{role}">
                    <option value="USER">일반 사용자</option>
                    <option value="ADMIN">관리자</option>
                </select>
            </div>
            <button type="submit">가입하기</button>
        </form>
        </body>
        </html>
        ```
        
<br>

> **GitHub 저장소 주소**
> 
> 
> https://github.com/seoyeoneel02/SpringBoot/issues/19
> 