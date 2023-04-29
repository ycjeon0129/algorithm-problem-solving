package com.ssafy.day17_;
// gold 4. 게리맨더링
// 조합, 비트마스킹, bfs

import java.io.*;
import java.util.*;

public class Main_17471_전윤철 {

    static int N, bitMask, diff = Integer.MAX_VALUE;
    static int[] populations;
    static boolean[] sector;
    static List<Integer> neighbors[], sectorA, sectorB;
    static Queue<Integer> queue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        populations = new int[N+1];
        sector = new boolean[N+1];
        bitMask = (int) Math.pow(2, N) - 1;

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            populations[i] = Integer.parseInt(st.nextToken());
        }
        neighbors = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) {
            neighbors[i] = new ArrayList<Integer>();
            st = new StringTokenizer(br.readLine());
            int connector = Integer.parseInt(st.nextToken());
            for (int j = 0; j < connector; j++) {
                neighbors[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        divide();
        // diff값이 갱신되지 않았다면 -1 출력
        System.out.println( diff==Integer.MAX_VALUE ? -1 : diff );
    }

    static void divide() {
        // 양 선거구에 최소 한개 이상의 구역이 포함되도록 분배
        for (int i = 1; i < bitMask; i++) {
            sectorA = new ArrayList<Integer>();
            sectorB = new ArrayList<Integer>();
            for (int j = 1; j <= N; j++) {
                // 해당 구역의 비트가 1이라면 선거구 A, 0이라면 선거구 B
                if ( (i & (1 << (j-1)) ) != 0) { sectorA.add(j); sector[j] = true; } else { sectorB.add(j); sector[j] = false; }
            }
            if (valid()) {
                // 두 선거구의 인구수 총합 차의 최솟값 갱신
                diff = Math.min(diff, Math.abs(getPopulation(true) - getPopulation(false)));
            }
        }
    }

    // 선거구에 포함된 구역의 인구수 총합 계산
    static int getPopulation(boolean flag) {
        int sum = 0;
        for (int i = 1; i <= N; i++) {
            if (sector[i] == flag) {
                sum += populations[i];
            }
        }
        return sum;
    }

    // 각 선거구가 유효하게 나뉘었는지 검사
    static boolean valid() {
        // 선거구 A의 첫 구역에서부터 이동할 수 있는 인접한 모든 선거구 A에 속한 구역 탐색
        queue = new ArrayDeque<Integer>();
        queue.offer(sectorA.get(0));
        sectorA.remove(0);
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int i = 0; i < neighbors[cur].size(); i++) {
                int next = neighbors[cur].get(i);
                if (sectorA.contains(next)) {
                    queue.offer(next);
                    sectorA.remove(sectorA.indexOf(next));
                }
            }
        }
        // 인접한 구역을 모두 탐색한 뒤에 방문하지 않은 선거구 A의 구역이 있다면 유효하지 않은 분배
        if (!sectorA.isEmpty()) {
            return false;
        }

        // 선거구 B의 첫 구역에서부터 이동할 수 있는 인접한 모든 선거구 A에 속한 구역 탐색
        queue = new ArrayDeque<Integer>();
        queue.offer(sectorB.get(0));
        sectorB.remove(0);
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int i = 0; i < neighbors[cur].size(); i++) {
                int next = neighbors[cur].get(i);
                if (sectorB.contains(next)) {
                    queue.offer(next);
                    sectorB.remove(sectorB.indexOf(next));
                }
            }
        }
        // 인접한 구역을 모두 탐색한 뒤에 방문하지 않은 선거구 B의 구역이 있다면 유효하지 않은 분배
        if (!sectorB.isEmpty()) {
            return false;
        }
        return true;
    }
}
