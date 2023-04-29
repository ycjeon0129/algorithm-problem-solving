package boj.gold._3_;
// bfs

import java.io.*;
import java.util.*;

public class BOJ2146_다리_만들기 {

    static int N, minBridge;
    static int[][] country, delta = { {-1, 0}, {0, 1}, {1, 0}, {0, -1} };
    static boolean[][] wasBuilt;
    static Queue<int[]> islandQueue;
    static List<List<int[]>> coasts;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        country = new int[N][N];
        minBridge = N + N;
        wasBuilt = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                country[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        coasts = new ArrayList<>();
        buildBridge();
        System.out.println(minBridge-1);
    }

    static void buildBridge() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (country[i][j] == 1) {
                    // 새로운 섬에 도착할 경우 섬의 범위 탐색 시작
                    islandQueue = new ArrayDeque<>();
                    // 섬의 해안가의 좌표를 저장할 ArrayList
                    coasts.add(new ArrayList<int[]>());
                    islandQueue.offer(new int[] {i, j});
                    setIsland(i, j);
                }
            }
        }
        // 섬 간의 최단거리 계산
        calDistance();
    }

    static void calDistance() {
        for (int i = 0; i < coasts.size(); i++) {
            for (int j = 0; j < coasts.get(i).size(); j++) {
                for (int k = i+1; k < coasts.size(); k++) {
                    for (int l = 0; l < coasts.get(k).size(); l++) {
                        // 각 해변간의 거리 계산 및 최단거리 갱신
                        int[] start = coasts.get(i).get(j);
                        int[] end = coasts.get(k).get(l);
                        minBridge = Math.min(minBridge, Math.abs(start[0]-end[0])+Math.abs(start[1]-end[1]));
                    }
                }
            }
        }
    }

    // bfs를 통한 섬 탐색 및 섬 내의 한 좌표가 바다와 맞닿아 있다면 해당 섬의 해변 arrayList에 좌표 저장
    static void setIsland(int i, int j) {
        int ni = i, nj = j, tempCo[];
        while (!islandQueue.isEmpty()) {
            tempCo = islandQueue.poll();
            for (int d = 0; d < delta.length; d++) {
                ni = tempCo[0] + delta[d][0];
                nj = tempCo[1] + delta[d][1];
                if (isIn(ni, nj)) {
                    if (country[ni][nj] == 1) {
                        islandQueue.offer(new int[] {ni, nj});
                        country[ni][nj] = -1;
                    } else if (country[ni][nj]==0 && country[tempCo[0]][tempCo[1]] != 2) {
                        // 이미 저장된 해변의 경우 방문 처리
                        country[tempCo[0]][tempCo[1]] = 2;
                        coasts.get(coasts.size()-1).add(new int[] {tempCo[0], tempCo[1]});
                    }
                }
            }
        }
    }

    // 배열의 범위 유효성 검사
    static boolean isIn(int i, int j) {
        return !(i<0 || i>=N || j<0 || j>=N);
    }
}
