package boj.gold._5;
// 스택, 문자열 처리

import java.util.Scanner;

public class BOJ9935_문자열_폭발 {

	static String str, boom;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		str = sc.next();
		boom = sc.next();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < Math.min(str.length(), boom.length()); i++) {
			sb.append(str.charAt(i));
			if (check(sb)) {
				for (int j = 0; j < boom.length(); j++) {
					sb.deleteCharAt(sb.length()-1);
				}
			}

		}
		for (int i = boom.length(); i < str.length(); i++) {
			sb.append(str.charAt(i));
			if (check(sb)) {
				for (int j = 0; j < boom.length(); j++) {
					sb.deleteCharAt(sb.length()-1);
				}
			}
		}
		str = sb.toString();
		if (str.length() != 0) {
			System.out.println(str);
		} else {
			System.out.println("FRULA");
		}
	}

	static boolean check(StringBuilder sb) {
		boolean flag = true;
		if (sb.length()-boom.length() < 0) {
			return false;
		}
		for (int i = sb.length()-boom.length(), idx = 0; i < sb.length(); i++, idx++) {
			if (sb.charAt(i) != boom.charAt(idx)) {
				flag = false;
				break;
			}			
		}
		return flag;
	}

}
