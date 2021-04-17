#include<iostream>
#include<vector>
using namespace std;

int n=3,m=3;
vector<vector<int>> dirA{{0, 1}, {-1, 0}, {0, -1}, {1, 0}};
vector<string> dirS{"R","U","L","D"};

int floodFillAlgo(int sr,int sc,int er,int ec,vector<vector<int>> &board,string ans)
{
    if(sr==er && sc ==ec)
    {
        cout<<ans<<endl;
        return 1;
    }
    board[sr][sc]=1;
    int count =0;
    for(int d =0;d<dirA.size();d++)
    {
        int r = sr + dirA[d][0];
        int c = sc + dirA[d][1];
        if (r >= 0 && c >= 0 && r <= er && c <= ec && board[r][c]==0)
        count+=floodFillAlgo(r,c,er,ec,board,ans+dirS[d]);
    }
    board[sr][sc] = 0; 
    return count;
}


int floodFillJumpAlgo(int sr,int sc,int er,int ec,vector<vector<int>> &board,string ans)
{
     if (sr == er && sc == ec)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    board[sr][sc] = 1; //mark visited
    for (int d = 0; d < dirA.size(); d++)
    {
        for (int rad = 1; rad <= max(n, m); rad++)
        {
            int r = sr + rad * dirA[d][0];
            int c = sc + rad * dirA[d][1];
            if (r >= 0 && c >= 0 && r <= er && c <= ec && board[r][c] == 0)
                count += floodFillJumpAlgo(r, c, er, ec, board, ans + dirS[d] + to_string(rad));
        }
    }

    board[sr][sc] = 0; //mark unvisited
    return count;
}

class floodFillPair
{
    public :
    string str = "";
    int len = 0;

    floodFillPair(string str, int len)
    {
        this->str = str;
        this->len = len;
    }
};

floodFillPair longestPath(int sr,int sc,int er,int ec,vector<vector<int>> &board)
{
    if(sr==er && sc ==ec)
    {
        floodFillPair base("",0);
        return base;
    }    
    floodFillPair myAns("",0);
    board[sr][sc] = 1;
    for(int d =0;d<dirA.size();d++)
    {
        for(int rad = 1;rad<=max(m,n);rad++)
        {
            int r = sr + rad*dirA[d][0];
            int c = sc + rad*dirA[d][1];

            if(r>=0 && c>=0 && r<=er && c<=ec && board[r][c]==0)
            {
                floodFillPair recAns = longestPath(r,c,er,ec,board);
                if(recAns.len + 1 > myAns.len)
                {
                    myAns.len = recAns.len+1;
                    myAns.str = dirS[d] + to_string(rad) + recAns.str;
                }
            }
        }
    }
    board[sr][sc]=0;
    return myAns;
}



void solve()
{
    int n=4,m=4;
    vector<vector<int>> board(n,vector<int>(m,0));
    // cout << floodFillAlgo(0, 0, n - 1, m - 1, board, "") << endl;
//    cout << floodFillJumpAlgo(0, 0, n - 1, m - 1, board, "") << endl;
    cout<<longestPath(0,0,n-1,m-1,board).str<<endl;
}  

int main()
{
    solve();
    return 0;
}

// best problem kb direction ka array lena h kb for loop se call lagana h
//https://practice.geeksforgeeks.org/problems/rat-maze-with-multiple-jumps/0#
void display(vector<vector<int>> & vis) {
        for (vector<int> &m : vis) {
            for (int ele : m)
                cout<<ele<<" ";
            cout<<endl;
        }
    }

bool floodfill(int sr,int sc,int dr,int dc,vector<vector<int>> &dirA,vector<vector<int>> &arr,vector<vector<int>> &vis){
    if(sr==dr && sc==dc){
        vis[sr][sc]=1;
        display(vis);
        vis[sr][sc]=0;
        return true;
    }
    vis[sr][sc]=1;
    bool res = false;
    for(int rad =1;rad<=arr[sr][sc];rad++){
        for(int i=0;i<dirA.size();i++){
            int r = sr + rad * dirA[i][0];
            int c = sc + rad * dirA[i][1];
            if(r<=dr && c<=dc && r>=0 && c>=0){
                res = res || floodfill(r,c,dr,dc,dirA,arr,vis);
            }
        }
    }
    vis[sr][sc]=0;
    return res;
    
    
}
int main()
 {
	//code
	int t;
	cin>>t;
	while(t-->0){
	    int n;
	    cin>>n;
	    vector<vector<int>> arr(n,vector<int>(n,0));
	    for(int i=0;i<n;i++)
	        for(int j=0;j<n;j++)
	            cin>>arr[i][j];
        
        if(arr[0][0]!=0){
            vector<vector<int>> dirA = {{0,1},{1,0}};
            vector<vector<int>> vis(n,vector<int>(n,0));
            bool res = floodfill(0,0,n-1,n-1,dirA,arr,vis);
            if(!res) cout<<-1<<endl;
        }
        else cout<<-1<<endl;
	    
	}
	return 0;
}