package com.ssafy.day20;
// D4. 보급로
// ~~

import java.io.*;
import java.util.*;

public class Solution_1249_전윤철 {

	static int N, operationDays;
	static final int INF = Integer.MAX_VALUE;
	static int[][] battleField[], delta = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
	static boolean[][] repaired;
	static PriorityQueue<int[]> queue;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			bw.write(String.format("#%d ", t));
			operationDays = 0;
			N = Integer.parseInt(br.readLine());
			battleField = new int[N][N][2];
			repaired = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				String line = br.readLine();
				for (int j = 0; j < N; j++) {
					battleField[i][j][0] = line.charAt(j) - '0';
					battleField[i][j][1] = INF;
				}
			}
			repair();
			bw.write(battleField[N - 1][N - 1] + "\n");
		}
		bw.flush();
		bw.close();
	}

	static void repair() {
		battleField[0][0][1] = 0;
		int ny, nx, minCost;
		queue = new PriorityQueue<int[]>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o1[2], o2[2]);
			}
		});
		queue.offer(new int[] { 0, 0, battleField[0][0][1] });
		int[] co = null;
		while (!queue.isEmpty()) {
			co = queue.poll();
//			if (co[0]==N-1 && co[1]==N-1) {
//				return;
//			}
			try {
				if (repaired[co[0]][co[1]]) {
					continue;
				}

			} catch (Exception e) {
				System.out.println(co);
			}
			repaired[co[0]][co[1]] = true;
			if (co[0] == N - 1 && co[1] == N - 1) {
				return;
			}

			for (int i = 0; i < delta.length; i++) {
				ny = co[0] + delta[i][0];
				nx = co[1] + delta[i][1];
				if (isIn(ny, nx) && !repaired[ny][nx] && battleField[ny][nx][1] > battleField[ny][nx][0] + co[2]) {
					queue.offer(new int[] { ny, nx, battleField[ny][nx][1] });
				}
			}
		}
	}

	static boolean isIn(int i, int j) {
		return !(i < 0 || i >= N || j < 0 || j >= N);
	}
}
