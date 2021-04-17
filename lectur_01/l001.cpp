#include<iostream>
#include<vector>
using namespace std;

void printInc(int a,int b)
{
    if(a==b+1)
    return;
    cout<<a<<" ";
    printInc(a+1,b);
}

vector<string> subseq(string str)
{
    if(str.length()==0)
    {
        vector<string> base;
        base.push_back("");
        return base;
    }
    char ch = str[0];
    string nstr = str.substr(1);
    vector<string> recAns = subseq(nstr);
    vector<string> myAns;
    for(string s : recAns)
    {
        myAns.push_back(s);
        myAns.push_back(ch+s);
    }
    return myAns;
}

void solve()
{
    //cout<<"Hi Coder!"<<endl;
    // int a,b;
    // cin>>a>>b;
    // printInc(a,b);
    string str;
    cin>>str;
    vector<string> ans = subseq(str);
    for(string s : ans)
    {
        cout<<s<<" ";
    }
}
int main()
{
    solve();
    return 0;
}