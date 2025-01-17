package com.mysite.test_sbb;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mysite.test_sbb.Answer;
import com.mysite.test_sbb.AnswerRepository;
import com.mysite.test_sbb.Question;

@SpringBootTest
class TestSbbApplicationTests {

	// QuestionRepository 객체 주입
	@Autowired
	private QuestionRepository qr;

	// AnswerRepository 객체 주입 : Answer Entity(테이블) 을 CRUD하는것...
	@Autowired // 객체 주입을 자동으로!
	private AnswerRepository ar;

	// @Test
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

	// @Test
	void contextLoads1() {

		Question q3 = new Question(); // Question 은 entity 거기에 값을 세터 주입. 그걸 sql쿼리를 안쓰고 JPA로(qr) 테이블에 값넣음.

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

	// @Test
	void recodeCount() {

		List<Question> all = qr.findAll();
		// qr : Question 테이블과 연결되어있음
		// findAll() : select * from question;

		// assertEquals(기대치, 값) //한 column이 하나의 question객체임. 그 걸 모은것이 List
		assertEquals(4, all.size()); // 맞으면 true이므로 오류x
	}

	// JSP 에서는 connection ---> statement ---> rs에넣고 ---> rs루프 ---> dtosetter 주입 --->
	// list에 담음
	// ======> 이걸 JPA 에서는 List<Question> all = qr.findAll();로 줄임.

	//@Test
	void subjectTest() {
		List<Question> all = qr.findAll();

		Question q = all.get(0);

		assertEquals("오늘의 날씨는?", q.getSubject());
	}
	
	
	
	
	
	

	 //@Test
	void idSearch() {

		// Optional ==> null을 쉽게 처리해주는 객체
		// qr.findById(1) ===> select * from question where id = 1;
		Optional<Question> o = qr.findById(1);
		// import 지바 utl

		// isPresent() : Optional에 저장된 객체가 null이 아닐때
		if (o.isPresent()) {
			Question q = o.get(); // Optional에 저장된 객체를 끄집어내서 q변수에 할당.

			// assertEquals(1, q.getId());
			assertEquals("오늘의 날씨는?", q.getSubject());
		}

	}

	 //@Test
	void subjectSearch() {
		List<Question> all = qr.findBySubject("오늘의 날씨는?");

		Question q = all.get(0);

		assertEquals("오늘의 날씨는?", q.getSubject());
		assertEquals(1, all.size());

	}

	// @Test
	void contentSearch() {
		List<Question> all = qr.findByContent("오늘의 날씨는 어떠한지 궁금합니다.");

		Question q = all.get(0);

		assertEquals("오늘의 날씨는 어떠한지 궁금합니다.", q.getContent());
		assertEquals(1, all.size());

	}

	 //@Test
	void subjectSearchLike() {
		// select * from question where subject like '%날씨%';
		List<Question> all = qr.findBySubjectLike("%날씨%");

		Question q = all.get(0);

		assertEquals(1, q.getId());
		assertEquals(2, all.size());

	}

	// @Test
	void contentSearchLike() {
		List<Question> all = qr.findByContentLike("%궁금%");

		Question q = all.get(0);

		assertEquals(1, q.getId());
		assertEquals(2, all.size());

	}

	// 제목과 내용을 동시에 검색
	// @Test
	void subjectcontentSearchLike() {
		// select * from question where subject like '%?%' or content like '%?%'
		List<Question> all = qr.findBySubjectLikeOrContentLike("%날씨%", "%부트%");

		Question q = all.get(0);

		assertEquals("오늘의 날씨는 어떠한지 궁금합니다.", q.getContent());
		assertEquals(3, all.size());

	}

	/*
	 * //@Test void sortCreateAsc() { //날짜를 기준으로 오름차순 정렬해서 List에 저장 List<Question>
	 * all = qr.findAllOrderByCreateDateAsc();
	 * 
	 * System.out.println("============ 날짜를 오름차순 정렬 출력 : 시작 ============="); for(int
	 * i = 0; i<all.size(); i++) { System.out.println("======"+i+"===========");
	 * 
	 * Question q = all.get(i);
	 * 
	 * System.out.println(q.getId()); System.out.println(q.getCreateDate());
	 * System.out.println(q.getSubject()); System.out.println(q.getContent());
	 * 
	 * } System.out.println("============ 날짜를 오름차순 정렬 출력 : 끝 =============");
	 * 
	 * }
	 * 
	 */
	// select * from question where subject Like '%?%' order by create_date asc;
	 //@Test
	void searchSubjectAndSort() {
		// 날짜를 기준으로 오름차순 정렬해서 List에 저장
		List<Question> all = qr.findBySubjectLikeOrderByCreateDateAsc("%날씨%");

		assertEquals(2, all.size());

		System.out.println("========콘솔 출력 시작===========");
		for (int i = 0; i < all.size(); i++) {
			System.out.println("======Question : " + i + "==============");
			Question q = all.get(i);
			System.out.println(q.getId());
			System.out.println(q.getCreateDate());
			System.out.println(q.getSubject());
			System.out.println(q.getContent());

		}

		System.out.println("========콘솔 출력 끝===========");

	}

	// select * from question where subject Like '%?%' order by create_date asc;
	// @Test
	void searchSubjectAndSortDesc() {
		// 날짜를 기준으로 오름차순 정렬해서 List에 저장
		List<Question> all = qr.findBySubjectLikeOrderByCreateDateDesc("%날씨%");

		assertEquals(2, all.size());

		System.out.println("========콘솔 출력 시작===========");
		for (int i = 0; i < all.size(); i++) {
			System.out.println("======Question : " + i + "==============");
			Question q = all.get(i);
			System.out.println(q.getId());
			System.out.println(q.getCreateDate());
			System.out.println(q.getSubject());
			System.out.println(q.getContent());

		}

		System.out.println("========콘솔 출력 끝===========");

	}

	// 값 넣기 : save(), insert
	// Question 테이블에 값 넣기, Answer 테이블에 값 넣기
	// @Test
	void insertQuestion() {
		Question q = new Question();

		q.setSubject("제목 - 스프링 부트는 프레임워크 입니까?");
		q.setContent("내용 - 스프링 부트는 프레임워크 입니다. 아주 쉽습니다. IoC/DI, AOP, PSA기능이 적용");
		q.setCreateDate(LocalDateTime.now());

		qr.save(q);

	}

	// update : 수정 , save()
	// @Test
	void updateQuestion() {
		Question q = new Question();

		q.setId(5); // id 값이 있으면 update 없으면 save

		q.setSubject("수정된 제목2입니다.");
		q.setContent("수정된 내용2입니다.");
		q.setCreateDate(LocalDateTime.now());

		qr.save(q);
	}

	// 삭제
	// @Test
	void deleteQuestion() {
		Question q = new Question();

		q.setId(5);

		qr.delete(q);
	}

	// Answer 테이블에 값을 insert : Question 테이블을 참조해서 값을 넣어야한다.
	// @Test
	void insertAnswer() {

		Question q = new Question();
		q.setId(1);

		Answer a = new Answer();

		a.setContent("오늘은 날씨가 매우 맑습니다.");
		a.setCreateDate(LocalDateTime.now());
		a.setQuestion(q);

		ar.save(a);
	}

	// @Test
	void insertAnswer2() {

		Question q = new Question();
		q.setId(2);

		Answer a = new Answer();

		a.setContent("스프링부트 질문의 답변1");
		a.setCreateDate(LocalDateTime.now());
		a.setQuestion(q);

		ar.save(a);

		Answer aa = new Answer();

		aa.setContent("스프링부트 질문의 답변2");
		aa.setCreateDate(LocalDateTime.now());
		aa.setQuestion(q);

		ar.save(aa);
	}

	// @Test
	void insertAnswer3() {

		Question q = new Question();
		q.setId(3);

		Answer a = new Answer();

		a.setContent("야구");
		a.setCreateDate(LocalDateTime.now());
		a.setQuestion(q);

		ar.save(a);

		Answer aa = new Answer();

		aa.setContent("축구");
		aa.setCreateDate(LocalDateTime.now());
		aa.setQuestion(q);

		ar.save(aa);

		Answer aaa = new Answer();

		aaa.setContent("농구");
		aaa.setCreateDate(LocalDateTime.now());
		aaa.setQuestion(q);

		ar.save(aaa);
	}

}
