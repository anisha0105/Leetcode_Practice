class Solution {
    int m;
    int n;
    final int MOD = (int)1e9 + 7;
    public int uniquePaths(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        int[][][] teleport = new int[m][n][2];
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                teleport[i][j][1] = getFinalPosi(grid, i, j, 1, 0);
                teleport[i][j][0] = getFinalPosi(grid, i, j, 0, 1);
            }
        }        
        int[][] dp = new int[m][n];
        dp[0][0] = 1;
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                for (int k = 0; k < 2; k++){
                    int nextPosi = teleport[i][j][k];
                    if (nextPosi == -1) continue; 
                    int nextX = nextPosi/n;
                    int nextY = nextPosi%n;
                    dp[nextX][nextY] = (dp[nextX][nextY]+dp[i][j]) % MOD;
                }
            }
        }
        return dp[m-1][n-1];
    }
    private int getFinalPosi(int[][] grid, int i, int j, int dx, int dy){
        int newX = i+dx, newY = j+dy;
        if (newX >= m || newY >= n) return -1;
        if (grid[newX][newY] == 0) return newX * n + newY;
        return getFinalPosi(grid, newX, newY, dy, dx);
    }
}