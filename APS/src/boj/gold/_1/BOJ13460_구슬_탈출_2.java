package boj.gold._1;
// BFS, 구현, 시뮬레이션, 그래프

import java.io.*;
import java.util.*;

public class BOJ13460_구슬_탈출_2 {

    static int N, M;
    static int[][] delta = { {-1, 0}, {0, 1}, {1, 0}, {0, -1} };
    static char[][] map;
    static Marble blue, red, exit;
    static Queue<Marble[]> queue;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j);
                switch (map[i][j]) {
                    case 'R':
                        red = new Marble(i, j, -1, 0);
                        map[i][j] = '.';
                        break;
                    case 'B':
                        blue = new Marble(i, j, -1, 0);
                        map[i][j] = '.';
                        break;
                    case '0':
                        exit = new Marble(i, j, -1, 0);
                        break;
                }
            }
        }
        play();
    }

    private static void play() {
        Map<String, Boolean> visied = new HashMap<>();
        queue = new ArrayDeque<Marble[]>();
        queue.offer(new Marble[] {red, blue});
        Marble r, b, nr, nb, marbles[] = null;
        while (!queue.isEmpty()) {
            marbles = queue.poll();
            r = marbles[0];
            b = marbles[1];
            if (r.times == 10) break;   // 제한 횟수 초과 시 중지
            for (int d = 0; d < delta.length; d++) {    // 4방 탐색
                if (r.dir == d) continue;
                boolean blueFirst;
                if (d % 2 == 1) blueFirst = rowCheck(r, b, d);  // 방향 및 구슬들의 위치에 따라 먼저 이동할 구슬 결정
                else blueFirst = colCheck(r, b, d);
                if (blueFirst) {    // 순서에 맞게 구슬 이동 및 구슬이 탈출할 경우에 대한 처리
                    nb = move(b, r, d);
                    if (nb.dir == -1) {
                        continue;
                    }
                    nr = move(r, nb, d);
                    if (nr.dir == -1) {
                        System.out.println(nr.times);
                        return;
                    }
                } else {
                    nr = move(r, b, d);
                    nb = move(b, nr, d);
                    if (nb.dir == -1) {
                        continue;
                    }
                    if (nr.dir == -1) {
                        System.out.println(nr.times);
                        return;
                    }
                }
                if (r.x != nr.x || r.y != nr.y || b.x != nb.x || b.y != nb.y) { // 해당 좌표 및 방향에서의 방향체크
                    StringBuilder sb = new StringBuilder();
                    sb.append(nr.y).append(nr.x).append(nb.y).append(nb.x).append(nr.dir);
                    String cur = sb.toString();
                    if (!visied.containsKey(cur)) {
                        queue.offer(new Marble[] {nr, nb});
                        visied.put(cur, true);
                    }
                }
            }
        }
        System.out.println(-1); // 빨간 공이 제한 횟수 내에 탈출하지 못한다면 -1 출력
    }

    private static Marble move(Marble marble, Marble another, int dir) {    // 구슬 이동 메서드
        int y = marble.y + delta[dir][0];
        int x = marble.x + delta[dir][1];
        int anotherY = another.y;
        int anotherX = another.x;
        // 범위 내에서 빈 칸(다른 구슬과 겹치지 않음)인 경우 계속 해당 방향으로 이동
        while ( isIn(y, x) && map[y][x] == '.' && !(x==anotherX && y==anotherY) ) {
            y += delta[dir][0];
            x += delta[dir][1];
        }
        if (isIn(y, x) && map[y][x] == 'O') {   // 구슬이 탈출할 경우
            return new Marble(-1, -1, -1, marble.times + 1);
        } else {    // 구슬이 탈출하지 못한 경우
            return new Marble(y + delta[(dir+2)%4][0], x + delta[(dir+2)%4][1], dir, marble.times + 1);
        }
    }

    private static boolean isIn(int i, int j) {
        return !(i<0 || i>=N || j<0 || j>=M);
    }

    private static boolean rowCheck(Marble red, Marble blue, int dir) { // 열 이동 시 먼저 움직여야 할 구슬 체크
        if (red.y == blue.y) {
            if ( (dir == 1 && red.x < blue.x) || (dir == 3 && red.x > blue.x) )
                return true;
        }
        return false;
    }

    private static boolean colCheck(Marble red, Marble blue, int dir) { // 행 이동 시 먼저 움직여야 할 구슬 체크
        if (red.x == blue.x) {
            if ( (dir == 0 && red.y > blue.y) || (dir == 2 && red.y < blue.y) )
                return true;
        }
        return false;
    }

    static class Marble {   // 구슬의 좌표, 방향, 이동횟수를 담은 클래스
        int y, x, dir, times;
        public Marble(int y, int x, int dir, int times) {
            this.y = y;
            this.x = x;
            this.dir = dir;
            this.times = times;
        }
    }
}
