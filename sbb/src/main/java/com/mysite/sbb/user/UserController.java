package com.mysite.sbb.user;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping ("/user")
@RequiredArgsConstructor
@Controller
public class UserController {
	
	private final UserService userService;
	
	//사용자 정보를 입력하는 요청(회원가입)
	@GetMapping("/signupTest")		//http://localhost:9696/user/signupTest
		// http://localhost:9696/user/signupTest?username=user3&email=user3@aaa.com&password=1234				==>이렇게 하면 값이 테이블안에 저장까지 됨.
	public String signupTest(@RequestParam(value = "username") String username  	//get 요청을 파란색 변수로 받아서 갈색변수에 넣어줌.
			, @RequestParam(value = "email") String email
			, @RequestParam(value = "password") String password
			) {
		
		System.out.println("==========get 방식으로 넘어오는 값을 @RequestParam 으로 받아서 출력===============");
		System.out.println("username : " + username);
		System.out.println("email : " + email);
		System.out.println("password : " + password);
		
		SiteUser user = 
		userService.create(username, email, password);
		
		return "redirect:/";		//loot페이지 호출
	}
	
	
	
	
	@GetMapping("/signup")		//http://localhost:9696/user/signup
	public String signup(UserCreateForm userCreateForm) {		//()안에 저걸 꼭 넣어줘야 오류가 안뜸
		
		return "signup_form";		//signup_form.html 호출
	}
	
	
	
	
	
	@PostMapping("/signup")		// /user/signup
	public String signup(@Valid UserCreateForm userCreateForm, BindingResult bindingResult) {
															//오류가 뜨면 bindingresult에 저장.
		
		//client Form 에서 넘긴 값이 잘 들어오는지 출력
		/*
		System.out.println("username : " + userCreateForm.getUsername());
		System.out.println("password1 : " + userCreateForm.getPassword1());
		System.out.println("password2 : " + userCreateForm.getPassword2());
		System.out.println("email : " + userCreateForm.getEmail());
		*/
		
		
		//1. userCreateForm 유효성 확인
		if (bindingResult.hasErrors()) {	//유효성 검증에 실패하면 bindingResult에 error가 들어감
			
			return "signup_form";
			
		}
		
		//2. userCreateForm : password1, password2 필드의 값이 동일한지 확인
		if(!userCreateForm.getPassword1().equals(userCreateForm.getPassword2())){
			//password1, password2 필드의 값이 같지 않으면 실행하는 코드
			
			//오류메세지를 넣어서 클라이언트에게 전송
			bindingResult.rejectValue("password2", "passwordInCorrect","2개의 패스워드가 일치하지 않습니다.");
			
			return "signup_form";
		}
		
			
		//3. Service에 create(username, email, password)를 호출해서 저장(unique 컬럼에서 걸러지는지도 확인)
		try {
		
		
		userService.create(userCreateForm.getUsername(), userCreateForm.getEmail(), userCreateForm.getPassword1());	
	
		
		}catch(Exception e) {
			//e.printStackTrace(); 	//로그에 출력
			//DB에 username, email이 값이 존재하는 경우
			
			//오류 메시지를 강제로 발생시킴
			bindingResult.reject("signupFailed", "이미 등록된 사용자나 메일주소 입니다.");
			//bindingResult.reject("signupFailed", e.getMessage()); //개발자만 보는 오류정보(브라우저에 보임)
			
			
			return "signup_form";
			
		}
		
		
		return "redirect:/";
	}

	//로그인 뷰페이지 전송 : 
	@GetMapping("/login")	//http://localhost:9696/user/login
	public String login_form() {
		
		
		return "login_form";
	}
	
	// 중요 : /user/login : post 요청은 SpringSecurity에서 자동으로 처리
		//SecurityConfig.java
	
	
	
	
	
	
}
