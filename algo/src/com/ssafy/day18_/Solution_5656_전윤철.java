package com.ssafy.day18_;
// 벽돌 깨기
// 중복순열, bfs, 구현

import java.io.*;
import java.util.*;

public class Solution_5656_전윤철 {
	
	static int N, W, H, bestScore;
	static int[] marbles;
	static int[][] map, save, delta = { {-1, 0}, {0, 1}, {1, 0}, {0, -1} };
	static Queue<int[]> queue;
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		
		for (int t = 1; t <= T; t++) {
			bw.write(String.format("#%d ", t));
			bestScore = Integer.MAX_VALUE;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			map = new int[H][W];
			save = new int[H][W];
			marbles = new int[N];
			
			for (int h = 0; h < H; h++) {
				st = new StringTokenizer(br.readLine());
				for (int w = 0; w < W; w++) {
					map[h][w] = Integer.parseInt(st.nextToken());
					save[h][w] = map[h][w];
				}
			}			
			perm(0);
			bw.write(String.format("%d\n", bestScore));
		}
		bw.flush();
		bw.close();
	}

	static void perm(int depth) {	// 중복 순열을 통해 구슬을 N번 발사할 순서를 정함
		if (depth == N) {
			play();
			return;
		}
		for (int i = 0; i < W; i++) {
			marbles[depth] = i+1;
			perm(depth+1);
		}
	}

	static void play() {	// 정해진 순서에 맞게 구슬을 투하하고 빈 공간을 밑으로 내리는 작업을 반복
							// 작업이 완료되면 점수를 기록하고 벽돌을 초기 상태로 복원
		for (int i = 0; i < N; i++) {
			drop(marbles[i]-1);	// 해당 순서에 맞는 열에 구슬을 투하
			gravity();	// 빈칸을 아래로 제거
		}
		recordScore();
		restore();
	}
	
	static void drop(int col) {
		for (int i = 0; i < H; i++) {	// 해당 열의 첫 벽돌에 닿을때까지 구슬을 투하
			if (map[i][col] != 0) {
				boom(i, col);	// 벽돌과 부딪친다면 해당 벽돌부터 연쇄작용 시작
				break;
			}
		}
	}

	static void boom(int row, int col) {
		int nr, nc, range = map[row][col];
		queue = new ArrayDeque<>();
		queue.offer(new int[] {row, col, range});	// 현 위치에서 bfs 시작
		while (!queue.isEmpty()) {
			int[] next = queue.poll();
			map[next[0]][next[1]] = 0;	// 현 탐색 위치의 벽돌 제거
			for (int r = 1; r < next[2]; r++) {	// 현 탐색 위치 벽돌의 범위만큼 사방탐색하며 벽돌을 제거할 queue에 삽입
				for (int d = 0; d < delta.length; d++) {
					nr = next[0] + delta[d][0] * r;
					nc = next[1] + delta[d][1] * r;
					if (isIn(nr, nc) && map[nr][nc]!=0) {
						queue.offer(new int[] {nr, nc, map[nr][nc]});
						map[nr][nc] = 0;	// queue에 삽입 시 해당 벽돌 제거 표시
					}
				}
			}
		}
	}
	
	static void gravity() {	// 값이 0이 아닌 모든 벽돌에 대해 중력을 적용시킴
		for (int w = 0; w < W; w++) {
			int next = H - 1;
			for (int h = next; h >= 0 ; h--) {
				if (map[h][w]!=0) {	// 뱍돌이 있다면
					if (next!=h) {	// 벽돌이 이미 정렬되어있지 않을 경우
						map[next][w] = map[h][w];	// 벽돌 이동
						map[h][w] = 0;	// 기존 위치의 벽돌 제거
					}
					next--;	// 다음 벽돌을 쌓을 위치 변경
				}
			}
		}
	}
	
	static void recordScore() {	// 남아있는 벽돌 수 계산 및 최솟값 갱신
		int score = 0;
		for (int h = 0; h < H; h++) {
			for (int w = 0; w < W; w++) {
				if (map[h][w] != 0) {
					score++;
				}
			}
		}
		bestScore = Math.min(score, bestScore);
	}

	static void restore() {	// 다음 중복순열을 탐색하기 위해 벽돌 복원
		for (int h = 0; h < H; h++) {
			for (int w = 0; w < W; w++) {
				map[h][w] = save[h][w];
			}
		}
	}
	
	static boolean isIn(int r, int c) {	// 범위 확인
		return !(r<0 || r>=H || c<0 || c>=W);
	}
}
