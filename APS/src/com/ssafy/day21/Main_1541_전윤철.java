package com.ssafy.day21;
//	silver 4. 잃어버린 괄호
// 문자열 파싱

import java.util.*;

public class Main_1541_전윤철 {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		String expressions = sc.next();
		
		int sum = 0, minusGroup;
		String[] minus = null, plus = null;
		
		if (expressions.contains("-")) {	// 전체 수식에 대해 -로 나눔
			minus = expressions.split("-");
		} else {	// 수식에 -가 없다면 모두 덧셈하기 위해 일단 minus[0]에 저장
			minus = new String[1];
			minus[0] = expressions;		
		}		
		
		if (minus[0].contains("+")) {	// -로 나눈 첫 수식을 +로 나눔 
			plus = minus[0].split("\\+");
		} else {	// 수식에 +가 없다면 결과를 출력하기 위해 일단 plus[0]에 저장
			plus = new String[1];
			plus[0] = minus[0];
		}
		
		for (int i = 0; i < plus.length; i++) {	// 첫 plus[] 값을 모두 덧셈 연산
			sum += Integer.parseInt(plus[i]);
		}
		//
		for (int i = 1; i < minus.length; i++) {	// - 로 묶이는 이후 모든 그룹에 대해 그룹합을 계산한 후 합계에서 뺄셈 연산
			minusGroup = 0;
			plus = null;
			if (minus[i].contains("+")) {
				plus = minus[i].split("\\+");			
			} else {
				plus = new String[1];
				plus[0] = minus[i];			
			}
			for (int j = 0; j < plus.length; j++) {
				minusGroup += Integer.parseInt(plus[j]);
			}
			sum -= minusGroup;
		}
		System.out.println(sum);
	}

}
