
  
import java.util.Scanner;
import java.util.ArrayList;
public class memory{
    
    public static Scanner scn=new Scanner(System.in);
    
    public static void subseq(String str,ArrayList<String> sa,ArrayList<ArrayList<String>> ans){
        if(str.length()==0){
            ArrayList<String> s=new ArrayList<>(sa);  //deep copy.
            ans.add(s);//shalow copy.
            return;
        }

        subseq(str.substring(1),sa,ans);

        sa.add(str.charAt(0)+"");
        subseq(str.substring(1),sa,ans);
        sa.remove(sa.size()-1);
    }

    public static void subseq(){
        ArrayList<ArrayList<String>> ans=new ArrayList<>();
        ArrayList<String> sa=new ArrayList<>();
        
        subseq("abc",sa,ans);
        
        System.out.println(ans);
    }


    public static void display(int[] arr)
    {
        for (int ele : arr)
            System.out.print(ele + " ");
        System.out.println();
    }
    
    public static void input(int[] arr)
    {
        for (int i = 0; i < arr.length; i++)
        {
           arr[i]=scn.nextInt();
        }
        display(arr);
    }

    public static void per(int[] arr,int idx,boolean[] visited,String ans)
    {
        if(idx==arr.length)
        {
            System.out.println(ans);
            return;
        }
        for(int i=0;i<arr.length;i++)
        {
            if(visited[i]==false)
            {
                visited[i]=true;
                per(arr,idx+1,visited,ans+arr[i]+",");
                visited[i]=false;
            }
        }
    }
    
    public static void solve()
    {
        // int[] arr=new int[10];
        // display(arr);
        // input(arr);
        // display(arr);
        //subseq();
        int[] arr=new int[3];
        for(int i=0;i<3;i++)
        {
            arr[i]=scn.nextInt();
        }
        boolean[] visited=new boolean[3];
        per(arr,0,visited,"");
    }

    public static void main(String[] args){
        solve();
    }
}
