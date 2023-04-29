package boj.gold._5;
// DnC

import java.util.*;

public class BOJ5904_Moo_게임 {

    static int N;
    static final int MAX = 28;
    static int[] len;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        len = new int[MAX];
        len[0] = 3;

        findLength();
    }

    static void findLength() {
        int idx = 0;
        while (len[idx] < N) {
            len[++idx] = (len[idx-1] * 2) + (idx + 2) + 1;    // 점화식
        }
        parse(idx);
    }

    static void parse(int depth) {
        if (depth == 0) {   // 분할 정복의 탈출조건 - 최소 단위
            if (N == 1) {
                System.out.println("m");
            } else {
                System.out.println("o");
            }
            return;
        }
        if (N > len[depth-1] && N <= len[depth-1]+depth+3) {    // 중간 영역일 경우
            if (N == len[depth-1]+1) System.out.println("m");   // 영역의 시작 m
            else System.out.println("o");   // 이외 o
            return;
        } else if (N > len[depth-1]) {  // 끝 영역일 경우 첫 영역 취급
            N -= (len[depth-1]+depth+3);
        }
        parse(depth-1); // 재귀 호출을 통한 분할 정복
    }
}
