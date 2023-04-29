package com.ssafy.day04;
// silver 3. DNA 비밀번호

import java.util.Scanner;

public class Main_12891_전윤철 {
	static int S, P;
	// 문자열
	static String DNA;
	// 비밀번호가 유효하기 위한 문자 별 최소 갯수 저장 배열
	static int[] ACGT = new int[4];
	// 현재 부분 문자열에서의 문자 별 갯수, 유효 비밀번호 갯수
	static int A, C, G, T, num;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		S = sc.nextInt();
		P = sc.nextInt();
		DNA = sc.next();
		for (int i=0; i<4; i++) {
			ACGT[i] = sc.nextInt();
		}
		slidingWindow();
		System.out.println(num);
	}
	
	static void slidingWindow() {
		// 첫 부분 문자열 설정 및 유효 확인
		for (int i=0; i<P; i++) {
			getElement(i);
		}
		isValid();
		// 슬라이딩 윈도우 이동하며 유효 확인
		for (int i=P; i<S; i++) {
			getElement(i);
			putElement(i-P);
			isValid();
		}
	}
	
	// 비밀번호 유효 조건을 성립한다면 num 개수 증가
	static void isValid() {
		if (A>=ACGT[0] && C >=ACGT[1] && G >=ACGT[2]  && T>=ACGT[3]  ) {
			num++;
		}
		
	}
	
	// 해당 위치 문자 개수 저장
	static void getElement(int i) {
		switch (DNA.charAt(i)) {
		case 'A':
			A++;
			break;
		case 'C':
			C++;
			break;
		case 'G':
			G++;
			break;
		case 'T':
			T++;
			break;
		}
	}
	
	static void putElement(int i) {
		switch (DNA.charAt(i)) {
		case 'A':
			A--;
			break;
		case 'C':
			C--;
			break;
		case 'G':
			G--;
			break;
		case 'T':
			T--;
			break;
		}
	}
}
