package com.ssafy.day14_;
// gold 3. 캐슬 디펜스
// 조합, 구현

import java.io.*;
import java.util.StringTokenizer;

public class Main_17135_전윤철 {

	static int N, M, Range, kill, maxDefeatedEnemy = -1, archers[];
	static final int archer = 3;
	static int[][] castle, saved;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		Range = Integer.parseInt(st.nextToken());
		castle = new int[N+1][M];
		saved = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				castle[i][j] = Integer.parseInt(st.nextToken());
				saved[i][j] = castle[i][j];
			}
		}
		archers = new int[3];	// 세 궁수가 자리할 위치를 저장할 배열
		combi(0, 0);	// 세 궁수가 자리할 위치 조합 계산
		System.out.println(maxDefeatedEnemy);
	}

	// mC3 계산
	static void combi(int start, int depth) {
		if (depth == archer) {
			defence();
			return;
		}
		for (int i = start; i < M; i++) {
			archers[depth] = i;
			combi(i+1, depth+1);
		}
	}

	// 궁수를 배치하고 모든 적이 사라질 때까지 턴 진행
	static void defence() {
		int idx = 0;
		kill = 0;
		for (int i = 0; i < M; i++) {
			if (archers[idx]==i) {
				idx++;
				castle[N][i] = 1;
				if (idx==3) break;
			}
		}
		while (!isEnd()) {
			playTurn();
		}
		// 이번 턴에 처히한 적의 수가 최대라면 갱신
		maxDefeatedEnemy = Math.max(kill, maxDefeatedEnemy);
		// 다음 조합을 진행하기 위해 게임 초기화
		reset();
	}

	// 1턴 진행
	static void playTurn() {
		// 세 궁수가 겨냥할 각각의 적의 좌표
		int[][] enemies = new int[3][2];
		for (int i = 0; i < archer; i++) {
			enemies[i] = aim(archers[i]);
		}
		// 해당 적이 다른 궁수에 의해 이미 처치되지 않았다면 이번 조합에 처치한 적 수 증가
		for (int i = 0; i < archer; i++) {
			if (enemies[i]!=null && castle[enemies[i][0]][enemies[i][1]]==1) {
				castle[enemies[i][0]][enemies[i][1]] = 0;
				kill++;
			}
		}
		// 이번 턴을 끝내고 적들을 한칸 전진시킴
		moveForward();
	}

	// 각 위치의 궁수가 조건에 맞는 적의 좌표 반환
	static int[] aim(int archer) {
		for (int r = 1; r <= Range; r++) {
			for (int x = archer-(r-1); x <= archer+(r-1); x++) {
				for (int y = N-1; y >= N-r; y--) {
					if (isIn(x, y) && isReachable(x, y, archer, r) && castle[y][x]==1) {
						int[] enemy = {y, x};
						return enemy;
					}
				}
			}
		}
		return null;
	}

	// 모든 행을 1칸 전진 후 0행 초기화
	static void moveForward() {
		for (int i = N-1; i > 0; i--) {
			for (int j = 0; j < M; j++) {
				castle[i][j] = castle[i-1][j];
			}
		}
		for (int i = 0; i < M; i++) {
			castle[0][i] = 0;
		}
	}

	// 플레이한 맵을 초기 상태로 복원
	static void reset() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				castle[i][j] = saved[i][j];
			}
		}
	}

	// 해당 조합의 게임이 끝났는지 확인
	static boolean isEnd() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (castle[i][j]==1) {
					return false;
				}
			}
		}
		return true;
	}

	// 궁수가 자리에서 해당 위치의 적에게 사거리가 닿는지 확인
	static boolean isReachable(int x, int y, int archer, int r) {
		return Math.abs(x-archer)+N-y==r;
	}

	// 해당 좌표가 배열 내부인지 확인
	static boolean isIn(int x, int y) {
		return x>=0 && x<M && y>=0 && y<N;
	}
}
