//package com.ssafy.day01;
//
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.OutputStreamWriter;
//import java.util.StringTokenizer;
//
//public class Solution_1289_전윤철 {
//
//	// static으로 입출력 선언
//	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//	// 테스트 케이스 회차별 정답을 담는 배열
//	static int[] result;
//	
//	public static void main(String[] args) throws IOException {
//		
//		// 반복 횟수T 입력
//		int T = Integer.parseInt(br.readLine());
//		result = new int[T];
//		String input;
//		
//		// 회차별 입력값을 받고 함수 실행
//		for (int t=0; t<T; t++) {
//			input = br.readLine();
//			restore(t, input, 0);
//		}
//		
//		// 회차별 정답 출력
//		for (int t=0; t<T; t++) {
//			bw.write("#" + (t+1) + " " + result[t]);
//			bw.newLine();
//		}
//		
//		// 버퍼에 있는 값 화면에 출력
//		bw.flush();
//		bw.close();
//	}
//	
//	// 현재 회차, 입력값, 현재 위치의 복구해야할 목표값을 매개변수로 받는 함수
//	static void restore(int t, String input, int cur) {
//		for (int i=0; i<input.length(); i++) {
//			// 입력값의 현재 위치 값이 목표값과 다를 경우
//			if ( (input.charAt(i)-'0') != cur ) {
//				// 변경 회수 증가
//				result[t]++;
//				// 목표값을 바꿈
//				cur = cur==0 ? 1 : 0; 
//			}
//		}
//	}
//
//}
package com.ssafy.day01;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution_1289_전윤철 {

	// static으로 입출력 선언
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	// 테스트 케이스 회차별 정답을 담는 배열
	static int[] result;
	
	public static void main(String[] args) throws IOException {
		
		// 반복 횟수T 입력
		int T = Integer.parseInt(br.readLine());
		result = new int[T];
		String input;
		// 회차별 입력값을 받고 함수 실행
		for (int t=0; t<T; t++) {
			input = br.readLine();
			restore(t, input);
		}
		
		// 회차별 정답 출력
		for (int t=0; t<T; t++) {
			bw.write("#" + (t+1) + " " + result[t]);
			bw.newLine();
		}
		
		// 버퍼에 있는 값 화면에 출력
		bw.flush();
		bw.close();
	}
	
	// 현재 회차, 입력값, 현재 위치의 복구해야할 목표값을 매개변수로 받는 함수
	static void restore(int t, String input) {
		int cur = 0;
		for (int i=0; i<input.length(); i++) {
			// 입력값의 현재 위치 값이 목표값과 다를 경우
			if ( (input.charAt(i)-'0') != cur ) {
				// 변경 회수 증가
				result[t]++;
				// 목표값을 바꿈
				cur = cur==0 ? 1 : 0; 
			}
		}
	}

}
