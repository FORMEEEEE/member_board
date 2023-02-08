package edu.kh.jdbc.member.view;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import edu.kh.jdbc.main.view.MainView;
import edu.kh.jdbc.member.model.service.MemberService;
import edu.kh.jdbc.member.vo.Member;


public class MemberView {
	
	
	private MemberService mService = new MemberService();
	private Scanner sc = new Scanner(System.in);
	
	// 로그인 회원 정보 저장용 변수
	private Member loginMember = null;
	
	private Member user = null;
	
	public void memberMenu(Member loginMember) {
		
		// 전달 받은 로그인 회원 정보를 필드에 저장
		this.loginMember = loginMember;
		this.user = user;
		
		int input = -1;
		
		do {
			try {
				System.out.println("<회원기능>");
				System.out.println("1. 내 정보 조회");
				System.out.println("2. 회원 목록 조회");
				System.out.println("3. 내 정보 수정(이름, 성별)");
				System.out.println("4.  비밀번호 변경");
				System.out.println("5. 회원 탈퇴");
				System.out.println("0. 뒤로가기");
				
				System.out.print("\n메뉴 선택 : ");
				input = sc.nextInt();
				
				sc.nextLine();
				
				System.out.println();
				
				switch(input) {
				case 1 : selectMyInfo(loginMember); break;
				case 2 : selectAll(); break;
				case 3 : break;
				case 4 : break;
				case 5 : break;
				case 0 : return;
				default : System.out.println("숫자 잘못 입력");break;
				
				}
			}catch(InputMismatchException e) {
				e.printStackTrace();
				System.out.println("입력 형식이 올바르지 않음");
			}
				
			
		}while(input != 0);
			
		
	}

	public void selectMyInfo(Member loginMember) {
		
		
		System.out.println("<내 정보 조회>");
		
			System.out.println("번호  |  아이디  |   이름   |   성별     |           가입일           | " );
			System.out.println("------------------------------------------------------------------------------------------------");
			System.out.printf(" %3d   | %6s | %2s    | %6s    | %2s ",
					loginMember.getMemberNo(),
					loginMember.getMemberId(),
					loginMember.getMemberName(),
					loginMember.getMemberGender(),
					loginMember.getEnrollDate());
			System.out.println();
			
			}
	public void selectAll() {	
		
		
		try {
			List<Member> memberList = mService.selectAll();
			System.out.println(" 아이디  |   이름   |   성별     |" );
			System.out.println("------------------------------------------------------------------------------------------------");
			for(Member loginMember : memberList)
			System.out.printf("\n%3s   | %6s | %2s ",
					loginMember.getMemberId(),
					loginMember.getMemberName(),
					loginMember.getMemberGender());
					
			System.out.println();
			
	
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		
	}
		
}
