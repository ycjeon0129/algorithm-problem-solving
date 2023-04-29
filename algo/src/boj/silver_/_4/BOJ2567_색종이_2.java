package boj.silver_._4;
// 구현

import java.io.*;
import java.util.*;

public class BOJ2567_색종이_2 {

    static int N, vertical, horizontal;
    static boolean[][] confettis;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        confettis = new boolean[100][100];

        int X, Y;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            X = Integer.parseInt(st.nextToken());
            Y = Integer.parseInt(st.nextToken());
            // 색종이로 덮인 영역 true 처리
            for (int x = X; x < X+10; x++) {
                for (int y = Y; y < Y+10; y++) {
                    confettis[x][y] = true;
                }
            }
        }

        // 가로 방향으로 이동하며 색종이의 경계를 지난다면 가로 길이 1 추가
        for (int i = 0; i < 100; i++) {
            for (int j = 1; j < 100; j++) {
                if (confettis[i][j-1]!=confettis[i][j]) {
                    horizontal++;
                }
            }
            // 행의 시작과 끝이 색종이로 덮였다면 가로 길이 1 추가
            if (confettis[i][0]) {
                horizontal++;
            }
            if (confettis[i][99]) {
                horizontal++;
            }
        }

        // 세로 방향으로 이동하며 색종이의 경계를 지난다면 세로 길이 1 추가
        for (int j = 0; j < 100; j++) {
            for (int i = 1; i < 100; i++) {
                if (confettis[i-1][j]!=confettis[i][j]) {
                    vertical++;
                }
            }
            // 열의 시작과 끝이 색종이로 덮였다면 세로 길이 1 추가
            if (confettis[0][j]) {
                vertical++;
            }
            if (confettis[99][j]) {
                vertical++;
            }
        }

        System.out.println(vertical+horizontal);
    }
}
