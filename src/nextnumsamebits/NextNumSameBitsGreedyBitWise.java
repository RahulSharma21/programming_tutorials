package nextnumsamebits;

import java.util.Scanner;

public class NextNumSameBitsGreedyBitWise {

	public static long getNextNumSameBits(long num) {

		long mask = 1;

		int minOneIndex = -1, minZeroIndex = -1;

		int oneBitsCount = 0, zeroBitsCount = 0;

		long res = 0;

		int idx = 0;

		// iterate through all bits of the input number
		while (mask <= num) {

			if ((mask & num) == mask) {

				// stores the count of 1 bits in the input number
				oneBitsCount++;

				// find the index of right most 1
				if (minOneIndex == -1)
					minOneIndex = idx;

			} else {

				// stores the count of 0 bits in the input number
				zeroBitsCount++;

				// find the index of right most 0
				if (minZeroIndex == -1)
					minZeroIndex = idx;
			}

			idx++;

			mask <<= 1;
		}

		int totalBitsCount = oneBitsCount + zeroBitsCount;

		// if the input number contains 01
		if (minZeroIndex > minOneIndex) {

			// copy of all bits of input number till right most 01
			for (int i = minZeroIndex + 1; i < totalBitsCount; i++) {
				res |= (num & (1 << i));
			}

			// append 10 instead of 01
			res |= (2 << (minZeroIndex - 1));

			// copy all bits of input number after right most 01
			for (int i = 0; i < minZeroIndex - 1; i++) {
				res |= (num & (1 << i));
			}
		} else { // if input number does not contain 01

			// the next number will start with 1 as leftmost bit
			res = 1 << totalBitsCount;

			// set all the oneBitCount - 1 left most bits to 1, rest all bits
			// expect leftmost bit will be 0
			// this will ensure that next number has same number of 1s and
			// difference of next number and
			// input number is minimal
			for (int i = 0; i < oneBitsCount - 1; i++) {
				res |= (1 << i);
			}
		}

		return res;
	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		long num = sc.nextLong();
		System.out.println("Input -> " + num);
		System.out.println("Input in Binary -> " + Long.toBinaryString(num));

		long result = getNextNumSameBits(num);

		System.out
				.println("Output in Binary -> " + Long.toBinaryString(result));
		System.out.println("Output -> " + result);

		sc.close();
	}
}
