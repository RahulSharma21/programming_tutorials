package mincost;

import lpsolve.LpSolve;

public class MinCostLP {

	public static void main(String[] args) {
		try {
	
			//System.load("E:\\Softwares\\Maths\\LPSolve\\lpsolve55j.dll");

			int cost_a = 100, cost_b = 20, cost_c = 10;

			int num_a = 5, num_b = 5, num_c = 5;

			int energy_a = 5, energy_b = 3, energy_c = 2;

			int target_energy = 15;
			
			LpSolve problem = LpSolve.makeLp(0, 3);
			
			problem.strAddConstraint("" + energy_a + " " + energy_b + " " + energy_c, LpSolve.EQ, target_energy);
			
			problem.strAddConstraint("1 0 0", LpSolve.LE, num_a);
			problem.strAddConstraint("1 0 0", LpSolve.GE, 0);
			
			problem.strAddConstraint("0 1 0", LpSolve.LE, num_b);
			problem.strAddConstraint("0 1 0", LpSolve.GE, 0);
			
			problem.strAddConstraint("0 0 1", LpSolve.LE, num_c);
			problem.strAddConstraint("0 0 1", LpSolve.GE, 0);
			
			problem.strSetObjFn("" + cost_a + " " + cost_b + " " + cost_c);
			
			problem.setMinim();	
			problem.solve();
			System.out.println("LP");
			problem.printLp();
			System.out.println("OBJ");
			problem.printObjective();
			System.out.println("SOL");
			problem.printSolution(1);
			System.out.println("CON");
			problem.printConstraints(1);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
