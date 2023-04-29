package com.ssafy.day29;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_16234_전윤철 {

    static int N, L, R, Days;
    static int[][] land, delta = { {-1, 0}, {0, 1}, {1, 0}, {0, -1} };
    static boolean[][] checked;
    static Graph edge;
    static ArrayList<int[]> country;
    static Queue<int[]> queue;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        land = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                land[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        days();
    }

    static void days() {    // 더이상 이동할 수 없을 때까지 인구 이동 및 날짜 기록
        for (;;) {
            checked = new boolean[N][N];
            edge = new Graph();
            country = new ArrayList<int[]>();

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!checked[i][j]) {
                        makeUnion(i, j);
                    }
                }
            }
            if (edge.size() == 0) break;
            Days++;
            move();
        }
        System.out.println(Days);
    }

    static void move() {    // 인구 이동이 가능한 땅 사이 인구 이동
        int avgPopulation = 0;
        for (int i = 0; i < country.size(); i++) {
            avgPopulation = country.get(i)[1] / country.get(i)[0];
            for (int j = 0; j < edge.get(i).size(); j++) {
                int ny = edge.get(i).get(j)[0];
                int nx = edge.get(i).get(j)[1];
                land[ny][nx] = avgPopulation;
            }
        }
        printLand();
    }

    static void printLand() {
        System.out.printf("%d Days\n", Days);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.printf("%d ", land[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    static void makeUnion(int y, int x) {   // 연합 결성 BFS 메서드
        int ny, nx, cur[];
        queue = new ArrayDeque<int[]>();
        queue.offer(new int[] {y, x});
        edge.add();
        edge.get(edge.size()-1).add(new int[] {y, x});
        country.add(new int[] {1, land[y][x]});
        checked[y][x] = true;
        while (!queue.isEmpty()) {
            cur = queue.poll();
            for (int d = 0; d < delta.length; d++) {
                ny = cur[0] + delta[d][0];
                nx = cur[1] + delta[d][1];
                if (isIn(ny, nx) && !checked[ny][nx] && isSimilar(cur, d)) {
                    checked[ny][nx] = true;
                    queue.add(new int[] {ny, nx});
                    edge.get(edge.size()-1).add(new int[] {ny, nx});
                    country.get(country.size()-1)[0]++;
                    country.get(country.size()-1)[1] += land[ny][nx];
                }
            }
        }
        if (country.get(country.size()-1)[0] == 1) {
            edge.remove(edge.size()-1);
            country.remove(country.size()-1);
        }
    }

    static boolean isSimilar(int[] cur, int d) {    // 인구 규모 비교 메서드
        int[] next = { cur[0] + delta[d][0], cur[1] + delta[d][1] };
        int gap = Math.abs(land[cur[0]][cur[1]] - land[next[0]][next[1]]);
        if (gap < L || gap > R) return false;
        return true;
    }

    static boolean isIn(int i, int j) { // 배열 범위 유효성 검사 메서드
        return !(i<0 || i>=N || j<0 || j>=N);
    }

    static class Graph extends ArrayList<ArrayList<int[]>> {    // 2차원 리스트를 상속받는 클래스
        void add() {
            add(new ArrayList<int[]>());
        }
    }
}
