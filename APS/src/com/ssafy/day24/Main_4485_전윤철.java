package com.ssafy.day24;
// gold 4. 녹색 옷 입은 애가 젤다지?
// BFS, Dijkstra

import java.io.*;
import java.util.*;

public class Main_4485_전윤철 {

	static int N;
	static final int INF = Integer.MAX_VALUE;
	static int[][] map, dijkstra, delta = { {1, 0}, {0, 1}, {-1, 0}, {0, -1} };
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int t = 0;
		N = Integer.parseInt(st.nextToken());
		while (N != 0) {
			t++;
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			dijkstra = new int[N][N];
			for (int i = 0; i < N; i++) {
				Arrays.fill(dijkstra[i], INF);
			}
			
			playZelda();
			bw.write(String.format("Problem %d: %d\n", t, dijkstra[N-1][N-1]));
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
		}
		bw.flush();
		bw.close();
	}

	static void playZelda() {
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return dijkstra[o1[0]][o1[1]] - dijkstra[o2[0]][o2[1]];	// 해당 칸에 도착할 때까지 잃은 루피가 적은 순으로 정렬
			}
		});
		dijkstra[0][0] = map[0][0];	// 시작 좌표 설정
		pq.offer(new int[] {0, 0});
		int ny, nx, co[] = null;
		while (!pq.isEmpty()) {	// 더 이상 탐색할 수 있는 칸이 없을 때까지
			co = pq.poll();
			for (int d = 0; d < delta.length; d++) {	// 사방탐색
				ny = co[0] + delta[d][0];
				nx = co[1] + delta[d][1];
				if (isIn(ny, nx) && dijkstra[co[0]][co[1]]+map[ny][nx]<dijkstra[ny][nx]) {	// co 좌표에서 다음 칸으로 이동하는 비용이 현재 다음 칸의 비용보다 작다면
					dijkstra[ny][nx] = dijkstra[co[0]][co[1]] + map[ny][nx];	// 최소 비용 갱신
					pq.offer(new int[] {ny, nx});
				}
			}
		}
		
	}

	static boolean isIn(int i, int j) {	// 배열 범위 유효성 검사 메서드
		return !(i<0 || i>=N || j<0 || j>=N);
	}
}