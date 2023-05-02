package boj.gold._3_;
// 투 포인터
// 이분 탐색

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2473_세_용액 {

    static int N, first, second, third;
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
        Arrays.sort(liquid);
        experiment();
        System.out.printf("%d %d %d", liquid[first], liquid[second], liquid[third]);
    }

    static void experiment() {
        int fore, body, rear;
        long sum, value = Long.MAX_VALUE;    // 초기값
        for (rear = 0; rear < N - 2; rear++) {  // rear값을 움직이며 모든 경우의 수 탐색
            body = rear + 1;
            fore = N - 1;
            while (body < fore) {
                sum = (long) liquid[fore] + (long) liquid[body] + (long) liquid[rear];
                if (Math.abs(sum) < Math.abs(value)) {  // 새로운 용액값이 최적값일 경우
                    value = sum;    // 정답 갱신
                    first = rear;
                    second = body;
                    third = fore;
                }
                if (sum >= 0) fore--;   // 합이 0보다 크다면 fore 후진
                else body++;    // 합이 0보다 작다면 body 전진
            }
        }
    }
}
