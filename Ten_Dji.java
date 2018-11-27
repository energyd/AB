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
