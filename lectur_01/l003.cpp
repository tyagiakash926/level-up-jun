#include <iostream>
#include <vector>
using namespace std;

int permutationINFI(vector<int> &arr,int idx,int tar,string ans)
{
    if(tar==0)
    {
        cout<<ans<<endl;
        return 1;
    }
    int count =0;
    for(int i=idx;i<arr.size();i++)
    {
        if(tar-arr[i]>=0)
        count+=permutationINFI(arr,0,tar-arr[i],ans+to_string(arr[i])+" ");
    }
    return count;
}

int combinationINFI(vector<int> &arr,int idx,int tar,string ans)
{
    if(tar==0)
    {
        cout<<ans<<endl;
        return 1;
    }
    int count =0;
    for(int i=idx;i<arr.size();i++)
    {
        if(tar-arr[i]>=0)
        count+=combinationINFI(arr,i,tar-arr[i],ans+to_string(arr[i])+" ");
    }
    return count;
}

int combinationSingle(vector<int> &arr,int idx,int tar,string ans)
{
    if(tar==0)
    {
        cout<<ans<<endl;
        return 1;
    }
    int count =0;
    for(int i=idx;i<arr.size();i++)
    {
        if(tar-arr[i]>=0)
        count+=combinationSingle(arr,i+1,tar-arr[i],ans+to_string(arr[i])+" ");
    }
    return count;
}

int permutationSingle(vector<int> &arr,int idx,int tar,string ans)
{
    if(tar==0)
    {
        cout<<ans<<endl;
        return 1;
    }
    int count =0;
    for(int i=idx;i<arr.size();i++)
    {
        if(arr[i]>0 && tar-arr[i]>=0)
        {
            int temp = arr[i];
            arr[i]=-arr[i];
            count+=permutationSingle(arr,0,tar-temp,ans+to_string(temp)+" ");
            arr[i]=-arr[i];
        }
        
    }

    return count;
}

//=======================================================================================>

int combinationSingle_sub(vector<int> &arr,int idx,int tar,string ans)
{
    if(tar==0 || idx==arr.size())
    {
        if(tar==0)
        {
            cout<<ans<<endl;
            return 1;
        }
        return 0;
    }
    int count =0;
    if(tar-arr[idx]>=0)
        count+=combinationSingle_sub(arr,idx+1,tar-arr[idx],ans+to_string(arr[idx])+" ");
    count+=combinationSingle_sub(arr,idx+1,tar,ans);
    
    return count;
}



int combinationINFI_subseq(vector<int> &arr, int idx, int tar, string ans)
{
    if (tar == 0 || idx == arr.size())
    {
        if (tar == 0)
        {
            cout << ans << endl;
            return 1;
        }
        return 0;
    }

    int count = 0;
    if (tar - arr[idx] >= 0)
        count += combinationINFI_subseq(arr,idx , tar - arr[idx], ans + to_string(arr[idx]) + " ");
    count += combinationINFI_subseq(arr,idx+1, tar, ans);
    return count;
}

int permutationINFI_subseq(vector<int> &arr, int idx, int tar, string ans)
{
    if (tar == 0 || idx == arr.size())
    {
        if (tar == 0)
        {
            cout << ans << endl;
            return 1;
        }
        return 0;
    }

    int count = 0;
    if (tar - arr[idx] >= 0)
        count += permutationINFI_subseq(arr,0 , tar - arr[idx], ans + to_string(arr[idx]) + " ");
    count += permutationINFI_subseq(arr, idx + 1, tar, ans);
    return count;
}

int permutationSingleCoin_subseq(vector<int> &arr, int idx, int tar, string ans)
{
    if (tar == 0 || idx == arr.size())
    {
        if (tar == 0)
        {
            cout << ans << endl;
            return 1;
        }
        return 0;
    }

    int count = 0;

    if (arr[idx] >= 0 && tar - arr[idx] >= 0)
    {

        int temp = arr[idx];
        arr[idx] = -arr[idx];
        count += permutationSingleCoin_subseq(arr,0 , tar - temp, ans + to_string(temp) + " ");
        arr[idx] = -arr[idx];
    }

    count += permutationSingleCoin_subseq(arr, idx + 1, tar, ans);
    return count;
}
//================================================================================================>

int queenCombination_1D(vector<bool> &boxes,int tnq,int idx,int qpsf,string ans)
{
    if(tnq == qpsf)
    {
        cout<<ans<<endl;
        return 1;
    }
    int count=0;
    for(int i=idx;i<boxes.size();i++)
    {
        count+=queenCombination_1D(boxes,tnq,i+1,qpsf+1,ans+"("+"b"+to_string(i)+" "+"q"+to_string(qpsf)+")");
    }
    return count;
}

int queenCombination_1D_subseq(vector<bool> &boxes,int tnq,int idx,int qpsf,string ans)
{
    if(tnq==qpsf || idx==boxes.size())
    {
        if(tnq==qpsf)
        {
            cout<<ans<<endl;
            return 1;
        }
        return 0;
    }
    int count = 0;
    count+=queenCombination_1D_subseq(boxes,tnq,idx+1,qpsf+1,ans+"("+"b"+to_string(idx)+" "+"q"+to_string(qpsf)+")");
    count+=queenCombination_1D_subseq(boxes,tnq,idx+1,qpsf,ans);
    return count;
}

int queenPermutation_1D(vector<bool> &boxes,int tnq,int idx,int qpsf,string ans)
{
    if(tnq == qpsf)
    {
        cout<<ans<<endl;
        return 1;
    }
    int count =0;
    for(int i=idx;i<boxes.size();i++)
    {
        if(!boxes[i])
        {
            boxes[i]=true;
            count+=queenPermutation_1D(boxes,tnq,0,qpsf+1,ans + "b" + to_string(i) + "q" + to_string(qpsf) + " ");
            boxes[i]=false;
        }
        
    }

    return count;
}

int queenPermutation_1D_subseq(vector<bool> &boxes,int tnq,int idx,int qpsf,string ans)
{
    if (tnq == qpsf || idx == boxes.size())
    {
        if (tnq == qpsf)
        {
            cout << ans << endl;
            return 1;
        }
        return 0;
    }

    int count = 0;

    if (!boxes[idx])
    {

        boxes[idx]=true;
        count +=  queenPermutation_1D_subseq(boxes,tnq,0,qpsf+1,ans + "b" + to_string(idx) + "q" + to_string(qpsf) + " ");
        boxes[idx]=false;
    }

    count +=  queenPermutation_1D_subseq(boxes,tnq,idx+1,qpsf, ans);
    return count;
}
//====================================================================================

int queen_Combination_2D(vector<vector<bool>> &boxes,int tnq, int idx, int qpsf, string ans)
{
    if(tnq==qpsf)
    {
        cout<<ans<<endl;
        return 1;
    }
    int count=0;
    for(int i=idx;i<boxes.size()*boxes.size();i++)
    {
        int r = i/boxes[0].size();
        int c = i%boxes[0].size();
        count+=queen_Combination_2D(boxes,tnq,i+1,qpsf+1, ans + "(" + to_string(r) + "," + to_string(c) + ")");
    }
    return count;
}

int queenPermutation_2D(vector<vector<bool>> &boxes, int tnq, int idx, int qpsf, string ans) // tnq is equal to target.
{
    if (qpsf == tnq)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    for (int i = idx; i < boxes.size() * boxes[0].size(); i++)
    {
        int r = i / boxes[0].size();
        int c = i % boxes[0].size();
        if (!boxes[r][c])
        {
            boxes[r][c] = true;
            count += queenPermutation_2D(boxes, tnq, 0, qpsf + 1, ans + "b" + to_string(i) + "q" + to_string(qpsf) + " ");
            boxes[r][c] = false;
        }
    }
    return count;
}

//================================================================================================

bool isSafeToPlaceQueen(vector<vector<bool>> &boxes,int r,int c)
{
   // vector<vector<int>> dir{{0,-1},{-1,-1},{-1,0},{-1,1}};   //for combination
   vector<vector<int>> dir{{0,-1},{-1,-1},{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1}};
    for(int d=0;d<dir.size();d++)
    {
        for(int radius=1;radius<=boxes.size();radius++)
        {
            int x = r + radius*dir[d][0];
            int y = c + radius*dir[d][1];
            if(x>=0 && y>=0 && x<boxes.size() && y<boxes[0].size())
            {
                if(boxes[x][y])
                return false;
            }
        }
    }
    return true;
}

int Nqueen_01(vector<vector<bool>> &boxes,int tnq, int idx, string ans)
{
    if(tnq==0)
    {
        cout<<ans<<endl;
        return 1;
    }
    int count=0;
    for(int i=idx;i<boxes.size()*boxes[0].size();i++)
    {
        int r = i/boxes[0].size();
        int c = i%boxes[0].size();
        if(isSafeToPlaceQueen(boxes,r,c))
        {
            boxes[r][c]=true;
            count+=Nqueen_01(boxes,tnq-1,i+1, ans + "(" + to_string(r) + "," + to_string(c) + ") ");
            boxes[r][c]=false;
        }
    }

    return count;
}

int Nqueen_02(vector<vector<bool>> &boxes, int tnq, int idx, string ans) // tnq is equal to target.
{
    if (tnq==0)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    for (int i = idx; i < boxes.size() * boxes[0].size(); i++)
    {
        int r = i / boxes[0].size();
        int c = i % boxes[0].size();
        if (!boxes[r][c] && isSafeToPlaceQueen(boxes,r,c))
        {
            boxes[r][c] = true;
            count += Nqueen_02(boxes, tnq-1, 0,  ans + "(" + to_string(r) + "," + to_string(c) + ") ");
            boxes[r][c] = false;
        }
    }
    return count;
}

vector<bool> rowA;
vector<bool> colA;
vector<bool> diagA;
vector<bool> adiagA;

int Nqueen_03(vector<vector<bool>> &boxes,int tnq, int idx, string ans)
{
    if(tnq==0)
    {
        cout<<ans<<endl;
        return 1;
    }
    int count=0;
    int n = boxes.size();
    int m = boxes[0].size();
    for(int i=idx;i<boxes.size()*boxes[0].size();i++)
    {
        int r = i/boxes[0].size();
        int c = i%boxes[0].size();
        if(!rowA[r] && !colA[c] && !diagA[r+c] && !adiagA[r-c+m-1])
        {
            rowA[r]=true;
            colA[c]=true;
            diagA[r+c]=true;
            adiagA[r-c+m-1]=true;
            count+=Nqueen_03(boxes,tnq-1,i+1, ans + "(" + to_string(r) + "," + to_string(c) + ") ");
            rowA[r]=false;
            colA[c]=false;
            diagA[r+c]=false;
            adiagA[r-c+m-1]=false;
        }
    }

    return count;
}

int calls=0;

int Nqueen_04(int n,int m,int tnq, int r, string ans)
{
    if(tnq==0)
    {
        cout<<ans<<endl;
        return 1;
    }
    int count=0;
    calls++;
    for(int c=0;c<m;c++)
    {
    
        if(!rowA[r] && !colA[c] && !diagA[r+c] && !adiagA[r-c+m-1])
        {
            rowA[r]=true;
            colA[c]=true;
            diagA[r+c]=true;
            adiagA[r-c+m-1]=true;
            count+=Nqueen_04(n,m,tnq-1,r+1, ans + "(" + to_string(r) + "," + to_string(c) + ") ");
            rowA[r]=false;
            colA[c]=false;
            diagA[r+c]=false;
            adiagA[r-c+m-1]=false;
        }
    }

    return count;
}

int row = 0;
int col = 0;
int diag = 0;
int adiag = 0;

int Nqueen_05(int n, int m, int tnq, int r, string ans) // tnq is equal to target.
{
    if (tnq == 0)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    calls++;

    for (int c = 0; c < m; c++)
    {
        if ((row & (1 << r)) == 0 && (col & (1 << c)) == 0 && (diag & (1 << (r + c))) == 0 && (adiag & (1 << (r - c + m - 1))) == 0)
        {
            row ^= (1 << r);
            col ^= (1 << c);
            diag ^= (1 << (r + c));
            adiag ^= (1 << (r - c + m - 1));

            count += Nqueen_05(n, m, tnq - 1, r + 1, ans + "(" + to_string(r) + "," + to_string(c) + ") ");

            row ^= (1 << r);
            col ^= (1 << c);
            diag ^= (1 << (r + c));
            adiag ^= (1 << (r - c + m - 1));
        }
    }
    return count;
}

//=====================================================================================


void Nqueen()
{
    int n = 4;
    int m = 4;

    vector<vector<bool>> boxes(n, vector<bool>(m, 0));
    int tnq = n;
    //cout<<Nqueen_01(boxes,tnq,0,"")<<endl;
    //cout<<Nqueen_02(boxes,tnq,0,"")<<endl;
    rowA.resize(n,false);
    colA.resize(m,false);
    diagA.resize(m+n-1,false);
    adiagA.resize(m+n-1,false);
    //cout<<Nqueen_03(boxes,tnq,0,"")<<endl;
    cout<<Nqueen_04(n,m,tnq,0,"")<<endl;
    cout<<calls<<endl;
    

}

void queen_Permutation_Combination()
{
    //vector<bool> boxes(5, false);
    //int tnq = 3;
    //cout << queenCombination_1D(boxes, tnq, 0, 0, "") << endl;
    //cout << queenCombination_1D_subseq(boxes, tnq, 0, 0, "") << endl;
    // cout << queenPermutation_1D(boxes, tnq, 0, 0, "") << endl;
    // cout << queenPermutation_1D_subseq(boxes, tnq, 0, 0, "") << endl;
    //vector<vector<bool>> boxes(4, vector<bool>(4, false));
    //int tnq = 4;
    //cout<<queen_Combination_2D(boxes,tnq,0,0,"")<<endl;
    //cout << queenPermutation_2D(boxes, tnq, 0, 0, "") << endl;

} 
void coin_Permutation_Combination()
{
     vector<int> arr{1,1,1,1,1,-1,-1,-1,-1,-1};
     int tar =3;
    //cout<<permutationINFI(arr,0,tar,"")<<endl;
    //cout<<combinationINFI(arr,0,tar,"")<<endl;
   cout<<combinationSingle(arr,0,tar,"")<<endl;
    // cout<<permutationSingle(arr,0,tar,"")<<endl;
    //cout<<combinationSingle_sub(arr,0,tar,"")<<endl;
    //cout<<combinationINFI_subseq(arr,0,tar,"")<<endl;
}
void solve()
{
     coin_Permutation_Combination();
    //   queen_Permutation_Combination();
    //Nqueen();
}
int main()
{
    solve();
    return 0;
}