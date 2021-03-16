package leetcode;

import java.util.*;

public class SolutionCode {
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public class Solution {
        //反转链表
        public ListNode reverseList(ListNode head) {
            ListNode pre=null;
            ListNode cur = head;
            while (cur!=null){
                ListNode next = cur.next;
                cur.next = pre;
                pre = cur;
                cur = next;
            }
            return pre;

        }
        //最大子序和
        public int maxSubArray(int[] nums) {
            int pre = 0, maxAns = nums[0];
            for (int x : nums) {
                pre = Math.max(pre + x, x);
                maxAns = Math.max(maxAns, pre);
            }
            return maxAns;

        }
        //最长回文子序列
        public String longestPalindrome02(String s) {
            if (s == null || s.length() < 1) {
                return "";
            }
            int start = 0, end = 0;
            for (int i = 0; i < s.length(); i++) {
                int len1 = expandAroundCenter(s, i, i);
                int len2 = expandAroundCenter(s, i, i + 1);
                int len = Math.max(len1, len2);
                if (len > end - start) {
                    start = i - (len - 1) / 2;
                    end = i + len / 2;
                }
            }
            return s.substring(start, end + 1);
        }

        public int expandAroundCenter(String s, int left, int right) {
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                --left;
                ++right;
            }
            return right - left - 1;
        }
        //动态规划
        public String longestPalindrome(String s) {
                int n = s.length();
                boolean[][] dp = new boolean[n][n];
                String ans = "";
                for (int l = 0; l < n; ++l) {
                    for (int i = 0; i + l < n; ++i) {
                        int j = i + l;
                        if (l == 0) {
                            dp[i][j] = true;
                        } else if (l == 1) {
                            dp[i][j] = (s.charAt(i) == s.charAt(j));
                        } else {
                            dp[i][j] = (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]);
                        }
                        if (dp[i][j] && l + 1 > ans.length()) {
                            ans = s.substring(i, i + l + 1);
                        }
                    }
                }
                return ans;
        }


        //无重复字符的最长子串
        public int lengthOfLongestSubstring(String s) {
            int count =0;
            //先考虑用set这种数据结构
            Set<Character> set = new HashSet<>();
            for (int i =0;i<s.length();i++){
                if(set.contains(s.charAt(i))){
                    count = Math.max(set.size(),count);
                    //到这里发现不对，应该移除重复字符及其前面的字符，由于set是无序的，没办法实现，考虑换用map，记录相应字符的位置。
                    set.removeAll(set);
                }
                set.add(s.charAt(i));

            }
            return count;

        }
        public int lengthOfLongestSubstring02(String s) {
            if(s.length()==0)return  0;
            int count =1;
            Map<Character,Integer> map = new HashMap<>();
            int start = 0;
            int end;
            for (int i =0;i<s.length();i++){
                if(map.containsKey(s.charAt(i)) && start<i){
                    start = Math.max(map.get(s.charAt(i))+1,start);
                    map.put(s.charAt(i),i);
                }
                map.put(s.charAt(i),i);
                end=i;
                count = Math.max(end-start+1,count);
            }
            return count;
        }
        //两数相加
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            int carry = 0;
            ListNode head = null;
            ListNode tail = null;
            while (l1!=null || l2!=null){
                int n1 = l1==null? 0:l1.val;
                int n2 = l2==null? 0:l2.val;
                if(head == null){
                    head =new ListNode((n1+n2+carry)%10);
                    tail = head;
                }else {
                   tail.next = new ListNode((n1+n2+carry)%10);
                   tail = tail.next;
                }
                carry = (n1+n2+carry)/10;
                if (l1 != null) {
                    l1 = l1.next;
                }
                if (l2 != null) {
                    l2 = l2.next;
                }
            }
            if(carry!=0){
                tail.next = new ListNode(carry);
            }
            return head;
        }
        //两数之和
        public int[] twoSum01(int[] nums, int target) {
            int[] res =new int[2];
            if(nums.length<2)return res;
            Map<Integer,Integer> map = new HashMap<>();
            for (int i = 0;i<nums.length;i++){
                int t = target-nums[i];
                if (map.containsKey(t)){
                        res[0]=map.get(t);
                        res[1]=i;
                }
                map.put(nums[i],i);
            }
            return res;
        }
        public int[] twoSum(int[] nums, int target) {
            int[] res =new int[2];
            if(nums.length<2)return res;
            for (int i = 0;i<nums.length;i++){
                int t = target-nums[i];
                for (int j=i+1;j<nums.length;j++){
                    if(nums[j] == t){
                        res[0]=i;
                        res[1]=j;
                    }
                }
            }
            return res;
        }

        //前序中序构建二叉树
            private Map<Integer, Integer> indexMap;
            public TreeNode myBuildTree(int[] preorder, int[] inorder, int preorder_left, int preorder_right, int inorder_left, int inorder_right) {
                if (preorder_left > preorder_right) {
                    return null;
                }

                // 前序遍历中的第一个节点就是根节点
                int preorder_root = preorder_left;
                // 在中序遍历中定位根节点
                int inorder_root = indexMap.get(preorder[preorder_root]);

                // 先把根节点建立出来
                TreeNode root = new TreeNode(preorder[preorder_root]);
                // 得到左子树中的节点数目
                int size_left_subtree = inorder_root - inorder_left;
                // 递归地构造左子树，并连接到根节点
                // 先序遍历中「从 左边界+1 开始的 size_left_subtree」个元素就对应了中序遍历中「从 左边界 开始到 根节点定位-1」的元素
                root.left = myBuildTree(preorder, inorder, preorder_left + 1, preorder_left + size_left_subtree, inorder_left, inorder_root - 1);
                // 递归地构造右子树，并连接到根节点
                // 先序遍历中「从 左边界+1+左子树节点数目 开始到 右边界」的元素就对应了中序遍历中「从 根节点定位+1 到 右边界」的元素
                root.right = myBuildTree(preorder, inorder, preorder_left + size_left_subtree + 1, preorder_right, inorder_root + 1, inorder_right);
                return root;
            }

            public TreeNode buildTree(int[] preorder, int[] inorder) {
                int n = preorder.length;
                // 构造哈希映射，帮助我们快速定位根节点
                indexMap = new HashMap<Integer, Integer>();
                for (int i = 0; i < n; i++) {
                    indexMap.put(inorder[i], i);
                }
                return myBuildTree(preorder, inorder, 0, n - 1, 0, n - 1);
            }

        //从尾到头打印链表
        public int[] reversePrint(ListNode head) {
            //先获取链表长度，创建对应长度数组
            ListNode currNode = head;
            int len = 0;
            while(currNode != null){
                len ++;
                currNode = currNode.next;
            }
            int[] result = new int[len];

            //再次遍历链表，将值倒序填充至结果数组
            currNode = head;
            while(currNode != null){
                result[len - 1] = currNode.val;
                len --;
                currNode = currNode.next;
            }
            return result;
        }
        //每个空格替换成%20
            public String replaceSpace01(String s) {
                StringBuilder res = new StringBuilder();
                for(Character c : s.toCharArray())
                {
                    if(c == ' ') res.append("%20");
                    else res.append(c);
                }
                return res.toString();
            }

        public String replaceSpace(String s) {
            int count =0;
            for (int i =0;i<s.length();i++){
                if (s.charAt(i) ==' ') count++;
            }
            char[] res = new char[s.length()+2*count];
            int j=0;
            for (int i =0;i<s.length();i++) {
                if (s.charAt(i) ==' ') {
                    res[j] = '%';
                    res[j++] = '2';
                    res[j++] = '0';
                } else {
                    res[j++] = s.charAt(i);
                }
            }
            return res.toString();
        }
        //递增二维数组中的查找 o(m+n) o(1)
        public boolean findNumberIn2DArray(int[][] matrix, int target) {
            if(matrix.length==0 || matrix[0].length==0)return false;
            int row =0;
            int colomn = matrix[0].length-1;
            while (row<matrix.length && colomn>=0){
                if(target==matrix[row][colomn])return true;
                else if (target<matrix[row][colomn]) colomn--;
                else row++;
            }
            return false;
        }

        //数组中重复的数字,o(n),o(n)
        public int findRepeatNumber(int[] nums) {
            Set<Integer> set =new HashSet<Integer>();
            for (int num :nums){
                if(set.contains(num)){
                    return num;
                }
                set.add(num);
            }
            return -1;
        }
        //原地置换-萝卜找坑 o(n),o(1)
        public int findRepeatNumber02(int[] nums) {
            int temp;
            for(int i=0;i<nums.length;i++){
                while (nums[i]!=i){
                    if(nums[i]==nums[nums[i]]){
                        return nums[i];
                    }
                    temp=nums[i];
                    nums[i]=nums[temp];
                    nums[temp]=temp;
                }
            }
            return -1;
        }


        //两个链表的第一个公共节点
        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            ListNode tempA = headA;
            ListNode tempB = headB;
            while (tempA != tempB) {
                tempA = tempA == null ? headB : tempA.next;
                tempB = tempB == null ? headA : tempB.next;
            }
            return tempA;
        }

        public int[] maxSlidingWindow2(int[] nums, int k) {
            int n = nums.length;
            if (n * k == 0) return new int[0];
            int [] output = new int[n - k + 1];
            for (int i = 0; i < n - k + 1; i++) {
                int max = Integer.MIN_VALUE;
                for(int j = i; j < i + k; j++)
                    max = Math.max(max, nums[j]);
                output[i] = max;
            }
            return output;
        }

        public int eatenApples(int[] apples, int[] days) {
            // 优先队列，队首是最早过期的  int[0]:苹果个数  int[1]:过期时间
            PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> o1[1] < o2[1] ? -1 : 1);
            int eatNum = 0;
            for (int i = 0; i < apples.length || queue.size() > 0; i++) {
                //1.移除过期的
                while (!queue.isEmpty()) {
                    int[] apple = queue.peek();
                    if (apple[1] <= i) {
                        queue.poll();
                    } else {
                        break;
                    }
                }
                //2.添加当天新长出来的
                if (i < apples.length && apples[i] > 0) {
                    queue.add(new int[]{apples[i], days[i] + i});
                }
                //3.吃掉已有的（优先吃最早过期的）
                int[] ap = queue.peek();
                if (ap != null && ap[0] > 0) {
                    eatNum++;
                    ap[0] -= 1;
                    if (ap[0] == 0) {
                        queue.poll();
                    }
                }
            }
            return eatNum;
        }
        public boolean halvesAreAlike(String s) {
            if(s.length()==0)return true;
            HashSet<Character> characters = new HashSet<>();
            characters.add('a');
            characters.add('e');
            characters.add('i');
            characters.add('o');
            characters.add('u');
            characters.add('A');
            characters.add('E');
            characters.add('I');
            characters.add('O');
            characters.add('U');
            String a = s.substring(0,s.length()/2);
            String b = s.substring(s.length()/2);
            if(count(a,characters) == count(b,characters)){
                return true;
            }else {
                return false;
            }
        }
        public int count(String s,HashSet characters ){
            int c = 0;
            for (int i=0;i<s.length();i++) {
                if(characters.contains(s.charAt(i))){
                    c++;
                }
            }
            return c;
        }

        public ListNode getKthFromEnd(ListNode head, int k) {
            ListNode fast = head;
            ListNode slow = head;
            while (k > 0) {
                if (fast == null) return null;
                k--;
                fast = fast.next;
            }
            while (fast != null) {
                slow = slow.next;
                fast = fast.next;
            }
            return slow;
        }

        public ListNode middleNode(ListNode head) {
            ListNode slow = head;
            ListNode fast = head;
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            return slow;
        }

        public List<Integer> topKFrequent(int[] nums, int k) {
            List<Integer> res = new ArrayList();
            // 使用字典，统计每个元素出现的次数，元素为键，元素出现的次数为值
            HashMap<Integer, Integer> map = new HashMap();
            for (int num : nums) {
                if (map.containsKey(num)) {
                    map.put(num, map.get(num) + 1);
                } else {
                    map.put(num, 1);
                }
            }

            //桶排序
            //将频率作为数组下标，对于出现频率不同的数字集合，存入对应的数组下标
            List<Integer>[] list = new List[nums.length + 1];
            for (int key : map.keySet()) {
                // 获取出现的次数作为下标
                int i = map.get(key);
                if (list[i] == null) {
                    list[i] = new ArrayList();
                }
                list[i].add(key);
            }

            // 倒序遍历数组获取出现顺序从大到小的排列
            for (int i = list.length - 1; i >= 0 && res.size() < k; i--) {
                if (list[i] == null) continue;
                res.addAll(list[i]);
            }
            return res;
        }


        public int minPathSum(int[][] grid) {
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (i == 0 && j == 0) continue;
                    else if (i == 0) grid[i][j] = grid[i][j - 1] + grid[i][j];
                    else if (j == 0) grid[i][j] = grid[i - 1][j] + grid[i][j];
                    else grid[i][j] = Math.min(grid[i - 1][j], grid[i][j - 1]) + grid[i][j];
                }
            }
            return grid[grid.length - 1][grid[0].length - 1];
        }


        public int getWinner2(int[] arr, int k) {
            int win = arr[0], count = 0;/*win:胜利者,count:获胜场次*/
            for (int i = 1; i < arr.length && count < k; i++) {/*到达获胜场次跳出循环*/
                if (arr[i] < win) {/*arr[i]比win小*的情况*/
                    count++;
                } else {/*如果win输掉了产生新的胜利者，count置为1*/
                    win = arr[i];
                    count = 1;
                }
            }
            return win;/*返回win*/
        }

        public int countGoodTriplets(int[] arr, int a, int b, int c) {
            int sum = 0;
            if (arr.length < 3) return sum;
            for (int i = 0; i < arr.length - 2; i++) {
                for (int j = i + 1; j < arr.length - 1; j++) {
                    if (Math.abs(arr[i] - arr[j]) > a) {
                        continue;
                    } else {
                        for (int k = j + 1; k < arr.length; k++) {
                            if (Math.abs(arr[k] - arr[j]) > b) {
                                continue;
                            } else {
                                sum += Math.abs(arr[k] - arr[i]) > c ? 0 : 1;
                            }
                        }
                    }
                }

            }
            return sum;
        }

        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            ListNode node = new ListNode(0);
            ListNode res = node;
            while (l1 != null && l2 != null) {
                if (l1.val < l2.val) {
                    node.next = l1;
                    l1 = l1.next;
                } else {
                    node.next = l2;
                    l2 = l2.next;
                }
                node = node.next;
            }
            node.next = l1 == null ? l2 : l1;
            return res.next;
        }

        public ListNode sortList(ListNode head) {
            if (head == null || head.next == null) return head;
            ListNode fast = head.next;
            ListNode slow = head;
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            ListNode temp = slow.next;
            slow.next = null;
            ListNode left = sortList(head);
            ListNode right = sortList(temp);
            ListNode h = new ListNode(0);
            ListNode res = h;
            while (left != null && right != null) {
                if (left.val < right.val) {
                    h.next = left;
                    left = left.next;
                } else {
                    h.next = right;
                    right = right.next;
                }
                h = h.next;
            }
            h.next = left == null ? right : left;
            return res.next;


        }

        public void moveZeroes(int[] nums) {
            int un_zero = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != 0) {
                    nums[un_zero++] = nums[i];
                }
            }
            while (un_zero < nums.length - 1) {
                nums[un_zero] = 0;
            }

        }

        public int minPartitions(String n) {
            int count = 0;
            while (n.length() > 1) {
                int temp = Integer.parseInt(n) - getDivisor(n);
                n = String.valueOf(temp);
                count++;
            }
            count += Integer.parseInt(n);
            return count;
        }

        public int getDivisor(String n) {
            int divisor = 1;
            int len = n.length();
            while (len > 1) {
                divisor = divisor * 10 + 1;
                len--;
            }
            return divisor;

        }

        public int numberOfMatches(int n) {
            int sum = 0;
            while (n > 1) {
                if (n % 2 == 0) {
                    sum += n / 2;
                    n = n / 2;
                } else {
                    sum += (n - 1) / 2;
                    n = (n - 1) / 2 + 1;
                }

            }
            return sum;

        }

        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if (root == null || root == p || root == q) return root;
            TreeNode left = lowestCommonAncestor(root.left, p, q);
            TreeNode right = lowestCommonAncestor(root.right, p, q);
            if (left == null) return right;
            if (right == null) return left;
            return root;
        }


        public int smallestDifference(int[] a, int[] b) {
            if (a.length == 0 || b.length == 0) return 0;
            int res = Integer.MAX_VALUE;
            if (a.length > b.length) {
                Arrays.sort(b);
                for (int i = 0; i < a.length; i++) {
                    for (int j = 0; j < b.length; j++) {
                        if (a[i] <= b[j]) {
                            res = Math.min(res, Math.abs(a[i] - b[j]));
                            res = Math.min(res, Math.abs(a[i] - b[j - 1]));
                        }
                    }
                    res = Math.min(res, Math.abs(a[i] - b[b.length - 1]));
                }
            } else {
                Arrays.sort(a);
                for (int i = 0; i < b.length; i++) {
                    for (int j = 1; j < a.length; j++) {
                        if (b[i] <= a[j]) {
                            res = Math.min(res, Math.abs(b[i] - a[j]));
                            res = Math.min(res, Math.abs(b[i] - a[j - 1]));
                        }
                    }
                    res = Math.min(res, Math.abs(b[i] - a[a.length - 1]));

                }
            }
            return res;

        }

        public int[] swapNumbers(int[] numbers) {
            numbers[0] = numbers[0] + numbers[1];
            numbers[1] = numbers[0] - numbers[1];
            numbers[0] = numbers[0] - numbers[1];
            return numbers;

        }


        public int longestValidParentheses(String s) {
            if (s.length() < 2) return 0;
            int res_max = 0;
            int res = 0;
            int left = 0;
            char[] chars = s.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] == '(') {
                    left++;
                }
                if (chars[i] == ')') {
                    if (left > 0) {
                        left--;
                        res++;
                    }
                    if (left == 0) {
                        res_max = Math.max(res, res_max);
                        res = 0;
                    }
                }
            }
            return res_max * 2;
        }


        public String reverseString(String string) {
            char[] s = string.toCharArray();
            if (s.length == 0 || s.length == 1) return s.toString();
            int before = 0;
            int after = s.length - 1;
            char temp;
            while (before < after) {
                temp = s[before];
                s[before++] = s[after];
                s[after--] = temp;
            }
            return s.toString();
        }

        public int findPoisonedDuration(int[] timeSeries, int duration) {
            if (timeSeries.length == 0) return 0;
            int sum = 0;
            for (int i = 0; i < timeSeries.length - 1; i++) {
//                if(timeSeries[i]+duration <timeSeries[i+1]){
//                    sum = sum+duration;
//                }else {
//                    sum = sum+(timeSeries[i+1] - timeSeries[i]);
//                }
                sum = sum + Math.min(duration, timeSeries[i + 1] - timeSeries[i]);
            }
            sum += duration;
            return sum;
        }

        public int maximumProduct(int[] nums) {
            int res = 1;
            if (nums.length < 3) {
                for (int i = 0; i < nums.length; i++) {
                    res = res * nums[i];
                }
                return res;
            }
            int max = nums[0];
            int min = nums[0];
            res = nums[0];
            int res_min = nums[0];
            int res_max = nums[0];
            for (int i = 1; i <= 3; i++) {
                max = Math.max(max, nums[i]);
                min = Math.min(min, nums[i]);
                res = res * nums[i];

            }
            for (int i = 0; i < nums.length; i++) {

            }
            return res;

        }

        public int[] maxSlidingWindow(int[] nums, int k) {
            if (nums == null || nums.length < 2) return nums;
            // 双向队列 保存当前窗口最大值的数组位置 保证队列中数组位置的数值按从大到小排序
            LinkedList<Integer> queue = new LinkedList();
            // 结果数组
            int[] result = new int[nums.length - k + 1];
            // 遍历nums数组
            for (int i = 0; i < nums.length; i++) {
                // 保证从大到小 如果前面数小则需要依次弹出，直至满足要求
                while (!queue.isEmpty() && nums[queue.peekLast()] <= nums[i]) {
                    queue.pollLast();
                }
                // 添加当前值对应的数组下标
                queue.addLast(i);
                // 判断当前队列中队首的值是否有效
                if (queue.peek() <= i - k) {
                    queue.poll();
                }
                // 当窗口长度为k时 保存当前窗口中最大值
                if (i + 1 >= k) {
                    result[i + 1 - k] = nums[queue.peek()];
                }
            }
            return result;
        }

        public int[] maxSlidingWindow1(int[] nums, int k) {
            int n = nums.length;
            if (n * k == 0) return new int[0];
            if (k == 1) return nums;

            int[] left = new int[n];
            left[0] = nums[0];
            int[] right = new int[n];
            right[n - 1] = nums[n - 1];
            for (int i = 1; i < n; i++) {
                // from left to right
                if (i % k == 0) left[i] = nums[i];  // block_start
                else left[i] = Math.max(left[i - 1], nums[i]);

                // from right to left
                int j = n - i - 1;
                if ((j + 1) % k == 0) right[j] = nums[j];  // block_end
                else right[j] = Math.max(right[j + 1], nums[j]);
            }

            int[] output = new int[n - k + 1];
            for (int i = 0; i < n - k + 1; i++)
                output[i] = Math.max(left[i + k - 1], right[i]);

            return output;
        }


        public int longestConsecutive(int[] nums) {
            if (nums.length == 0) return 0;
            int res = 0;
            HashSet<Integer> hashSet = new HashSet<>();
            for (int i = 0; i < nums.length; i++) {
                hashSet.add(nums[i]);
            }
            for (int i = 0; i < nums.length; i++) {
                int count_add = 0;
                int count_dec = 0;
                int flag_add = nums[i];
                int flag_dec = nums[i];
                while (hashSet.contains(++flag_add)) {
                    hashSet.remove(flag_add);
                    count_add++;
                }
                while (hashSet.contains(--flag_dec)) {
                    hashSet.remove(flag_dec);
                    count_dec++;
                }
                if (count_add + count_dec + 1 == nums.length) {
                    return nums.length;
                }
                res = Math.max(count_add + count_dec + 1, res);
            }
            return res;
        }
    }
}

