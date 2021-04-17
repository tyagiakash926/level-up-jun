#include<iostream> 
#include<vector>
#include<queue>
#include<stack>

using namespace std;

class Edge
{
public:
    int v;
    int w;
    Edge(int v,int w)
    {
        this->v = v;
        this->w = w;
    }
};
int N;
vector<vector<Edge>> graph;

void addEdge(int u,int v,int w)
{
    graph[u].push_back(Edge(v,w));
    graph[v].push_back(Edge(u,w));
}

void display()
{
    for(int i=0;i<graph.size();i++)
    {
        cout<<i<<" -> ";
        for(Edge e:graph[i])
            cout << "(" << e.v << ", " << e.w << ") ";
        cout<<endl;
    }
}

int searchVrtx(int u,int v)
{
    int idx =-1;
    for(int i=0;i<graph[u].size();i++)
    {
        Edge e = graph[u][i];
        if(e.v==v)
        {
            idx=i;
            break;
        }
    }
    return idx;
}

void removeEdge(int u,int v)
{
    int idx =-1;
    idx = searchVrtx(u, v);
    graph[u].erase(graph[u].begin() + idx);

    idx = searchVrtx(v,u);
    graph[v].erase(graph[v].begin()+idx);
}

void removeVrtx(int u)
{
    int size = graph[u].size();
    while(size-->0)
    {
        Edge e = graph[u].back();
        removeEdge(u,e.v);
    }
}

bool hasPath(int src,int dest,vector<bool>& vis)
{
    if(src==dest)
        return true;
    vis[src]=true;
    bool res = false;
    for(Edge e : graph[src])
    {
        if(!vis[e.v])
        {
            res = res || hasPath(e.v,dest,vis);
        }
    }
    return res;
}

int allPath(int src,int dest,string path,vector<bool> &vis)
{
    if(src==dest)
    {
        cout<<path<<" "<<to_string(src)<<endl;
        return 1;
    }
    vis[src]=true;
    int count=0;
    for(Edge e: graph[src])
    {
        if(!vis[e.v])
        {
            count+=allPath(e.v,dest,path+to_string(src)+" ",vis);
        }
    }
    vis[src]=false;
    return count;
}

void preorder(int src, string path, vector<bool> &vis)
{
    vis[src] = true;
    cout << to_string(src) + " @ " + path + to_string(src) << endl;
    for (Edge e : graph[src])
        if (!vis[e.v])
            preorder(e.v, path + to_string(src) + " ", vis);

    vis[src] = false;
}

class pathpair{
public:
    int len = 0;
    string path = "";
    pathpair(int a,string b)
    {
        len = a;
        path = b;
    } 
};

pathpair smallestPath(int src, int dest, vector<bool> &vis)
{
    if(src==dest)
    {
        return pathpair(0,to_string(src));
    }
    vis[src]=true;
    pathpair myAns = pathpair(1e8,"");
    for(Edge e : graph[src])
    {
        if(!vis[e.v])
        {
            pathpair recAns = smallestPath(e.v,dest,vis);
            if(recAns.len + 1 < myAns.len)
            {
                myAns.len = recAns.len+1;
                myAns.path = to_string(src)+" "+recAns.path;
            }
        }
    }
    vis[src]=false;
    return myAns;
}
pathpair LongestPath(int src, int dest, vector<bool> &vis)
{
    if(src==dest)
    {
        return pathpair(0,to_string(src));
    }
    vis[src]=true;
    pathpair myAns = pathpair(0,"");
    for(Edge e : graph[src])
    {
        if(!vis[e.v])
        {
            pathpair recAns = LongestPath(e.v,dest,vis);
            if(recAns.len + 1 > myAns.len)
            {
                myAns.len = recAns.len+1;
                myAns.path = to_string(src)+" "+recAns.path;
            }
        }
    }
    vis[src]=false;
    return myAns;
}

// ksmallest global wala method

int ksmall;

void KthSmallest_(int src,int dest,int weight,int floor,vector<bool>& vis)
{
    if(src==dest)
    {
        if(weight>floor)
        {
            ksmall = min(ksmall,weight);
        }
    }
    vis[src] = true;
    for(Edge e: graph[src])
    {
        if(!vis[e.v])
        {
            KthSmallest_(e.v,dest,weight+e.w,floor,vis);
        }
    }
    vis[src] = false;
}

void KthSmallest(int src, int dest, int k)
{
    int floor = -1e8;
    vector<bool> vis(N,false);
    while(k-->0)
    {
        ksmall = 1e8; 
        KthSmallest_(src, dest, 0, floor, vis);
        floor = ksmall;
    }
    cout<<floor;
}

int hamintoninPath(int src, int osrc, int noEdge, vector<bool> &vis, string path)
{
    if(noEdge==N-1)
    {
        int idx = searchVrtx(src,osrc);
        path +=to_string(src);
        if(idx!=-1)
        {
            cout << "Cycle : " << path << endl;
        }
        else
        {
            cout << "Non Cycle : " << path << endl;
        }
        return 1;
    }
    vis[src]=true;
    int count =0;
    for(Edge e:graph[src])
    {
        if(!vis[e.v])
        {
            count+=hamintoninPath(e.v,osrc,noEdge+1,vis,path+" "+to_string(src)+" ");
        }
    }
    vis[src]=false;
    return count;
}
// bfs 
void BFS_01(int src,vector<bool>& vis)
{
    queue<int> que;
    que.push(src);
    int dest =6;
    while(que.size()!=0)
    {
        int rvtx = que.front();
        que.pop();

        vis[rvtx] = true;
        for(Edge e : graph[rvtx])
        {
            if(!vis[e.v])
                que.push(e.v);
        } 
    }
}

void BFS_02(int src,vector<bool>& vis)
{
    queue<int> que;
    que.push(src);
    int dest = 6;
    int level = 0;
    while(que.size()!=0)
    {
        int size = que.size();
        while(size-->0)
        {
            int rvtx = que.front();
            que.pop();
            if(vis[rvtx])
            {
                continue;
            }
            vis[rvtx] = true;
            for(Edge e : graph[rvtx])
            {
                if(!vis[e.v])
                {
                    que.push(e.v);
                }
            } 
        }
        level++;
    } 
}

void BFS_03(int src, vector<bool> &vis)
{
    queue<int> que;
    que.push(src);
    int desti = 6;

    int level = 0;
    vis[src] = true;
    while (que.size() != 0)
    {
        int size = que.size();
        while (size-- > 0)
        {
            int rvtx = que.front();
            que.pop();

            for (Edge e : graph[rvtx])
            {
                if (!vis[e.v])
                {
                    vis[e.v] = true;
                    que.push(e.v);
                }
            }
        }
        level++;
    }
}

void GCC_UsingBFS()
{
    vector<bool> vis(N, false);
    int count = 0;
    for (int i = 0; i < N; i++)
    {
        if (!vis[i])
        {
            BFS_03(i, vis);
            count++;
        }
    }
}

void BFS()
{

    vector<bool> vis(N, false);
    BFS_02(0, vis);
}

// topological sort
void topoDfs(int src,vector<bool> &vis, stack<int>& st)
{
    vis[src]=true;
    for(Edge e: graph[src])
    {
        if(!vis[e.v])
            topoDfs(e.v,vis,st);
    }
    st.push(src);
}

void topologicalSort()
{
    vector<bool> vis(N,false); // graph u,v ka edge rhege lekin v se u ka comment kr dena kyuki topologicalsort only works on directed graph
    stack<int> st;
    for(int i=0;i<N;i++)
    {
        if(!vis[i])
        {
            topoDfs(i,vis,st);
        }
    }
}

void kahnsAlgo()
{
    vector<int> indegree(N,0);
    for(int i=0;i<N;i++)
    {
        for(Edge e : graph[i])
        {
            indegree[e.v]++;
        }
    }
    queue<int> que;
    vector<int> ans;
    for(int i=0;i<N;i++)
    {
        if(indegree[i]==0)
            que.push(i);
    }

    while(que.size()!=0)
    {
        int rvtx = que.front();
        que.pop();

        ans.push_back(rvtx);
        for (Edge e : graph[rvtx])
        {
            if (--indegree[e.v] == 0)
                que.push(e.v);
        }
    }
     if (ans.size() != N)
        cout << "There is a Cycle:" << endl;
    else
    {
        for (int ele : ans)
            cout << ele << " ";
    }
}

//Kosaraju Algo for -> stronglyconnected components
void addEdge2(int u, int v, int w)
{
    graph[u].push_back(Edge(v, w));
}

void dfs(int src,vector<bool>& vis,vector<vector<Edge>>& graph,vector<int> &res)
{
    vis[src]=true;
    for(Edge e:graph[src])
    {
        if(!vis[e.v])
            dfs(e.v,vis,graph,res);
    }
    res.push_back(src);
}

void KosaRajuAlgoFor_SCC()
{
    vector<bool> vis(N,false);
    vector<int> res;
    for(int i=0;i<N;i++)
    {
        if(!vis[i])
            dfs(i,vis,graph,res);
    }

    vector<vector<Edge>> gp(N, vector<Edge>());
    for (int i = 0; i < N; i++)
        for (Edge e : graph[i])
            gp[e.v].push_back(Edge(i, e.w));

    for (int i = 0; i < N; i++)
        vis[i] = false;
    int count = 1;
    vector<int> vtx;

    for (int i = res.size() - 1; i >= 0; i--)
        if (!vis[res[i]])
        {
            dfs(res[i], vis, gp, vtx);
            cout << count++ << " -> ";
            while (vtx.size() != 0)
            {
                cout << vtx.back() << ", ";
                vtx.pop_back();
            }
            cout << endl;
        }

}

void constructDirectedGraph()
{
    N = 11;
    graph.resize(N, vector<Edge>());

    addEdge2(0, 5, 10);
    addEdge2(6, 0, 10);
    addEdge2(5, 6, 40);

    addEdge2(6, 8, 10);

    addEdge2(8, 9, 10);
    addEdge2(9, 10, 2);
    addEdge2(10, 8, 2);

    addEdge2(10, 7, 10);

    addEdge2(7, 3, 8);
    addEdge2(3, 1, 3);
    addEdge2(1, 2, 8);
    addEdge2(2, 7, 3);

    addEdge2(2, 4, 8);

    KosaRajuAlgoFor_SCC();
}

void constructor()
{
    // N=7;
    // graph.resize(N,vector<Edge>());
    // for (int i = 0; i < N; i++)
    // {
    //     vector<Edge> g;
    //     graph.push_back(g);
    // }
    // addEdge(0, 1, 10);
    // addEdge(1, 2, 10);
    // addEdge(2, 3, 40);
    // addEdge(3, 0, 10);
    // addEdge(3, 4, 2);
    // addEdge(4, 5, 2);
    // addEdge(4, 6, 8);
    // addEdge(5, 6, 3);
    

    //addEdge(0, 6, 10);

    // display();
    // removeEdge(4,5);
    // display();
    // removeVrtx(4);
    // display();
    // vector<bool> vis(N,false);
    // cout<<allPath(0,6,"",vis);
    //preorder(0,"",vis);
    // pathpair myAns = smallestPath(0,6,vis);
    // cout<<myAns.len <<"     "<<myAns.path<<endl;

    // pathpair myAns_ = LongestPath(0,6,vis);
    // cout<<myAns_.len <<"     "<<myAns_.path<<endl;

    // KthSmallest(0,6,2);

//     cout<<hamintoninPath(0,0,0,vis,"")<<endl;
    constructDirectedGraph();
}



void solve()
{
    constructor();
    BFS();
}
int main()
{
    solve();
    return 0;
}