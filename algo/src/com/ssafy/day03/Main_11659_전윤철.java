package com.ssafy.day03;
// silver 3. 구간 합 구하기 4

import java.io.*;
import java.util.StringTokenizer;

public class Main_11659_전윤철 {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    
    static int[] arr;
    // 인덱스까지의 누적 합을 저장할 배열
    static int[] accum;

	public static void main(String[] args) throws IOException {

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		arr = new int[N+1];
		accum = new int[N+1];
		st = new StringTokenizer(br.readLine());
		// 인덱스별 값 입력 및 누적 합 저장
		for (int i=1; i<=N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			accum[i] = accum[i-1] + arr[i];
		}
		
		// 구간 입력 및 구간 합 계산
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			addSum(start, end);
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		
	}
	
	// end까지의 합에서 start 앞 까지의 합 뺄셈
	static void addSum(int start, int end) throws IOException {
		int sum = accum[end] - accum[start-1];
		sb.append(sum+"\n");
	}

}
