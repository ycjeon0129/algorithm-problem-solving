package boj.gold._4_;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ2580_스도쿠 {

    static final int SIZE = 9;
    static int[][] sudoku;
    static boolean flag;    // 스도쿠의 종료 여부 저장

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        sudoku = new int[SIZE][SIZE];

        for (int i = 0; i < SIZE; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < SIZE; j++) {
                sudoku[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        flag = false;
        play(0);

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(sudoku[i][j] + " ");
            }
            System.out.println();
        }
    }

    static void play(int idx) {
        if (idx == 81) {    // (9, 9)까지 모두 유효한 값을 채웠다면 flag 값 토글 후 리턴
            flag = true;
            return;
        }
        int y = idx / SIZE;
        int x = idx % SIZE;
        if (sudoku[y][x] == 0) {    // 값을 채워야 한다면
            for (int n = 1; n <= SIZE; n++) {
                if (!flag) {    // 스도쿠가 아직 진행중이라면
                    sudoku[y][x] = n;
                    if (rowCheck(y, x) && colCheck(y, x) && boxCheck(y, x)) {   // 유효한 값이라면
                        play(idx+1);    // 다음 칸으로 이동
                    }
                }
            }
            if (!flag) sudoku[y][x] = 0;    // 스도쿠가 아직 진행중인 상황에서 해당 값에서 파생된 유효한 재귀가 없다면 해당 값 리셋
        } else {    // 문제에서 이미 주어진 값이라면 다음 칸 진행
            play(idx+1);
        }
    }

    static boolean rowCheck(int i, int j) { // 행 유효성 검사
        for (int k = 0; k < SIZE; k++) {
            if (k == j) {
                continue;
            }
            if (sudoku[i][k] == sudoku[i][j]) {
                return false;
            }
        }
        return true;
    }

    static boolean colCheck(int i, int j) { // 열 유효성 검사
        for (int k = 0; k < SIZE; k++) {
            if (k == i) {
                continue;
            }
            if (sudoku[k][j] == sudoku[i][j]) {
                return false;
            }
        }
        return true;
    }

    static boolean boxCheck(int i, int j) { // 블럭 유효성 검사
        int y = (i / 3) * 3;    // 블럭의 시작점 계산
        int x = (j / 3) * 3;
        for (int m = y; m < y+3; m++) { // 블럭 탐색
            for (int n = x; n < x+3; n++) {
                if (m == i && n == j) {
                    continue;
                }
                if (sudoku[m][n] == sudoku[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
