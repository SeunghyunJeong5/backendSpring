package com.mysite.test_sbb;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {
	//AnswerRepository는 JpaRepository의 모든 메소드를 상속 받음.
		//메소드를 사용해서 Answer 테이블을 CRUD 할수있음.(entity class==> 테이블임)
	// findAll() : select * from Answer;
	// save (answer) : insert into Answer values(값)

}
