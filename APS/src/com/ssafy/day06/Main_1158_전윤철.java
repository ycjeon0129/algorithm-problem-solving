package com.ssafy.day06;
// silver 4. 요세푸스 문제
// 수학, 큐

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_1158_전윤철 {

	static int N, K;
	static List<Integer> inputCircle;
	static int[] Josephus;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		// N명의 원형으로 앉은 사람을 나타내는 ArrayList
		inputCircle = new ArrayList<Integer>();
		// 요세푸스 순열에 따라 제거된 번호를 담는 배열
		Josephus = new int[N];

		// ArrayList 세팅
		for (int i=0; i<N; i++) {
			inputCircle.add(i+1);
		}

		// 함수 실행
		delete();
		bw.write("<");
		for (int i=0; i<N-1; i++) {
			bw.write(Josephus[i]+", ");
		}
		bw.write(Josephus[N-1]+">");
		bw.flush();
		bw.close();
	}

	static void delete() {
		// ArrayList에서 제거할 사람의 인덱스. 매번 K번째 뒤의 사람을 찾아가는 로직에 첫 경우까지 포함하기 위해 -1로 초기화
		int div = -1;
		// 요세푸스 순열 결과 배열의 인덱스
		int idx = 0;
		// 결과 배열이 가득 찰 때까지
		while (idx < N) {
			// K번째 뒤의 사람을 찾아감
			for (int i=0; i<K; i++) {
				// 이때 이미 제거된 사람의 경우 카운트하지 않음 -> 반복문의 인덱스를 뒤로 돌림
				if ( inputCircle.get( ++div % N ) == 0) {
					i--;
				}
			}
			// 결과 배열에 이번에 제거된 사람의 번호 저장
			Josephus[idx++] = inputCircle.get(div%N);
			// 제가된 사람의 경우 값을 0으로 표시
			inputCircle.set(div%N, 0);
		}
		
	}

}
