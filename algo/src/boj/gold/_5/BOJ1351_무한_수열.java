package boj.gold._5;
// 해시 맵, DP

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BOJ1351_무한_수열 {

	static HashMap<Long, Long> infiniteSeries;
	static long N, P, Q;

	public static void main(String[] args) {

		infiniteSeries = new HashMap<Long, Long>() {
			{
				put(0L, 1L);
			}
		};
		Scanner sc = new Scanner(System.in);

		N = sc.nextLong();
		P = sc.nextLong();
		Q = sc.nextLong();
		if (N != 0L) {
			set(N);
		}

		System.out.println(infiniteSeries.get(N));
	}

	// 목표 수를 계산. 계산에 필요한 수를 모른다면 해당 수에 대해 재귀 실행
	static void set(long n) {
		long first = n / P;
		long last = n / Q;
		if (!infiniteSeries.containsKey(first)) {
			set(first);
		}
		if (!infiniteSeries.containsKey(last)) {
			set(last);
		}
		infiniteSeries.put(n, infiniteSeries.get(first) + infiniteSeries.get(last));
	}

}
