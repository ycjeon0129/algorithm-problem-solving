package com.ssafy.day09_;
// D3. 햄버거 다이어트
// DP, 배낭 문제

import java.io.*;
import java.util.StringTokenizer;

public class Solution_5215_전윤철 {

    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, Limit;
    static final int value = 0, weight = 1;
    static int[][] ingredients;
    static int[][] recipe;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            Limit = Integer.parseInt(st.nextToken());
            ingredients = new int[N+1][2];
            recipe = new int[N+1][Limit+1];

            for (int i = 1; i <= N; i++) {
                st = new StringTokenizer(br.readLine());
                ingredients[i][value] = Integer.parseInt(st.nextToken());
                ingredients[i][weight] = Integer.parseInt(st.nextToken());
            }
//            Arrays.sort(ingredients, new Comparator<int[]>() {
//                @Override
//                public int compare(int[] o1, int[] o2) {
//                    return o1[weight]-o2[weight];
//                }
//            });
            cook();
            bw.write(String.format("#%d %d\n", t, recipe[N][Limit]));
        }
        bw.flush();
        bw.close();
    }

    static void cook() {
        for (int n = 1; n <= N; n++) {
            for (int w = 1; w <= Limit; w++) {
                // 현재 재료의 칼로리가 제한 칼로리보다 낮다면
                if (ingredients[n][weight] <= w) {
                    // 현재 재료를 고려하지 않은 최적해와 현재 재료의 합 VS 현재 재료를 선택하지 않은 최적해
                    recipe[n][w] = Math.max(ingredients[n][value] + recipe[n-1][w-ingredients[n][weight]], recipe[n-1][w]);
                } else {
                    // 현재 재료를 선택하지 않은 최적해
                    recipe[n][w] = recipe[n-1][w];
                }
            }
        }
    }
}
