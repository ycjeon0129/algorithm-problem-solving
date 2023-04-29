package com.ssafy.day09_;
// bronze 2. 블랙잭
// 조합 (완전탐색)

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2798_전윤철 {

    static int N, M, result = -1;
    static final int NUM = 3;
    static int[] blackJack, selected;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        blackJack = new int[N];
        selected = new int[NUM];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            blackJack[i] = Integer.parseInt(st.nextToken());
        }

        playGame(0, 0);
        System.out.println(result);
    }

    // 주어진 blackJack 배열에 담긴 N개의 카드 중 3개의 카드를 골라 selected 배열에 저장
    static void playGame(int start, int depth) {
        if (depth == NUM) {
            getSum();
            return;
        }
        for (int i = start; i < N; i++) {
            selected[depth] = blackJack[i];
            playGame(i+1, depth+1);
        }
    }

    // selected 배열에 담긴 카드의 합 계산 및 계산값이 M보다 크지 않고 현재까지 조합의 합보다 크다면 결과값 갱신
    static void getSum() {
        int temp = 0;
        for (int i = 0; i < NUM; i++) {
            temp += selected[i];
            if (temp > M) {
                return;
            }
        }
        if (temp > result) {
            result = temp;
        }
    }
}
