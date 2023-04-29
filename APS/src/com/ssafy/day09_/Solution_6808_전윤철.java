package com.ssafy.day09_;
// D3. 규영이와 인영이의 카드게임
// 순열

import java.io.*;
import java.util.StringTokenizer;

public class Solution_6808_전윤철 {

    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int win, lose;
    static final int NUMBER = 9;
    static int[] player, dealer, ordered;
    static boolean[] isSelected;


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int T = Integer.parseInt(st.nextToken());

        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());

            player = new int[NUMBER];
            dealer = new int[NUMBER];
            ordered = new int[NUMBER];
            isSelected = new boolean[NUMBER];
            win = lose = 0;

            // player의 카드 순서를 입력받음
            for (int i = 0; i < 9; i++) {
                player[i] = Integer.parseInt(st.nextToken());
            }
            // player가 선택하지 않은 카드를 dealer가 선택할 수 있는 카드 배열에 저장
            int index = 0;
            loop1: for (int i = 1; i <= 18; i++) {
                for (int j = 0; j < 9; j++) {
                    if (i == player[j]) {
                        continue loop1;
                    }
                }
                dealer[index++] = i;
            }
            // 모든 경우의 수 확인
            permut(0);
            bw.write(String.format("#%d %d %d\n", t, win, lose));
        }
        bw.flush();
        bw.close();
    }

    // 9P9 계산
    static void permut(int depth) {
        if (depth == NUMBER) {
            play();
            return;
        }
        for (int i = 0; i < NUMBER; i++) {
            if (!isSelected[i]) {
                ordered[depth] = dealer[i];
                isSelected[i] = true;
                permut(depth+1);
                isSelected[i] = false;
            }
        }
    }

    // 각 경우의 수에 대해 승 or 패 기록
    static void play() {
        int playerPoint = 0, dealerPoint = 0;
        for (int i = 0; i < NUMBER; i++) {
            if (player[i] > ordered[i]) {
                playerPoint += (player[i] + ordered[i]);
            } else {
                dealerPoint += (player[i] + ordered[i]);
            }
        }
        if (playerPoint > dealerPoint) {
            win++;
        } else if (playerPoint < dealerPoint) {
            lose++;
        }
    }
}
