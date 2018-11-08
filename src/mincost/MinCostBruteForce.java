package mincost;

public class MinCostBruteForce {

	public static void main(String[] args) {
		
		long startTime = System.currentTimeMillis();

		int cost_a = 100, cost_b = 20, cost_c = 10;

		int num_a = 5, num_b = 5, num_c = 5;

		//int num_a = 10000, num_b = 10000, num_c = 10000;

		int energy_a = 5, energy_b = 3, energy_c = 2;

		int min_target_energy = 2;
		
		int max_target_energy = 15;
		
		//int max_target_energy = 100;

		for (int target_energy = min_target_energy; target_energy <= max_target_energy; target_energy++) {

			int min_cost = Integer.MAX_VALUE;

			boolean found = false;

			for (int i = 0; i <= num_a; i++)
				for (int j = 0; j <= num_b; j++)
					for (int k = 0; k <= num_c; k++) {

						int t = i * energy_a + j * energy_b + k * energy_c;

						if (t == target_energy) {
							found = true;
							min_cost = Math.min(min_cost, i * cost_a + j
									* cost_b + k * cost_c);
						}
					}

			System.out.println ("target = " + target_energy + " cost = " + (found?min_cost:-1));

		}
		
		System.out.println();
		
		System.out.println("Time Taken -> " + (System.currentTimeMillis() - startTime));

	}

}
