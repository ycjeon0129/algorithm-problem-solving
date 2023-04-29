package boj.gold._1;
// 중복순열, 구현, 시뮬레이션

import java.io.*;
import java.util.*;

public class BOJ23290_마법사_상어와_복제 {

	static int M, S, cnt;
	static final int N = 4;
	static int[] input, output, path;
	static int[][] smell,
			delta = { { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 } };
	static Fish[][] map, tmpMap, casting;
	static boolean[][] eaten;
	static MagicianShark ycJeon;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());

		smell = new int[N + 1][N + 1];	// 물고기 냄새 저장 배열
		map = new Fish[N + 1][N + 1];	// 맵
		tmpMap = new Fish[N + 1][N + 1];	// 이동한 물고기를 담은 맵
		casting = new Fish[N + 1][N + 1];	// 복제되어 대기중인 물고기를 담은 맵
		input = new int[] {2, 0, 6, 4};		// 마법사 상어 경로의 우선순위
		output = new int[3];	// 마법사 상어의 이동 경로 후보를 담을 배열

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				map[i][j] = new Fish();
				tmpMap[i][j] = new Fish();
			}
		}
		int x, y, dir;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			y = Integer.parseInt(st.nextToken());
			x = Integer.parseInt(st.nextToken());
			dir = Integer.parseInt(st.nextToken());
			map[y][x].offer(dir);
		}
		st = new StringTokenizer(br.readLine());
		y = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		ycJeon = new MagicianShark(y, x);
		for (int i = 0; i < S; i++) {
			dupSpell();	// 주어진 마법 횟수만큼 복제 마법 시행
		}
		int sum = 0;
		for (int i = 1; i <= N; i++) {	// 남은 물고기 수 계산
			for (int j = 1; j <= N; j++) {
				sum += map[i][j].size();
			}
		}
		System.out.println(sum);
	}

	static void dupSpell() {	// 복제 마법 1회 싸이클
		int sharkX = ycJeon.x, sharkY = ycJeon.y, nx, ny;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				casting[i][j] = (Fish) map[i][j].clone();	// 현재 존재하는 물고기에 대해 복제 마법 실행
				tmpMap[i][j].clear();	// 임시 배열 초기화
			}
		}
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				while (!map[i][j].isEmpty()) {	// 각 칸에 존재하는 모든 물고기에 대해
					boolean move = true;	// 8방 탐색을 통해 이동할 수 있는지 여부 저장
					int fish = map[i][j].poll();
					for (int d = delta.length; d > 0; d--) {
						int dir = (fish - 1 + d) % delta.length;
						ny = i + delta[dir][0];
						nx = j + delta[dir][1];
						if (isIn(ny, nx) && smell[ny][nx] == 0 && !(ny == sharkY && nx == sharkX)) {
							tmpMap[ny][nx].offer(dir + 1);	// 8방 탐색으로 이동 가능하다면 임시 배열에 저장 후 반복문 종료
							move = false;
							break;
						}
					}
					if (move) {	// 8방 탐색으로 이동할 수 없다면
						tmpMap[i][j].offer(fish);	// 현 위치와 방향을 그대로 임시 배열에 저장
					}
				}
			}
		}
		cnt = -1;
		permutR(0);
		for (int i = 1; i <= N; i++) {	// 냄새 감소
			for (int j = 1; j <= N; j++) {
				if (smell[i][j] > 0) {
					smell[i][j]--;
				}
			}
		}
		for (int i = 0; i < 3; i++) {	// 상어가 지난 자리의 물고기 삭제
			sharkY += delta[path[i]][0];
			sharkX += delta[path[i]][1];
			if (!tmpMap[sharkY][sharkX].isEmpty()) {	// 상어가 지난 자리에 대해 물고기 제거 및 냄새 표시
				tmpMap[sharkY][sharkX].clear();
				smell[sharkY][sharkX] = 2;
			}
		}
		ycJeon.y = sharkY;
		ycJeon.x = sharkX;
		for (int i = 1; i <= N; i++) {	// 턴 종료 전 물고기 세팅
			for (int j = 1; j <= N; j++) {
				map[i][j] = (Fish) casting[i][j].clone();	// 복제중인 물고기 반영
				map[i][j].addAll(tmpMap[i][j]);	// 살아남은 물고기 반영
			}
		}
	}

	static void sharkTime() {	// 중복순열로 선택된 상어의 이동방향에 대해 점수 계산
		int tmpCount = 0;
		eaten = new boolean[N+1][N+1];
		int ny = ycJeon.y, nx = ycJeon.x;
		for (int i = 0; i < 3; i++) {	// 각 방향으로 이동
			ny += delta[output[i]][0];
			nx += delta[output[i]][1];
			if (!isIn(ny, nx)) {
				return;
			}
			if (!eaten[ny][nx]) {	// 이미 방문한 위치라면 물고기 수를 합산하지 않음
				tmpCount += tmpMap[ny][nx].size();
			}
			eaten[ny][nx] = true;
		}
		if (cnt < tmpCount) {	// 가장 유리한 경로인지 확인
			cnt = tmpCount;
			path = output.clone();
		}
	}

	static void permutR(int depth) {	// 중복 순열 계산
		if (depth == 3) {
			sharkTime();
			return;
		}
		for (int i = 0; i < 4; i++) {
			output[depth] = input[i];
			permutR(depth + 1);
		}
	}

	static boolean isIn(int i, int j) {	// 배열 범위 유효성 검사 메서드
		return !(i < 1 || i > N || j < 1 || j > N);
	}

	static class Fish extends ArrayDeque<Integer> {
	}

	static class MagicianShark {
		int y, x;
		public MagicianShark(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}
