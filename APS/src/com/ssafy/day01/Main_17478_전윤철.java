package com.ssafy.day01;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

//public class Main {
public class Main_17478_전윤철 {
	
	// static으로 입출력 선언
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static {
		try {
			st = new StringTokenizer(br.readLine());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static void main(String[] args) throws IOException {
		
		// 반복 횟수 n 입력
		int n = Integer.parseInt(st.nextToken());
		
		// 인트로 문장 출력
		bw.write("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.\n");
		
		// 재귀함수 호출
		chatbot(n, 0);
		// 버퍼에 있는 값 화면에 출력
		bw.flush();
		bw.close();
	}
	
	// 제시된 반복횟수와 현재 횟수를 매개변수로 받는 재귀함수
	static void chatbot(int n, int count) throws IOException {
		// 현재 횟수만큼 ____ 출력 후 문장 출력
		for(int i=0; i<count; i++) bw.write("____");
		bw.write("\"재귀함수가 뭔가요?\"\n");
		// 제시된 반복횟수에 도달하지 못했을 경우 자기 자신 호출
		if (n > 0) {
			for(int i=0; i<count; i++) bw.write("____");
			bw.write("\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.\n");
			for(int i=0; i<count; i++) bw.write("____");
			bw.write("마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.\n");
			for(int i=0; i<count; i++) bw.write("____");
			bw.write("그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"\n");
			// 재귀함수
			chatbot(n-1, count+1);
		}
		// 제시된 반복횟수에 도달했을 경우 아웃트로 출력
		else {
			for(int i=0; i<count; i++) bw.write("____");
			bw.write("\"재귀함수는 자기 자신을 호출하는 함수라네\"\n");
		}
		for(int i=0; i<count; i++) bw.write("____");
		bw.write("라고 답변하였지.\n");
	}

}
