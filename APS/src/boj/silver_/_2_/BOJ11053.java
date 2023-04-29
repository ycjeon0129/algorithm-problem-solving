package boj.silver_._2_;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ11053 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int[] arr;
	static int[] result;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		arr = new int[N];
		result = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		result[0] = 1;
		int min = arr[0];
		for (int i=1; i<N; i++) {
			if (arr[i] <= min) {
				min = arr[i];
				result[i] = 1;
			}
			else {
				for (int j=i-1; j>=0; j--) {
					if (arr[j] < arr[i]) {
						result[i] = Math.max(result[i], result[j]);
					}
				}
				result[i]++;
			}
			
		}
		
		Arrays.sort(result);


		bw.write(result[N-1]+"");
			
		bw.flush();
		bw.close();
		
	}





}
