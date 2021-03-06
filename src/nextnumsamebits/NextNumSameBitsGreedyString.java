package nextnumsamebits;

import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

public class NextNumSameBitsGreedyString {

	public static String getNextNumSameBits(long num) {

		String result = null;

		String str = "" + Long.toBinaryString(num);

		int idx = str.lastIndexOf("01");

		// if input number in binary representation contains 01
		if (idx > -1) {

			// replace the left most 01 in input number with 10
			result = str.substring(0, idx) + "10";

			String substr = str.substring(idx + 2, str.length());

			int oneCount = StringUtils.countMatches(substr, "1");

			int zeroCount = StringUtils.countMatches(substr, "0");

			// Append the 0s in the remaining substring after left most 01,
			// followed by 1s in the remaining substring
			result += (StringUtils.repeat("0", zeroCount) + StringUtils.repeat(
					"1", oneCount));

		} else { // if input number does not contain 01

			int oneCount = StringUtils.countMatches(str, "1");

			int zeroCount = StringUtils.countMatches(str, "0");

			// number should have at least one 1
			if (oneCount > 0) {
				// start with 1 and append zeroCount + 1 0s where zeroCount is
				// count
				// of 0s in input number
				result = "1" + StringUtils.repeat("0", zeroCount + 1);

				// now append oneCount - 1 1s where oneCount is count of 1s in
				// input
				// number
				result = result + StringUtils.repeat("1", oneCount - 1);
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

			String result = getNextNumSameBits(num[i]);

			System.out.println("Output in Binary -> "
					+ (result != null ? result : ""));

			System.out.println("Output -> "
					+ (result != null ? Long.parseLong(result, 2) : ""));
			
			System.out.println();
		}

		sc.close();
	}
}
