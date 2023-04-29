package ssafy;

import java.util.Arrays;
import java.util.Scanner;

public class PermutationBasicTest {

	static int N, R;
	static int[] numbers;
	static int[] input;
	static boolean[] isSelected;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		R = sc.nextInt();
		
		numbers = new int[R];
		input = new int[N];
		isSelected = new boolean[N];
		
		for(int i = 0; i<N; ++i) {
			input[i] = sc.nextInt();
		}
		
		permutation2(0,0);
	}

	private static void permutation(int cnt) {
		if(cnt== R) {
			System.out.println(Arrays.toString(numbers));
			return;
		}
		for (int i = 0; i < N; i++) {
			if (isSelected[i])
				continue;
			numbers[cnt] = input[i];
			isSelected[i] = true;
			permutation(cnt + 1);
			isSelected[i] = false;
		}
	}
	
	private static void permutation2(int cnt,int flag) {
		if(cnt== R) {
			System.out.println(Arrays.toString(numbers));
			return;
		}
		for (int i = 0; i < N; i++) {
			if ((flag &(1<<i)) != 0)
				continue;
			numbers[cnt] = input[i];
			permutation2(cnt + 1,flag|(1<<i));
		}
	}
}
