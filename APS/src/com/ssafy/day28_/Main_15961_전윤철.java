package com.ssafy.day28_;
// 회전 초밥
// 슬라이딩 윈도우

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_15961_전윤철 {

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

        num++;  // 쿠폰으로 제공되는 초밥은 무조건 먹음
        ate[Coupon]++;

        eat();
        System.out.println(num);
    }

    static void eat() {
        int temp = 1;
        for (int i=0; i<K; i++) {   // 이번트에 참여하기 위해 K개의 초밥을 먼저 먹음
            if ( (ate[belt[i]]++) == 0 ) {  // 먹은 적 없는 초밥이라면 temp값 증가
                temp++;
            }
        }
        num = temp;

        for (int i=0; i<N; i++) {
            // 새로 추가하는 숫자
            if ( ( ate[ belt[(i+K)%N] ]++ ) == 0 ) {    // 새로운 초밥이 먹은 적 없는 초밥이라면
                temp++;
            }
            if ( belt[(i+K)%N] == Coupon ) {

            }
            if ( ( --ate[ belt[i] ] ) == 0 ) {  // 새로운 초밥을 선택하기 위해 포기하는 초밥이 기존에 유일하게 먹었던 초밥이라면
                temp--;
            }
            num = temp > num ? temp : num;  // 최댓값 갱신
        }
    }
}
