package com.ssafy.day23;
// gold 3. 말이 되고픈 원숭이
// BFS, 그래프

import java.io.*;
import java.util.*;

public class Main_1600_전윤철 {

	static int K, W, H, times;
	static int[][] map, dm = { {-1, 0}, {0, 1}, {1, 0}, {0, -1} }, dh = { {-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1} };
	static Queue<pos> queue;
	static boolean[][][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		K = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new int[H][W];
		visited = new boolean[H][W][K+1];	// 점프 횟수별 방문체크 배열
		times = -1;	// 도착점에 도달하지 못할 경우 출력값 저장
		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		travel();
		System.out.println(times);
	}

	static void travel() {
		queue = new ArrayDeque<>();
		queue.add(new pos(0, 0, 0, 0));	// 시작점 queue에 삽입
		visited[0][0][0] = true;	// 시작점 방문처리
		pos temp = null;
		int nx, ny;
		while (!queue.isEmpty()) {	// 이동 가능한 좌표가 있는 동안 계속 진행
			temp = queue.poll();
			if (temp.i==H-1 && temp.j==W-1) {	// 현재 좌표가 도착점이라면 현재까지의 이동 횟수 저장
				times = temp.times;
				return;
			}
			for (int d = 0; d < dm.length; d++) {	// 원숭이 이동
				ny = temp.i + dm[d][0];
				nx = temp.j + dm[d][1];
				if (isIn(ny, nx) && map[ny][nx]!=1 && !visited[ny][nx][temp.jump]) {	// 다음 좌표가 이동 가능하고 해당 점프 횟수에서 방문한 적이 없다면
					visited[ny][nx][temp.jump] = true;
					queue.offer(new pos(ny, nx, temp.jump, temp.times+1));
				}
			}
			if (temp.jump < K) {	// 점프 횟수가 남았다면
				for (int d = 0; d < dh.length; d++) {	// 말 이동
					ny = temp.i + dh[d][0];
					nx = temp.j + dh[d][1];
					if (isIn(ny, nx) && map[ny][nx]!=1 && !visited[ny][nx][temp.jump+1]) {	// 다음 좌표가 이동 가능하고 해당 점프 횟수에서 방문한 적이 없다면
						visited[ny][nx][temp.jump+1] = true;
						queue.offer(new pos(ny, nx, temp.jump+1, temp.times+1));
					}
				}
			}
		}
	}

	static boolean isIn(int h, int w) {	// 배열 범위 유효성 확인 메서드
		return !(w<0 || w>=W || h<0 || h>=H);
	}

	static class pos {	// 현재 좌표, 점프 횟수, 이동 횟수를 저장하는 클래스
		int i, j, jump, times;
		public pos(int i, int j, int jump, int times) {
			this.i = i;
			this.j = j;
			this.jump = jump;
			this.times = times;
		}
	}

}
