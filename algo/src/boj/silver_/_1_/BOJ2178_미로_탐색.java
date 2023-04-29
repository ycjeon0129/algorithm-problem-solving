package boj.silver_._1_;
// bfs, 그래프

import java.io.*;
import java.util.*;

public class BOJ2178_미로_탐색 {

    static int N, M;
    static boolean[][] maze;
    static int[][] delta = { {0, 1}, {1, 0}, {0, -1}, {-1, 0} };
    static Queue<int[]> queue;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        maze = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                if (line.charAt(j)=='1') maze[i][j] = true;
            }
        }
        bfs();
    }

    static void bfs() {
        int ni, nj;
        queue = new ArrayDeque<int[]>();
        queue.offer(new int[] {0, 0, 1});
        maze[0][0] = false;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int i = 0; i < delta.length; i++) {
                ni = cur[0] + delta[i][0];
                nj = cur[1] + delta[i][1];
                if (isIn(ni, nj) && maze[ni][nj]) {
                    if (ni==N-1 && nj==M-1) {
                        System.out.println(cur[2]+1);
                        return;
                    }
                    maze[ni][nj] = false;
                    queue.offer(new int[] {ni, nj, cur[2]+1});
                }
            }
        }
    }

    static boolean isIn(int i, int j) {
        return !(i<0 || i>=N || j<0 || j>=M);
    }
}
