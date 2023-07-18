package com.mysite.test_sbb;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TestSbbApplicationTests {
	
	//QuestionRepository 객체 주입
	@Autowired
	private QuestionRepository qr;

	//@Test
	void contextLoads() {
		
		Question q1 = new Question();
		
		q1.setSubject("오늘의 날씨는?");
		q1.setContent("오늘의 날씨는 어떠한지 궁금합니다.");
		q1.setCreateDate(LocalDateTime.now());
		
		qr.save(q1);
		
		
		Question q2 = new Question();
		
		q2.setSubject("스프링 부트 질문");
		q2.setContent("스프링 부트가 무엇인지 어떠한지 궁급합니다.");
		q2.setCreateDate(LocalDateTime.now());
		
		qr.save(q2);
		
		
	}
	
	//@Test
	void contextLoads1() {
		
		Question q3 = new Question();		//Question 은 entity 거기에 값을 세터 주입. 그걸 sql쿼리를 안쓰고 JPA로(qr) 테이블에 값넣음.
		
		q3.setSubject("오늘의 스포츠는?");
		q3.setContent("오늘의 스포츠는 어떠한지 궁금합니다.");
		q3.setCreateDate(LocalDateTime.now());
		
		qr.save(q3);
		
		
		Question q4 = new Question();
		
		q4.setSubject("미국의 날씨는?");
		q4.setContent("미국의 날씨가 어떠한지 궁급합니다.");
		q4.setCreateDate(LocalDateTime.now());
		
		qr.save(q4);
		
		
	}
	
	//@Test
	void recodeCount() {
		
		List<Question> all = qr.findAll();
			//qr : Question 테이블과 연결되어있음
			//findAll() : select * from question;
		
		//assertEquals(기대치, 값)		//한 column이 하나의 question객체임. 그 걸 모은것이 List
		assertEquals(4, all.size()); 	//맞으면 true이므로 오류x
	}
	
	
	
	//JSP 에서는 connection ---> statement ---> rs에넣고 ---> rs루프 ---> dtosetter 주입 ---> list에 담음  
	//======> 이걸 JPA 에서는 List<Question> all = qr.findAll();로 줄임.
	
	@Test
	void subjectTest() {
		List<Question> all = qr.findAll();
		
		Question q = all.get(0);
		
		assertEquals("오늘의 날씨는?", q.getSubject());
	}

}