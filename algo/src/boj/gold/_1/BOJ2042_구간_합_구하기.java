package boj.gold._1;
// 세그먼트 트리, 펜윅 트리 (바이너리 인덱스 트리)
// https://www.acmicpc.net/blog/view/21

import java.io.*;
import java.util.*;

public class BOJ2042_구간_합_구하기 {
	
	static int N, M, K;
	static long[] arr, stArr;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		arr = new long[N+1];
		stArr = new long[N+1];
		
		for (int i = 1; i <= N; i++) {
			arr[i] = Long.parseLong(br.readLine());
		}
		
		init();	// Fenwick Tree 초기화
		int op, paramA, paramBI;
		long paramBL;
		for (int i = 0; i < M + K; i++) {
			st = new StringTokenizer(br.readLine());
			op = Integer.parseInt(st.nextToken());
			paramA = Integer.parseInt(st.nextToken());
			if (op == 1) {	// 업데이트
				paramBL = Long.parseLong(st.nextToken());
				update(paramA, paramBL);
			} else {	// 구간 합 출력
				paramBI = Integer.parseInt(st.nextToken());
				bw.write(getSum(paramA-1, paramBI)+"\n");
			}
		}
		bw.flush();
		bw.close();
	}

	static long getSum(int from, int to) {	// (1 ~ to)까지의 구간 합 - (1 ~ from)까지의 구간 합
		Long startSum = 0L, endSum = 0L;
		int idx = to;
		while (idx > 0) {
			endSum += stArr[idx];
			idx -= (idx & -idx);
		}
		idx = from;
		while (idx > 0) {
			startSum += stArr[idx];
			idx -= (idx & -idx);
		}
		return endSum - startSum;		
	}

	static void update(int idx, long value) {	// 해당 인덱스 및 2의 보수를 응용한 인덱스 갱신을 반복하며 구간 합 갱신
		long diff = value - arr[idx];
		arr[idx] = value;
		while (idx <= N) {
			stArr[idx] += diff;
			idx += (idx & -idx);
		}
	}

	static void init() {	// 2의 보수를 응용한 인덱스 갱신을 반복하며 구간 합 계산
		int idx;
		for (int i = 1; i <= N; i++) {
			idx = i;
			while (idx <= N) {
				stArr[idx] += arr[i];
				idx += (idx & -idx);
			}
		}
	}
}
