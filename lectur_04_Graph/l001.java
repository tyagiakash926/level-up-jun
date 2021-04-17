import java.util.ArrayList;
public class l001{
    public static void main(String[] args) {
        solve();
    }

    public static class Edge{
        int v;
        int w;
        Edge(int v,int w)
        {
            this.v=v;
            this.w=w;
        }
    }
    static int N = 7;
    static ArrayList<Edge>[] graph = new ArrayList[N];

    public static void addEdge(int u, int v, int w) {
        graph[u].add(new Edge(v, w));
        graph[v].add(new Edge(u, w));
    }

    public static void display() {
        for (int i = 0; i < N; i++) {
            System.out.print(i + " -> ");
            for (Edge e : graph[i])
                System.out.print("(" + e.v + ", " + e.w + ") ");
            System.out.println();
        }
    }

    public static void constructor() {
        // Arrays.fill(graph, new ArrayList<Edge>());
        for (int i = 0; i < N; i++) {
            ArrayList<Edge> ar = new ArrayList<>();
            graph[i] = ar;
        }

        addEdge(0, 1, 10);
        addEdge(0, 3, 10);
        addEdge(1, 2, 10);
        addEdge(2, 3, 40);
        addEdge(3, 4, 2);
        addEdge(4, 5, 2);
        addEdge(4, 6, 8);
        addEdge(5, 6, 3);

        display();
    }

    public static int findVtx(int u,int v)
    {
        int idx=-1;
        for(int i=0;i<graph[u].size();i++)
        {
            Edge e = graph[u].get(i);
            if(e.v==v)
            {
                idx=i;
                break;
            }
        }
        return idx;
    }

    public static void removeEdge(int u,int v)
    {
        int idx1 = findVtx(u,v);
        int idx2 = findVtx(v,u);
        graph[u].remove(idx1);
        graph[v].remove(idx2);
    }

    public static void solve() {
        constructor();
        //boolean[] vis = new boolean[N];
        //System.out.println(hasAllPath(0, 6, 0, vis, ""));
        // AllPath(0, 6, 0, vis, "");
        // System.out.println(hwp + " @ " + hw);
        // System.out.println(lwp + " @ " + lw);
    }
}