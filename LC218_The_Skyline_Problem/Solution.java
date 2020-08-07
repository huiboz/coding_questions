// https://leetcode.com/problems/the-skyline-problem/
class Solution {
    class Point {
        public int x;
        public int height;
        public boolean isStart;

        public Point(int x, int height, boolean isStart) {
            this.x = x;
            this.height = height;
            this.isStart = isStart;
        }
    }

    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> res = new ArrayList<>();
        List<Point> list = new ArrayList<>();
        for (int[] building : buildings) {
            list.add(new Point(building[0], building[2], true));
            list.add(new Point(building[1], building[2], false));
        }

        Collections.sort(list, (a, b) -> {
            if (a.x != b.x)
                return a.x - b.x;

            // if they are both start: higher height comes first
            // if they are both end: lower height comes first
            if (a.isStart == b.isStart)
                return a.isStart ? b.height - a.height : a.height - b.height;

            // Otherwise, start will comes first
            return a.isStart ? -1 : 1;
        });

        // max heap
        PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
        queue.offer(0);
        int max = 0;

        for (Point point : list) {
            if (point.isStart) {
                queue.offer(point.height);
                int curMax = queue.peek();
                if (curMax > max) {
                    res.add(new ArrayList<>(Arrays.asList(point.x, point.height)));
                    max = curMax;
                }

            } else {
                queue.remove(point.height);
                int curMax = queue.peek();
                if (curMax < max) {
                    res.add(new ArrayList<>(Arrays.asList(point.x, curMax)));
                    max = curMax;
                }
            }
        }

        return res;
    }
}
