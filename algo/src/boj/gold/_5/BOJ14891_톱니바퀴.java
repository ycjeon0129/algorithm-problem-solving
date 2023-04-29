package boj.gold._5;
// 비트마스크, 구현

import java.io.*;
import java.util.*;

public class BOJ14891_톱니바퀴 {

    static int K, score;
    static final int numOfGear = 4, numOfSaw = 8;
    static int[] gears = new int[4];
    static int[][] orders;
    static boolean[] visited;
    static Queue<int[]> tasks;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 톱니바퀴의 상태를 비트마스크 처리
        for (int i = 0; i < numOfGear; i++) {
            String line = br.readLine();
            for (int j = 0; j < numOfSaw; j++) {
                if (line.charAt(j)=='1')
                gears[i] += (1 << (numOfSaw-j-1));
            }
        }
        K = Integer.parseInt(br.readLine());
        // 수행할 명령을 배열에 저장
        orders = new int[K][2];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            orders[i][0] = Integer.parseInt(st.nextToken())-1;
            orders[i][1] = Integer.parseInt(st.nextToken());
        }
        run();
        System.out.println(score);
    }

    static void run() {
        int[] temp = new int[2];
        for (int i = 0; i < K; i++) {
            tasks = new ArrayDeque<int[]>();
            tasks.offer(orders[i]);
            visited = new boolean[numOfGear];
            visited[orders[i][0]] = true;
            // 회전 가능한 톱니의 상황을 저장
            List<int[]> rotateList = new ArrayList<int[]>();
            while (!tasks.isEmpty()) {
                temp = tasks.poll();
                rotateList.add(temp);
                // 좌측 톱니 확인
                if ( temp[0]>0 && !visited[temp[0]-1] && ( ((gears[temp[0]] >> 1) & 1) != ((gears[temp[0]-1] >> 5) & 1) ) ) {
                    tasks.offer(new int[] {temp[0]-1, temp[1]*-1});
                    visited[temp[0]-1] = true;
                }
                // 우측 톱니 확인
                if (temp[0]<3 && !visited[temp[0]+1] && ( ((gears[temp[0]] >> 5) & 1) != ((gears[temp[0]+1] >> 1) & 1) )) {
                    tasks.offer(new int[] {temp[0]+1, temp[1]*-1});
                    visited[temp[0]+1] = true;
                }
            }
            // 회전 가능한 톱니를 모두 회전
            for (int[] doIt : rotateList) {
                rotate(doIt[0], doIt[1]);
            }
        }
        // 점수 계산
        getScore();
    }

    // 각 톱니바퀴의 최상단 톱니 상태에 따라 점수 계산
    static void getScore() {
        for (int i = 0; i < numOfGear; i++) {
            if (gears[i]>>7 == 1) {
                score += (1 << i);
            }
        }
    }

    // 방향에 따라 톱니바퀴 회전
    static void rotate(int gear, int direction) {
        if (direction == 1) {
            gears[gear] = (gears[gear] >> 1) | ((gears[gear] << 7) & 0b10000000);
        } else {
            gears[gear] = ((gears[gear] << 1) & 0b11111111) | (gears[gear] >> 7);
        }
    }
}
