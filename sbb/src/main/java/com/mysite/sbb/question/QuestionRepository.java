package com.mysite.sbb.question;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
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

	
	
	
	
	//JPA 메소드를 사용한 테이블 검색 : 기본적으로 2개의 메소드는 자동으로 등록되어 있음.
			//그외는 등록해서 사용해야한다.
		//findAll() : select * from question ===> 모든 레코드를 출력
		//findById(1) : select * from question where id = 1 ===>primary key가 들어간 컬럼.(id를 기준으로 레코드 1개)
	
		//다른 컬럼을 검색하려면 설정이 필요함.
	
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
	//List<Question> findAllByOrderByCreateDateAsc();
	
	//select * from question order by create_date desc;
	//List<Question> findAllByOrderByCreateDateDesc();
	
	//제목을 기준으로 검색 후 날짜를 기준으로 오름차순 정렬 후 출력
	//select * from question where subject Like '%?%' order by create_date asc;
	List<Question> findBySubjectLikeOrderByCreateDateAsc(String subject);

	//제목을 기준으로 검색 후 날짜를 기준으로 내림차순 정렬 후 출력
	//select * from question where subject Like '%?%' order by create_date desc;
	List<Question> findBySubjectLikeOrderByCreateDateDesc(String subject);

	//검색기능을 사용(select, find)
	
	//save()	: insert, update
	
	//delete()  : delete
	
	
	//제목을 기준으로 오름차순 정렬 
		List<Question> findAllByOrderBySubjectAsc(); 
		
		//제목을 기준으로 내림차순 정렬 
		List<Question> findAllByOrderBySubjectDesc(); 
		
		//날짜를 기준으로 검색 
		//select * from question order by creat_date asc; 
		List<Question> findAllByOrderByCreateDateAsc();
		//select * from question order by creat_date desc; 
		List<Question> findAllByOrderByCreateDateDesc();
	
	
		// Question (질문) 의 페이징 처리 
		Page<Question> findAll(Pageable pageable); 
	
	
		// 검색어를 처리한 페이징 처리 메소드(공백처리가 중요), kw값(입력변수)을 받아서 넣겠다.
		// 주의 : 테이블 명이 아니라 엔티티명으로 매핑(실제엔티티의 이름으로)
		
		@Query("select distinct q "
				+ "from Question q "
				+ "	LEFT OUTER JOIN SiteUser u1 "
				+ "		ON q.author= u1 "
				+ "	LEFT OUTER JOIN Answer a "
				+ "		ON a.question = q "
				+ "	LEFT OUTER JOIN SiteUser u2 "
				+ "		ON a.author = u2 "
				+ "where q.subject like %:kw% "
				+ "	  or q.content like %:kw% "
				+ "	  or u1.username like %:kw% "
				+ "	  or a.content like %:kw% "
				+ "	  or u2.username like %:kw%"
				)
		Page<Question> findAllByKeyword(@Param("kw") String kw, Pageable pageable);
	
	
	
	




}
