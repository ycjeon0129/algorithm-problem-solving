package boj.gold._3_;
// 연속하는 소수의 합
// ~~

import java.io.*;
import java.util.*;

public class BOJ7512 {

    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int m, value, range = 10_000_000;
    static int[] scenarios, primes = new int[700_000];
    static boolean[] isPrime = new boolean[range+1];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        findPrimes();

        int N = Integer.parseInt(br.readLine());

        for (int i=1; i<=N; i++) {
            m = Integer.parseInt(br.readLine());
            scenarios = new int[m];
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<m; j++) {
                scenarios[j] = Integer.parseInt(st.nextToken());
            }

            msp();
            bw.write(String.format("Scenario %d:\n%d\n\n", i, value));
        }
        bw.flush();
        bw.close();
    }

    static int slidingWindow(int num, int index) {
        int temp = 0;
        int count = 0;
        if (scenarios[num]%2==1 && scenarios[num]!=1) {
            for (int i=index; i<index+scenarios[num]; i++) {
                temp += primes[i];
            }
            if (veri(temp)) {
                return temp;
            }
            for (int i=index+scenarios[num]; i< primes.length; i++) {
                temp = temp + primes[i] - primes[i-scenarios[num]];
                if (veri(temp)) {
                    break;
                }

            }
        } else if (scenarios[num] == 1) {
            for (int i=0; i<scenarios[num]; i++) {
                temp += primes[i];
            }
            if (veri(temp)) {
                return temp;
            }

        } else {
            for (int i=0; i<scenarios[num]; i++) {
                temp += primes[i];
            }
            if (!veri(temp)) {
                temp = -1;
            }
        }
        return temp;
    }

    static boolean veri(int temp) {
        if (isPrime[temp]) {
            return false;
        }
        return true;
    }

    static void msp() {
        int[] indexes = new int[m];
        while (true) {
            int target = slidingWindow(0, indexes[0]);
            for (int i=1; i<m; i++) {
                while (target > slidingWindow(i, indexes[i])) {
                    indexes[i]++;
                }
            }
            boolean same = true;
            for (int i=1; i<m; i++) {
                if (target != slidingWindow(i, indexes[i])) {
                    same = false;
                    break;
                }
            }
            if (same) {
                value = target;
                break;
            }
            indexes[0]++;
        }
    }

    static void findPrimes() {
        isPrime[0] = isPrime[1] = true;
        int count = 0;
        int cut = 0;
        for (int i=2; i*i<=range; i++) {
            if (isPrime[i]) {
                cut = i;
                continue;
            }
            primes[count++] = i;
            for (int j=i+i; j<=range; j+=i) {
                isPrime[j] = true;
            }
        }
        for (int i = cut; i < range; i++) {
            if (!isPrime[i]) {
                primes[count++] = i;
            }
        }
    }

}
