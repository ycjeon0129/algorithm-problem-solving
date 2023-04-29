package boj.silver_._2_;
// dp, 소수판별

import java.io.*;

public class BOJ4948_베르트랑_공준 {
	
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static boolean[] primes = new boolean[250_000];
	static int[]  pDP = new int[250_000];

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		isPrime();
		setDP();

		while (true) {
			 int temp = Integer.parseInt(br.readLine());
			 if (temp==0) {
			 	break;
			 }
			 sb.append(pDP[temp*2]-pDP[temp]+"\n");
		}
		System.out.println(sb.toString());
	}

	static void setDP() {
		int count = 0;
		for (int i=1; i<250_000; i++) {
			if (!primes[i]) {
				count++;
			}
			pDP[i] = count;
		}
	}

	static void isPrime() {
		primes[0] = primes[1] = true;
		for (int i=2; i*i<250_000; i++) {
			if (primes[i]) {
				continue;
			}
			for (int j=i+i; j<250_000; j+=i) {
				primes[j] = true;
			}
		}
		
	}

}
