import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

import static java.lang.Math.*;

/* Utility Class for representation of Point(x,y) */
class Point implements Comparable<Point> {
	public double x;
	public double y;

	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public int compareTo(Point other) {
		return (this.x > other.x) ? 1 : -1;
	}

	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + "]";
	}
}

/* Utility class for handling fractions using big integer */
class FractionBig implements Comparable<FractionBig> {
	public BigInteger num;
	public BigInteger den;

	@Override
	public boolean equals(Object other) {
		FractionBig f = (FractionBig) other;

		return ((f.num.longValue() == num.longValue()) && (f.den.longValue() == den
				.longValue()));
	}

	@Override
	public String toString() {
		return num.longValue() + "/" + den.longValue() + "="
				+ (num.longValue() * 1.0 / den.longValue());
	}

	FractionBig() {

	}

	FractionBig(long n, long d) {
		num = BigInteger.valueOf(n);
		den = BigInteger.valueOf(d);
	}

	public static FractionBig add(FractionBig a, FractionBig b) {
		FractionBig c = new FractionBig();
		c.den = a.den.multiply(b.den);
		c.num = a.den.multiply(b.num).add(b.den.multiply(a.num));
		BigInteger gcd = c.den.gcd(c.num);
		c.den = c.den.divide(gcd);
		c.num = c.num.divide(gcd);
		return c;
	}

	public static FractionBig substract(FractionBig a, FractionBig b) {
		FractionBig c = new FractionBig(0, 1);
		c.den = a.den.multiply(b.den);
		c.num = b.den.multiply(a.num).subtract(a.den.multiply(b.num));
		BigInteger gcd = c.den.gcd(c.num);
		c.den = c.den.divide(gcd);
		c.num = c.num.divide(gcd);
		return c;

	}

	public static FractionBig multiply(FractionBig a, FractionBig b) {
		FractionBig c = new FractionBig();
		c.den = a.den.multiply(b.den);
		c.num = a.num.multiply(b.num);
		BigInteger gcd = c.den.gcd(c.num);
		c.den = c.den.divide(gcd);
		c.num = c.num.divide(gcd);
		return c;
	}

	public static FractionBig multiply(FractionBig a, long mult) {
		FractionBig c = new FractionBig();
		c.den = a.den;
		c.num = a.num.multiply(BigInteger.valueOf(mult));
		BigInteger gcd = c.den.gcd(c.num);
		c.den = c.den.divide(gcd);
		c.num = c.num.divide(gcd);
		return c;
	}

	public static FractionBig inverse(FractionBig a) {
		FractionBig c = new FractionBig();
		c.num = a.den;
		c.den = a.num;
		return c;
	}

	public static FractionBig divide(FractionBig a, long div) {
		FractionBig c = new FractionBig();
		c.den = a.den.multiply(BigInteger.valueOf(div));
		c.num = a.num;
		BigInteger gcd = c.den.gcd(c.num);
		c.den = c.den.divide(gcd);
		c.num = c.num.divide(gcd);
		return c;
	}

	public static FractionBig divide(FractionBig a, FractionBig b) {
		FractionBig c = new FractionBig();
		c.den = a.den.multiply(b.num);
		c.num = a.num.multiply(b.den);
		BigInteger gcd = c.den.gcd(c.num);
		c.den = c.den.divide(gcd);
		c.num = c.num.divide(gcd);
		return c;
	}

	public static boolean isBetween(FractionBig a, FractionBig b, FractionBig c) {
		return (a.compareTo(b) >= 0) && (a.compareTo(c) <= 0);
	}

	public int compareTo(FractionBig c) {
		BigInteger b1 = num;
		BigInteger b2 = den;
		b1 = b1.multiply(c.den);
		b2 = b2.multiply(c.num);
		return b1.compareTo(b2);
	}
}

/* Utility class for handling fractions */
class Fraction implements Comparable<Fraction> {
	public long num;
	public long den;

	@Override
	public boolean equals(Object other) {
		Fraction f = (Fraction) other;

		return (f.num == num) && (f.den == den);
	}

	@Override
	public String toString() {
		return num + "/" + den;
	}

	Fraction() {

	}

	Fraction(long n, long d) {
		num = n;
		den = d;
	}

	public static Fraction add(Fraction a, Fraction b) {
		Fraction c = new Fraction(0, 1);

		c.den = Library.lcm(a.den, b.den);
		c.num = (c.den / a.den) * a.num + (c.den / b.den) * b.num;
		long gcd = Library.gcd(c.den, c.num);
		c.den /= gcd;
		c.num /= gcd;
		return c;
	}

	public static Fraction substract(Fraction a, Fraction b) {
		Fraction c = new Fraction();
		c.den = Library.lcm(a.den, b.den);
		c.num = (c.den / a.den) * a.num - (c.den / b.den) * b.num;
		long gcd = Library.gcd(c.den, c.num);
		c.den /= gcd;
		c.num /= gcd;
		return c;
	}

	public static Fraction multiply(Fraction a, Fraction b) {
		Fraction c = new Fraction();
		c.den = a.den * b.den;
		c.num = a.num * b.num;
		long gcd = Library.gcd(c.den, c.num);
		c.den /= gcd;
		c.num /= gcd;
		return c;
	}

	public static Fraction multiply(Fraction a, long l) {
		Fraction c = new Fraction();
		c.den = a.den;
		c.num = a.num * l;
		long gcd = Library.gcd(c.den, c.num);
		c.den /= gcd;
		c.num /= gcd;
		return c;
	}

	public static Fraction inverse(Fraction a) {
		Fraction c = new Fraction();
		c.num = a.den;
		c.den = a.num;
		return c;
	}

	public static Fraction divide(Fraction a, long l) {
		Fraction c = new Fraction();
		c.den = a.den * l;
		c.num = a.num;
		long gcd = Library.gcd(c.den, c.num);
		c.den /= gcd;
		c.num /= gcd;
		return c;
	}

	public static Fraction divide(Fraction a, Fraction b) {
		Fraction c = new Fraction();
		c.den = a.den * b.num;
		c.num = a.num * b.den;
		long gcd = Library.gcd(c.den, c.num);
		c.den /= gcd;
		c.num /= gcd;
		return c;
	}

	public static boolean isBetween(Fraction a, Fraction b, Fraction c) {
		return (a.compareTo(b) >= 0) && (a.compareTo(c) <= 0);
	}

	public int compareTo(Fraction c) {
		if (c.num * den > c.den * num) {
			return -1;
		}

		if (c.num * den < c.den * num) {
			return 1;
		}

		return 0;
	}
}

/*
 * Library class to provide various utility methods
 */

public class Library {

	/*
	 * Computes all the distinct permutations from 0 upto given integer - 1. For
	 * exmple if k = 2 permutations would be 0,1 and 1,0 etc.
	 * 
	 * input = given number. output = integer array of permutations
	 */

	public static int[][] getPermutations(int k) {

		int[][] perms = new int[(int) factorial(k)][k];

		List<Integer> initialOptions = new ArrayList<Integer>();

		for (int i = 0; i < k; i++) {
			initialOptions.add(Integer.valueOf(i));
		}

		List<List<Integer>> permutations = computePermutations(initialOptions);

		int listidx = 0;
		for (List<Integer> list : permutations) {
			int idx = 0;
			for (Integer igr : list) {
				perms[listidx][idx++] = igr.intValue();
			}
			listidx++;
		}

		return perms;
	}

	/*
	 * Computes all the distinct permutations from 0 upto given integer - 1. For
	 * exmple if k = 2 permutations would be 0,1 and 1,0 etc.
	 * 
	 * input = list of numbers (0...k-1) output = list of list of integers of
	 * permutations
	 */

	public static List<List<Integer>> computePermutations(List<Integer> options) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();

		if (options.size() == 1) {
			result.add(new ArrayList<Integer>(options));
			return result;
		}

		for (Integer i : options) {
			List<Integer> newOptions = new ArrayList<Integer>(options);
			newOptions.remove(i);
			List<List<Integer>> results = computePermutations(newOptions);
			for (List<Integer> r : results) {
				r.add(0, i);
				result.add(r);
			}
		}
		return result;
	}

	/*
	 * Computes the factorial of given number
	 * 
	 * input = given number output = factorial of given number
	 */
	public static long factorial(long m) {
		long result = 1;
		while (m != 1) {
			result = result * m--;
		}
		return result;
	}

	/* calculates factorial(n) mod m */
	public static long factMod(long n, long m) {
		long result = 1;

		if (n <= 1)
			return 1;

		while (n != 1) {
			result = ((result * n--) % m);
		}
		return result;
	}

	/* Factorial using big integer for big numbers */
	public static BigInteger factorialBig(long m) {
		BigInteger result = BigInteger.valueOf(1);
		while (m >= 1) {
			result = result.multiply(BigInteger.valueOf(m));
			m--;
		}
		return result;
	}

	/*
	 * Computes the combinations of q entities where each entity has p distinct
	 * values. For example if p = 2 (values 0,1) and q = 3, output would be
	 * (000,001, 010,011,100,101,110,111)
	 * 
	 * input = numbers p and q output = integer array of combinations
	 */
	public static int[][] getCombinations(int p, int q) {

		int pow = (int) Math.pow(p, q);
		int[][] res = new int[pow][q];

		for (int i = 0; i < pow; i++) {
			int m = i;
			int k = q;
			while (m != 0) {
				res[i][--k] = m % p;
				m = m / p;
			}
		}
		return res;
	}

	/*
	 * Computes the all pair shortest paths. Implementations of Warshall's
	 * algorithm.
	 * 
	 * input = connectivity matrix (must be a square matrix) output = all pair
	 * shortes path matrix
	 */
	public static int[][] getAllPairShortetPaths(int[][] connectivity) {
		int len = connectivity.length;

		int[][] dist = connectivity;

		for (int j = 0; j < len; j++) {
			for (int k = 0; k < len; k++) {
				for (int l = 0; l < len; l++) {
					if (dist[k][j] + dist[j][l] < dist[k][l]) {
						dist[k][l] = dist[k][j] + dist[j][l];
					}
				}
			}
		}

		return dist;
	}

	/*
	 * Implementation of Hungarian algorithm to get minimum cost for an
	 * assignment problem.
	 * 
	 * input = cost matrix, size of matrix (matrix is assumed to be a square
	 * matrix)
	 * 
	 * output = minimum cost of assignment
	 */
	public static int getMinCostHungarian(int[][] table, int N) {
		int min;
		ArrayList[] matchGraph = new ArrayList[80];
		int rowSel[] = new int[80];
		int colSel[] = new int[80];
		int rowMatch[] = new int[80];
		int colMatch[] = new int[80];
		int check[] = new int[80];
		int[][] graph = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				graph[i][j] = table[i][j];
			}
		}

		table.clone();

		for (int i = 0; i < 80; i++) {
			matchGraph[i] = new ArrayList<Integer>();
		}
		for (int i = 0; i < N; i++) {
			min = table[i][0];
			for (int j = 1; j < N; j++)
				if (min > table[i][j])
					min = table[i][j];

			for (int j = 0; j < N; j++)
				table[i][j] -= min;
		}
		for (int i = 0; i < N; i++) {
			min = table[0][i];
			for (int j = 1; j < N; j++)
				if (min > table[j][i])
					min = table[j][i];

			for (int j = 0; j < N; j++)
				table[j][i] -= min;
		}

		while (true) {
			for (int i = 0; i < N; i++) {
				matchGraph[i].clear();
				for (int j = 0; j < N; j++) {
					if (table[i][j] == 0)
						matchGraph[i].add(j);
				}
			}
			if (maxMatch(N, matchGraph, rowMatch, colMatch, check) == N)
				break;

			selectLine(N, rowMatch, colMatch, rowSel, colSel, table);

			min = -1;
			for (int i = 0; i < N; i++) {
				if (rowSel[i] != 0)
					continue;
				for (int j = 0; j < N; j++) {
					if (colSel[j] != 0)
						continue;
					if (min > table[i][j] || min == -1)
						min = table[i][j];
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (rowSel[i] != 0 && colSel[j] != 0)
						table[i][j] += min;
					if (rowSel[i] == 0 && colSel[j] == 0)
						table[i][j] -= min;
				}
			}
		}

		int result = 0;
		for (int i = 0; i < N; i++) {
			result += graph[i][rowMatch[i]];
		}
		return result;
	}

	/*
	 * Supporting method for getMinCostHungarian method
	 */
	private static int extendMatch(int a, ArrayList[] matchGraph,
			int[] rowMatch, int[] colMatch, int[] check) {
		for (int i = 0; i < matchGraph[a].size(); i++) {
			int b = ((Integer) matchGraph[a].get(i)).intValue();
			if (check[b] != 0)
				continue;

			check[b] = 1;
			if (colMatch[b] == -1
					|| (extendMatch(colMatch[b], matchGraph, rowMatch,
							colMatch, check) != 0)) {
				rowMatch[a] = b;
				colMatch[b] = a;
				return 1;
			}
		}

		return 0;
	}

	/*
	 * Supporting method for getMinCostHungarian method
	 */
	private static int maxMatch(int len, ArrayList[] matchGraph,
			int[] rowMatch, int[] colMatch, int[] check) {
		Arrays.fill(rowMatch, 0, len, -1);
		Arrays.fill(colMatch, 0, len, -1);
		int cnt = 0;
		for (int i = 0; i < len; i++) {
			Arrays.fill(check, 0, len, 0);
			if (extendMatch(i, matchGraph, rowMatch, colMatch, check) != 0)
				cnt++;
		}

		return cnt;
	}

	/*
	 * Supporting method for getMinCostHungarian method
	 */
	private static void selectLine(int len, int[] rowMatch, int[] colMatch,
			int[] rowSel, int[] colSel, int[][] costMatrix) {
		ArrayList<Integer> rowQueue = new ArrayList<Integer>();
		ArrayList<Integer> colQueue = new ArrayList<Integer>();

		for (int i = 0; i < len; i++) {
			colSel[i] = 0;
			rowSel[i] = 0;
			if (rowMatch[i] != -1)
				rowSel[i] = 1;
			else
				rowQueue.add(i);
		}

		while (!rowQueue.isEmpty()) {
			colQueue.clear();
			for (int i = 0; i < rowQueue.size(); i++) {
				int a = rowQueue.get(i);
				for (int j = 0; j < len; j++) {
					if (colSel[j] != 0)
						continue;

					if ((costMatrix[a][j] == 0) && rowMatch[a] != j) {
						colSel[j] = 1;
						colQueue.add(j);
					}
				}
			}

			rowQueue.clear();
			for (int i = 0; i < colQueue.size(); i++) {
				int a = colQueue.get(i);
				if (rowSel[colMatch[a]] == 0)
					continue;

				rowSel[colMatch[a]] = 0;
				rowQueue.add(colMatch[a]);
			}
		}
	}

	/*
	 * Evaluates the string as an expression having + and - operators only
	 * (without braces)
	 * 
	 * input = the expression string output = expression value
	 */
	public static long evaluate(String s) {
		int idx1 = s.lastIndexOf('+');
		int idx2 = s.lastIndexOf('-');

		if (idx1 > 0) {
			return evaluate(s.substring(0, idx1))
					+ evaluate(s.substring(idx1 + 1));
		} else if (idx2 > 0) {
			return evaluate(s.substring(0, idx2))
					- evaluate(s.substring(idx2 + 1));
		} else {
			return Long.parseLong(s);
		}
	}

	/*
	 * Computes the area of triangle on basis of coordinates
	 * 
	 * input = x and y coordinats of 3 points output = area of triangle
	 */
	public static double triangleArea(int x1, int x2, int x3, int y1, int y2,
			int y3) {
		double area = 0.0;
		area = 0.5 * (x1 * y2 + x2 * y3 + x3 * y1 - y1 * x2 - y2 * x3 - y3 * x1);
		return area;
	}

	/*
	 * Computes all the distinct permutations from 0 upto given integer - 1. For
	 * exmple if k = 2 permutations would be 0,1 and 1,0 etc. This method uses
	 * nextPermutation method.
	 * 
	 * input = given number k output = list of list of integers of permutations
	 */
	public static int[][] getPermutations2(int k) {
		int[][] perms = new int[(int) factorial(k)][k];
		int[] xs = new int[k];
		for (int i = 0; i < k; i++)
			xs[i] = i;

		int m = 0;

		do {
			System.arraycopy(xs, 0, perms[m++], 0, k);
		} while (nextPermutation(xs, 0, k));

		return perms;
	}

	/* Supporting method for getPermutations2 method */
	public static boolean nextPermutation(int[] a, int b, int e) {
		if ((e - b) < 2)
			return false;
		int p = e;
		e--;
		while (b != e) {
			int q = e;
			e--;
			if (a[e] < a[q]) {
				int r = p;
				while (!(a[e] < a[--r]))
					;
				swap(a, e, r);
				reverse(a, q, p);
				return true;
			}
		}
		reverse(a, b, p);
		return false;
	}

	private static void swap(int[] a, int i, int j) {
		int tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}

	private static void reverse(int[] a, int b, int e) {
		while (b < e) {
			e--;
			swap(a, b, e);
			b++;
		}
	}

	/*
	 * checks if a number n is prime input - n output - true if number is prime
	 */
	public static boolean isPrime(long n) {
		boolean result = true;

		long sqrt = (long) Math.sqrt(n);
		if (n == 0 || n == 1) {
			result = false;
		}

		else if (n > 2) {
			for (long i = 2; i <= sqrt; i++) {
				if (n % i == 0) {
					result = false;
					break;
				}
			}
		}
		return result;
	}

	/*
	 * computes gcd of two number a and b input - a and b output - gcd of a and
	 * b
	 */
	public static long gcd(long a, long b) {
		if (b == 0) {
			return a;
		} else {
			return gcd(b, a % b);
		}
	}

	/*
	 * computes gcd of two number a and b input - a and b output - gcd of a and
	 * b. Non recursive
	 */
	public static long gcd2(long a, long b) {
		long t = 0;
		while (b != 0) {
			t = b;
			b = a % b;
			a = t;
		}
		return a;
	}

	/*
	 * computes lcm of two number a and b input - a and b output - lcm of a and
	 * b beaware of number overflow
	 */
	public static long lcm(long a, long b) {
		if (a == 0)
			return 0;

		if (a == 1) {
			return b;
		}

		if (b == 1) {
			return a;
		}

		return ((a * b) / gcd2(a, b));
	}

	/*
	 * computes range of primes b/w a and b input - a and b output - array of
	 * primes b/w a and b inclusive both
	 */

	// old method not optimized takes very long time
	public static long[] primesInRange(long a, long b) {
		List<Long> list = new ArrayList<Long>();
		for (long i = a; i <= b; i++) {
			if (isPrime(i)) {
				list.add(i);
			}
		}

		long[] res = new long[list.size()];

		for (int i = 0; i < res.length; i++) {
			res[i] = list.get(i);
		}

		return res;
	}

	/*
	 * New optimized method to find primes <= b works for int, using "sieve of
	 * eratosthenes
	 */
	public static int[] primesInRange(int b) {
		BitSet bits = new BitSet(b);

		int count = 0;

		int i;

		for (i = 2; i <= b; i++) {
			bits.set(i);
		}

		i = 2;
		while (i * i <= b) {
			if (bits.get(i)) {
				count++;
				int k = 2 * i;
				while (k <= b) {
					bits.clear(k);
					k += i;
				}
			}
			i++;
		}

		while (i <= b) {
			if (bits.get(i)) {
				count++;
			}
			i++;
		}

		int[] res = new int[count];

		int idx = 0;
		for (i = 0; i <= b; i++) {
			if (bits.get(i)) {
				res[idx++] = i;
			}
		}

		return res;
	}

	/*
	 * Method to find next increasing permutation of string for ex 115 would
	 * return 151. If no permutation found null is returned
	 */

	public static String nextPermutation(String str) {
		int len = str.length();

		int[] elems = new int[len];
		char[] ch = str.toCharArray();

		for (int i = 0; i < len; i++) {
			elems[len - 1 - i] = Integer.parseInt("" + ch[i]);
		}

		boolean found = false;
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < len; i++) {
			int min = 1000;
			int idx = -1;

			for (int j = 0; j < i; j++) {
				if ((elems[j] < min) && (elems[j] > elems[i])) {
					min = elems[j];
					idx = j;
				}
			}
			if ((idx >= 0) && (elems[idx] > elems[i])) {

				for (int j = len - 1; j > i; j--) {
					sb.append(elems[j]);
				}
				sb.append(elems[idx]);

				int[] em = new int[i];
				int count = i - 1;
				for (int j = i; j >= 0; j--) {
					if (j != idx) {
						em[count] = -1 * elems[j];
						count--;
					}
				}
				Arrays.sort(em);
				for (int j = i - 1; j >= 0; j--) {
					sb.append(em[j] * -1);
				}
				found = true;
				break;
			}
		}

		if (!found) {
			return null;
		}
		return sb.toString();
	}

	/* This method takes a, b and c as inputs and returns (a ^ b) mod c */

	public static long powerMod(long a, long b, long c) {
		long result = 1;
		long temp = 1;
		long mask = 1;

		for (int i = 0; i < 64; i++) {
			mask = (i == 0) ? 1 : (mask * 2);

			temp = (i == 0) ? (a % c) : (temp * temp) % c;

			/* Check if (i + 1)th bit of power b is set */

			if ((b & mask) == mask) {
				result = (result * temp) % c;
			}
		}
		return result;
	}

	public static long power(long a, long b) {
		long result = 1;
		long temp = 1;
		long mask = 1;

		for (int i = 0; i < 32; i++) {
			mask = (i == 0) ? 1 : (mask * 2);
			temp = (i == 0) ? a : (temp * temp);
			/* Check if (i + 1)th bit of power b is set */
			if ((b & mask) == mask) {
				result = (result * temp);
			}
		}
		return result;
	}

	/* returns a/b mod m, works only if m is prime and b divides a */
	public static long divMod(long a, long b, long m) {
		long c = powerMod(b, m - 2, m);
		return ((a % m) * (c % m)) % m;
	}

	/* returns nCr mod m */
	public static long comb(long n, long r, long m) {
		long p1 = 1, p2 = 1;

		for (long i = r + 1; i <= n; i++) {
			p1 = (p1 * i) % m;
		}
		p2 = factMod(n - r, m);
		p1 = divMod(p1, p2, m);

		return p1 % m;
	}

	/* Gets all the possible subsets of a set */
	public static int[][] getAllSubsets(int[] elems) {
		int len = elems.length;
		int[][] subsets = new int[(1 << len) - 1][];
		int[] mask = new int[len];
		int count = 0;

		int[] arr;
		while ((arr = next(mask, elems)) != null) {
			subsets[count++] = arr;
		}
		return subsets;
	}

	/* Generates the next mask */
	private static int[] next(int[] mask, int[] elems) {
		int i;
		int n = mask.length;

		for (i = 0; (i < n) && (mask[i] != 0); ++i) {
			mask[i] = 0;
		}

		if (i >= n) {
			return null;
		}

		mask[i] = 1;

		int count = 0;
		for (i = 0; i < n; ++i) {
			if (mask[i] != 0) {
				count++;
			}
		}

		int[] ret = new int[count];

		int cnt = 0;

		for (i = 0; i < n; ++i) {
			if (mask[i] != 0) {
				ret[cnt++] = elems[i];
			}
		}
		return ret;
	}

	/* Another variation of get all subsets */
	public static int[][] getAllSubsets2(int[] elems) {
		int len = elems.length;
		int[][] subsets = new int[(1 << len) - 1][];

		for (int i = 1; i < (1 << len); i++) {
			int count = 0;
			for (int mask = 1; mask <= i; mask *= 2) {
				if ((mask & i) == mask) {
					count++;
				}
			}

			subsets[i - 1] = new int[count];
			count = 0;
			for (int mask = 1, tmp = 0; mask <= i; mask *= 2, tmp++) {
				if ((mask & i) == mask) {
					subsets[i - 1][count++] = elems[tmp];
				}
			}
		}
		return subsets;
	}

	/*
	 * Dijkstra algorithm for single source all target. Not optimzed using Set
	 * instead of array
	 */
	public static int dijkstra_old(int[][] arr, int start, int end) {
		int n = arr.length;
		int[] dist = new int[n];
		int[] prev = new int[n];
		Arrays.fill(dist, 10000000);
		Arrays.fill(prev, -1);
		dist[start] = 0;

		List<Integer> Q = new ArrayList<Integer>();
		for (int i = 0; i < n; i++) {
			Q.add(i);
		}

		while (!Q.isEmpty()) {
			int u = findMin(dist, Q);
			if (dist[u] == 10000000) {
				break;
			}
			for (Integer it : Q) {
				if (it == u) {
					Q.remove(it);
					break;
				}
			}

			for (int v = 0; v < n; v++) {
				if ((u != v) && (Q.contains(v))) {
					int alt = dist[u] + arr[u][v];
					if (alt < dist[v]) {
						dist[v] = alt;
						prev[v] = u;
					}
				}
			}
		}

		// print the shortest path
		/*
		 * int k = end; System.out.print(end);
		 * 
		 * while(prev[k] != -1){ System.out.print(" " + prev[k] + " "); k =
		 * prev[k]; } System.out.println();
		 */

		return dist[end];
	}

	/* Dijkstra helper */
	private static int findMin(int[] dist, List<Integer> q) {
		int min = dist[q.get(0)];
		int ret = q.get(0);

		for (int i = 1; i < q.size(); i++) {
			if (dist[q.get(i)] <= min) {
				min = dist[q.get(i)];
				ret = q.get(i);
			}
		}
		return ret;
	}

	/* Dijkstra algorithm (improved) for single source all target */
	public static int dijkstra(int[][] nodes, int start, int end) {
		int n = nodes.length;
		int[] dist = new int[n];
		int[] prev = new int[n];
		boolean[] visited = new boolean[n];

		Arrays.fill(dist, Integer.MAX_VALUE);
		Arrays.fill(prev, -1);
		dist[start] = 0;

		while (!allvisited(visited)) {
			int u = findMin(dist, visited);
			if (dist[u] == Integer.MAX_VALUE) {
				break;
			}
			visited[u] = true;
			if (u == end) {
				break;
			}
			for (int v = 0; v < n; v++) {
				if ((u != v) && (!visited[v])
						&& (nodes[u][v] != Integer.MAX_VALUE)) {
					int alt = dist[u] + nodes[u][v];
					if (alt < dist[v]) {
						dist[v] = alt;
						prev[v] = u;
					}
				}
			}
		}

		// print the shortest path
		/*
		 * int k = end; System.out.print(end);
		 * 
		 * while(prev[k] != -1){ System.out.print(" " + prev[k] + " "); k =
		 * prev[k]; } System.out.println();
		 */

		return dist[end];
	}

	/*
	 * Another version of Dijkstra that returns multiple shortes paths between
	 * two nodes
	 */
	public static int dijkstraMult(int[][] nodes, int start, int end,
			ArrayList[] prev) {
		int n = nodes.length;
		int[] dist = new int[n];

		boolean[] visited = new boolean[n];

		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[start] = 0;

		while (!allvisited(visited)) {
			int u = findMin(dist, visited);
			if (dist[u] == Integer.MAX_VALUE) {
				break;
			}
			visited[u] = true;
			if (u == end) {
				break;
			}
			for (int v = 0; v < n; v++) {
				if ((u != v) && (!visited[v])
						&& (nodes[u][v] != Integer.MAX_VALUE)) {
					int alt = dist[u] + nodes[u][v];
					if (alt <= dist[v]) {
						dist[v] = alt;
						if (prev[v] == null)
							prev[v] = new ArrayList<Integer>();
						prev[v].add(u);
					}
				}
			}
		}

		return dist[end];
	}

	/* Utility method for above Dijkstra (multiple to return int[][] instead of 
	 * ArrayList[]
	 */
	private static void populate(int[][] paths, ArrayList[] prev, int i) {
		if (prev[i] == null)
			return;

		ArrayList<Integer> list = (ArrayList<Integer>) prev[i];

		for (int val : list) {
			paths[i][val] = paths[val][i] = 1;
			populate(paths, prev, val);
		}
	}

	private static boolean allvisited(boolean[] visited) {
		for (int i = 0; i < visited.length; i++) {
			if (!visited[i]) {
				return false;
			}
		}
		return true;
	}

	private static int findMin(int[] dist, boolean[] visited) {
		int min = Integer.MAX_VALUE;
		int ret = -1;

		for (int i = 0; i < dist.length; i++) {
			if ((dist[i] <= min) && !visited[i]) {
				min = dist[i];
				ret = i;
			}
		}
		return ret;
	}

	/*
	 * Find all dearrangeemnt for r objects such that no object is assigned to
	 * it's correct place.
	 */
	public static BigInteger dearrange(long r) {
		if (r <= 1) {
			return BigInteger.ONE;
		}
		BigInteger fact = factorialBig(r);
		BigInteger ret = BigInteger.ZERO;

		for (int i = 0; i <= r + 1; i++) {
			if (i % 2 == 0)
				ret = ret.add(fact.divide(factorialBig(i)));
			else
				ret = ret.subtract(fact.divide(factorialBig(i)));
		}
		return ret;
	}

	/* To find Matrix Inverse */
	public static double[][] inverse(double[][] mat) {
		int len = mat.length;
		double[][] ret = new double[len][len];
		double det = determinent(mat);

		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len; j++) {
				ret[j][i] = (((i + j) % 2 == 0) ? 1 : -1)
						* determinent(cofactor(mat, i, j)) / det;
			}
		}
		return ret;
	}

	/* To find Matrix Determinant */
	public static double determinent(double[][] mat) {
		int len = mat.length;
		if (len == 2) {
			return mat[0][0] * mat[1][1] - mat[0][1] * mat[1][0];
		}

		double ret = 0.0;

		for (int i = 0; i < len; i++) {
			ret += (((i % 2 == 0) ? 1 : -1) * mat[0][i] * determinent(cofactor(
					mat, 0, i)));
		}

		return ret;
	}

	/* To find Cofactor */
	public static double[][] cofactor(double[][] mat, int k, int l) {
		int len = mat.length;
		double[][] ret = new double[len - 1][len - 1];
		int y = 0, x = 0;

		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len; j++) {
				if (!((i == k) || (j == l))) {
					if (y == (len - 1)) {
						y = 0;
						x++;
					}
					ret[x][y++] = mat[i][j];
				}
			}
		}
		return ret;
	}

	/* Gauss Jordan Elimination for solving simulatneous equations */
	/* Takes The Augemented matrix A|B as input */
	public static void gaussJordan(double[][] mat) {

		int i = 0, j = 0, k = 0;
		int ri = mat.length - 1;
		int ci = mat[0].length - 1;

		while (i <= ri && j <= ci) {
			k = i;
			while (k <= ri && mat[k][j] == 0)
				k++;
			if (k <= ri) {
				if (k != i) {
					swap(mat, i, k, j);
				}
				if (mat[i][j] != 1) {
					divide(mat, i, j);
				}
				eliminate(mat, i, j);
				i++;
			}
			j++;
		}
	}

	// helper for gaussJordan
	private static void swap(double[][] mat, int i, int k, int j) {
		int m = mat[0].length - 1;
		double temp;
		for (int q = j; q <= m; q++) {
			temp = mat[i][q];
			mat[i][q] = mat[k][q];
			mat[k][q] = temp;
		}
	}

	// helper for gaussJordan
	private static void divide(double[][] mat, int i, int j) {
		int m = mat[0].length - 1;
		for (int q = j + 1; q <= m; q++)
			mat[i][q] /= mat[i][j];
		mat[i][j] = 1;
	}

	// helper for gaussJordan
	private static void eliminate(double[][] mat, int i, int j) {
		int n = mat.length - 1;
		int m = mat[0].length - 1;
		for (int p = 0; p <= n; p++) {
			if (p != i && mat[p][j] != 0) {
				for (int q = j + 1; q <= m; q++) {
					mat[p][q] -= mat[p][j] * mat[i][q];
				}
				mat[p][j] = 0;
			}
		}
	}

	// gaussJordan method, mod 2
	private static int[][] gaussJordanMod2(int[][] mat) {
		int i = 0, j = 0, k = 0;
		int ri = mat.length - 1;
		int ci = mat[0].length - 1;

		while (i <= ri && j <= ci) {
			k = i;
			while (k <= ri && mat[k][j] == 0)
				k++;
			if (k <= ri) {
				if (k != i) {
					swap(mat, i, k, j);
				}
				eliminate(mat, i, j);
				i++;
			}
			j++;
		}
		return mat;
	}

	// helper for gaussJordan2
	private static void swap(int[][] mat, int i, int k, int j) {
		int m = mat[0].length - 1;
		int temp;
		for (int q = j; q <= m; q++) {
			temp = mat[i][q];
			mat[i][q] = mat[k][q];
			mat[k][q] = temp;
		}
	}

	// helper for gaussJordan2
	private static void eliminate(int[][] mat, int i, int j) {
		int n = mat.length - 1;
		int m = mat[0].length - 1;
		for (int p = 0; p <= n; p++) {
			if (p != i && mat[p][j] != 0) {
				for (int q = j + 1; q <= m; q++) {
					mat[p][q] += mat[p][j] * mat[i][q];
					mat[p][q] %= 2;
				}
				mat[p][j] = 0;
			}
		}
	}

	// FFT preparation method for initialization
	public static void FFTmakeg(int n, double[][] g) {
		double th;
		for (int i = 0; i < n; ++i) {
			th = PI * 2.0 * i / n;
			g[i][0] = cos(th);
			g[i][1] = sin(th);
		}
	}

	// swap two complex numbers
	public static void swap(double[] z1, double[] z2) {
		double[] temp = new double[2];
		temp[0] = z1[0];
		temp[1] = z1[1];
		z1[0] = z2[0];
		z1[1] = z2[1];
		z2[0] = temp[0];
		z2[1] = temp[1];
	}

	// add two complex numbers
	public static double[] add(double[] x, double[] y) {
		double[] z = new double[2];
		z[0] = x[0] + y[0];
		z[1] = x[1] + y[1];
		return z;
	}

	// multiply two complex numbers
	public static double[] mult(double[] x, double[] y) {
		double[] z = new double[2];
		z[0] = x[0] * y[0] - x[1] * y[1];
		z[1] = x[1] * y[0] + x[0] * y[1];
		return z;
	}

	// The FFT algorithm, also does inverse FFT
	public static void FFT(int n, double z[][], double g[][], boolean inv) {
		int i, j, k, l, m;
		double x[] = new double[2];
		double y[] = new double[2];

		for (i = 1, j = 0; i < n; ++i) {
			for (k = (n >> 1); (j ^= k) < k; k >>= 1)
				;
			if (i < j)
				swap(z[i], z[j]);
		}

		for (l = k = 1, m = Integer.numberOfTrailingZeros(n); m-- > 0; l |= (k <<= 1)) {

			for (i = 0; i < n; ++i)
				if (i < (j = (i ^ k))) {
					x = add(z[i], mult(z[j], g[(i & l) << m]));
					y = add(z[i], mult(z[j], g[(j & l) << m]));
					z[i] = x;
					z[j] = y;
				}
		}
		if (inv) {
			for (i = 0; i < n; ++i) {
				z[i][0] = z[i][0] * (1.0 / n);
				z[i][1] = z[i][1] * (1.0 / n);
			}
		}
	}

	// FFT helper method to reverse array
	private static double[][] reverse(double[][] gs, int nn) {
		int len = gs.length;
		double[][] ret = new double[len][2];
		ret[0] = gs[0];
		for (int i = 1; i < nn; i++) {
			ret[nn - i][0] = gs[i][0];
			ret[nn - i][1] = gs[i][1];
		}
		return ret;
	}

	// To find primitive root
	public static boolean isPrimitiveRoot(long g, long p) {
		long gg = 1;
		for (long i = 0; i < p - 2; ++i) {
			gg = gg * g % p;
			if (gg == 1)
				return false;
		}
		return true;
	}

	/*
	 * Method to compute polygon area with given set of points in cycylic order
	 */
	public static double getPolygonArea(Point[] p) {
		double area = 0.0;
		for (int i = 0; i < p.length - 1; i++) {
			area += p[i].x * p[i + 1].y;
			area -= p[i].y * p[i + 1].x;
		}
		area = area / 2.0;
		return area;
	}

	/* Get distance if you are allowed to move one square in 
	   any direction horizontal, vertical or diagonal
	*/

	private static int getDist(int x1, int y1, int x2, int y2) {

		int signx = (int) Math.signum(x1 - x2);

		int signy = (int) Math.signum(y1 - y2);

		int d = Math.min(Math.abs(x1 - x2), Math.abs(y1 - y2));

		x1 -= signx * d;

		y1 -= signy * d;

		d += Math.abs(x1 - x2) + Math.abs(y1 - y2);

		return d;
	}

	/* A much simpler variant of above method :)
		Get distance if you are allowed to move one square in 
	   any direction horizontal, vertical or diagonal
	*/

	private static int getDist2(int x1, int y1, int x2, int y2) {

		return Math.max(Math.abs(x1 - x2), Math.abs(y1 - y2));

	}

//Sorts an integer array in ascending order.
 //Parameters:
 //   data - reference to the integer array to sort, must not be null
 //Postcondition:
 //   The array is sorted in ascending order.
 
 public static void bubbleSort(int[] data)
 {
    for (int k = 0; k < data.length - 1; k++)
    {
       boolean isSorted = true;
 
       for (int i = 1; i < data.length - k; i++)
       {
          if (data[i] < data[i - 1])
          {
             int tempVariable = data[i];
             data[i] = data[i - 1];
             data[i - 1] = tempVariable;
 
             isSorted = false;
 
          }
       }
 
       if (isSorted)
          break;
    }
 }

   /**
     * Checks if the parentheses contained within the string are matched correctly.
     */
    public static boolean matches( final String input )
    {
        final Stack<Character> stack = new Stack<Character>( );
        for( int i = 0; i < input.length( ); i++ )
        {
            final char inputChar = input.charAt( i );
 
            if( ( inputChar == '{' ) || ( inputChar == '[' )
                    || ( inputChar == '(' ) )
            {
                stack.push( inputChar );
            }
            else if( ( inputChar == ')' ) || ( inputChar == ']' )
                    || ( inputChar == '}' ) )
            {
                if( stack.isEmpty( ) ) {
                    return false;
                }
 
                final char lastChar = stack.pop( );
 
                if( ( ( inputChar == ')' ) && ( lastChar != '(' ) )
                        || ( ( inputChar == ']' ) && ( lastChar != '[' ) )
                        || ( ( inputChar == '}' ) && ( lastChar != '{' ) ) )
                {
                    return false;
                }
            }
        }
 
        return stack.isEmpty( );
    }
	

	// Min Heap 
	class MinHeap {

	/** Fixed-size array heap representation */
	private long[] array;
	/** Number of nodes in the heap (array) */
	private int n = 0;

	/** Constructs a heap of specified size */
	public MinHeap(final int size) {
		array = new long[size];
	}

	/** Returns (without removing) the smallest (min) element from the heap. */
	public long min() {
		if (isEmpty()) {
			throw new RuntimeException("Heap is empty!");
		}

		return array[0];
	}

	/** Removes and returns the smallest (min) element from the heap. */
	public long removeMin() {
		if (isEmpty()) {
			throw new RuntimeException("Heap is empty!");
		}

		final long min = array[0];
		array[0] = array[n - 1];
		if (--n > 0)
			siftDown(0);
		return min;
	}

	/** Checks if the heap is empty. */
	public boolean isEmpty() {
		return n == 0;
	}

	/** Adds a new element to the heap and sifts up/down accordingly. */
	public void add(final long value) {
		if (n == array.length) {
			throw new RuntimeException("Heap is full!");
		}

		array[n] = value;
		siftUp(n);
		n++;
	}

	/**
	 * Sift up to make sure the heap property is not broken. This method is used
	 * when a new element is added to the heap and we need to make sure that it
	 * is at the right spot.
	 */
	private void siftUp(final int index) {
		if (index > 0) {
			final int parent = (index - 1) / 2;
			if (array[parent] > array[index]) {
				swap(parent, index);
				siftUp(parent);
			}
		}
	}

	/**
	 * Sift down to make sure that the heap property is not broken This method
	 * is used when removing the min element, and we need to make sure that the
	 * replacing element is at the right spot.
	 */
	private void siftDown(int index) {

		final int leftChild = 2 * index + 1;
		final int rightChild = 2 * index + 2;

		// Check if the children are outside the array bounds.
		if (rightChild >= n && leftChild >= n)
			return;

		// Determine the smallest child out of the left and right children.
		final int smallestChild = array[rightChild] > array[leftChild] ? leftChild
				: rightChild;

		if (array[index] > array[smallestChild]) {
			swap(smallestChild, index);
			siftDown(smallestChild);
		}
	}

	/** Helper method for swapping array elements */
	private void swap(int a, int b) {
		long temp = array[a];
		array[a] = array[b];
		array[b] = temp;
	}

	/** Test Method */
	public void main(String[] args) {

		int[] input = { 6, 5, 3, 1, 8, 7, 2, 4 };
		MinHeap heap = new MinHeap(input.length);
		for (int i = 0; i < input.length; i++) {
			heap.add(input[i]);
		}

		while (!heap.isEmpty()) {
			System.out.print(heap.removeMin() + " ");
		}
	}
}

/* To get length of largest increasing sub sequence
*/
public static int getLengthLongestSubSequence(int[] seq) {
		int[] L = new int[seq.length];
		L[0] = 1;
		for (int i = 1; i < L.length; i++) {
			int maxn = 0;
			for (int j = 0; j < i; j++) {
				if (seq[j] < seq[i] && L[j] > maxn) {
					maxn = L[j];
				}
			}
			L[i] = maxn + 1;
		}
		int maxi = 0;
		for (int i = 0; i < L.length; i++) {
			if (L[i] > maxi) {
				maxi = L[i];
			}
		}
		return (maxi);
	}

/* To get indexes of all the largest increasing sub 
 * sequences of a sequence
 */
public static List<ArrayList<Integer>> getAllMaxSubsequences(
			int[] input) {

		int n = input.length;

		List<ArrayList<ArrayList<Integer>>> paths = new ArrayList<ArrayList<ArrayList<Integer>>>(
				n);

		int[] subSeqLength = new int[n];

		subSeqLength[0] = 1;

		paths.add(new ArrayList<ArrayList<Integer>>());
		paths.get(0).add(new ArrayList<Integer>());
		paths.get(0).get(0).add(0);

		for (int i = 1; i < subSeqLength.length; i++) {
			
			subSeqLength[i] = 1;
			
			paths.add(i, new ArrayList<ArrayList<Integer>>());
			
			boolean found = false;
			
			for (int j = 0; j < i; j++) {
				
				if (input[j] < input[i]) {
					
					subSeqLength[i] = Math.max(subSeqLength[i],
							subSeqLength[j] + 1);
					
					found = true;
				}
			}
			
			if(!found) {
				paths.get(i).add(new ArrayList<Integer>());
				paths.get(i).get(0).add(i);
				continue;
			}

			for (int j = 0; j < i; j++) {
				
				if ((subSeqLength[i] - subSeqLength[j] == 1)
						&& (input[j] < input[i])) {

					for (ArrayList<Integer> path : paths.get(j)) {
						
						ArrayList<Integer> tempPath = new ArrayList<Integer>(
								path);
						
						tempPath.add(i);
						
						paths.get(i).add(tempPath);
					}
				}
			}

		}
		int maxLength = 0;

		for (int i = 0; i < subSeqLength.length; i++) {
			
			if (subSeqLength[i] > maxLength) {
			
				maxLength = subSeqLength[i];
			
			}
		}

		List<ArrayList<Integer>> finalPaths = new ArrayList<ArrayList<Integer>>(
				n);

		for (int i = 0; i < subSeqLength.length; i++) {

			if (subSeqLength[i] == maxLength) {
			
				for (ArrayList<Integer> path : paths.get(i)) {

					finalPaths.add(path);
				}
			}
		}

		return finalPaths;
	}


/*
 * Generic method to get all subsequences, actual values :)
 */
public static <T extends Comparable<? super T>> List<ArrayList<T>> getAllMaxSubsequences(
			T[] input) {

		int n = input.length;

		List<ArrayList<ArrayList<Integer>>> paths = new ArrayList<ArrayList<ArrayList<Integer>>>(
				n);

		int[] subSeqLength = new int[n];

		subSeqLength[0] = 1;

		paths.add(new ArrayList<ArrayList<Integer>>());
		paths.get(0).add(new ArrayList<Integer>());
		paths.get(0).get(0).add(0);

		for (int i = 1; i < subSeqLength.length; i++) {

			subSeqLength[i] = 1;

			paths.add(i, new ArrayList<ArrayList<Integer>>());

			boolean found = false;

			for (int j = 0; j < i; j++) {

				if (input[j].compareTo(input[i]) < 0) {

					subSeqLength[i] = Math.max(subSeqLength[i],
							subSeqLength[j] + 1);

					found = true;
				}
			}

			if (!found) {
				paths.get(i).add(new ArrayList<Integer>());
				paths.get(i).get(0).add(i);
				continue;
			}

			for (int j = 0; j < i; j++) {

				if ((subSeqLength[i] - subSeqLength[j] == 1)
						&& (input[j].compareTo(input[i]) < 0)) {

					for (ArrayList<Integer> path : paths.get(j)) {

						ArrayList<Integer> tempPath = new ArrayList<Integer>(
								path);

						tempPath.add(i);

						paths.get(i).add(tempPath);
					}
				}
			}

		}
		int maxLength = 0;

		for (int i = 0; i < subSeqLength.length; i++) {

			if (subSeqLength[i] > maxLength) {

				maxLength = subSeqLength[i];

			}
		}

		List<ArrayList<Integer>> finalPaths = new ArrayList<ArrayList<Integer>>(
				n);

		for (int i = 0; i < subSeqLength.length; i++) {

			if (subSeqLength[i] == maxLength) {

				for (ArrayList<Integer> path : paths.get(i)) {

					finalPaths.add(path);
				}
			}
		}
		
		List<ArrayList<T>> output = new ArrayList<ArrayList<T>>();
		
		for(ArrayList<Integer> path : finalPaths){
			ArrayList<T> values = new ArrayList<T>();
			for(int pos : path) {
				values.add(input[pos]);
			}
			output.add(values);
		}
		
		return output;
	}


	/* Patient sort implemented in java
	 * http://en.wikipedia.org/wiki/Patience_sorting
	*/
	public static <E extends Comparable<? super E>> void sort (E[] n)
    {
        List<Pile<E>> piles = new ArrayList<Pile<E>>();
        // sort into piles
        for (E x : n)
        {
            Pile<E> newPile = new Pile<E>();
            newPile.push(x);
            int i = Collections.binarySearch(piles, newPile);
            if (i < 0) i = ~i;
            if (i != piles.size())
                piles.get(i).push(x);
            else
                piles.add(newPile);
        }
        System.out.println("longest increasing subsequence has length = " + piles.size());
 
        // priority queue allows us to retrieve least pile efficiently
        PriorityQueue<Pile<E>> heap = new PriorityQueue<Pile<E>>(piles);
        for (int c = 0; c < n.length; c++)
        {
            Pile<E> smallPile = heap.poll();
            n[c] = smallPile.pop();
            if (!smallPile.isEmpty())
                heap.offer(smallPile);
        }
        assert(heap.isEmpty());
    }
 
    private static class Pile<E extends Comparable<? super E>> extends Stack<E> implements Comparable<Pile<E>>
    {
        public int compareTo(Pile<E> y) { return peek().compareTo(y.peek()); }
    }

	/* for testing only */
	public static void main(String[] args) {

		int[][] arr = getAllSubsets2(new int[] { 1, 2, 3 });

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				System.out.print(" " + arr[i][j]);
			}
			System.out.println();
		}
		/*
		 * long[] res = primesInRange(1, 50); for (int i = 0; i < res.length;
		 * i++) { System.out.println(res[i]); }
		 */
		/*
		 * int count = 0; int [][] res = getPermutations2(10); for(int i = 0; i
		 * < res.length; i++){ for(int j = 0; j < res[i].length; j++){
		 * System.out.print(" " + res[i][j]); } count++; System.out.println(); }
		 * System.out.println("Count = " + count);
		 */
	}
}
