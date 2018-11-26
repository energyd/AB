import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Median_Double {
  public static void main(String[] args) {
    List<Double> input = Arrays.asList(1.0, -30000000.0);
    List<Double> input1 = Arrays.asList(0.0, 1.0, 1.0, 1.0, 1.0);
    List<Double> input2 = Arrays.asList(0.0, 0.1, 1.0, 1.0);
    System.out.println(findMedian(input2));
  }

  public static double findMedian (List<Double> input) {
    int n = 0;
    Iterator<Double> it = input.iterator();
    double max = Double.MIN_VALUE;
    double min = Double.MAX_VALUE;
    while (it.hasNext()) {
      double num = it.next();
      n++;
      max = Math.max(max, num);
      min = Math.min(min, num);
    }

    if (n % 2 == 0) {
      return (kthNumber(input, min, max, n / 2) + kthNumber(input, min, max, n / 2 + 1)) / 2.0;
    } else {
      return kthNumber(input, min, max, n / 2 + 1);
    }

  }
  static double precision = 1e-6;
  public static double kthNumber(List<Double> list, double lb, double hb, int k) {
    if (lb - hb > precision || Math.abs(lb - hb) < precision) {
      return lb;
    }
    Iterator<Double> it = list.iterator();
    double guess = lb + (hb - lb) / 2;
    int count = 0;
    double biggestLessThanGuess = lb;
    while (it.hasNext()) {
      double num = it.next();
      if (num < guess) {
        count++;
        if (num > biggestLessThanGuess) {
          biggestLessThanGuess = num;
        }
      }
    }
    if (count == k) {
      return biggestLessThanGuess;
    }
    if (count < k) {
      return kthNumber(list, guess, hb, k);
    } else {
      return kthNumber(list, lb, biggestLessThanGuess, k);
    }
  }
}
