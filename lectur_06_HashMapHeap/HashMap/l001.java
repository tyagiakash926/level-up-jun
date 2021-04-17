import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Arrays;

public class l001{
    public static void freqMap(String str){
        HashMap<Character,Integer> map = new HashMap<>();
        // for(int i=0;i<str.length();i++){
        //     char ch = str.charAt(i);
        //     if(map.containsKey(ch)){
        //         map.put(ch,map.get(ch)+1);
        //     }else{
        //         map.put(ch,1);
        //     }
        // }

        for(int i=0;i<str.length();i++){
            char ch = str.charAt(i);
            map.put(ch,map.getOrDefault(ch,0)+1);
        }
        System.out.println(map.get('@'));
        for(char ch : map.keySet())
            System.out.println(map.get(ch));
        
        System.out.println(map);
    }


    public static void freqMap_pos(String str){
        HashMap<char,ArrayList<Integer>> map = new HashMap<>();
        for (int i = 0; i < str.length(); i++)
        {
            char ch = str.charAt(i);
            map.putIfAbsent(ch,new ArrayList<>());
            map.get(ch).add(i);
        }
    
        for(Character ch : map.keySet()){    
            System.out.println(ch + " -> " + map.get(ch));  
        }
    }

    //leetcode 349
    public static int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> map = new HashSet<>();
        for(int ele : nums1){
            map.add(ele);
        }
        ArrayList<Integer> ans = new ArrayList<>();
        for(int ele : nums2){
            if(map.contains(ele)){
                ans.add(ele);
                map.remove(ele);
            }
        }
        int[] arr = new int[ans.size()];
        int i=0;
        for(int ele :ans) arr[i++] = ele;

        return arr;
    }

    //leetcode 350
    public static int[] intersect(int[] nums1, int[] nums2) {
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int ele : nums1){
            map.put(ele,map.getOrDefault(ele,0)+1);
        }
        ArrayList<Integer> ans = new ArrayList<>();
        for(int ele : nums2){
            if(map.containsKey(ele)){
                ans.add(ele);
                map.put(ele,map.get(ele)-1);
                if(map.get(ele)==0)
                    map.remove(ele);
            }
        }
        int[] arr = new int[ans.size()];
        int i=0;
        for(int ele :ans) arr[i++] = ele;

        return arr;

    }

    //leetcode 219
    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int ele : nums){
            if(map.containsKey(ele)){
                int idx = map.get(ele);
                if(i-idx<=k)
                    return true;
            }
            map.put(ele,i);
        }
        return false;
    }

    //leetcode 451
    public static String frequencySort(String s) {
        HashMap<Character,Integer> map = new HashMap<>();
        for(int i=0;i<str.length();i++){
            char ch = str.charAt(i);
            map.put(ch,map.getOrDefault(ch,0)+1); 
        }
        PriorityQueue<Character> pq = new PriorityQueue<>((a,b)->{
            return map.get(b)-map.get(a);
        });

        for(Character ch : map.keySet())
            pq.add(ch);
        
        StringBuilder sb = new StringBuilder();
        while(pq.size()!=0){
            Character ch = pq.remove();
            int freq = map.get(ch);
            for(int i=0;i<freq;i++) sb.append(ch);
        }
        return sb.toString();
    }

    //leetcode 347
    public static int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int ele : nums) map.put(ele,map.getOrDefault(ele,0)+1);
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->{
            return map.get(b)-map.get(a);
        });
        for(int ele : map.keySet())
            pq.add(ele);
        ArrayList<Integer> ans = new ArrayList<>();
        int i = k;
        while(i-->0){
            int ele = pq.remove();
            ans.add(ele);
        }
        i=0;
        int[] arr = new int[ans.size()];
        for(int ele : ans)
            arr[i++] = ele
        return arr;
    }


    
    //Leetcode 128
    public int longestConsecutive(int[] nums) {
        HashSet<Integer> map=new HashSet<>();
        for(int ele: nums) map.add(ele);

        int len = 0;
        for(int ele: nums){
            if(!map.contains(ele)) continue;
        
            map.remove(ele);
            int prev = ele - 1;
            int next = ele + 1;
    
            while(map.contains(prev)) {
                map.remove(prev);
                prev--;
            }
    
            while(map.contains(next)) {
                map.remove(next);
                next++;
            }
    
            len = Math.max(len, next - prev - 1);
        }
        return len;
    }

    //Leetcode 49
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String,ArrayList<String>> map = new HashMap<>();
        
        int[] freq = new int[26];
        for(String s: strs){
            
            Arrays.fill(freq,0);
            for(int i=0;i<s.length();i++) freq[s.charAt(i)-'a']++;

            StringBuilder sb=new StringBuilder();
            for(int i=0;i<26;i++){
                if(freq[i] > 0){
                    sb.append((char)(i+'a'));
                    sb.append(freq[i]);
                }
            }

            String RLES = sb.toString();
            map.putIfAbsent(RLES,new ArrayList<>());
            map.get(RLES).add(s);
        }

        List<List<String>> ans=new ArrayList<>();
        for(String key : map.keySet()){
            ans.add(map.get(key));
        }

        return ans;
    }

    //Leetcode 295.=============================================

    class MedianFinder {
         
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a,b)->{
            return b - a;
        });
        
        public MedianFinder() {
             
        }
        
        public void addNum(int num) {
            if( maxHeap.size() == 0 || num <= maxHeap.peek())  maxHeap.add(num);
            else minHeap.add(num);

            if(maxHeap.size() > minHeap.size() + 1){
                minHeap.add(maxHeap.peek());
                maxHeap.remove();
            }else if(maxHeap.size() < minHeap.size()){
                maxHeap.add(minHeap.peek());
                minHeap.remove();
            }  
        }
        
        public double findMedian() {
            if(maxHeap.size() == minHeap.size() && maxHeap.size() != 0) return (maxHeap.peek() + minHeap.peek()) / 2.0;
            else if(maxHeap.size() != minHeap.size()) return maxHeap.peek();
            return -1;
        }
    }


    public static void solve(){
        freqMap("ajhvcjhjhagsx1321465kasjdbd8445632");
        // freqMap_pos("ajhvcjhjhagsx1321465kasjdbd8445632");
    }

    public static void main(String[] args){
        solve();
    }
}