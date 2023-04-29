package com.ssafy.day13_;
// D5. 최적 경로
// 순열

import java.io.*;
import java.util.StringTokenizer;

public class Solution_1247_전윤철 {
	
	static int N, minDistance;
	static Co work, home, input[], output[];
	static boolean visited[];

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());
		
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			input = new Co[N];
			output = new Co[N];
			visited = new boolean[N];
			minDistance = Integer.MAX_VALUE;
			st = new StringTokenizer(br.readLine());
			work = new Co(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			home = new Co(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			for (int i = 0; i < N; i++) {
				input[i] = new Co(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			permut(0);
			
			bw.write(String.format("#%d %d\n", t, minDistance));
		}
		bw.flush();
		bw.close();
	}
	
	// 순열 nPn 계산
	static void permut(int depth) {
		if (depth == N) {
			getMinDistance();
			return;
		}
		for (int i = 0; i < N; i++) {
			if (!visited[i]) {
				visited[i] = true;
				output[depth] = input[i];
				permut(depth + 1);				
				visited[i] = false;
			}
		}
	}

	// 현재 순열에 대해 경로의 길이 계산
	static void getMinDistance() {
		int distance = 0;
		distance += calculate(work, output[0]);
		for (int i = 1; i < N; i++) {
			distance += calculate(output[i-1], output[i]);
		}
		distance += calculate(output[N-1], home);
		minDistance = Math.min(distance, minDistance);
	}

	// 두 좌표 사이의 거리 계산 메서드
	static int calculate(Co from, Co to) {
		return Math.abs(from.x-to.x) + Math.abs(from.y-to.y);
	}

	// 위치 좌표를 담은 클래스
	static class Co {
		int x, y;
		public Co(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
	}
}
