#include <iostream>
#include <vector>
using namespace std;

int n, m;
vector<vector<int>> dirA{{0, 1}, {-1, 0}, {0, -1}, {1, 0}};

//Leetcode 980:========================================================

int uniquePathsIII_(int sr, int sc, int freeCell, vector<vector<int>> &grid)
{
    if (freeCell == 1)
        return grid[sr][sc] == 2 ? 1 : 0;
    int count = 0;
    int temp = grid[sr][sc];
    grid[sr][sc] = -1; //mark visited
    for (int d = 0; d < dirA.size(); d++)
    {
        int r = sr + dirA[d][0];
        int c = sc + dirA[d][1];
        if (r >= 0 && c >= 0 && r < n && c < m && grid[r][c] >= 0)
            count += uniquePathsIII_(r, c, freeCell - 1, grid);
    }

    grid[sr][sc] = temp; //mark unvisited
    return count;
}

int uniquePathsIII(vector<vector<int>> &grid)
{
    n = grid.size();
    m = grid[0].size();

    int sr, sc, freeCell = 0;
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            if (grid[i][j] != -1)
                freeCell++;

            if (grid[i][j] == 1)
            {
                sr = i;
                sc = j;
            }
        }
    }

    return uniquePathsIII_(sr, sc, freeCell, grid);
}

//Leetcode 1219:=========================================================

int getMaximumGold(int sr, int sc, vector<vector<int>> &grid)
{

    grid[sr][sc] = -grid[sr][sc];
    int maxGold = 0;
    for (int d = 0; d < dirA.size(); d++)
    {
        int r = sr + dirA[d][0];
        int c = sc + dirA[d][1];
        if (r >= 0 && c >= 0 && r < n && c < m && grid[r][c] > 0)
            maxGold = max(maxGold, getMaximumGold(r, c, grid));
    }

    grid[sr][sc] = -grid[sr][sc];
    return maxGold + grid[sr][sc];
}

int getMaximumGold(vector<vector<int>> &grid)
{
    n = grid.size();
    m = grid[0].size();
    int maxGold = 0;
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            if (grid[i][j] != 0)
            {
                maxGold = max(maxGold, getMaximumGold(i, j, grid));
            }
        }
    }
    return maxGold;
}

// Leetcode 191: ========================================================================
int OffOnBits(int x, int k)
{
    int mask = (1 << k);
    return (x | mask);
}

int onOffBits(int x, int k)
{
    int mask = (~(1 << k));
    return (x & mask);
}

int noOfOneBits_01(int x)
{
    int count = 0;
    for (int i = 0; i < 32; i++)
    {
        int mask = 1 << i;
        if ((x & mask) != 0)
            count++;
    }
    return count;
}

int noOfOneBits_02(int x)
{
    int count = 0;
    int t = 0;
    while (x != 0 && t < 32)
    {
        if ((x & 1) != 0)
            count++;
        x >>= 1;
        t++;
    }

    return count;
}

int noOfOneBits_03(int x)
{
    int count = 0;
    while (x != 0)
    {
        count++;
        x &= (x - 1);
    }

    return count;
}
 // Leetcode 136:================================================================
 int singleNumber(vector<int> &nums)
{
    int ans = 0;
    for (int ele : nums)
    {
        ans ^= ele;
    }
    return ans;
}

//Leetcode 231 : power of two.=========================================

bool isPowerOfTwo(int n)
{
    return n != 0 && (n & (n - 1)) == 0;
}

bool isPowerOfFour(int num)
{
    if (num == 0 || (num & (num - 1) != 0))
        return false;

    int count = 0;
    int bits = 0;
    while (num > 1 && bits <= 32)
    {
        num >>= 1;
        bits++;
        count++;
    }

    return (count & 1) == 0;
}

//Leetcode 137: single number ii.

int singleNumber_BekarSolution(vector<int> &nums)
{
    // unordered_map<int, int> map;
    // for (int ele : nums)
    //     map[ele]++;
    // for (pair<int, int> a : map)
    // {
    //     if (a.second == 1)
    //         return a.first;
    // }
    // return -1;
}

int singleNumber(vector<int> &nums)
{
    int singleNumber = 0;
    for (int i = 0; i < 32; i++)
    {
        int mask = 1 << i;
        int count = 0;
        for (int ele : nums)
        {
            if ((mask & ele) != 0)
                count++;
        }

        if (count % 3 != 0)
            singleNumber |= mask;
    }

    return singleNumber;
}

//https://practice.geeksforgeeks.org/problems/rat-in-a-maze-problem/1#
vector<vector<int>> dirA = {{-1,0},{0,1},{1,0},{0,-1}};
vector<string> move = {"U","R","D","L"};
    
    void mazepath(vector<vector<int>> &arr,vector<vector<bool>> &vis,int sr,int sc,int dr,int dc, int n,string psf,vector<string> &ans){
        if(sr==dr && sc==dc){
            ans.push_back(psf);
            return ;
        }
        vis[sr][sc] = true;
        for(int i=0;i<dirA.size();i++){
            int r = sr + dirA[i][0];
            int c = sc + dirA[i][1];
            if(r>=0 && c>=0 && r<=dr && c<=dc && !vis[r][c] && arr[r][c]==1){
                mazepath(arr,vis,r,c,dr,dc,n,psf+move[i],ans);
            }
        }
        vis[sr][sc]=false;
        
    }
    vector<string> findPath(vector<vector<int>> &arr, int n) {
        // Your code goes here
        if(arr[0][0]==0 || arr[n-1][n-1]==0) return {"-1"};
        vector<vector<bool>> vis(n,vector<bool>(n,false));
        vector<string> ans;
        mazepath(arr,vis,0,0,n-1,n-1,n,"",ans);
        sort(ans.begin(), ans.end());
        return ans;
    }

    //
    void mazepath(vector<vector<int>> &arr,vector<vector<bool>> &vis,int sr,int sc,int dr,int dc, int n,string psf,vector<string> &ans){
        if(sr==dr && sc==dc){
            ans.push_back(psf);
            return ;
        }
        arr[sr][sc] = -arr[sr][sc];
        for(int i=0;i<dirA.size();i++){
            for(int rad=1;rad<=arr[r][c];rad++){
                int r = sr +rad * dirA[i][0];
                int c = sc +rad * dirA[i][1];
                if(r>=0 && c>=0 && r<=dr && c<=dc && arr[r][c]>0){
                    mazepath(arr,vis,r,c,dr,dc,n,psf+move[i],ans);
                }
            }
        }
        arr[sr][sc] = -arr[sr][sc];
        
    }
    vector<string> findPath(vector<vector<int>> &arr, int n) {
        // Your code goes here
        if(arr[0][0]==0 || arr[n-1][n-1]==0) return {"-1"};
        vector<vector<bool>> vis(n,vector<bool>(n,false));
        vector<string> ans;
        mazepath(arr,vis,0,0,n-1,n-1,n,"",ans);
        sort(ans.begin(), ans.end());
        return ans;
    }

    // longest path && shortest path
    