package com.ssafy.day19;
// gold 4. 미세먼지 안녕!
// 구현, 시뮬레이션

import java.io.*;
import java.util.*;

public class Main_17144_전윤철 {

    static int R, C, T, upper, lower;
    static int[][] room, spreadingRoom, delta = { {-1, 0}, {0, 1}, {1, 0}, {0, -1} };

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        room = new int[R][C];
        boolean setAirCleaner = false;

        for (int r = 0; r < R; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < C; c++) {
                room[r][c] = Integer.parseInt(st.nextToken());
                if (!setAirCleaner && room[r][c]==-1) {
                    upper = r;  // 공기청정기의 위치 저장
                    lower = r + 1;
                    setAirCleaner = true;
                }
            }
        }
        sanitizer();
    }

    static void sanitizer() {   // 주어진 횟수만큼 미세먼지의 확산과 공기청정기 가동을 반복 후 미세먼지 카운트
        for (int t = 0; t < T; t++) {
            spread();
            airCleaner();
        }
        System.out.println(countAmount());
    }

    static int countAmount() {  // 배열 내 공기청정기를 제외한 미세먼지의 총합 계산
        int count = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (room[i][j] != -1) {
                    count += room[i][j];
                }
            }
        }
        return count;
    }

    static void airCleaner() {  // 공기청정기의 위 아래 각 싸이클 구현
        // upper cycle
        for (int i = upper-1; i > 0; i--) {
            room[i][0] = room[i-1][0];
        }
        for (int j = 0; j < C - 1; j++) {
            room[0][j] = room[0][j+1];
        }
        for (int i = 0; i < upper; i++) {
            room[i][C-1] = room[i+1][C-1];
        }
        for (int j = C-1; j > 1; j--) {
            room[upper][j] = room[upper][j-1];
        }
        room[upper][1] = 0;

        // lower cycle
        for (int i = lower+1; i < R - 1; i++) {
            room[i][0] = room[i+1][0];
        }
        for (int j = 0; j < C - 1; j++) {
            room[R-1][j] = room[R-1][j+1];
        }
        for (int i = R-1; i > lower; i--) {
            room[i][C-1] = room[i-1][C-1];
        }
        for (int j = C-1; j > 1; j--) {
            room[lower][j] = room[lower][j-1];
        }
        room[lower][1] = 0;
    }

    static void spread() {  // 미세먼지가 있는 칸에 대해 확산 구현
        int ny, nx, spreadableDirection;
        spreadingRoom = new int[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (room[i][j] > 0) {
                    spreadableDirection = 0;    // 확산 가능한 방향 수
                    for (int d = 0; d < delta.length; d++) {
                        ny = i + delta[d][0];
                        nx = j + delta[d][1];
                        if (isIn(ny, nx) && room[ny][nx]!=-1) { // 범위를 벗어나지 않고 공기청정기가 아니라면 확산
                            spreadableDirection++;
                            spreadingRoom[ny][nx] += (room[i][j] / 5);
                        }
                    }
                    spreadingRoom[i][j] += ( room[i][j] - spreadableDirection*(room[i][j] / 5) );   // 확산 후 남은 미세먼지 계산
                }
            }
        }
        for (int i = 0; i < R; i++) {   // 이번 차례에 동시에 확산된 미세먼지를 반영(기존 배열에 저장)
            for (int j = 0; j < C; j++) {
                if (room[i][j] != -1) {
                    room[i][j] = spreadingRoom[i][j];
                }
            }
        }
    }

    static boolean isIn(int i, int j) { // 배열 범위 유효성 검사
        return !(i<0 || i>=R || j<0 || j>=C);
    }
}
