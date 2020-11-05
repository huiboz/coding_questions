class Solution {
    public int minimumEffortPath(int[][] heights) {
        int[][] dirs = new int[][] {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
        boolean[][] visited = new boolean[heights.length][heights[0].length];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        pq.add(new int[] {0, 0, 0});
        
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            visited[cur[0]][cur[1]] = true;
            if (cur[0] == heights.length - 1 && cur[1] == heights[0].length - 1)
                return cur[2];
            
            for (int[] dir : dirs) {
                int newX = dir[0] + cur[0];
                int newY = dir[1] + cur[1];
                
                if (newX < 0 || newY < 0 || newX >= heights.length || newY >= heights[0].length || visited[newX][newY])
                    continue;
                
                int diff = Math.abs(heights[newX][newY] - heights[cur[0]][cur[1]]);
                pq.add(new int[] {newX, newY, Math.max(diff, cur[2])});
            }
        }
        
        return -1;
    }
}
