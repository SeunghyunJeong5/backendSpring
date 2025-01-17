package com.mysite.sbb.user;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mysite.sbb.DataNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor		//DI 중의 하나 : 생성자를 통한 객체 주입방식
@Service
public class UserService {
	// Client ===> Controller ==> Service ==> Repository ===> Entity ===> DB Table
	
	// service에 중복된 코드들을 만들어놓고 controller가 가져와서 Repository에 접속하도록함.
	
	//				    타입				객체
	//private final UserRepository userRepository;
	//단순히 이렇게 하면 객체가 안만들어지는데... bean(Spring Container)의 객체를 주입해줘야함. 방식은 밑에 있음.
	
	//@Autowired			//객체를 DI해서 주입, autowired는 요즘은 잘 안씀.(타입이 같은경우 오류가 날수도 있음)
	//private UserRepository userRepository;
	
	//요즘 방식			클래스 위에 RequiredArgsConstructor 를 사용함.
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	//Controller로 부터 id, password, email 을 받아서 DB에 저장하는 메소드
	public SiteUser create (String username, String email, String password) {				//이거는 client가 던져주는것.
		SiteUser user = new SiteUser();
		
		//매개변수의 값을 받아서 setter 주입(user객체안에)
		user.setUsername(username);
		user.setEmail(email);
		
		
		
		//password는 암호화해서 넣어야함.(암호화 처리 후 setter주입)
		//user.setPassword(password);
		
		//BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		// 메소드 내부에서 암호화 객체를 생성하면 암호화 키가 변경이 되면 수정하기가 어렵다.
		// 이것도 DI통해서 만들어야함...
		// 스프링이 부팅될때 IoC 컨테이너에 객체를 생성하고 DI통해서 사용 
		// 위에서 객체 주입
		
		user.setPassword(passwordEncoder.encode(password));
		
		
		
		
		userRepository.save(user);	//이러면 DB안에 저장이됨.
		
		return user;
	}
	
	
	//새로운 테스트 방법 ===> user service 선택하고 junit test case -- 패키지 옆에 있음
	//next 누르고 원하는 메소드 체크하고 finish
	
	
	
	
	// 사용자 정보를 읽어오는 메소드
	public void selectUser(String username) {
		
		Optional<SiteUser> Ouser=
		userRepository.findByUsername(username);
		
		
		// select가 되면 존재한다는 것
		if (Ouser.isPresent()) {
			System.out.println(username + " 는 존재하는 사용자 입니다.");
		
			SiteUser user = Ouser.get();
			System.out.println("username : " + user.getUsername());
			System.out.println("email : " + user.getEmail());
			System.out.println("password : " + user.getPassword());
		
		}else {
			System.out.println(username + " 는 존재하지 않는 사용자 입니다.");
		}
	}
	
	
	//username을 받아서 DB에서 값을 읽어오는 메소드
	public SiteUser getUser(String username) {
		
		Optional<SiteUser> _siteUser=
		userRepository.findByUsername(username);
		
		if(_siteUser.isPresent()) {
			//값이 DB에 존재할 경우
			return _siteUser.get();
		}else {
			//값이 DB에 존재하지 않는 경우
			throw new DataNotFoundException("사용자가 DB에 존재하지 않습니다.");
		}
		
	}
	
	
}
