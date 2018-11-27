import java.io.*;
import java.util.*;

class Solution {
  
  public static void main(String[] args) {
    int[][] input = {{1,2,3}, {4,5,6}, {7,8,0}};
    Solution sol = new Solution();
    System.out.println(sol.canSolve(input));
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
      int[] start = getStartPoint();
      x = start[0];
      y = start[1];
      strBoard = encodeBoard();
    }
    
    private Status go(int xx, int yy) {
      int[][] newBoard = new int[m][n];
      for (int i = 0; i < m; i++) {
        newBoard[i] = Arrays.copyOf(board[i], board[i].length);
      }
      newBoard[x][y] = newBoard[xx][yy];
      newBoard[xx][yy] = 0;
      return new Status(newBoard);
    }
    
    private int[] getStartPoint() {
      int m = board.length;
      int n = board[0].length;
      int[] startPoint = new int[2];
      for (int i = 0; i < board.length; i++) {
        for (int j = 0; j < board[0].length; j++) {
          if (board[i][j] == 0) {
            startPoint[0] = i;
            startPoint[1] = j;
          }
        }
      }
      return startPoint;
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
      if (curStatus.win(targetStr)) {
        return true;
      }
      visited.add(curStatus.strBoard);
      for (int i = 0; i < 4; i++) {
        int xx = curStatus.x + dx[i];
        int yy = curStatus.y + dy[i];
        Status newStatus = null;
        if (canGo(curStatus, xx, yy, visited)) {
          newStatus = curStatus.go(xx, yy);
        }
        if (newStatus != null) {
          queue.offer(newStatus);
        }
      }
    }
    return false;
  }
  
  public String getTargetStr(int m, int n) {
    StringBuilder sb = new StringBuilder();
    for (int i = 1; i < m * n; i++) {
      sb.append(i + ",");
    }
    sb.append(0);
    return sb.toString();
  }
  
  public boolean canGo(Status sts, int xx, int yy, Set<String> visited) {
    if (xx < 0 || xx >= sts.m || yy < 0 || yy >= sts.n || visited.contains(sts.strBoard)) {
      return false;
    }
    return true;
  }
}
