public class l002{
    public static int compareTo(int[] arr, boolean isMax, int a, int b)
{
    if (isMax)
        return arr[a] - arr[b];
    else
        return arr[b] - arr[a];
}

public static void downHeapify(int[] arr,int pi, boolean isMax,int n)
{
    int maxidx = pi;
    int lci = 2 * pi + 1;
    int rci = 2 * pi + 2;

    if (lci <= n && compareTo(arr,isMax,lci, maxidx)>0)
        maxidx = lci;
    if (rci <= n && compareTo(arr,isMax,rci, maxidx)>0)
        maxidx = rci;

    if (maxidx != pi)
    {
        swap(arr,pi,maxidx);
        downHeapify(arr,maxidx,isMax,n);
    }
}

public static void swap(int[] arr,int a,int b){
    int temp = arr[a];
    arr[a] = arr[b];
    arr[b] = temp;
}

public static void solve()
{
    int[] arr = {10, 20, 30, -2, -3, -4, 5, 6, 7, 8, 9, 22, 11, 13};
    int n = arr.length;
    boolean isMax = true;
    
    for (int i = n - 1; i >= 0; i--)
    {
        downHeapify(arr,i,isMax,n-1);
    }

    int i = n-1;
    while(i>=0){
        swap(arr,0,i--);
        downHeapify(arr,0,isMax,i);
    }
    
    for(int ele: arr)
    System.out.print(ele+" ");
}

public static void main(String[] args)
{
    solve();
}

}
