package boj.gold._4_;
// 3차원 DP

import java.io.*;
import java.util.*;

public class BOJ17404_RGB거리_2 {

    static int N;
    static final int INF = 1_000_000;
    static int[][] houses, dp[];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        houses = new int[N][3];
        dp = new int[N][3][3];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                houses[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {   // 1번째 집 각 RGB 출발점에서 갈 수 있는 각각의 최적헤(구간 별 페인트 비용)
                if (i == j) {
                    dp[1][i][j] = INF;
                } else {
                    dp[1][i][j] = houses[0][i] + houses[1][j];
                }
            }
        }
        color();
    }

    static void color() {
        for (int i = 2; i < N-1; i++) { // 집의 순서
            for (int j = 0; j < 3; j++) {   // RGB 각 집
                for (int k = 0; k < 3; k++) {   // 시작 RGB 집에 따른 각각의 최적해 계산
                    dp[i][j][k] = Math.min(dp[i-1][j][(k+1)%3], dp[i-1][j][(k+2)%3]) + houses[i][k];
                }
            }
        }
        int last;
        for (int i = 0; i < 3; i++) {   // 시작점, 직전과 색이 다른 최소 비용 합산
                last = Math.min(dp[N-2][(i+1)%3][(i+1)%3], dp[N-2][(i+1)%3][(i+2)%3]);
                last = Math.min(last, dp[N-2][(i+2)%3][(i+1)%3]);
                last = Math.min(last, dp[N-2][(i+2)%3][(i+2)%3]);
                dp[N-1][0][i] = last + houses[N-1][i];
        }
        int min = Math.min(dp[N-1][0][0], dp[N-1][0][1]);   // 최종 도착점에서의 최소 비용 계산
        min = Math.min(min, dp[N-1][0][2]);
        System.out.println(min);
    }
}
