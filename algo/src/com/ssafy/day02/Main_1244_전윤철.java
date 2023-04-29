package com.ssafy.day02;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_1244_전윤철 {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static boolean[] swit;

	public static void main(String[] args) throws IOException {
		// 스위치 배열의 길이 입력
		int S = Integer.parseInt(br.readLine());
		swit = new boolean[S];
		// 스위치 상태 입력
		st = new StringTokenizer(br.readLine());
		for (int s=0; s<S; s++) {
			swit[s] = (st.nextToken().equals("1")) ? true : false;
		}
		// 학생 수 입력
		int num = Integer.parseInt(br.readLine());
		List<Pair> students = new ArrayList<Pair>();
		// students 리스트에 Pair 클래스의 객체로 학생 저장
		for (int i=0; i<num; i++) {
			st = new StringTokenizer(br.readLine());
			boolean boy = (st.nextToken().equals("1")) ? true : false;
			int position = Integer.parseInt(st.nextToken())-1;
			students.add(new Pair(boy, position));
		}
		
		// 리스트 내 학생을 순서대로 일 시켜
		for (Pair s : students) {
			light(s.boy, s.position);
		}
		
		// 리스트 결과값 출력. 단, 20개 씩 잘라서 출력함
		for (int i=1; i<=S; i++) {
			if (swit[i-1]) {
				bw.write("1 ");
			} else {
				bw.write("0 ");
			}
			if (i%20 == 0) {
				bw.newLine();
			}
		}
		
		bw.flush();
		bw.close();

	}
	
	static void light(boolean boy, int position) {
		// 남학생: 위치의 배수 자리 값 토글
		if (boy) {
			int p = position+1;
			for (int i=p; i<=swit.length; i+=p) {
				swit[i-1] = !swit[i-1];
			}
		} else {
			// 여학생: 대칭 여부 확인
			decal(position, 1);
			// 자기 자신 토글
			swit[position] = !swit[position];
		}
	}
	
	
	static void decal(int position, int count) {
		// 범위 확인
		if (position-count>=0 && position+count<swit.length) {
			// 대칭일 경우 대칭값 토글 및 재귀함수 호출
			if (swit[position+count]==swit[position-count]) {
				swit[position+count] = !swit[position+count];
				swit[position-count] = !swit[position-count];
				decal(position, count+1);
			}
		}
	}

	// 성별과 위치 정보를 갖는 클래스
	static class Pair {
		boolean boy;
		int position;
		Pair(boolean boy, int position) {
			this.boy = boy;
			this.position = position;
		}
	}

}
