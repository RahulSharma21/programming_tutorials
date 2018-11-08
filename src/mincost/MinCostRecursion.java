package mincost;

public class MinCostRecursion {

	public static void main(String[] args) {
		
		long startTime = System.currentTimeMillis();
		
		int min_target_energy = 2;
		
		int max_target_energy = 15;
		
		//int max_target_energy = 100;

		for (int target_energy = min_target_energy; target_energy <= max_target_energy; target_energy++) {
	
			int[] cost = { 10, 20, 100 };

			int[] num = { 5, 5, 5 };

			//int[] num = { 10000, 10000, 10000 };

			int[] energy = { 2, 3, 5 };

			int min_cost = solve(target_energy, cost, num, energy);

			System.out.println ("target = " + target_energy + " cost = " + min_cost);
		}
		
		System.out.println();
		
		System.out.println("Time Taken -> " + (System.currentTimeMillis() - startTime));

	}

	private static int solve(int target_energy, int[] cost, int[] num,
			int[] energy) {

		if (target_energy == 0) {
			return 0;
		}

		int min_cost = -1;

		for (int i = 0; i < energy.length; i++) {

			if ((target_energy >= energy[i]) && (num[i] > 0)) {

				int []num2 = new int[num.length];
				
				System.arraycopy(num, 0, num2, 0, num.length);
				
				num2[i]--;
				
				int cst = solve(target_energy - energy[i], cost, num2, energy);
						
				if ((cst != -1) && ((min_cost == -1) || (min_cost > cst + cost[i]))) {
					min_cost = cst + cost[i];
				}
			}
		}

		return min_cost;
	}
}