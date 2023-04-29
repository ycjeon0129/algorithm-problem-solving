package boj.gold._5;
// 문자열 처리, 파싱 (, 덱)

import java.io.*;
import java.util.StringTokenizer;

public class BOJ5430_AC {

	static int N, start, end, outputIdx;
	static int[] ac, output;
	static boolean flag;
	static String op;
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		
		for (int t = 0; t < T; t++) {
			op = br.readLine();
			N = Integer.parseInt(br.readLine());
			ac = new int[N];
			output = new int[N];
			start = 0;
			end = N - 1;
			outputIdx = N;
			flag = false;
			String line = br.readLine();
			String[] acs = line.substring(1, line.length()-1).split(",");
			for (int i = 0; i < N; i++) {
				ac[i] = Integer.parseInt(acs[i]);
			}
			parseAC();
		}
		bw.flush();
		bw.close();
	}

	static void parseAC() throws IOException {
		for (int i = 0; i < op.length(); i++) {
			if (op.charAt(i) == 'R') {
				flag = flag ? false: true;
			} else {
				boolean burst = drop();
				if (burst) {
					bw.write(String.format("error\n"));
					return;
				}
			}
		}
		int idx = 0;
		if (flag) {
			for (int i = end; i >= start; i--) {
				output[idx++] = ac[i];
			}
		} else {
			for (int i = start; i <= end; i++) {
				output[idx++] = ac[i];
			}
		}
		print();
	}

	static void print() throws IOException {
		bw.write("[");
		if (outputIdx > 0) {
			bw.write(output[0]+"");
		}
		for (int i = 1; i < outputIdx; i++) {
			bw.write("," + output[i]);
		}
		bw.write("]\n");
	}

	static boolean drop() {
		if (flag) {
			end--;
		} else {
			start++;
		}
		outputIdx--;
		if (outputIdx < 0) {
			return true;
		}
		return false;
	}

}
