package com.ssafy.day03;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution_4012_전윤철 {
	
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int[][] ingredients;

	// 재료의 개수
	static int[] arr;
	// 선택한 재료
	static boolean[] visited;
	// 최소 시너지 차
	static int diff;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		byte T = Byte.parseByte(br.readLine());

		for (int t=0; t<T; ++t) {
			diff = 987_654_321;
			byte N = Byte.parseByte(br.readLine());
			ingredients = new int[N][N];
			for (int i=0; i<N; ++i) {
				st = new StringTokenizer(br.readLine());
				for (int j=0; j<N; ++j) {
					ingredients[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			arr = new int[N];
			visited = new boolean[N];
			// 첫번째 재료는 항상 선택
			visited[0] = true;
			// 모든 재료별 조합 탐색
			combi(1, 1, N, N/2);

			bw.write("#"+(t+1)+" "+diff+"\n");
		}

		bw.flush();
		bw.close();
	}

	static void cook(int N) {
		// 각 요리 별 시너지의 합
		int first = 0;
		int second = 0;

		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				// 두 재료가 같은 요리에 선택됐다면 해당 재료간의 시너지를 요리의 시너지에 더함
				if (visited[i] && visited[j]) {
					first += ingredients[i][j];
				} else if (!visited[i] && !visited[j]) {
					second += ingredients[i][j];
				}
			}
		}
		// 시너지 차를 최소로 유지
		diff = Math.min( Math.abs(first-second), diff );

	}

	// 시작점, 깊이, N, M을 파라미터로 갖는 메서드
	private static void combi(int start, int depth, int N, int M) throws IOException {
		// 최대 깊이 도달 시 시너지 비교 후 종료
		if (depth == M) {
			cook(N);
			return ;
		}
		// 시작점부터 반복문 시작
		for (int i=start; i<N; i++) {
			// 현재 재료를 첫번째 요리에 추가
			visited[i] = true;
			// 다음 위치, 다음 깊이에서 재귀함수 호출
			combi(i+1, depth+1, N, M);
			visited[i] = false;
		}

	}

}
