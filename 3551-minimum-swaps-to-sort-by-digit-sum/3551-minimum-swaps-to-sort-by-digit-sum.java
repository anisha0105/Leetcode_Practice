class Solution {
    private int sumofdigits(int n) {
        if (n == 0) return 0;
        return n % 10 + sumofdigits(n / 10);
    }
    public int minSwaps(int[] nums1) {
        int[] nums2 = nums1.clone();
        Integer[] arr = new Integer[nums2.length];
        for (int i = 0; i < nums2.length; i++) {
            arr[i] = nums2[i];
        }
        Arrays.sort(arr, (a, b) -> {
            int x = sumofdigits(a);
            int y = sumofdigits(b);
            if (x == y) return Integer.compare(a, b);
            return Integer.compare(x, y);
        });
        Map<Integer, Integer> mp = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            mp.put(arr[i], i);
        }
        int ans = 0;
        int i = 0;
        while (i < nums1.length) {
            if (mp.get(nums1[i]) == i) {
                i++;
            } else {
                ans++;
                int j = mp.get(nums1[i]);
                int temp = nums1[i];
                nums1[i] = nums1[j];
                nums1[j] = temp;
            }
        }
        return ans;
    }
}