public class cutType{
    public static int MCM_Rec(int[] arr,int si,int ei,int[][] dp){
        if(si+1==ei)
            return dp[si][ei]=0;
        if(dp[si][ei]!=0)
            return dp[si][ei];

        int myAns = (int)1e8;
        for(int cut = si+1;cut<ei;cut++)
        {
            int lans = MCM_Rec(arr,si,cut,dp);
            int rans = MCM_Rec(arr,cut,ei,dp);

            int recAns = lans + arr[si]*arr[cut]*arr[ei] + rans;
            if(recAns<myAns)
                myAns = recAns;
        }
        return dp[si][ei]=myAns;
    }

    public static int MCM_DP(int[] arr,int si,int ei,int[][] dp){

        for(int gap = 1; gap < arr.length;gap++){
            for(si=0,ei=gap;ei<arr.length;si++,ei++){
                if(si+1 == ei) {
                    dp[si][ei] = 0;
                    continue;
                }
                int myAns=(int)1e8;
                for(int cut=si+1;cut<ei;cut++){
                    int leftRes = dp[si][cut];//MCM_Rec(arr,si,cut,dp);
                    int rightRes = dp[cut][ei];//MCM_Rec(arr,cut,ei,dp);
            
                    int recRes = leftRes +  arr[si] * arr[cut] * arr[ei]  + rightRes;
                    if(recRes<myAns) myAns = recRes; 
                }

            dp[si][ei] = myAns;
            }
        }
    
        return dp[0][arr.length-1];
    }

    public static int MCM_Rec_02(int[] arr,int si,int ei,int[][] dp,String[][] sdp){
        if(si+1 == ei) {
            sdp[si][ei] = ((char)(si+'A')+"");
            return dp[si][ei] = 0;
        }
            
        if(dp[si][ei] != 0) return dp[si][ei]; 

        dp[si][ei]=(int)1e8;
        for(int cut=si+1;cut<ei;cut++){
            int leftRes=MCM_Rec_02(arr,si,cut,dp,sdp);
            int rightRes=MCM_Rec_02(arr,cut,ei,dp,sdp);
            
            int recRes = leftRes +  arr[si] * arr[cut] * arr[ei]  + rightRes;
            if(recRes < dp[si][ei]) {
                sdp[si][ei] = "(" + sdp[si][cut] + sdp[cut][ei] + ")";
                dp[si][ei] = recRes; 
            }
        }
        return dp[si][ei];
    }


    public static int summation(int si,int ei,int[] freq){
        int sum=0;
        while(si<=ei) sum+=freq[si++];
        return sum;
    }

    public static int OBST_Rec(int[] freq,int si,int ei,int[][] dp,int[] prefixSum){
        if(dp[si][ei] != 0) return dp[si][ei]; 

        int myAns=(int)1e8;
        for(int cut=si;cut<=ei;cut++){
            int leftRes = cut == si ? 0 : OBST_Rec(freq,si,cut,dp,prefixSum);
            int rightRes = cut == ei ? 0 : OBST_Rec(freq,cut,ei,dp,prefixSum);
            
            int recRes = leftRes +  (prefixSum[ei]-(si==0?0:prefixSum[si])) + rightRes;
            if(recRes<myAns) myAns = recRes; 
        }

        return dp[si][ei] = myAns;
    }


    public static class pair{
        int min = (int) 1e8;
        int max = (int) -1e8;
        String minStr = "";
        String maxStr = "";
        public pair(int min,int max,String minStr,String maxStr)
        {
            this.min = min;
            this.max = max;
            this.minStr = minStr;
            this.maxStr = maxStr;
        }
    }

    public static int evaluateExpression(int a,int b,char operatror)
    {
        if(operatror=='+') 
            return a+b;
        else
            return a*b;
    }

    public static pair minMaxEval(String str,int si,int ei,pair[][] dp)
    {
        if(si==ei)
        {
            char ch = str.charAt(si);
            int val = ch-'0';
            return new pair(val,val,val+"",val+"");
        }
        if(dp[si][ei]!=null)
            return dp[si][ei];
        pair minMaxAns = new pair((int)1e8,(int)-1e8,"","");
        for(int cut = si+1;cut<ei;cut+=2)
        {
            pair leftRes = minMaxEval(str,si,cut-1,dp);
            pair rightRes = minMaxEval(str,cut+1,ei,dp);

            int minval = evaluateExpression(leftRes.min,rightRes.min,str.charAt(cut));
            int maxval = evaluateExpression(leftRes.max,rightRes.max,str.charAt(cut));

            if(minval < minMaxAns.min)
            {
                minMaxAns.min = minval;
                minMaxAns.minStr = "("+ leftRes.minStr + str.charAt(cut) + rightRes.minStr + ")";
            }

            if(maxval > minMaxAns.max)
            {
                minMaxAns.max = maxval;
                minMaxAns.maxStr = "("+ leftRes.maxStr + str.charAt(cut) + rightRes.maxStr + ")";
            }

        }
        return dp[si][ei]  = minMaxAns;
    }

    public static int minPlaindromicCut(String str,int si,int ei,int[][] dp,boolean[][] palindromicSubstring){
        if(palindromicSubstring[si][ei]) return 0;
        if(dp[si][ei] != -1) return dp[si][ei];
        int minCut=(int)1e8;
        
        for(int cut = si; cut < ei; cut++){
            int leftRes = minPlaindromicCut(str,si,cut,dp,palindromicSubstring);
            int rightRes = minPlaindromicCut(str,cut+1,ei,dp,palindromicSubstring);
            
            int myAns= leftRes + 1 + rightRes;
            minCut = Math.min(minCut, myAns);
        }

        return dp[si][ei]=minCut;
    }

    public static int minPlaindromicCut_bestMethod(String str,int si,int ei,int[] dp,boolean[][] palindromicSubstring){
        if(palindromicSubstring[si][ei]) return 0;
        if(dp[si] != -1) return dp[si];
        
        int minCut=(int)1e8;
        for(int cut = si; cut < ei; cut++){
            if(palindromicSubstring[si][cut]){
              minCut = Math.min(minCut,minPlaindromicCut_bestMethod(str,cut+1,ei,dp,palindromicSubstring)+1);
            }            
        }

        return dp[si]=minCut;
    }

    public static int minCut(String str) {
		int n = str.length();
		int[][] dp=new int[n][n];
		boolean[][] isPalindrome=new boolean[n][n];

		for(int[] d: dp) Arrays.fill(d,-1);

		for (int gap = 0; gap < n; gap++) {
			for (int si = 0, ei = gap; ei < n; si++, ei++) {
				if (gap == 0) isPalindrome[si][ei] = true;
				else if (str.charAt(si) == str.charAt(ei) && gap == 1) isPalindrome[si][ei] = true;
				else isPalindrome[si][ei] = str.charAt(si) == str.charAt(ei) && isPalindrome[si + 1][ei - 1];
			}
		}

		return minPlaindromicCut(str,0,n-1,dp,isPalindrome);
    }

    public int minScoreTriangulation(int[] arr,int si,int ei,int[][] dp)
    {
        if(ei-si<2)
            return 0;
        if(dp[si][ei]!=-1)
            return dp[si][ei];
        int ans = (int)1e8;
        for(int cut = si+1;cut<ei;cut++)
        {
            int leftAns = minScoreTriangulation(arr,si,cut,dp);
            int rightAns = minScoreTriangulation(arr,cut,ei,dp);
            int myAns = leftAns + arr[si]*arr[cut]*arr[ei] + rightAns;
            
            ans = Math.min(ans,myAns);
        }
        return dp[si][ei]=ans;
    }
    public int minScoreTriangulation(int[] A) {
        
        int[][] dp = new int[A.length][A.length];
        for(int[] d:dp)
            Arrays.fill(d,-1);
        return minScoreTriangulation(A,0,A.length-1,dp);
    }

    //leetcode wildcard
    public int isMatch(String str1,String str2,int n,int m,int[][] dp)
    {
        if(n==0 || m==0)
        {
            if(n==0 && m==0)
                return dp[n][m] = 1;
            else if(m==0) return dp[n][m] = 0;
            else
            { // n==0
                return dp[n][m] =(str2.charAt(m-1)=='*' && m-1==0)?1:0; 
            }
        }
        if(dp[n][m]!=-1) return dp[n][m];
        
        
        if(str1.charAt(n-1)==str2.charAt(m-1)) return dp[n][m] = isMatch(str1,str2,n-1,m-1,dp);
        else if(str2.charAt(m-1)=='?') return dp[n][m] = isMatch(str1,str2,n-1,m-1,dp);
        else if(str2.charAt(m-1)=='*')
        {
            boolean emptyMapping = isMatch(str1,str2,n,m-1,dp)==1;
            boolean sequenceMapping = isMatch(str1,str2,n-1,m,dp)==1;
            dp[n][m] = (emptyMapping||sequenceMapping)?1:0;
            return dp[n][m];
        }
        return dp[n][m] = 0;
    }
    public String removeStar(String p)
    {
        StringBuilder str = new StringBuilder();
        for(int i =0;i<p.length();i++)
        {
            if(p.charAt(i)=='*')
            {
                str.append('*');
                while(i<p.length() && p.charAt(i)=='*')
                    i++;
                i--;
            }
            else
                str.append(p.charAt(i));
        }
        return str.toString();
    }
    public boolean isMatch(String s, String p) {
        int[][] dp = new int[s.length()+1][p.length()+1];
        for(int[] d:dp)
            Arrays.fill(d,-1);
        p = removeStar(p);
        return isMatch(s,p,s.length(),p.length(),dp)==1;
    }

    // 


    public static void minMaxEval(){
        String str="1+2*3+4*5";
        int n=str.length();
        pair[][] dp=new pair[n][n];

        pair ans=minMaxEval(str,0,n-1,dp);
        System.out.println(ans.minStr + " -> "+ans.min);
        System.out.println(ans.maxStr + " -> "+ans.max);
        

        // for(pair[] d : dp){
        //     for(pair e : d){
        //         System.out.print(e + " ");
        //     }
        //     System.out.println();
        // }
    }

    // public static void MCM(){
    //     int[] arr={10, 20, 30, 40, 30};
    //     int n=arr.length;

    //     int[][] dp=new int[n][n];
    //     String[][] sdp=new String[n][n];
        
    //     // System.out.println(MCM_Rec(arr,0,n-1,dp));
    //     // System.out.println(MCM_DP(arr,0,n-1,dp));
    //     System.out.println(MCM_Rec_02(arr,0,n-1,dp,sdp));
        
    //     System.out.println(sdp[0][n-1]);
    //     display2D(dp);
    //     for(String[] s: sdp){
    //         for(String e:s) System.out.print(e+" ");
    //         System.out.println();
    //     }
    // }

    public static void main(String[] args){
        // MCM();
        minMaxEval();
       
    }
}