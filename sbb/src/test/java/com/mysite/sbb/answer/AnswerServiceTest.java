package com.mysite.sbb.answer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AnswerServiceTest {

	@Autowired
	AnswerService answerService;
	
	@Test
	void testCreate() {
		fail("Not yet implemented");
		
	}

	@Test
	void testGetAnswer() {
		Answer answer =
				answerService.getAnswer(5);
				
				System.out.println(answer.getId());
				System.out.println(answer.getContent());
				System.out.println(answer.getCreateDate());
				System.out.println("질문을 쓴 사용자정보" + answer.getQuestion().getAuthor().getUsername());
				System.out.println("답변을 쓴 사용자정보" + answer.getAuthor().getUsername());
	}

	@Test
	void testModify() {
		fail("Not yet implemented");
	}
	
	
	@Test
	void testDelete() {
		Answer answer = new Answer();
		answer.setId(5);
		
		
		answerService.delete(answer);
	}

	@Test
	void testAnswerService() {
		fail("Not yet implemented");
	}

}
