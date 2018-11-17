package nextnumsamebits;

import java.util.Scanner;

public class NextNumSameBitsBruteForce {

	public static long getNextNumSameBits(long num) {

		long result = -1;

		if (num > 0) {
			int bitsSetInNum = Long.bitCount(num);

			while (true) {
				num++;

				if (bitsSetInNum == Long.bitCount(num)) {
					result = num;
					break;
				}
			}
		}

		return result;
	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int cases = sc.nextInt();

		long[] num = new long[cases];

		for (int i = 0; i < cases; i++) {
			num[i] = sc.nextLong();
		}

		for (int i = 0; i < cases; i++) {
			
			System.out.println();
			
			System.out.println("Input -> " + num[i]);

			System.out
					.println("Input in Binary -> " + Long.toBinaryString(num[i]));

			long result = getNextNumSameBits(num[i]);

			System.out.println("Output in Binary -> "
					+ (result != -1 ? Long.toBinaryString(result) : ""));

			System.out.println("Output -> " + (result != -1 ? result : ""));
			
			System.out.println();
		}
		sc.close();
	}
}
