class Solution {
  
  public static void main(String[] args) {
    Solution sol = new Solution();
    
    List<List<Integer>> wizards = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      wizards.add(new ArrayList<>());
    }
    wizards.get(0).addAll(Arrays.asList(new Integer[] {1, 4, 5}));
    wizards.get(4).add(9);
    List<Integer> res = sol.getShortestPath(wizards, 0, 9);
    System.out.println("min cost: " + sol.min);
    for (int i : res) {
      System.out.print(i + ", ");
    }
  }
  
  int min = Integer.MAX_VALUE;
  List<Integer> minPath = new ArrayList<>();
  List<List<Integer>> wizards;
  public List<Integer> getShortestPath(List<List<Integer>> wizards, int source, int target) {
    Set<Integer> visited = new HashSet<>();
    this.wizards = wizards;
    
    dfs(new ArrayList<>(), 0, source, target, visited);
    
    return minPath;
  }
  
  private void dfs(List<Integer> curPath, int curCost, int src, int target, Set<Integer> visited) {
    if (src == target) {
      if (curCost < min) {
        min = curCost;
        minPath = new ArrayList<>(curPath);
      }
      return;
    }
    
    for (int next : wizards.get(src)) {
      if (visited.contains(next)) {
        continue;
      }
      curPath.add(next);
      visited.add(next);
      int diff = Math.abs(src - next);
      int newCost = curCost + diff * diff;
      if (newCost > min) continue;
      dfs(curPath, curCost + diff * diff, next, target, visited);
      curPath.remove(curPath.size() - 1);
      visited.remove(next);
    }
  }
}
