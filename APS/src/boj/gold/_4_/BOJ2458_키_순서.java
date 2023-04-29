package boj.gold._4_;
// 그래프, 플로이드-워샬

import java.io.*;
import java.util.*;

public class BOJ2458_키_순서 {

    static int N, M, EA;
    static List<Integer>[] taller, shorter;
    static boolean[][] known;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        EA = 0;
        known = new boolean[N][N];
        int a, b;
        taller = new ArrayList[N];
        shorter = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            taller[i] = new ArrayList<Integer>();
            shorter[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken()) - 1;
            b = Integer.parseInt(st.nextToken()) - 1;
            taller[a].add(b);
            shorter[b].add(a);
        }
        diff();

        System.out.println(EA);


    }

    static void diff() {
        Queue<Integer> queue;
        L1: for (int i = 0; i < N; i++) {
            queue = new ArrayDeque<>();
            queue.offer(i);
            known[i][i] = true;
            while (!queue.isEmpty()) {
                int cur = queue.poll();
                for (int num : taller[cur]) {
                    if (!known[i][num]) {
                        known[i][num] = true;
                        queue.offer(num);
                    }
                }
            }
            queue.offer(i);
            while (!queue.isEmpty()) {
                int cur = queue.poll();
                for (int num : shorter[cur]) {
                    if (!known[i][num]) {
                        known[i][num] = true;
                        queue.offer(num);
                    }
                }
            }
            for (int j = 0; j < N; j++) {
                if (!known[i][j]) {
                    continue L1;
                }
            }
            EA++;
        }

    }

}
