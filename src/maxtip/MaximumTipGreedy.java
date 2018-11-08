	package maxtip;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Tip {

	private int tipR;

	private int tipA;

	public int getTipR() {
		return tipR;
	}

	public int getTipA() {
		return tipA;
	}

	public Tip(int tipR, int tipA) {
		this.tipR = tipR;
		this.tipA = tipA;
	}

	@Override
	public String toString() {
		return "Tip [tipR=" + tipR + ", tipA=" + tipA + "]";
	}
}

public class MaximumTipGreedy {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int cases = sc.nextInt();

		for (int cs = 0; cs < cases; cs++) {

			int n = sc.nextInt();

			int r_capacity = sc.nextInt();

			int a_capacity = sc.nextInt();

			int r[] = new int[n];

			int a[] = new int[n];

			List<Tip> tips = new ArrayList<Tip>(n);

			for (int i = 0; i < n; i++) {
				r[i] = sc.nextInt();
			}

			for (int i = 0; i < n; i++) {
				a[i] = sc.nextInt();
			}

			for (int i = 0; i < n; i++) {
				tips.add(new Tip(r[i], a[i]));
			}

			tips.sort((t1,  t2) -> Math.abs(t1.getTipR() - t1.getTipA()) >= Math
					.abs(t2.getTipR() - t2.getTipA()) ? -1 : 1);

			int r_count = 0, a_count = 0;

			int maxTip = 0;

			for (Tip tip : tips) {

				if ((tip.getTipR() - tip.getTipA()) >= 0) {

					if (r_count < r_capacity) {
						maxTip += tip.getTipR();
						r_count++;
					} else if (a_count < a_capacity) {
						maxTip += tip.getTipA();
						a_count++;
					}

				} else {

					if (a_count < a_capacity) {
						maxTip += tip.getTipA();
						a_count++;
					} else if (r_count < r_capacity) {
						maxTip += tip.getTipR();
						r_count++;
					}
				}
			}

			System.out.println(maxTip);
		}
		sc.close();
	}
}
