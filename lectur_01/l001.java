package lectur_01;

import java.util.Scanner;
import java.util.ArrayList;
public class l001{

    public static Scanner scn = new Scanner(System.in);

    public static void printDec(int a,int b)
    {
        if(a==b+1) return ;
        printDec(a+1, b);
        System.out.print(a+" ");
    }

    public static int firstIdx(int[] arr,int idx,int data){
        if(idx==arr.length) return -1;
        if(arr[idx]==data) return idx;
        return firstIdx(arr,idx+1,data);
    }

    public static int lastIdx(int[] arr,int idx,int data){
        if(idx==arr.length) return -1;
        int ans=lastIdx(arr,idx+1,data);
        if(ans!=-1) return ans;

        return arr[idx]==data?idx:-1;
    }

    public static int[] allIdx(int[] arr,int idx,int data,int count){
        if(idx==arr.length) return new int[count];
        if(arr[idx]==data) count++;
        
        int[] ans=allIdx(arr,idx+1,data,count);

        if(arr[idx]==data) ans[count-1] =idx;

        return ans;
    }

    public static int calls(int n) {  // n=5
        if (n <= 1) {
            System.out.println("base: " + n);
            return n + 1;
        }

        int count = 0;

        System.out.println("Pre: " + n);
        count += calls(n - 1);

        System.out.println("In: " + n);

        count += calls(n - 2);
        System.out.println("Post: " + n);

        return count + 3;
    }
    public static String[] words={":;/", "abc", "def", "ghi", "jkl", "mno",
    "pqrs", "tuv", "wxyz", "&*%", "#@$"};
    public static ArrayList<String> nokiaKeyPad(String str){
        if(str.length()==0) {
            ArrayList<String> base=new ArrayList<>();
            base.add("");
            return base;
        }

        char ch=str.charAt(0);
        String nstr=str.substring(1);

        String word=words[ch-'0'];
        ArrayList<String> smallAns=nokiaKeyPad(nstr);

        ArrayList<String> myAns=new ArrayList<>();
        for(String s: smallAns){
            for(int i=0;i<word.length();i++){
                myAns.add(word.charAt(i) + s);
            }
        }

        return myAns;
    }

    public static int nokiaKeyPad_02(String str,String ans){
        if(str.length()==0) {
            System.out.println(ans);
            return 1;
        }

        char ch=str.charAt(0);
        String nstr=str.substring(1);
        String word=words[ch-'0'];
        int count=0;

        for(int i=0;i<word.length();i++){
            count+=nokiaKeyPad_02(nstr,ans+word.charAt(i));
        }

        if(str.length() > 1){
            char ch2=str.charAt(1);
            int num = (ch-'0')*10 + (ch2-'0');
            if(num >= 10 && num <= 11){
                word=words[num];
                for(int i=0;i<word.length();i++){
                    count+=nokiaKeyPad_02(str.substring(2),ans + word.charAt(i));
                }
            }
        }
       
        return count;
    }

    public static int encoding(String str,String ans){
        if(str.length()==0) {
            System.out.println(ans);
            return 1;
        }

        char ch=str.charAt(0);
        String nstr=str.substring(1);
        int count=0;
        
        count+=encoding(nstr,ans+(char)('a'+ (ch-'0')));

        if(str.length() > 1){
            char ch2=str.charAt(1);
            int num = (ch-'0')*10 + (ch2-'0');
            if(num >= 10 && num <= 25){
                count+=encoding(str.substring(2),ans+ (char)('a'+ num));
            }
        }
       
        return count;
    }


    public static void solve(){
        System.out.println("Hi Pepecoders!!");
        int a = scn.nextInt();
        int b = scn.nextInt();
        printDec(a,b);
        System.out.println(nokiaKeyPad("589"));
        System.out.println(nokiaKeyPad_02("589",""));
        // int[][] coordinates = {{1,2},{3,4},{7,8}};
        // int[] a = coordinates[0];
        // int[] b = coordinates[1];
        // int x1 = a[0];
        // int y1 = a[1];
        // int x2 = b[0];
        // int y2 = b[1];
        // System.out.println(x1 +"    " + y1);
        // System.out.println(x2 +"    " + y2);

    }

    public static void main(String[] args){
        solve();
    }
}