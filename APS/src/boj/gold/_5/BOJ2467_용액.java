package boj.gold._5;
// 투 포인터

import java.io.*;
import java.util.*;

public class BOJ2467_용액 {

    static int N, first, second;
    static int[] liquid;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        liquid = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            liquid[i] = Integer.parseInt(st.nextToken());
        }
        experiment();
        System.out.printf("%d %d", liquid[first], liquid[second]);
    }

    static void experiment() {
        int fore = N - 1, rear = 0, sum;
        int value = liquid[fore] + liquid[rear];    // 양 끝 값
        first = rear;
        second = fore;
        while (rear < fore) {   // 전체 조합 중
            sum = liquid[fore] + liquid[rear];
            if (Math.abs(sum) < Math.abs(value)) {  // 새로운 용액값이 최적값일 경우
                value = sum;    // 정답 갱신
                first = rear;
                second = fore;
            }
            if (sum >= 0) fore--;   // 합이 0보다 크다면 fore 후진
            else rear++;    // 합이 0보다 작다면 rear 전진
        }
    }
}
