package boj.silver_._1_;
// 회전 초밥
// 슬라이딩 윈도우

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2531 {

    static int N, Sushi, K, Coupon, num;
    static int[] belt;
    static int[] ate;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        Sushi = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        Coupon = Integer.parseInt(st.nextToken());
        belt = new int[N];
        ate = new int[Sushi+1];
        for (int i = 0; i < N; i++) {
            belt[i] = Integer.parseInt(br.readLine());
        }

        num++;
        ate[Coupon]++;

        eat();
        System.out.println(num);
    }

    static void eat() {
        int temp = 1;
        for (int i=0; i<K; i++) {
            if ( (ate[belt[i]]++) == 0 ) {
                temp++;
            }
        }
        num = temp;
       // 진짜 어렵다 aorqnr 왜 >?????? dlrj dl안녕 ? 왜 그래 정말 바보멍청이 윤철리 안
        for (int i=0; i<N; i++) {
            // 새로 추가하는 숫자
            if ( ( ate[ belt[(i+K)%N] ]++ ) == 0 ) {
                temp++;
            }
            if ( belt[(i+K)%N] == Coupon ) {

            }
            if ( ( --ate[ belt[i] ] ) == 0 ) {
                temp--;
            }
            num = temp > num ? temp : num;
        }
    }
}
