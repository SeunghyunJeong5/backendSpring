package com.mysite.test_sbb;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mysite.test_sbb.Question;

public interface QuestionRepository extends JpaRepository <Question, Integer>{
		// QuestionRepository : Question 테이블을 select, insert, update, delete 하는 메소드 구현
	

	//client ===> Controller ===> Service ====> Repository ====> Entity 클래스 ====> DB
	//							(비지니스 로직)	(DAO, DB를 직접접근)

	//클라에서 변수값이 dto로 자동으로 컨트롤러로 넘어가고 또 dto로 넘어가고 레파지토리까지 dto로 쭉 넘어감.
	

	// Repository : 메소드를 사용해서 DB의 테이블을 select, insert, update, delete
		//JpaRepository <Question, Integer> 인터페이스를 구현해서 생성해야한다.(crud하는 메소드를 전달받아서!)
				//Question : Entity 클래스, 
				//Integer : Question Entity 클래스의 Primary Key 컬럼의 DataType 

	//JPA 메소드
		//findAll() : select
		//save() 	: insert, update
		//delete()  : delete


	//select * from question where subject = 'String subject';
		List<Question> findBySubject(String subject);	//List에 담는 이유는 String subject로 검색했을때 여러개로 결과가 나올수있으므로.
														// 검색된 결과가 1개일대는 Optional에 저장.
		
		//select * from question where content = 'String content';
		List<Question> findByContent(String content);
		
		
		//특정 컬럼의 값을 검색 : Like 검색 ===> 레코드가 여러개 검색 : List
		//Select * from question where subject like '%?%';
		List<Question> findBySubjectLike(String subject);
		
		//Select * from question where content like '%?%';
		List<Question> findByContentLike(String content);
		
		//제목과 내용컬럼에서 검색
		//Select * from question where subject like '%?%' or content like '%?%';
		List<Question> findBySubjectLikeOrContentLike(String subject, String content);
		
		//정렬해서 출력하는 메소드 생성 ===> 간단하고 자주 사용하는것, 복잡한 쿼리는 JPQL, QueryDSL 사용
		//날짜를 기준으로 오름차순 정렬(asc) : 1~9, A~Z, ㄱ~ㅎ
		//날짜를 기준으로 내림차순 정렬(desc)
		
		//select * from question order by create_date asc;
		//List<Question> findOrderByCreateDateAsc();
		
		//select * from question order by create_date desc;
		//List<Question> findOrderByCreateDateDesc();
		
		//제목을 기준으로 검색 후 날짜를 기준으로 오름차순 정렬 후 출력
		//select * from question where subject Like '%?%' order by create_date asc;
		List<Question> findBySubjectLikeOrderByCreateDateAsc(String subject);

		//제목을 기준으로 검색 후 날짜를 기준으로 내림차순 정렬 후 출력
		//select * from question where subject Like '%?%' order by create_date desc;
		List<Question> findBySubjectLikeOrderByCreateDateDesc(String subject);

		//검색기능을 사용(select, find)
		
		//save()	: insert, update
		
		//delete()  : delete






}
