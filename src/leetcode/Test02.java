package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test02 {
    public static void main(String args[]) {

//        SolutionCode.ListNode node = new SolutionCode.ListNode(4);
//        SolutionCode.ListNode head = node;
//        node.next = new SolutionCode.ListNode(2);
//        node = node.next;
//        node.next = new SolutionCode.ListNode(1);
//        node = node.next;
//        node.next = new SolutionCode.ListNode(3);
//        sortList(head);
//        while (head!=null){
//            System.out.println(head.val);
//            head = head.next;
//
//        }
//        int[] nums = {1,1,1,2,2,3};
//        List<Integer> res = new ArrayList();
//        res = topKFrequent(nums,2);
        String s = "abba";
        lengthOfLongestSubstring(s);




    }

    public static int lengthOfLongestSubstring(String s) {
        if(s.length()==0)return  0;
        int count =1;
        Map<Character,Integer> map = new HashMap<>();
        int start = 0;
        int end = 0;
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
    public static String replaceSpace(String s) {
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
    public static List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> res = new ArrayList();
        // 使用字典，统计每个元素出现的次数，元素为键，元素出现的次数为值
        HashMap<Integer,Integer> map = new HashMap();
        for(int num : nums){
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }

        //桶排序
        //将频率作为数组下标，对于出现频率不同的数字集合，存入对应的数组下标
        List<Integer>[] list = new List[nums.length+1];
        for(int key : map.keySet()){
            // 获取出现的次数作为下标
            int i = map.get(key);
            if(list[i] == null){
                list[i] = new ArrayList();
            }
            list[i].add(key);
        }

        // 倒序遍历数组获取出现顺序从大到小的排列
        for(int i = list.length - 1;i >= 0 && res.size() < k;i--){
            if(list[i] == null) continue;
            res.addAll(list[i]);
        }
        return res;
    }}
//    public static SolutionCode.ListNode sortList(SolutionCode.ListNode head) {
//        SolutionCode.ListNode fast = head.next;
//        SolutionCode.ListNode slow = head;
//        while (fast!=null && fast.next!=null ){
//            slow  =slow.next;
//            fast = fast.next.next;
//        }
//        SolutionCode.ListNode temp = slow.next;
//        slow.next = null;
//        SolutionCode.ListNode left = sortList(head);
//        SolutionCode.ListNode right = sortList(temp);
//        SolutionCode.ListNode h = new SolutionCode.ListNode(0);
//        SolutionCode.ListNode res = h;
//        while (left!=null && right!=null){
//            if(left.val<right.val){
//                h.next = left;
//                left=left.next;
//            }else {
//                h.next = right;
//                right = right.next;
//            }
//            h = h.next;
//        }
//        h.next = left==null?right : left;
//        return res.next;
//    }
//
//    public static int minPartitions(String n) {
//        int ans=0;
//        for(int i=0;i<n.length();i++){
//            int t = n.charAt(i)-'0';
//            ans=Math.max(ans,t);
//        }
//        return ans;
//    }
//    public static int getDivisor(String n){
//        int divisor = 1;
//        int len =  n.length();
//        while (len>1){
//            divisor = divisor*10+1;
//            len--;
//        }
//        return divisor;
//
//    }
//
//    public static  int smallestDifference(int[] a, int[] b) {
//        if(a.length ==0 || b.length == 0) return 0;
//        int res = Integer.MAX_VALUE;
//        if(a.length>b.length){
//            Arrays.sort(b);
//            for (int i = 0;i<a.length;i++) {
//                for (int j=0;j<b.length;j++) {
//                    if(a[i]<=b[j]){
//                        res = Math.min(res,Math.abs(a[i]-b[j]));
//                        res = Math.min(res,Math.abs(a[i]-b[j-1]));
//                    }
//                }
//                res = Math.min(res,Math.abs(a[i]-b[b.length-1]));
//            }
//        }else {
//            Arrays.sort(a);
//            for (int i = 0;i<b.length;i++) {
//                for (int j=1;j<a.length;j++) {
//                    if(b[i]<=a[j]){
//                        res = Math.min(res,Math.abs(b[i]-a[j]));
//                        res = Math.min(res,Math.abs(b[i]-a[j-1]));
//                    }
//                }
//                res = Math.min(res,Math.abs(b[i]-a[a.length-1]));
//
//            }
//        }
//        return  res;
//
//    }
//    public static  int longestValidParentheses(String s) {
//        if (s.length()<2)return 0;
//        int res_max = 0;
//        int res = 0;
//        int left = 0;
//        char[] chars = s.toCharArray();
//        for(int i = 0;i<chars.length; i++){
//            if(chars[i] == '('){
//                left++;
//            }
//            if(chars[i] == ')'){
//                if(left>0){
//                    left--;
//                    res++;
//                    res_max=res;
//                }
//                else if(left==0){
//                    res_max = Math.max(res,res_max);
//                    res = 0;
//                }
//            }
//        }
//        return res_max*2;
//    }
//
//}
