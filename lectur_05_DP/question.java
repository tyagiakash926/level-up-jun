public class question{


    // leetcode 63
    public int uniquePaths_DP(int sr,int sc,int er,int ec,int[][] dp,int[][] grid)
    {
        for(sr=er;sr>=0;sr--)
        {
            for(sc=ec;sc>=0;sc--)
            {
                if(sr==er && sc==ec)
                {
                    dp[sr][sc]=1;
                    continue;
                }
                int count=0;
                if(sc+1<=ec)
                {
                    if(grid[sr][sc+1]==0)
                    count+=dp[sr][sc+1];//uniquePaths_Rec(sr,sc+1,er,ec,dp);
                }
                if(sr+1<=er)
                {
                    if(grid[sr+1][sc]==0)
                    count+=dp[sr+1][sc];//uniquePaths_Rec(sr+1,sc,er,ec,dp);
                }
                dp[sr][sc]=count;
            }
        }
        return dp[0][0];
    }
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        if(obstacleGrid[0][0]==1 || obstacleGrid[m-1][n-1]==1)
            return 0;
        int[][] dp = new int[m][n];
        return uniquePaths_DP(0,0,m-1,n-1,dp,obstacleGrid);
    }

    // leetcode 62

    public int uniquePaths_DP(int sr,int sc,int er,int ec,int[][] dp)
    {
        for(sr=er;sr>=0;sr--)
        {
            for(sc=ec;sc>=0;sc--)
            {
                if(sr==er && sc==ec)
                {
                    dp[sr][sc]=1;
                    continue;
                }
                int count=0;
                if(sc+1<=ec)
                {
                    count+=dp[sr][sc+1];//uniquePaths_Rec(sr,sc+1,er,ec,dp);
                }
                if(sr+1<=er)
                {
                    count+=dp[sr+1][sc];//uniquePaths_Rec(sr+1,sc,er,ec,dp);
                }
                dp[sr][sc]=count;
            }
        }
        return dp[0][0];
    }
    public int uniquePaths(int m, int n) {
        if(m==0 || n==0)
            return 0;
        int[][] dp = new int[m][n];
        return uniquePaths_DP(0,0,m-1,n-1,dp);
    }

import java.util.*;
import java.lang.*;
import java.io.*;

class frinePairingProblem {
    static int mod = (int) 1e9 + 7;

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int t = scn.nextInt();
        while (t-- > 0) {
            int n = scn.nextInt();
            friendsPairingProblem(n);
        }

    }

    public static long friendsPairingProblem(int n, long[] dp) {
        if (n <= 1)
            return dp[n] = 1;
        if (dp[n] != 0)
            return dp[n];

        long single = friendsPairingProblem(n - 1, dp) % mod;
        long pairUp = friendsPairingProblem(n - 2, dp) % mod * (n - 1) % mod;

        return dp[n] = (single + pairUp) % mod;
    }

    public static long friendsPairingProblem_DP(int n, long[] dp) {
        int N = n;
        for (n = 0; n <= N; n++) {
            if (n <= 1) {
                dp[n] = 1;
                continue;
            }

            long single = dp[n - 1] % mod;// friendsPairingProblem(n - 1, dp);
            long pairUp = dp[n - 2] % mod * (n - 1) % mod;// friendsPairingProblem(n - 2, dp) * (n - 1);

            dp[n] = single + pairUp;
        }

        return dp[N] % mod;
    }

    public static int friendsPairingProblem_Opti(int n) {
        int single = 1;
        int pairUp = 1;

        int ans = 0;
        for (int i = 2; i <= n; i++) {
            ans = single + pairUp * (i - 1);

            single = pairUp;
            pairUp = ans;
        }

        return ans;
    }

    public static void friendsPairingProblem(int n) {
        // int n=scn.nextInt();
        long[] dp = new long[n + 1];
        long ans = friendsPairingProblem(n, dp);

        System.out.println(ans);
    }

}

// gfg  Count number of ways to partition a set into k subsets

    public static int   count_of_ways(int n, int k, int[][] dp) 
    {
        if(k==n || k==1)
            return dp[k][n] = k;
        if(dp[k][n]!=-1)
            return dp[k][n];
        int ownGroup = count_of_ways(n - 1, k - 1, dp);
        int partOfOtherGroup = count_of_ways(n - 1, k, dp) * k;
        return dp[k][n] = ownGroup + partOfOtherGroup; 
    }

    public static int count_of_ways_DP(int n, int k, int[][] dp) {
        int K = k;
        int N = n;
        for (k = 1; k <= K; k++) {
            for (n = k; n <= N; n++) {
                if (k == n || k == 1) {
                    dp[k][n] = 1;
                    continue;
                }

                int ownGroup = dp[k - 1][n - 1]; // count_of_ways(n - 1, k - 1, dp);
                int partOfOtherGroup = dp[k][n - 1] * k;// count_of_ways(n - 1, k, dp) * k;

                dp[k][n] = ownGroup + partOfOtherGroup;
            }
        }

        return dp[K][N];
    }

    public static void count_of_ways(int n, int k) {
        int[][] dp = new int[k + 1][n + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);

        // System.out.println(count_of_ways(n, k, dp));
        System.out.println(count_of_ways_DP(n, k, dp));

        display2D(dp);
    }

    import java.util.*;
import java.lang.*;
import java.io.*;
    
public class questions{
	public static void main (String[] args)
	 {
	      Scanner scn=new Scanner(System.in);
	      int t=scn.nextInt();
	      while(t-->0){
	          int[] arr=new int[scn.nextInt()];
	          for(int i=0;i<arr.length;i++) arr[i]=scn.nextInt();
	          System.out.println(LBS(arr));
	      }
	 }
	 
	 
    // Left to Right
    public static int LIS_LR(int[] arr,int[] dp){
        int max_=0;
        for(int i=0;i<arr.length;i++){
            dp[i] = 1;
            for(int j = i-1;j>=0;j--){
                if(arr[j] < arr[i]){
                    dp[i]=Math.max(dp[i],dp[j]+1);
                }
            }
            max_=Math.max(max_,dp[i]);
        }
        return max_;
    }

    // Right to Left
    public static int LIS_RL(int[] arr,int[] dp){
            int max_=0;
            for(int i = arr.length-1; i>=0 ; i--){
                dp[i] = 1;
                for(int j = i+1;j < arr.length;j++){
                    if(arr[j] < arr[i]){
                        dp[i]=Math.max(dp[i],dp[j]+1);
                    }
                }
                max_=Math.max(max_,dp[i]);
            }
            return max_;
    }

    // https://www.geeksforgeeks.org/longest-bitonic-subsequence-dp-15/
    public static int LBS(int[] arr){
        int n=arr.length;
        int[] LIS=new int[n];
        int[] LDS=new int[n];

        LIS_LR(arr,LIS);
        LIS_RL(arr,LDS);

        int maxLen=0;
        for(int i=0;i<n;i++){
            int len=LIS[i]+LDS[i]-1;
            maxLen=Math.max(maxLen,len);
        }

        return maxLen;
    }


    //Maximum Sum Bitonic subsequnece
    // Left to Right
    public static int LIS_LR(int[] arr,int[] dp){
        int max_=0;
        for(int i=0;i<arr.length;i++){
            dp[i] = arr[i];
            for(int j = i-1;j>=0;j--){
                if(arr[j] < arr[i]){
                    dp[i]=Math.max(dp[i],dp[j]+arr[i]);
                }
            }
            max_=Math.max(max_,dp[i]);
        }
        return max_;
    }

    // Right to Left
    public static int LIS_RL(int[] arr,int[] dp){
            int max_=0;
            for(int i = arr.length-1; i>=0 ; i--){
                dp[i] = arr[i];
                for(int j = i+1;j < arr.length;j++){
                    if(arr[j] < arr[i]){
                        dp[i]=Math.max(dp[i],dp[j]+arr[i]);
                    }
                }
                max_=Math.max(max_,dp[i]);
            }
            return max_;
    }

    public static int LBS(int[] arr){
        int n=arr.length;
        int[] LIS=new int[n];
        int[] LDS=new int[n];

        LIS_LR(arr,LIS);
        LIS_RL(arr,LDS);

        int maxLen=0;
        for(int i=0;i<n;i++){
            int len=LIS[i]+LDS[i]-arr[i];
            maxLen=Math.max(maxLen,len);
        }

        return maxLen;
    }

}


    //leetcode ballon brust:
    int n;
    public int maxCoins(int[] nums) {
        n = nums.length; 
        if(n==0)
            return 0;
        int[][] dp = new int[n][n];
        return maxCoins_(nums,0,n-1,dp);
    }
    public int maxCoins_(int[] arr,int si,int ei,int[][] dp)
    {
        for(int gap=0;gap<n;gap++)
        {
            for(si=0,ei=gap;ei<n;si++,ei++)
            {
                
                int lval = (si==0)?1:arr[si-1];
                int rval = (ei==n-1)?1:arr[ei+1];
                int oMAX_=0;
                for(int cut = si;cut<=ei;cut++)
                {
                    int lans = (cut==si)?0:dp[si][cut-1];//maxCoins_(arr,si,cut-1,dp);
                    int rans = (cut==ei)?0:dp[cut+1][ei];//maxCoins_(arr,cut+1,ei,dp);

                    int myAns = lans + lval*arr[cut]*rval + rans;
                    oMAX_=Math.max(myAns,oMAX_);
                }
                dp[si][ei] = oMAX_;
            }
        }
        return dp[0][n-1];
       
    }

      // Leetcode 416
      public boolean canPartition(int[] nums) {
        
        int sum=0;
        for(int ele: nums){
            sum+=ele;
        }
        
        if(sum%2!=0) return false;
        
        boolean[][] dp=new boolean[nums.length+1][sum/2+1];
        return targetSum_01_DP(nums,sum/2,nums.length,dp);
    }
    
     public static boolean targetSum_01_DP(int[] coins,int tar,int n,boolean[][] dp){
        int N=n;
        int Tar=tar;
 
        for(n=0;n<=N;n++){
            for(tar=0;tar<=Tar;tar++){
                if(tar == 0 || n == 0){
                    dp[n][tar] = (tar==0)?true:false;
                    continue;
                }
                
                dp[n][tar]=false;
                if(tar-coins[n-1]>=0)
                   dp[n][tar]=dp[n][tar] || dp[n-1][tar-coins[n-1]];
                dp[n][tar]=dp[n][tar] || dp[n-1][tar];
            }
        }

        return dp[N][Tar];
    }

    //leetcode target sum====>
    public static int findTargetSumWays_memo(int[] nums, int n, int sum, int tar,int[][] dp){
        if (n == 0)
            return dp[n][sum] = ((tar == (0 + sum)) ? 1 : 0);
    
        if (dp[n][sum] != -1)
            return dp[n][sum];
    
        int include = findTargetSumWays_memo(nums, n - 1, sum - nums[n - 1], tar,dp);  // positive call
        int exclude = findTargetSumWays_memo(nums, n - 1, sum + nums[n - 1], tar,dp); // nrgative call
    
        return dp[n][sum] = include + exclude;
    }
    
    
    public static int findTargetSumWays(vector<int> &nums, int s)
    {
        if (nums.length == 0)
            return 0;
    
        int n = nums.length;
        int sum = 0;
    
        for (int i : nums)
            sum += i;
        if (s > sum || s < -sum)
            return 0;
    
        int[][] dp=new int[n+1][2*sum+1];
        for(int[] a: dp) Arrays.fill(a,-1);
    
        return findTargetSumWays_memo(nums, n, sum, s+sum,dp);
      }
    
}