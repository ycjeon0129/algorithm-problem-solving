package com.ssafy.day05;
// D4. 괄호 짝짓기
// Stack

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_1218_전윤철 {

	// 입력받을 문자수, 각 괄호의 현재 개수
	static int N, A, B, C, D;
	static String str;
	static boolean valid;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T=10;
		for (int t=0; t<T; t++) {
			
			N = Integer.parseInt(br.readLine());
			str = br.readLine();
			valid = true;
			A = B = C = D = 0;
			isValid();
			int result = valid ? 1 : 0;
			sb.append("#").append(t+1).append(" ").append(result).append("\n");
		}
		System.out.println(sb.toString());
	}

	static void isValid() {
		for (int i=0; i<N; i++) {
			// 문자열의 각 위치 문자에 대한 연산
			translate(str.charAt(i));
			// 매번 조건 확인
			if (isBurst()) {
				valid = false;
				return;
			}
		}
		
		// 문자열이 끝난 뒤 모든 괄호가 닫혔는지 확인
		if (A!=0 || B!=0 || C!=0 || D!=0 ) {
			valid = false;
		}
		
	}
	
	// 특정 괄호가 열린 횟수보다 닫힌 횟수가 많은지 확인
	static boolean isBurst() {
		if (A<0 || B<0 || C<0 || D<0) {
			return true;
		}
		return false;
	}
	
	// 각 문자에 대한 처리
	static void translate(char cur) {
		switch (cur) {
		case '(':
			A++;
			break;
		case '[':
			B++;
			break;
		case '{':
			C++;
			break;
		case '<':
			D++;
			break;
		case ')':
			A--;
			break;
		case ']':
			B--;
			break;
		case '}':
			C--;
			break;
		case '>':
			D--;
			break;
		}		
	}

}
