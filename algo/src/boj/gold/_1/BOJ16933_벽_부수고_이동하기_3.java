package boj.gold._1;

import java.io.*;
import java.util.*;

public class BOJ16933_벽_부수고_이동하기_3 {

    static int N, M, K, times;
    static int[][] map, delta = { {-1, 0}, {0, 1}, {1, 0}, {0, -1} };
    static boolean[][][] visited;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M][K+1];
        times = -1;

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }
        go();
        System.out.println(times);
    }

    static void go() {
        int ny, nx, broken, day, co[];
        Queue<int[]> queue = new ArrayDeque<int[]>();
        queue.offer(new int[] {0, 0, 0, 1});    // ny, nx, broken, day
        visited[0][0][0] = true;
        while (!queue.isEmpty()) {
            co = queue.poll();
            if (co[0] == N-1 && co[1] == M-1) { // 도착점일 경우 현재까지의 경로 리턴
                times = co[3];
                return;
            }
            for (int d = 0; d < delta.length; d++) {    // 사방탐색
                ny = co[0] + delta[d][0];
                nx = co[1] + delta[d][1];
                broken = co[2];
                day = co[3];
                if (isIn(ny, nx)) { // 범위 내에 있을때
                    if (map[ny][nx]==0 && !visited[ny][nx][broken]) {   // 만약 방문하지 않은 0이라면 대기열에 추가
                        visited[ny][nx][broken] = true;
                        queue.offer(new int[] {ny, nx, broken, day+1});
                    }
                    if (broken<K && map[ny][nx]==1 && !visited[ny][nx][broken+1]) { // 벽 제거 횟수가 남았고 현재 횟수에서 방문하지 않은 벽이라면
                        if (day%2 == 1) {   // 낮일 경우 벽을 제거하고 이동
                            visited[ny][nx][broken+1] = true;
                            queue.offer(new int[] {ny, nx, broken+1, day+1});
                        } else {    // 밤일 경우 현재 자리에서 대기
                            queue.offer(new int[] {co[0], co[1], broken, day+1});
                        }

                    }
                }
            }
        }
    }

    static boolean isIn(int i, int j) {
        return !(i<0 || i>=N || j<0 || j>=M);
    }
}
