package com.mysite.test_sbb;

import org.springframework.data.jpa.repository.JpaRepository;

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









}