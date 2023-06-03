package boj.gold._5;
// DP

import java.io.*;
import java.util.*;

public class BOJ2293_동전_1 {

    static int N, K;
    static int[] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dp = new int[K + 1];
        dp[0] = 1;

        for (int i = 0; i < N; i++) {
            int coin = Integer.parseInt(br.readLine());
            for (int j = coin; j <= K; j++) {
                dp[j] += dp[j - coin];
            }
        }
        System.out.println(dp[K]);

    }
}
