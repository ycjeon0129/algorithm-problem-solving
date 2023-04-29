import java.util.Arrays;
import java.util.Scanner;

// nPn 
public class PermutationNPTest {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int[] input = new int[N];

        
        for(int i = 0; i<N; ++i) input[i] = sc.nextInt();
        
        // 전처리 : 오름차순 정렬
        Arrays.sort(input);
    
        do {
            System.out.println(Arrays.toString(input));
            
            
        }while(np(input));// 다음 순열이 있으면 true 없으면 false
    }

    private static boolean np(int[] input) {

        int n = input.length;
        
        // step 1. 뒤쪽부터 꼭대기를 찾는다. (꼭대기 바로 앞이 교환할 자리)
        int i = n -1; // 맨 뒤부터 탐색
        while(i > 0 &&input[i-1]>=input[i]) i--; // 평평한 모습이나 내리막길 모습이다.
        // 가장 큰 순열은 없다
        if(i==0) return false;
        
        // step2. 꼭대기 바로 앞(i-1)자리에 교환할 값을 뒤쪽부터 찾는다.
        int j = n-1;
        while(input[i-1]>= input[j]) --j; // i-1(비교할 값)보다 한단계 큰것과 교환해야한다.
            
        // step3. 꼭대기 바로 앞(i-1)자리와 그 자리값보다 한단계 큰 자리(j) 수와 교환
        swap(input,i-1,j);
        
        // step4. 꼭대기부터 맨 뒤까지 오름차순으로 정렬
        int k = n-1;
        while(i<k) {
            swap(input,i++,k--);
        }
        return true;
    }
    private static void swap(int[] input, int i, int j) {
        int temp = input[i];
        input[i] = input[j];
        input[j] = temp;
    }

}