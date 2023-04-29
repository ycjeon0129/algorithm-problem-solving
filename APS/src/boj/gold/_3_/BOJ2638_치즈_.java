package boj.gold._3_;
// 구현, 그래프, 시뮬레이션, BFS

import java.io.*;
import java.util.*;

public class BOJ2638_치즈_ {

    static int N, M, answer;
    static int[][] map, delta = { {-1, 0}, {0, 1}, {1, 0}, {0, -1} };
    static boolean[][] visited;
    static Queue<int[]> cheese, air;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        run();
        System.out.println(answer);
    }

    static void run() {
        air = new ArrayDeque<>();
        cheese = new ArrayDeque<>();
        air.offer(new int[] {0, 0});    // (0, 0) 좌표는 무조건 외부 공기
        do {
            findOuterAir();
            answer++;
            melt();
        } while (still());  // 치즈가 모두 없어질 때까지 반복하며 시간 증가
    }

    static void findOuterAir() {
        int ny, nx, co[] = null;
        boolean[][] already = new boolean[N][M];    // 치즈 방문 처리 배열
        while (!air.isEmpty()) {
            co = air.poll();
            map[co[0]][co[1]] = -1; // 현 좌표에 대해 외부 공기 (-1) 처리
            for (int d = 0; d < delta.length; d++) {    // 4방 탐색
                ny = co[0] + delta[d][0];
                nx = co[1] + delta[d][1];
                if (isIn(ny, nx) && !visited[ny][nx]) { // 미방문 공기에 대한 영역 확장(큐에 저장) 및 방문처리
                    if (map[ny][nx] == 0) {
                        air.offer(new int[] {ny, nx});
                        visited[ny][nx] = true;
                    }
                    if (map[ny][nx] == 1 && !already[ny][nx]) { // 방문한 적 없는 치즈라면
                        cheese.offer(new int[] {ny, nx});    // 치즈 큐에 저장
                        already[ny][nx] = true;
                    }
                }
            }
        }
    }

    static void melt() {    // 치즈 큐에 담긴 좌표값 처리
        int ny, nx, side, co[];
        while (!cheese.isEmpty()) {
            co = cheese.poll();
            side = 0;
            for (int d = 0; d < delta.length; d++) {    // 4방 탐색
                ny = co[0] + delta[d][0];
                nx = co[1] + delta[d][1];
                if (map[ny][nx] == -1) side++;  // 현재 변에 대해 외부 공기와 닿아 있다면 side 1 증가
            }
            if (side >= 2) {    // 외부 공기와 닿은 변이 2개 이상이라면
                map[co[0]][co[1]] = 0;  // 현 좌표에 대해 빈 공기(0) 처리 및 공기 큐에 저장
                air.offer(co);
            }
        }
    }

    static boolean still() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1) return true;
            }
        }
        return false;
    }

    static boolean isIn(int i, int j) {
        return !(i < 0 || i >= N || j < 0 || j >= M);
    }

}
