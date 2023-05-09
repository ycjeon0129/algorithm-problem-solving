package boj.gold._2;

import java.io.*;
import java.util.*;

public class BOJ1938_통나무_옮기기 {
	
	static int N, times;
	static char[][] map;
	static int[] by, bx, ey, ex;
	static int[][] delta = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		int bCnt = 0, eCnt = 0;
		by = new int[3];	// B 기둥의 y 좌표 배열
		bx = new int[3];	// B 기둥의 x 좌표 배열
		ey = new int[3];	// E 기둥의 y 좌표 배열
		ex = new int[3];	// E 기둥의 x 좌표 배열
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = line.charAt(j);
				if (map[i][j] == 'B') {
					by[bCnt] = i;
					bx[bCnt++] = j;
				}
				else if (map[i][j] == 'E') {
					ey[eCnt] = i;
					ex[eCnt++] = j;
				}
			}
		}
		run();
		System.out.println(times);
	}

	static void run() {
		boolean checked[][] = new boolean[N][N];	// 방문 체크 배열
		boolean turned[][] = new boolean[N][N];		// 해당 위치에서 회전 했는지 여부 저장 배열
		Queue<oak> queue = new ArrayDeque<oak>();
		queue.offer(new oak(0, by, bx));	// B 위치 큐에 저장
		checked[by[1]][bx[1]] = true;		// B의 초기 위치 방문 처리
		while (!queue.isEmpty()) {
			oak cur = queue.poll();
			int[] cy = cur.co[0].clone();
			int[] cx = cur.co[1].clone();
			int time = cur.time;
			if (isSame(cy, ey) && isSame(cx, ex)) {		// 현재 좌표가 E의 좌료와 같다면
				times = time;	// 현재까지 이동 횟수 저장 및 종료
				return;
			}
			for (int d = 0; d < delta.length; d++) {	// 사방탐색
				int[] ny = move(cy, d, 0);
				int[] nx = move(cx, d, 1); 
				// 범위 내 && 미방문 좌표 && 이동 가능 하다면
				if (isIn(ny, nx) && !checked[ny[1]][nx[1]] && isMove(ny, nx)) {	
					checked[ny[1]][nx[1]] = true;	// 방문처리 
					queue.offer(new oak(time+1, ny, nx));	// 큐에 저장
				}
			}
			if (!turned[cy[1]][cx[1]] && isTurn(cy[1], cx[1])) {	// 회전하지 않은 좌표이고 회전 가능하다면
				turned[cy[1]][cx[1]] = true;	// 회전 여부 저장
				queue.offer(turn(cur));		// 큐에 저장
			}
		}
		
	}
	
	static oak turn(oak cur) {
		int[] ny = null;
		int[] nx = new int[3];
		if (cur.co[0][0] == cur.co[0][1]) {	// y값이 같다면 -> 수평
			ny = new int[] {cur.co[0][1]-1, cur.co[0][1], cur.co[0][1]+1};	// 회전
			nx = new int[] {cur.co[1][1], cur.co[1][1], cur.co[1][1]};
		} else {	// x값이 같다면 -> 수직
			ny = new int[] {cur.co[0][1], cur.co[0][1], cur.co[0][1]};
			nx = new int[] {cur.co[1][1]-1, cur.co[1][1], cur.co[1][1]+1};
		}
		return new oak(cur.time+1, ny, nx);
	}

	static boolean isTurn(int y, int x) {
		if (y<1 || y>=N-1 || x<1 || x>=N-1) {	// 기둥 전체가 범위 내이고
			return false;
		}
		for (int i = y-1; i < y+2; i++) {	// 중점 기준 앞뒤 한칸에 '1'이 없다면
			for (int j = x-1; j < x+2; j++) {
				if (map[i][j] == '1') {
					return false;
				}
			}
		}
		return true;
	}

	static boolean isMove(int[] ny, int[] nx) {	// 기둥의 다음 좌표에 1이 없는지 확인
		for (int i = 0; i < ny.length; i++) {
			if (map[ny[i]][nx[i]] == '1') {
				return false;
			}
		}
		return true;
	}

	static boolean isIn(int[] ny, int[] nx) {	// 기둥이 범위 내에 있는지 확인
		for (int i = 0; i < ny.length; i++) {
			if (ny[i] < 0 || ny[i] >= N) {
				return false;
			}
		}
		for (int i = 0; i < nx.length; i++) {
			if (nx[i] < 0 || nx[i] >= N) {
				return false;
			}
		}
		return true;
	}

	static int[] move(int[] arr, int d, int dd) {	// 기둥의 x, y좌표를 각 delta 순서에 맞게 이동
		int[] arr2 = new int[3];
		for (int i = 0; i < arr2.length; i++) {
			arr2[i] = arr[i] + delta[d][dd];
		}
		return arr2;
	}

	static boolean isSame(int[] a, int[] b) {	// 기둥의 위치 비교
		boolean flag = true;
		for (int i = 0; i < 3; i++) {
			if (a[i] != b[i]) {	// 한 좌표라도 위치가 다르면 flase 리턴
				flag = false;
			}
		}
		return flag;
	}

	static class oak {	// 기둥의 좌표 및 시간을 저장하는 클래스
		int time;
		int[][] co;
		public oak(int time, int[] y, int[] x) {
			this.time = time;
			co = new int[][] {y, x};
		}
	}

}
