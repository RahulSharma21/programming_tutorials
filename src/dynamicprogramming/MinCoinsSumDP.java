package dynamicprogramming;

import java.util.Arrays;

public class MinCoinsSumDP {

	public static void main(String[] args) {

		 int[] coins = { 1, 5, 9, 6 };
		 
		// int[] coins = { 25, 5, 10 };
		
		// int[] coins = { 1, 3, 4 };

		// int target_value = 11;
		
		 int target_value = 30;

		//int target_value = 6;

		int[] dp = new int[target_value + 1];

		// to check the previous indexes used in derving optimal solution for
		// target_value
		int[] prevIdx = new int[target_value + 1];

		Arrays.fill(dp, -1);

		Arrays.fill(prevIdx, -1);

		Arrays.sort(coins);

		dp[0] = 0;

		prevIdx[0] = -1;

		// Determine the optimal number of coins for each value <= target_value
		for (int i = 1; i <= target_value; i++) {

			int minIdx = -1;

			// check for all coins
			for (int j = 0; j < coins.length; j++) {

				int idx = i - coins[j];

				if ((idx >= 0) && (dp[idx] != -1)) {

					if ((dp[i] == -1) || (dp[i] > dp[idx] + 1)) {

						dp[i] = dp[idx] + 1;

						// capture the index that was used
						minIdx = idx;
					}
				}
			}

			prevIdx[i] = minIdx;
		}

		System.out.println("Minimum number of coins required -> " + dp[target_value]);
		
		System.out.println();

		System.out.println("DP states -> " + Arrays.toString(dp));
		
		System.out.println();

		int idx = target_value;
		
		System.out.println("How the solution was dervied - ");

		System.out.println();
		
		System.out.println(idx);

		do {

			int usedCoin = idx - prevIdx[idx];

			idx = prevIdx[idx];

			System.out.println(" -> " + idx + " (used coin of value " + usedCoin
					+ ")");

		} while (prevIdx[idx] >= 0);
	}
}
