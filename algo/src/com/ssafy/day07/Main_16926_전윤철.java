package com.ssafy.day07;
// silver 1. 배열 돌리기 1
// 구현

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_16926_전윤철 {
	
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int N, M, r, layer;
	static int[][] matrix, output;
	static Deque<co> curCo, nextCo;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		
		matrix = new int[N][M];
		output = new int[N][M];
		layer = Math.min(N, M) / 2;
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<M; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken()); 
			}
		}
		
		// 레이어 별 회전
		for (int i=1; i<=layer; i++) {
			findMyPlace(i);
		}
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				bw.write(output[i][j] + " ");
			}
			bw.newLine();
		}
		bw.flush();
		bw.close();
	}

	// 해당 레이어의 껍질 부분의 좌표를 반시계 방향으로 저장하는 메서드
	private static void findMyPlace(int l) {
		curCo = new ArrayDeque<>();
		nextCo = new ArrayDeque<>();
		int i=l-1, j=l-1;
		for (; i<N-l; i++) {
			curCo.offer(new co(i, j));;
			nextCo.offer(new co(i, j));
		}
		for (; j<M-l; j++) {
			curCo.offer(new co(i, j));;
			nextCo.offer(new co(i, j));
		}
		for (; i>l-1; i--) {
			curCo.offer(new co(i, j));;
			nextCo.offer(new co(i, j));
		}
		for (; j>l-1; j--) {
			curCo.offer(new co(i, j));;
			nextCo.offer(new co(i, j));
		}
		// 한바퀴를 회전하는 횟수를 제외한 순수 이동 횟수 count
		int count = r % curCo.size();
		// count 수만큼 큐를 회전
		for (int k=0; k<count; k++) {
			nextCo.offer(nextCo.poll());
		}
		// 이동 후 죄표값에 현재 값을 저장
		while (curCo.size() > 0) {
			co next = nextCo.poll();
			co cur = curCo.poll();
			output[next.i][next.j] = matrix[cur.i][cur.j];
		}
		
	}

	static class co {
		int i;
		int j;
		public co(int i, int j) {
			this.i = i;
			this.j = j;
		}
		
	}

}
