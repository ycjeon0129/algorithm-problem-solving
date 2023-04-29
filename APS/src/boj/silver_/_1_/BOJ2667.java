package boj.silver_._1_;

import java.io.*;
import java.util.*;

public class BOJ2667 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static boolean[][] map;
    static List<Pair> queue;

    public static void main(String[] args) throws IOException {
        queue = new ArrayList<Pair>();
        int N = Integer.parseInt(br.readLine());
        map = new boolean[N][N];
        String line;

        for (int i=0; i<N; i++) {
            line = br.readLine();
            for (int j=0; j<N; j++) {
                map[i][j] = (line.charAt(j)=='1') ? true : false;
            }
        }

        List<Integer> result = search(N);
        bw.write(result.size()+"\n");
        Collections.sort(result);
        for (int i :result) {
            bw.write(i+"");
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }

    static List<Integer> search(int N) {
        List<Integer> count = new ArrayList<Integer>();

        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                int num = 0;
                if (map[i][j]) {
                    push(new Pair(j, i));
                    map[i][j] = false;
                    while (peek() != null) {
                        Pair temp = peek();
                        around(temp.x, temp.y, N);
                        pop();
                        num++;
                    }
                    count.add(num);
                }
            }
        }

        return count;
    }

    static void around(int x, int y, int N) {
        int dx[] = {1, 0, -1, 0};
        int dy[] = {0, -1, 0, 1};

        for (int i=0; i<4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
                continue;
            }
            if (map[ny][nx]) {
                push(new Pair(nx, ny));
                map[ny][nx] = false;
            }
        }
    }

    static void push(Pair co) {
        queue.add(co);
    }
    static Pair pop() {
        if (queue.size() == 0) {
            return null;
        }
        return queue.remove(0);
    }
    static Pair peek() {
        if (queue.size() == 0) {
            return null;
        }
        return queue.get(0);
    }
    public static class Pair {
        int x, y;
        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
