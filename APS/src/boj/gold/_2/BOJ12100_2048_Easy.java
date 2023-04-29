package boj.gold._2;
// 중복순열, 시뮬레이션, back-tracking

import java.io.*;
import java.util.*;

public class BOJ12100_2048_Easy {

	static int N, biggest;
	static final int TURN = 5;
	static int[] order;
	static int[][] map, savedMap, delta = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		savedMap = new int[N][N];
		order = new int[TURN];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				savedMap[i][j] = map[i][j];
			}
		}
        permunR(0);
		System.out.println(biggest);
	}

	static void permunR(int depth) {	// 중복순열을 통한 이동 방향 결정
		if (depth == TURN) {
			play();
			return;
		}
		for (int i = 0; i < 4; i++) {
			order[depth] = i;
			permunR(depth + 1);
		}
	}

	static void play() {	// 정해진 방향에 맞게 숫자 이동
		int tmpBiggest = 0;
		for (int i = 0; i < TURN; i++) {
			switch (order[i]) {
			case 0:
				moveU();
				break;
			case 1:
				moveR();
				break;
			case 2:
				moveD();
				break;
			case 3:
				moveL();
				break;
			}
			tmpBiggest = findBiggest();	// 현재까지의 최댓값 계산
			if (biggest < tmpBiggest) {	// 최댓값 갱신
				biggest = tmpBiggest;
			} else if (getLogDiff(tmpBiggest) >= (TURN - i - 1)) {	// 해당 순서를 끝까지 진행해도 기존 최댓값보다 클 수 없다면 리턴
				break;
			}
		}
		restore();	// 다음 경우의 수를 위한 배열 복원
	}

	static int getLogDiff(int tmpBiggest) {	// 각 최댓값의 로그값의 차 계산
		return (int) ((Math.log10(biggest)/Math.log10(2)) - (Math.log10(tmpBiggest)/Math.log10(2)));
	}

	private static int findBiggest() {	// 현재 최댓값 계산
		int tmpBiggest = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				tmpBiggest = Math.max(tmpBiggest, map[i][j]);
			}
		}
		return tmpBiggest;
	}

	private static void restore() {	// 배열 복원 메서드
		for (int i = 0; i < N; i++) {
			map[i] = savedMap[i].clone();
		}
	}

	static void moveU() {	// 위로 이동
		int ni;
		int[] fixed = new int[N];
		Arrays.fill(fixed, -1);
		for (int i = 1; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 0) {
					continue;
				}
				ni = i;
				while (isIn(--ni, j) && map[ni][j] == 0) {	// 이동가능하다면 계속 이동
					map[ni][j] = map[ni + 1][j];
					map[ni + 1][j] = 0;
				}
				if (isIn(ni, j) && map[ni][j] == map[ni + 1][j] && fixed[j] < ni) {	// 이동 종료 후 결합 가능하다면
					map[ni][j] *= 2;
					map[ni + 1][j] = 0;
					fixed[j] = ni;	// 이미 결합된 칸은 더 이상 결합하지 않음
				}
			}
		}
	}

	static void moveR() {
		int nj;
		int[] fixed = new int[N];
		Arrays.fill(fixed, N);
		for (int i = 0; i < N; i++) {
			for (int j = N - 2; j >= 0; j--) {
				if (map[i][j] == 0) {
					continue;
				}
				nj = j;
				while (isIn(i, ++nj) && map[i][nj] == 0) {
					map[i][nj] = map[i][nj - 1];
					map[i][nj - 1] = 0;
				}
				if (isIn(i, nj) && map[i][nj] == map[i][nj - 1]  && fixed[i] > nj) {
					map[i][nj] *= 2;
					map[i][nj - 1] = 0;
					fixed[i] = nj;
				}
			}
		}
	}

	static void moveD() {
		int ni;
		int[] fixed = new int[N];
		Arrays.fill(fixed, N);
		for (int i = N - 2; i >= 0; i--) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 0) {
					continue;
				}
				ni = i;
				while (isIn(++ni, j) && map[ni][j] == 0) {
					map[ni][j] = map[ni - 1][j];
					map[ni - 1][j] = 0;
				}
				if (isIn(ni, j) && map[ni][j] == map[ni - 1][j] && fixed[j] > ni) {
					map[ni][j] *= 2;
					map[ni - 1][j] = 0;
					fixed[j] = ni;
				}
			}
		}
	}

	static void moveL() {
		int nj;
		int[] fixed = new int[N];
		Arrays.fill(fixed, -1);
		for (int i = 0; i < N; i++) {
			for (int j = 1; j < N; j++) {
				if (map[i][j] == 0) {
					continue;
				}
				nj = j;
				while (isIn(i, --nj) && map[i][nj] == 0) {
					map[i][nj] = map[i][nj + 1];
					map[i][nj + 1] = 0;
				}
				if (isIn(i, nj) && map[i][nj] == map[i][nj + 1] && fixed[i] < nj) {
					map[i][nj] *= 2;
					map[i][nj + 1] = 0;
					fixed[i] = nj;
				}
			}
		}
	}

	static boolean isIn(int i, int j) {
		return !(i < 0 || i >= N || j < 0 || j >= N);
	}
}
