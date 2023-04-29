package com.ssafy.day25;
// gold 1. 달이 차오른다, 가자.
// 3차원 BFS, 그래프, 비트마스킹

import java.io.*;
import java.util.*;

public class Main_1194_전윤철 {

    static int N, M, x, y, times;
    static final int KEYS = 6, NumOfKeys = (int) Math.pow(2, KEYS);
    static byte[][] maze;
    static int[][] delta = { {-1, 0}, {0, 1}, {1, 0}, {0, -1} };
    static boolean[][][] visited;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        maze = new byte[N][M];
        visited = new boolean[N][M][NumOfKeys];
        times = -1;

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                maze[i][j] = parse(i, j, line.charAt(j));
            }
        }

        moon();
        System.out.println(times);
    }

    static void moon() {    // 3차원 BFS
        Queue<Node> queue = new ArrayDeque<Node>();
        queue.offer(new Node(y, x, 0, 0));
        int ny, nx, status, keys;
        visited[y][x][0] = true;
        Node co = null;
        while (!queue.isEmpty()) {
            co = queue.poll();
            for (int d = 0; d < delta.length; d++) {
                ny = co.y + delta[d][0];
                nx = co.x + delta[d][1];
                keys = co.keys;
                if (isIn(ny, nx) && !visited[ny][nx][keys] && 0<=maze[ny][nx]) { // 범위 내 방문하지 않았고 벽이 아니라면
                    status = maze[ny][nx];
                    if (status == 1) {  // 출구라면
                        times = co.sec + 1;
                        return;
                    } else if (10<=status && status<=15) {  // 열쇠라면
                        if (!hasKey(keys, status-10)) { // 아직 획득하지 않은 열쇠라면
                            keys = getKey(keys, status-10); // 열쇠 획득 처리
                        }
                    } else if (20<=status && status<=25) {  // 문이라면
                        if (!hasKey(keys, status-20)) continue; // 열쇠가 없다면 반복문 탈출
                    }
                    visited[ny][nx][keys] = true;   // 해당 키 조합에서 해당 좌표 방문처리
                    queue.offer(new Node(ny, nx, co.sec + 1, keys));
                }
            }
        }
    }

    static int getKey(int keys, int key) {  // 해당 키 보유 처리 - 비트마스킹
        return keys | (1 << key);
    }

    static boolean hasKey(int keys, int key) {  // 해당 키 보유 여부 확인 - 비트마스킹
        return (keys & (1 << key) ) != 0;
    }

    static boolean isIn(int i, int j) { // 배열 범위 유효성 확인 메서드
        return !(i<0 || i>=N || j<0 || j>=M);
    }

    static byte parse(int i, int j, char value) {
        byte tmp = (byte) (value - '0');
        switch (tmp) {
            case 0:
                y = i;
                x = j;
                break;
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 54:
                tmp -= 39;  // 열쇠의 경우 10 ~ 15 처리
                break;
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
                tmp += 3;   // 문의 경우 20 ~ 25 처리
                break;
            case -13:
                tmp = -1;   // 벽의 경우 -1 처리
                break;
            case -2:
                tmp = 0;    // 빈 칸의 경우 0 처리
        }
        return tmp;
    }

    static class Node { // 좌표의 경과 시간, 보유 키 상태를 담는 클래스
        int x, y, sec, keys;
        public Node(int y, int x, int sec, int keys) {
            this.y = y;
            this.x = x;
            this.sec = sec;
            this.keys = keys;
        }
    }
}
