package com.mysite.sbb.question;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class QuestionServiceTest {
	
	//test는 requirearg...안됨
	@Autowired
	private QuestionService questionService;

	@Test
	void testGetList() {
		fail("Not yet implemented");
	}

	@Test
	void testGetListInt() {
		fail("Not yet implemented");
	}

	@Test
	void testGetQuestion() {
		
		Question question =
		questionService.getQuestion(1);
		//1번의 값에 대한 객체의 값을 밑에 출력
		
		System.out.println(question.getId());
		System.out.println(question.getSubject());
		System.out.println(question.getContent());
		System.out.println(question.getAuthor().getUsername());
					//getAuthor : 사이트 유저객체
		System.out.println("Question 객체 : 출력 성공");
	}

	@Test
	void testDelete() {
		//Delete할 Question객체
		Question question =
				questionService.getQuestion(1);
		//1번 id에 해당하는 값을 받아옴
		questionService.delete(question);
		
		System.out.println("레코드 삭제 성공");
	}

	@Test
	void testQuestionService() {
		
		Question question =
				questionService.getQuestion(1);
		//1번 id에 해당하는 값을 받아옴
		
		questionService.modify(question, "제목수정-금요일", "내용수정-금요일");
		
		System.out.println("글 수정 성공됨.");
	}

}
