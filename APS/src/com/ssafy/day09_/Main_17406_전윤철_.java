package com.ssafy.day09_;
// gold 4. 배열 돌리기 4
// 순열, 구현

import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_17406_전윤철_ {
	
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int N, M, K, minSumOfRow = Integer.MAX_VALUE;
	static byte[][] matrix, temp, origin;
	static int[][] order;
	static int[] execute;
	static boolean[] isSelected;
	static Queue<co> queue;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		matrix = new byte[N+1][M+1];
		temp = new byte[N+1][M+1];
		origin = new byte[N+1][M+1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				byte cur = Byte.parseByte(st.nextToken());
				matrix[i][j] = cur;
				temp[i][j] = cur;
				origin[i][j] = cur;
			}
		}
		execute = new int[K];
		order = new int[K][3];
		isSelected = new boolean[K];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			order[i][0] = Integer.parseInt(st.nextToken());
			order[i][1] = Integer.parseInt(st.nextToken());
			order[i][2] = Integer.parseInt(st.nextToken());
		}
		perm(0);
		System.out.println(minSumOfRow);
	}

	private static void rotateCW(int rr, int cc, int ss) {
		if (ss==0) {
			return;
		}
		queue = new ArrayDeque<co>();
		int tempCoI, tempCoJ;
		co cur;
		for (int t = 1; t <= ss; t++) {

			tempCoJ = cc-t;
			for (int i=rr-t; i<rr+t; i++) {
				tempCoI = i;
				queue.offer(new co(tempCoI, tempCoJ));
			}
			tempCoI = rr+t;
			for (int i=cc-t; i<cc+t; i++) {
				tempCoJ = i;
				queue.offer(new co(tempCoI, tempCoJ));
			}
			tempCoJ = cc+t;
			for (int i=rr+t; i>rr-t; i--) {
				tempCoI = i;
				queue.offer(new co(tempCoI, tempCoJ));
			}
			tempCoI = rr-t;
			for (int i=cc+t; i>cc-t; i--) {
				tempCoJ = i;
				queue.offer(new co(tempCoI, tempCoJ));
			}
			
			queue.offer(queue.poll());
			
			for (int i=rr-t; i<rr+t; i++) {
				cur = queue.poll();
				temp[i][cc-t] = matrix[cur.i][cur.j];
			}
			for (int i=cc-t; i<cc+t; i++) {
				cur = queue.poll();
				temp[rr+t][i] = matrix[cur.i][cur.j];
			}
			for (int i=rr+t; i>rr-t; i--) {
				cur = queue.poll();
				temp[i][cc+t] = matrix[cur.i][cur.j];
			}
			for (int i=cc+t; i>cc-t; i--) {
				cur = queue.poll();
				temp[rr-t][i] = matrix[cur.i][cur.j];
			}
			
		}
		
		for (int i = rr-ss; i <= rr+ss; i++) {
			for (int j = cc-ss; j <= cc+ss; j++) {
				matrix[i][j] = temp[i][j];
			}
		}
		
	}

	static void perm(int depth) {
		if (depth == K) {
			for (int i = 0; i < K; i++) {
				rotateCW(order[execute[i]][0], order[execute[i]][1], order[execute[i]][2]);
			}
			getMin();
			restore();
			return;
		}
		for (int i = 0; i < K; i++) {
			if (!isSelected[i]) {
				isSelected[i] = true;
				execute[depth] = i;
				perm(depth+1);
				isSelected[i] = false;
			}
		}
		
	}

	static void restore() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				matrix[i][j] = origin[i][j];
				temp[i][j] = origin[i][j];
			}
		}
	}

	static void getMin() {
		int min = Integer.MAX_VALUE;
		for (int i = 1; i <=N; i++) {
			int temp = 0;
			for (int j = 1; j <= M; j++) {
				temp += matrix[i][j];
			}
			min = Math.min(min, temp);
		}
		minSumOfRow = Math.min(minSumOfRow, min);
	}

	static class co {
		int i, j;
		co(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}

}
