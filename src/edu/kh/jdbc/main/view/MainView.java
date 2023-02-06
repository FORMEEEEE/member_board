package edu.kh.jdbc.main.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import edu.kh.jdbc.main.model.service.MainService;
import edu.kh.jdbc.member.vo.Member;

public class MainView {

	private Scanner sc = new Scanner(System.in);
	private MainService service = new MainService();
	
	// 로그인 된 회원정보를 저장하는 객체 참조 변수
	private static Member LoginMember = null;
	// 로그인 안했으면 null
	// 로그인 했으면 null이 아님 
	
	
	/** 
	 * 메인 메뉴 출력 메서드
	 */
	public void mainMenu() {
		
		int input = -1;
		
		do {
		
		try {
			if(LoginMember == null) {
				
			
			System.out.println("******회원제 게시판 프로그램******");
			System.out.println("1. 로그인");
			System.out.println("2. 회원가입");
			System.out.println("0. 프로그램 종료");
			
			System.out.print("\n메뉴 선택 : ");
		    input = sc.nextInt();
			sc.nextLine(); // 입력버퍼 개행문자
			System.out.println();
			
			switch(input) {
			
			case 1 : login(); break;
			case 2 : signUp(); break;
			case 0 : 
				System.out.println("프로그램 종료");
				break;
			default : System.out.println("번호 재입력");
			
			}
			
			}else {
				
			}
			
			
		}catch(InputMismatchException e) {
			System.out.println("\n 입력형식이 올바르지 않음");
			e.printStackTrace();
			sc.nextLine();
		}
			
			
		}while(input != 0);
		

		/*
		 * 1. 로그인
		 * 2. 회원가입
		 * 0. 프로그램 종료
		 * 
		 * 
		 * -------------------------
		 *  
		 * 로그인 했을 때 
		 *  
		 *  1. 회원 기능
		 *  2. 계시판 기능
		 *  0. 로그아웃
		 *  99. 프로그램
		 * 
		 * */
		
		
	}

	/**
	 * 로그인 화면
	 */
	private void login() {
		
		System.out.print("[로그인]");
		
		System.out.print("아이디 : ");
		String memberId = sc.next();
		
		System.out.print("비밀번호 : ");
		String memberPw = sc.next();
		
		//로그인 서비스 호출 후 조회 결과를 LoginMember에 저장
		try {
			LoginMember = service.login(memberId,memberPw);
			
			if(LoginMember != null) {
				System.out.println(LoginMember.getMemberName() + "님 환영합니다");
			}else {
				System.out.println("로그인 실패");
			}
			
			
			
			
			
			
			
			
		} catch (Exception e) {
			System.out.println("로그인 중 예외발생");
			e.printStackTrace();
		}
		
		
	}


	private void signUp() {
		System.out.println("[회원가입]");
		
		String memberId = null;
		
		String memberPw1 = null;
		String memberPw2 = null;
		
		String memberName = null;
		String memberGender = null;
		
		try {
			while(true) {
				System.out.print("아이디 : ");
				memberId = sc.next();
				
				//입력받은 아이디를 매개변수로 전달하여
				// 중복여부를 검사하는 서비스 호출
				// 후 결과 반환 받기
				
				int result = service.idDupCheck(memberId); 
				
				System.out.println();
				
				if(result == 0) {
					System.out.print("사용가능한 아이디");
					break;
					
				}else {
					 System.out.println("이미 사용중인 아이디");
				}
				System.out.println();
			}
			
			// 비밀번호 입력
			// 비밀번호 / 비밀번호 확인이 일치 할때까지 무한 반복
			
			while(true) {
				System.out.print("비밀번호 입력 : ");
				memberPw1 = sc.next();
				System.out.print("비밀번호 확인 : ");
				memberPw2 = sc.next();
				
				if(memberPw1.equals(memberPw2)) {
					System.out.print("비밀번호 일치함");
					break;
				}else {
					System.out.println("비밀번호가 일치하지 않음");
				}
				
				System.out.println();
				
			}
			
			//이름 입력 
			System.out.print("이름 입력 : ");
			memberName = sc.next();
			
			// 성별
			// m또는 f가 입력될때까지 반복
			while(true) {
				System.out.print("성별 (M/F): ");
				memberGender = sc.next().toUpperCase();
				
				System.out.println();
				if(memberGender.equals("M") || memberGender.equals("F")) {
					break;
				}else {
					System.out.println("M 또는 F만 입력");
				}
				System.out.println();
			}
			
			
			
			
			
			// 아이디, 비밀번호, 이름, 성별 입력 완료
			// 하나의 vo에 담아서 서비스 호출 후 결과 반환 받기
			Member member = new Member(memberId,memberPw1, memberName,memberGender);
			
			// 회원가입은 insert >> int형
			int result = service.signUp(member);
			
			//서비스 처리 결과에 따른 출력 화면제어
			System.out.println();
			if(result > 0) {
				System.out.println("회원가입 성공");
			}else {
				System.out.println("회원가입 실패");
			}
			System.out.println();
			
		}catch(Exception e) {
			System.out.println("\n<회원가입중 예외발생>\n");
			e.printStackTrace();
		}
		
		
		
		
		
	}




}
