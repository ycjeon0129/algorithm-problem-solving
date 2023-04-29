package com.ssafy.day19;
// gold 3. 아기 상어
// BFS, 구현, 시뮬레이션, 그래프

import java.io.*;
import java.util.*;

public class Main_16236_전윤철 {

    static int N, times, eaten;
    static int[] shark;
    static int[][] oceans, delta = { {1, 0}, {0, 1}, {-1, 0}, {0, -1} };
    static boolean[][] visited;
    static Queue<int[]> queue;
    static PriorityQueue<int[]> fishMenu;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        shark = new int[] {-1, -1, 2};
        oceans = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                oceans[i][j] = Integer.parseInt(st.nextToken());
                if (oceans[i][j]==9) {
                    oceans[i][j] = 0;
                    shark[0] = i; shark[1] = j;
                }
            }
        }
        callMotherShark();
    }

    static void callMotherShark() {
        int distance = 0, nx, ny;
        queue = new ArrayDeque<>();
        fishMenu = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return (o1[0]==o2[0]) ? o1[1]-o2[1] : o1[0]-o2[0];  // 우선순위 반영 (상>하, 좌>우)
            }
        });
        queue.offer(new int[] {shark[0], shark[1], distance});  // 상어의 초기 위치와 시작 시간 0을 queue에 저장
        visited[shark[0]][shark[1]] = true; // 상어 초기 위치 방문처리
        while (!queue.isEmpty()) {
            int[] curCo = queue.poll(); // 상어의 현재 위치 및 시간
            if (distance!=curCo[2] && !fishMenu.isEmpty()) {    // 같은 거리에 이동할 수 있는 경우의 수를 모두 탐색했고 먹을 수 있는 물고기가 있다면
                curCo = eat(curCo[2]);  // 마지막으로 잡아먹힌 물고기의 위치에서 새롭게 탐색 시작
                visited = new boolean[N][N];    // 방문처리 여부 초기화
                visited[shark[0]][shark[1]] = true;
            }
            distance = curCo[2];
            for (int d = 0; d < delta.length; d++) {    // 사방탐색
                ny = curCo[0] + delta[d][0];
                nx = curCo[1] + delta[d][1];
                if (isIn(ny, nx) && !visited[ny][nx] && oceans[ny][nx]<=shark[2]) { // 범위를 벗어나지 않고 해당 칸을 방문하지 않았으며 지나갈 수 있다면
                    if (oceans[ny][nx]!=0 && oceans[ny][nx]<shark[2]) { // 해당 칸에 물고기가 있고 먹을 수 있다면
                        fishMenu.offer(new int[] {ny, nx, oceans[ny][nx]}); // 해당 칸의 물고기를 메뉴판에 추가
                    }
                    queue.offer(new int[] {ny, nx, curCo[2]+1});    // 해당 칸을 다음 이동 경로에 추가
                    visited[ny][nx] = true; // 해당 칸 방문처리
                }
            }
        }
        System.out.println(times);
    }

    static int[] eat(int distance) {
        int[] sushi = fishMenu.poll();  // 우선순위가 가장 높은 물고기 선택
        times += distance;  // 해당 물고기까지 이동 시간을 최종 시간에 반영
        shark[0] = sushi[0];    // 상어의 위치를 해당 물고기 위치로 이동
        shark[1] = sushi[1];
        if (shark[2] == ++eaten) {  // 상어가 이번 크기에서 잡아먹은 물고기의 수가 상어의 크기와 같다면
            shark[2]++; // 상어 크기 1 증가
            eaten = 0;
        }
        fishMenu.clear();   // 현 위치에서 새롭게 탐색하기 위해 큐 초기화
        queue.clear();
        oceans[sushi[0]][sushi[1]] = 0; // 해당 위치의 물고기 삭제
        sushi[2] = 0;
        return sushi; // 마지막으로 잡아먹힌 물고기의 위치 반환
    }

    static boolean isIn(int i, int j) { // 범위 유효성 검사
        return !(i<0 || i>=N || j<0 || j>=N);
    }
}
