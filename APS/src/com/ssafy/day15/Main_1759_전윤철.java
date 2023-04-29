package com.ssafy.day15;
// gold 5. 암호 만들기

import java.io.*;
import java.util.*;

public class Main_1759_전윤철 {
	
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int L, C;
	static char[] given, password; 
	// 모음을 담은 해시맵
	static HashMap<Character, Boolean> oxford = new HashMap<Character, Boolean>() {{
		put('a', true); put('e', true); put('i', true); put('o', true); put('u', true);
	}};
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		password = new char[L];
		given = new char[C];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < C; i++) {
			given[i] = st.nextToken().charAt(0);
		}
		// 제시 문자 사전순 정렬
		Arrays.sort(given);
		combi(0, 0);
		bw.flush();
		bw.close();
	}

	// 조합 탐색
	static void combi(int depth, int start) throws IOException {
		if (depth == L) {
			// 유효한 암호라면 출력
			if (isValid()) {
				print();
			}
			return;
		}
		for (int i = start; i < C; i++) {
			password[depth] = given[i];
			combi(depth+1, i+1);
		}
		
	}

	private static void print() throws IOException {
		for (int i = 0; i < password.length; i++) {
			bw.write(password[i]+"");
		}
		bw.newLine();
	}

	// 암호의 현 위치 문자의 자모음 여부 확인
	private static boolean isValid() {
		int vowelCnt = 0, consonantCnt = 0;
		for (int i = 0; i < password.length; i++) {
			if (oxford.containsKey(password[i])) {
				vowelCnt++;
			} else {
				consonantCnt++;
			}
		}
		return (consonantCnt>=2 && vowelCnt>=1);
	}
}
