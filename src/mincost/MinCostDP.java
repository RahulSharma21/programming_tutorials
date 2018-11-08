package mincost;

import java.util.Arrays;

public class MinCostDP {

	public static void main(String[] args) {

		long startTime = System.currentTimeMillis();

		// cost of the fruits
		int[] cost_fruits = { 100, 20, 10 };

		// number of fruits
		int[] num_fruits = { 5, 5, 5 };

		//int[] num_fruits = { 10000, 10000, 10000 };

		// energies of fruits
		int[] energy_fruits = { 5, 3, 2 };

		int target_energy = 15;

		//int target_energy = 100;

		// dp holds the cost of achieving a particular target energy
		int[] dp = new int[target_energy + 1];

		// dp num holds the number of fruits consumed while achieving a
		// particular target energy
		int[][] dp_num_consumed = new int[target_energy + 1][num_fruits.length];

		// initialize all dp costs with -1 indicating they are not achievable
		Arrays.fill(dp, -1);

		// Cost of achieving 0 energy is 0
		dp[0] = 0;

		// Sort energy and cost by increasing order of energy
		for (int i = 0; i < energy_fruits.length; i++) {

			for (int j = i + 1; j < energy_fruits.length; j++) {

				if (energy_fruits[i] > energy_fruits[j]) {

					int temp = energy_fruits[i];
					energy_fruits[i] = energy_fruits[j];
					energy_fruits[j] = temp;

					temp = cost_fruits[i];
					cost_fruits[i] = cost_fruits[j];
					cost_fruits[j] = temp;
				}
			}
		}

		// cost of achieving the minimum energy
		dp[energy_fruits[0]] = cost_fruits[0];

		// achieving minimum energy level equal to energy level of minimum fruit
		// consumes 1 fruit of minimum cost
		dp_num_consumed[energy_fruits[0]][0] = 1;

		// calculate costs for all energy levels till target energy
		for (int current_energy_level = 1 + energy_fruits[0]; current_energy_level <= target_energy; current_energy_level++) {

			int min_cost = Integer.MAX_VALUE;

			int min_j = -1;

			int min_energy = -1;

			// iterate through energy of all fruits to determine one with
			// minimum cost

			for (int j = 0; j < energy_fruits.length; j++) {

				int energy_fruit = energy_fruits[j];

				/*
				 * if this fruits's energy more than or equal to current energy
				 * level cost of last state is achievable i.e. != -1 we have
				 * consumed less than total number of available for this fruit
				 */

				if ((current_energy_level - energy_fruit >= 0)
						&& (dp[current_energy_level - energy_fruit] != -1)
						&& (dp_num_consumed[current_energy_level - energy_fruit][j] < num_fruits[j])) {

					if (dp[current_energy_level - energy_fruit]
							+ cost_fruits[j] < min_cost) {

						min_cost = dp[current_energy_level - energy_fruit]
								+ cost_fruits[j];

						min_energy = energy_fruit;

						min_j = j;
					}
				}
			}

			if (min_j > -1) {

				// increment the number of this fruit consumed

				System.arraycopy(dp_num_consumed[current_energy_level
						- min_energy], 0,
						dp_num_consumed[current_energy_level], 0,
						dp_num_consumed[0].length);

				dp_num_consumed[current_energy_level][min_j]++;

			}

			if (min_cost != Integer.MAX_VALUE)
				dp[current_energy_level] = min_cost;
		}

		
		System.out.println ("target = " + target_energy + " cost = " + dp[target_energy]);
		
		System.out.println();
		
		for(int i = 0; i < dp.length; i++)
			System.out.println ("target = " + i + " cost = " + dp[i]);
		
		// System.out.println(Arrays.toString(dp));

		/*
		 * for (int[] dp_num : dp_num_consumed)
		 * System.out.println(Arrays.toString(dp_num));
		 */
		
		System.out.println();
		
		System.out.println("Time Taken -> "
				+ (System.currentTimeMillis() - startTime));

	}
}
