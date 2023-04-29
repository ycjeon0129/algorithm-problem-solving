package com.ssafy.day21;
// 보물상자 비밀번호
// 문자열 파싱, 구현

import java.io.*;
import java.util.*;

public class Solution_5658_전윤철 {
	
	static int N, K;
	static List<Integer> pass;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		
		for (int t = 1; t <= T; t++) {
			bw.write(String.format("#%d ", t));
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			pass = new ArrayList<Integer>();
			String line = br.readLine();
			int first, second, third, fourth;
			for (int i = 0; i < N/4; i++) {	// 전체 문자열을 4분할하여 회전한 값을 16진법 정수로 변환
				first = Integer.parseInt(line.substring(i, N/4+i), 16);
				second = Integer.parseInt(line.substring(N/4+i, 2*N/4+i), 16);
				third = Integer.parseInt(line.substring(2*N/4+i, 3*N/4+i), 16);
				fourth = Integer.parseInt(line.substring(3*N/4+i) + line.substring(0, i), 16);
				if (!pass.contains(first)) {	// 해당 값이 나온적이 없다면 리스트에 저장
					pass.add(first);
				}
				if (!pass.contains(second)) {
					pass.add(second);
				}
				if (!pass.contains(third)) {
					pass.add(third);
				}
				if (!pass.contains(fourth)) {
					pass.add(fourth);
				}
			}
			Collections.sort(pass, Collections.reverseOrder());	// 내림차순 정렬
			bw.write(pass.get(K-1)+"\n");	// K번째 수 출력
		}
		bw.flush();
		bw.close();
	}

}
