package boj.gold._2;

import java.io.*;
import java.util.*;

public class BOJ16946_벽_부수고_이동하기_4 {

	static int N, M, unionNum;
	static int[][] map, unite[], delta = { {-1, 0}, {0, 1}, {1, 0}, {0, -1} };
	static boolean[][] visited;
	static Queue<int[]> queue;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		unite = new int[N][M][2];
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j) - '0';
			}
		}

		recordZero();	// 연결된 0의 집합에 대해 각 집합의 인덱스와 크기 계산
		updateMap();	// 모든 1에 대해 사방탐색을 실시하여 0의 집합의 크기 합 연산

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				bw.write(map[i][j]+"");
			}
			bw.newLine();
		}
		bw.flush();
		bw.close();
	}

	static void updateMap() {
		int ny, nx, tempArea, counted[];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1) {	// 모든 벽(1)에 대해 사방탐색
					tempArea = 1;
					counted = new int[4];
					L1: for (int d = 0; d < delta.length; d++) {
						ny = i + delta[d][0];
						nx = j + delta[d][1];
						if (isIn(ny, nx) && unite[ny][nx][1]!=0) {	// 사방 탐색 결과가 벽이 아닐 때
							int num = unite[ny][nx][0];
							for (int dd = 0; dd < d; dd++) {	// 해당 0의 집합이 현 좌표에서 이미 처리되었다면 continue
								if (counted[dd] == num) {
									continue L1;
								}
							}
							tempArea += unite[ny][nx][1];	// 처음 만난 0의 집합이라면 영역의 넓이 합 연산
							tempArea %= 10;
							counted[d] = num;
						}
					}
					map[i][j] = tempArea;	// 맵 테이블 업데이트
				}
			}
		}
	}

	static void recordZero() {
		int size, co[];
		queue = new ArrayDeque<int[]>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0 && !visited[i][j]) {	// 아직 처리되지 않은 0을 만난다면
					unionNum++;	// 해당 0 집합의 인덱스 지정
					size = makeUnion(i, j);	// 해당 0 집합의 사이즈 계산
					while (!queue.isEmpty()) {	// 해당 0 집합 내 모든 좌표에 대해 별도 테이블의 값 갱신
						co = queue.poll();
						unite[co[0]][co[1]][0] = unionNum;
						unite[co[0]][co[1]][1] = size;
					}
				}
			}
		}
	}

	static int makeUnion(int i, int j) {	// bfs 사방탐색 - 영역 크기 리턴
		int num = 1, co[];
		Queue<int[]> unionQueue = new ArrayDeque<int[]>();
		unionQueue.offer(new int[] {i, j});
		visited[i][j] = true;
		while (!unionQueue.isEmpty()) {
			co = unionQueue.poll();
			int ny, nx;
			for (int d = 0; d < delta.length; d++) {
				ny = co[0] + delta[d][0];
				nx = co[1] + delta[d][1];
				if (isIn(ny, nx) && map[ny][nx]==0 && !visited[ny][nx]) {
					visited[ny][nx] = true;
					num++;
					unionQueue.offer(new int[]{ny, nx});
				}
			}
			queue.offer(co);
		}
		return num;
	}

	static boolean isIn(int i, int j) {
		return !(i < 0 || i >= N || j < 0 || j >= M);
	}

}
