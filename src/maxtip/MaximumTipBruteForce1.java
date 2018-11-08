package maxtip;

public class MaximumTipBruteForce1 {

	public static void main(String[] args) {

		int n = 5;

		int x = 3;

		int y = 3;

		int a[] = { 1, 2, 3, 4, 5 };

		int b[] = { 5, 4, 3, 2, 1 };

		int maxtip = 0;

		int tip = 0;

		for (int x1 = 0; x1 < 2; x1++)
			for (int x2 = 0; x2 < 2; x2++)
				for (int x3 = 0; x3 < 2; x3++)
					for (int x4 = 0; x4 < 2; x4++)
						for (int x5 = 0; x5 < 2; x5++)
							for (int y1 = 0; y1 < 2; y1++)
								for (int y2 = 0; y2 < 2; y2++)
									for (int y3 = 0; y3 < 2; y3++)
										for (int y4 = 0; y4 < 2; y4++)
											for (int y5 = 0; y5 < 2; y5++) {

												tip = 0;
												
												if ((x1 + x2 + x3 + x4 + x5 <= x)
														&& (y1 + y2 + y3 + y4
																+ y5 <= y)
														&& (x1 + x2 + x3 + x4
																+ x5 + y1 + y2
																+ y3 + y4 + y5 <= n)) {

													tip += (x1 == 1 && y1 == 0 ? a[0] : 0);
													tip += (x2 == 1 && y2 == 0 ? a[1] : 0);
													tip += (x3 == 1 && y3 == 0 ? a[2] : 0);
													tip += (x4 == 1 && y4 == 0 ? a[3] : 0);
													tip += (x5 == 1 && y5 == 0 ? a[4] : 0);

													tip += (y1 == 1 && x1 == 0 ? b[0] : 0);
													tip += (y2 == 1 && x2 == 0 ? b[1] : 0);
													tip += (y3 == 1 && x3 == 0 ? b[2] : 0);
													tip += (y4 == 1 && x4 == 0 ? b[3] : 0);
													tip += (y5 == 1 && x1 == 0 ? b[4] : 0);

													maxtip = Math.max(tip,
															maxtip);
												}
											}
		System.out.println(maxtip);
	}
}
