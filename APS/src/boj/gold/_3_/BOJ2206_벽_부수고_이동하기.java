package boj.gold._3_;

import java.io.*;
import java.util.*;

public class BOJ2206_벽_부수고_이동하기 {

    static int N, M;
    static int[][] map, delta = { {-1, 0}, {0, 1}, {1, 0}, {0, -1} };
    static boolean[][] breakable, broke;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        breakable = new boolean[N][M];
        broke = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }
        System.out.println(move());
    }

    static int move() {
        if (N==1 && M==1) return 1;
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] {0, 0, 0, 1});    // ny, nx, 벽 파괴 여부 (0/1), 경로 길이
        int ny, nx, path, co[];
        boolean drill;
        while (!queue.isEmpty()) {
            co = queue.poll();
            drill = co[2] == 0 ? true : false;
            path = co[3];
            for (int d = 0; d < delta.length; d++) {
                ny = co[0] + delta[d][0];
                nx = co[1] + delta[d][1];
                if (isIn(ny, nx)) {
                    if (ny == N-1 && nx == M-1) {
                        return path + 1;
                    }
                    if (drill && map[ny][nx] == 0 && !breakable[ny][nx]) {
                        breakable[ny][nx] = true;
                        queue.offer(new int[] {ny, nx, 0, path + 1});
                    }
                    if (drill && map[ny][nx] == 1) {
                        broke[ny][nx] = true;
                        queue.offer(new int[] {ny, nx, 1, path + 1});
                    }
                    if (!drill && map[ny][nx] == 0 && !broke[ny][nx]) {
                        broke[ny][nx] = true;
                        queue.offer(new int[] {ny, nx, 1, path + 1});
                    }
                }
            }
        }
        return -1;
    }

    static boolean isIn(int i, int j) {
        return !(i<0 || i>=N || j<0 || j>=M);
    }
}
