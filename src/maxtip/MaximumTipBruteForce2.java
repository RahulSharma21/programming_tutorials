package maxtip;

import java.util.Scanner;

public class MaximumTipBruteForce2 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();

		int x = sc.nextInt();

		int y = sc.nextInt();

		int a[] = new int[n];

		int b[] = new int[n];

		for (int i = 0; i < n; i++) {
			a[i] = sc.nextInt();
		}

		for (int i = 0; i < n; i++) {
			b[i] = sc.nextInt();
		}

		int maxtip = 0;

		outer: for (int i = 0; i < 1 << n; i++) {

			int tip = 0;

			int mask = 1;

			int count_a = 0;

			int count_b = 0;

			mask = 1;
			
			for (int j = 0; j < n; j++) {

				mask = 1 << j;
				
				//mask = (j == 0?1:mask*2);

				if ((i & mask) == mask) {

					count_a++;

					if (count_a <= x) {
						tip += a[j];
					} else {
						continue outer;
					}

				} else {

					count_b++;

					if (count_b <= y) {
						tip += b[j];
					} else {
						continue outer;
					}
				}
			}

			maxtip = Math.max(tip, maxtip);
		}

		System.out.println(maxtip);
		
		sc.close();
	}
}
