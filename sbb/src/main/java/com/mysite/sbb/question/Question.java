package com.mysite.sbb.question;



import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import org.springframework.data.annotation.CreatedDate;

import com.mysite.sbb.answer.Answer;
import com.mysite.sbb.user.SiteUser;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;	//spring boot 3.0,
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
//import javax.persistence.Entity;	//spring boot 2.0

@Getter
@Setter


@Entity
public class Question {
		//질문 테이블 : 부모 테이블
	
	
	//@Entity : 클래스 위에 부여, DataBase의 테이블과 매칭되는 자바 클래스
		// Question : 테이블명
		//		변수 : 컬럼명
	//테이블의 컬럼명을 지정
	//JPA를 사용해서 테이블의 컬럼을 정의할 시 반드시 Primary key를 정의해야함.
		//@Id 어노테이션 : Primary key 컬럼을 부여, 중복된 데이터를 넣을 수 없음.
		//GeneratedValue : 자동으로 값을 증가, 시퀀스(오라클), auto_increament(MYSQL)
				// identity(MSSQL)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; 
	
	@Column (length = 200)
	private String subject; 
	
	//@Column(columnDefinition = "Text")
	@Column(length=4000)
	private String content; 
	
	@CreatedDate
	private LocalDateTime createDate; 
	
	private LocalDateTime modifyDate;
	
	
	//실제 테이블에는 적용되지 않음.
	//해당 질문에 대한 모든 답변을 불러오는 컬럼
	//Question 객체의 id 필드에 들어오는 값에 대한 Answer 테이블의 모든 값을 List에 담아서 온다.
	//getAnswerList()
		//질문 : one, 답변 : Many
		//cascade = cascade = CascadeType.REMOVE) ==> 해당 질문이 제거될 경우, 그 질문에 대한 답변도 모두 함께 제거되도록하는 옵션.
	
	@OneToMany (mappedBy = "question", cascade = CascadeType.REMOVE)		
	private List<Answer> answerList;
	
	
	
	
	
	
	
	/*
	@Column(length= 100)
	private String name;
	
	@Column(length= 100)
	private String age;
	*/
	
	
	
	
	//글쓴이 컬럼을 추가함.	//컬럼을 추가, 제거하더라도 유지보수가 최소화
		//SiteUser 테이블의 특정 레코드를 참조해서 입력
	//Foreign Key설정
	@ManyToOne
	private SiteUser author;
	
	
	
	
	//한 사용자는 여러질문에 추천을 할수있고, 한 질문은 여러사용자가 추천을 할수있으므로 다대다
	//질문과 추천인의 관계는 다:다
	//Set은 중복된 값이 올 수 없다.(한사람이 중복투표가 불가능하게)
	//QUESTION_VOTER 테이블이 생성됨 : QUESTION_ID(QUESTION의 ID컬럼참조), VOITER_ID(SITE_USER의 ID컬럼참조) 컬럼이 자동으로 만들어짐
	@ManyToMany
	Set<SiteUser> voter;
	
	
	
	
	
	
}
