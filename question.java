import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Arrays;
import java.util.*;

public class question {
    //leetcode 39 :==================================================
public ArrayList<ArrayList<Integer>> combinationSum(int[] candidates, int target) {
    ArrayList<ArrayList<Integer>> results = new ArrayList<>();
    
    if (candidates == null || candidates.length == 0) {
        return results;
    }
    
    Arrays.sort(candidates);
    
    ArrayList<Integer> combination = new ArrayList<>();
    toFindCombinationsToTarget(results, combination, candidates, target, 0);
    
    return results;
}
 public static void toFindCombinationsToTarget(ArrayList<ArrayList<Integer>> results, ArrayList<Integer> combination, int[] candidates, int target, int startIndex)
 {
     if(target==0)
     {
        ArrayList<Integer> c = new ArrayList<>(combination);
         results.add(c);
         return ;
     }
     for(int i =startIndex;i<candidates.length;i++)
     {
         if(target-candidates[i]>=0)
         {
             combination.add(candidates[i]);
             toFindCombinationsToTarget(results,combination,candidates,target-candidates[i],i);
             combination.remove(combination.size() - 1);
         }
     }
 }

 //leetcode 40================================================================>

 class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<Integer> small = new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();
        combinationSum_(candidates,target,0,small,ans);
        return ans;
    }
    public void combinationSum_(int[] candidates, int target,int idx,List<Integer> small,List<List<Integer>> ans)
    {
        if(target==0)
        {
            List<Integer> s = new ArrayList<>(small);
            ans.add(s);
            return;
        }
        for(int i=idx;i<candidates.length;i++)
        {
            if(i>idx && candidates[i]==candidates[i-1])
                continue;
            if(target-candidates[i]>=0)
            {
                small.add(candidates[i]);
                combinationSum_(candidates,target-candidates[i],i+1,small,ans);
                small.remove(small.size()-1);
            }
        }
    }
}

 //leetcode ====================================>
 List<List<Integer>> ans = new ArrayList<>();
    List<Integer> sans = new ArrayList<>();
    boolean[] vis;

    public void permuteUnique(int[] nums, int count) {
        if (count == nums.length) {
            List<Integer> base = new ArrayList<>(sans);
            ans.add(base);
            return;
        }

        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (!vis[i] && !set.contains(nums[i])) {

                vis[i] = true;
                set.add(nums[i]);
                sans.add(nums[i]);

                permuteUnique(nums, count + 1);

                vis[i] = false;
                sans.remove(sans.size() - 1);

            }
        }

    }

    //
    

    public List<List<Integer>> permuteUnique(int[] nums) {
        vis = new boolean[nums.length];
        permuteUnique(nums, 0);
        return ans;
    }
}

