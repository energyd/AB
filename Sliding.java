import java.io.*;
import java.util.*;

class Solution {
  
  public static void main(String[] args) {
    //int[][] input = {{1,2,3}, {4,5,6}, {7,8,0}};
    Solution sol = new Solution();
    int[][] inputRand = sol.shuffle(3, 3);
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        System.out.print(inputRand[i][j] + " ");
      }
      System.out.println();
    }
    System.out.println(sol.canSolve(inputRand));
  }
  
  public class Status {
    int[][] board;
    String strBoard;
    int x;
    int y;
    int m;
    int n;
    
    public Status (int[][] board) {
      this.board = board;
      m = board.length;
      n = board[0].length;
      getStartPoint();
      strBoard = encodeBoard();
    }
    
    private Status go(int xx, int yy, Set<String> visited) {
      if (xx < 0 || xx >= m || yy < 0 || yy >= n) {
        return null;
      }
      int[][] newBoard = new int[m][n];
      for (int i = 0; i < m; i++) {
        newBoard[i] = Arrays.copyOf(board[i], board[i].length);
      }
      newBoard[x][y] = newBoard[xx][yy];
      newBoard[xx][yy] = 0;
      Status newSts = new Status(newBoard);
      if (visited.contains(newSts.strBoard)) {//注意查重不要用老的当前status查
        return null;
      }
      return newSts;
    }
    
    private void getStartPoint() {
      int m = board.length;
      int n = board[0].length;
      int[] startPoint = new int[2];
      for (int i = 0; i < board.length; i++) {
        for (int j = 0; j < board[0].length; j++) {
          if (board[i][j] == 0) {
            x = i;
            y = j;
          }
        }
      }
    }
    
    private String encodeBoard() {
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
          sb.append(board[i][j]);
          sb.append(",");
        }
      }
      sb.setLength(sb.length() - 1);
      return sb.toString();
    }
    
    private boolean win(String target) {
      return strBoard.equals(target);
    }
  }
  
  int[] dx = {1, 0, -1, 0};
  int[] dy = {0, -1, 0, 1};
  public boolean canSolve (int[][] board) {
    Status beginStatus = new Status(board);
    String targetStr = getTargetStr(board.length, board[0].length);
    Queue<Status> queue = new LinkedList<>();
    queue.offer(beginStatus);
    Set<String> visited = new HashSet<>();
    
    while (!queue.isEmpty()) {
      Status curStatus = queue.poll();
      System.out.println("Current Board: " + curStatus.strBoard);
      if (curStatus.win(targetStr)) {
        return true;
      }
      visited.add(curStatus.strBoard);
      for (int i = 0; i < 4; i++) {
        int xx = curStatus.x + dx[i];
        int yy = curStatus.y + dy[i];
        Status newStatus = newStatus = curStatus.go(xx, yy, visited);//注意这里查重是用新status来查
        if (newStatus != null) {
          queue.offer(newStatus);
        }
      }
    }
    return false;
  }
  
  public int[][] shuffle(int m, int n) {
    int[][] res = new int[m][n];
    List<Integer> list = new ArrayList<>();
    for (int i = 0; i < m * n; i++) {
      list.add(i);
    }
    Collections.shuffle(list);
    for (int i = 0; i < m * n; i++) {
      res[i / n][i % n] = list.get(i);
    }
    return res;
  }
  
  public String getTargetStr(int m, int n) {
    StringBuilder sb = new StringBuilder();
    for (int i = 1; i < m * n; i++) {//注意不好含m*n
      sb.append(i + ",");
    }
    sb.append(0);
    return sb.toString();
  }
}
