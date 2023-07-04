package com.ssafy.day25;
// gold 4. 치즈
// BFS, 구현, 시뮬레이션, 그래프

import java.io.*;
import java.util.*;

public class Main_2636_전윤철 {
	
	static int H, W, SizeOfBoundary, Seconds;
	static int[][] cheese, delta = { {-1, 0}, {0, 1}, {1, 0}, {0, -1} };
	static Queue<int[]> voidQueue, boundaryQueue;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		cheese = new int[H][W];
		for (int h = 0; h < H; h++) {
			st = new StringTokenizer(br.readLine());
			for (int w = 0; w < W; w++) {
				cheese[h][w] = Integer.parseInt(st.nextToken());
			}
		}
		countTime();
		System.out.println(Seconds);
		System.out.println(SizeOfBoundary);
	}

	static void countTime() {
		while (still()) {
			Seconds++;
			findBoundary();
		}
	}

	static void findBoundary() {	// 해당 초에서 boundary 파악
		voidQueue = new ArrayDeque<int[]>();
		voidQueue.offer(new int[] {0, 0});
		boundaryQueue = new ArrayDeque<int[]>();
		visited = new boolean[H][W];
		int ny, nx, co[] = null;
		while (!voidQueue.isEmpty()) {
			co = voidQueue.poll();
			for (int d = 0; d < delta.length; d++) {
				ny = co[0] + delta[d][0];
				nx = co[1] + delta[d][1];
				if (isIn(ny, nx)) {
					if (cheese[ny][nx]==0 && !visited[ny][nx]) {	// 빈 공간이라면 파악된 빈 공간(-1) 처리 
						visited[ny][nx] = true;
						voidQueue.offer(new int[] {ny, nx});						
					} else if (cheese[ny][nx] == 1) {	// 만난적 없는 치즈라면 바운더리(2) 처리
						cheese[ny][nx] = 2;
						boundaryQueue.offer(new int[] {ny, nx});
					} 
				}
			}
		}
		if (!boundaryQueue.isEmpty()) {
			melt();			
		}
	}

	static void melt() {	// boundaryQueue에 든 값을 모두 제거
		SizeOfBoundary = boundaryQueue.size();
		int co[] = null;
		while (!boundaryQueue.isEmpty()) {
			co = boundaryQueue.poll();
			cheese[co[0]][co[1]] = 0;
		}
	}
	
	static boolean still() {	// 아직 남은 치즈가 있다면 true
		for (int h = 0; h < H; h++) {
			for (int w = 0; w < W; w++) {
				if (cheese[h][w] > 0) {
					return true;
				}
			}
		}
		return false;
	}

	static boolean isIn(int i, int j) {
		return !(i<0 || i>=H || j<0 || j>=W);
	}

}
