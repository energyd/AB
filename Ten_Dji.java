import java.util.*;

public class Ten {

  public static void main(String[] args) {
    List<List<Integer>> graph = new ArrayList<>();
    for (int i = 0; i <= 9; i++) {
      graph.add(new ArrayList<>());
    }

    // 0: 1,3,9
    // 3: 5,9
    // 4: 6
    // 5: 6,9,4
    // 7: 5
    // 9: 4
    graph.get(0).add(1);
    graph.get(0).add(9);
    graph.get(0).add(3);
    graph.get(3).add(5);
    graph.get(3).add(9);
    graph.get(4).add(6);
    graph.get(5).add(4);
    graph.get(5).add(6);
    graph.get(5).add(9);
    graph.get(7).add(5);
    graph.get(9).add(4);
    Ten sol = new Ten();
    List<Integer> res = sol.getShortestPath(graph, 0 , 9);
    System.out.println("min cost: " + sol.min);
    for (int i : res) {
      System.out.print(i + ", ");
    }
  }

  class Wizard implements Comparable<Wizard> {
    int cost;
    int id;
    Wizard parent;
    public Wizard(int id, int cost) {
      this.id = id;
      this.cost = cost;
    }

    @Override
    public int compareTo(Wizard w) {
      return Integer.compare(this.cost, w.cost);
    }
  }

  int min = Integer.MAX_VALUE;
  public List<Integer> getShortestPath(List<List<Integer>> wizards, int src, int target) {
    Wizard end = null;
    List<Integer> res = new LinkedList<>();
    Queue<Wizard> pq = new PriorityQueue<>();
    Set<Integer> seen = new HashSet<>();
    pq.offer(new Wizard(src, 0));
    while (!pq.isEmpty()) {
      Wizard curWiz = pq.poll();
      if (curWiz.id == target) {
        end = curWiz;
        min = end.cost;
        break;
      }
      seen.add(curWiz.id);
      for (int next : wizards.get(curWiz.id)) {
        if (seen.contains(next)) {//错点,写成currId
          continue;
        }
        int newCost = curWiz.cost + (next - curWiz.id) * (next - curWiz.id);
        Wizard nextWiz = new Wizard(next, newCost);
        nextWiz.parent = curWiz;
        pq.offer(nextWiz);
      }
    }
    if (end == null) {
      min = -1;
      return res;
    } else {
      Wizard curr = end;
      while (curr != null) {
        res.add(0, curr.id);
        curr = curr.parent;
      }
    }
    return res;
  }
}
