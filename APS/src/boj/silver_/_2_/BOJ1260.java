package boj.silver_._2_;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ1260 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static {
        try {
            st = new StringTokenizer(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static boolean[][] map;
    static boolean[] visited;
    static boolean[] visitedBfs;
    static List<Integer> output = new ArrayList<Integer>();
    static List<Integer> queue = new ArrayList<Integer>();

    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int V = Integer.parseInt(st.nextToken());

        visited = new boolean[N+1];
        visitedBfs = new boolean[N+1];
        map = new boolean[N+1][N+1];

        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            map[start][end] = true;
            map[end][start] = true;
        }

        dfs(N, V);
        print();
        output.clear();
        bfs(N, V);
        print();

        bw.flush();
        bw.close();
    }
    static void dfs(int N, int V) throws IOException {

        visited[V] = true;
        output.add(V);
        for (int i=1; i<=N; i++) {
            if (map[V][i]) {
                if (!visited[i]) {
                    dfs(N, i);
                }
            }
        }
    }
    static void bfs(int N, int V) throws IOException {
        visitedBfs[V] = true;
        output.add(V);
        for (int i=1; i<=N; i++) {
            if (map[V][i]) {
                if (!visitedBfs[i]) {
                    visitedBfs[i] = true;
                    push(i);
                }
            }
        }
        while (queue.size() > 0) {
            bfs(N, pop());
        }
    }
    static void push(int V) {
        queue.add(V);
    }
    static int pop() {
        if (queue.size() == 0) {
            return 0;
        }
        return queue.remove(0);
    }
    static public void print() throws IOException {
        for (int i=0; i<output.size(); i++) {
            bw.write(output.get(i) + " ");
        }
        bw.newLine();
    }
}
