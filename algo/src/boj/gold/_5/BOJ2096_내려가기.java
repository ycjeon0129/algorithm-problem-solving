package boj.gold._5;
// DP, 슬라이딩 윈도우

import java.io.*;
import java.util.*;

public class BOJ2096_내려가기 {
	
	static int N;
	static final int INF = Integer.MAX_VALUE;
	static short[][] map;
	static int[][] maxDP, minDP;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		map = new short[N+1][3];
		maxDP = new int[N+1][3];
		minDP = new int[N+1][3];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {	// 각 DP 테이블 초기화
				map[i][j] = Short.parseShort(st.nextToken());
				minDP[i][j] = INF;
			}
		}
		dp();
		System.out.println(maxDP[N][0] + " " + minDP[N][0]);
	}

	static void dp() {
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j < 3; j++) {
				for (int k = j - 1; k <= j + 1; k++) {
					if (isIn(k)) {	// 이동가능한 경우 DP 갱신
						maxDP[i][j] = Math.max(maxDP[i-1][k], maxDP[i][j]);
						minDP[i][j] = Math.min(minDP[i-1][k], minDP[i][j]);
					}
				}
				maxDP[i][j] += map[i][j];
				minDP[i][j] += map[i][j];
			}
		}
		maxDP[N][0] = Math.max(maxDP[N][0], Math.max(maxDP[N][1], maxDP[N][2]));	// 출력을 위해 DP 배열 (N, 0)에 결과 저장
		minDP[N][0] = Math.min(minDP[N][0], Math.min(minDP[N][1], minDP[N][2]));
	}

	static boolean isIn(int k) {	// 범위 확인 메서드
		return !(k<0 || k>=3);
	}

}
