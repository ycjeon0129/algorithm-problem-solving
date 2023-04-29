package com.ssafy.day19;
// gold 5. 토마토
// BFS

import java.io.*;
import java.util.*;

public class Main_7569_전윤철 {

    static int N, M, H, times;
    static int[][][] tomatoes;
    static int[][] delta = { {1, 0, 0}, {0, 1, 0}, {-1, 0, 0}, {0, -1, 0}, {0, 0, 1}, {0, 0, -1} };
    static Queue<int[]> queue;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        tomatoes = new int[N][M][H];
        times = -1;

        for (int h = 0; h < H; h++) {   // 토마토 상자 입력
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    tomatoes[i][j][h] = Integer.parseInt(st.nextToken());
                }
            }
        }
        rotten();
        System.out.println(times);
    }

    static void rotten() {
        int timeRecord = 0; // 시작 시간 0 설정
        queue = new ArrayDeque<int[]>();
        for (int h = 0; h < H; h++) {   // 이미 썩어있는 토마토(1)을 모두 큐에 저장하고 해당 토마토를 2로 방문처리
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (tomatoes[i][j][h] == 1) {
                        queue.offer(new int[] {i, j, h, 0});
                        tomatoes[i][j][h] = 2;
                    }
                }
            }
        }
        while (!queue.isEmpty()) {
            int[] tomato = queue.poll();
            timeRecord = tomato[3]; // 마지막 토마토가 썩는데 걸리는 시간 갱신
            for (int d = 0; d < delta.length; d++) {    // 상하좌우 탐색
                int ny = tomato[0] + delta[d][0];
                int nx = tomato[1] + delta[d][1];
                int nz = tomato[2] + delta[d][2];

                if (isIn(ny, nx, nz) && tomatoes[ny][nx][nz]==0) {  // 배열 범위를 벗어나지 않고 해당 위치의 토마토가 썩지 않은 상태라면
                    tomatoes[ny][nx][nz] = 2;   // 방문처리
                    queue.offer(new int[] {ny, nx, nz, tomato[3]+1}); // 큐에 삽입
                }
            }
        }
        if (!surviveTomato()) { // 썩지 않은 토마토가 없을 경우 시간 갱신
            times = timeRecord;
        }
    }

    static boolean surviveTomato() {
        for (int h = 0; h < H; h++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (tomatoes[i][j][h] == 0) {   // 하나라도 썩지 않은 토마토가 있다면 거짓
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // 배열 범위 확인
    static boolean isIn(int i, int j, int h) {
        return !(i<0 || i>=N || j<0 || j>=M || h<0 || h>=H);
    }

}