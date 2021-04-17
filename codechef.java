import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
public class codechef
{
    
    public static void checkString(String s){
        int n = s.length();
        if(n<10){
            System.out.println("NO");
            return;
        }
        int lcount=0,ucount=0,digit=0,scount=0,faltu=0;
        for(int i=0;i<n;i++){
            char ch = s.charAt(i);
            if(ch>='A' && ch<='Z'){
                if(i==0 || i==n-1)
                ucount++;
            } 
            else if(ch>='a' && ch<='z') lcount++;
            else if(ch>='0' && ch<='9') digit++;
            else if(ch=='@' || ch=='#' || ch=='%' || ch=='&' || ch=='?') scount++;
            else faltu++;
        }
        if(lcount>=1 && ucount>=1 && digit>=1 && scount>=1 && faltu==0) 
            System.out.println("YES");
        else System.out.println("NO");
    }




    public static Scanner scn = new Scanner(System.in);
	public static void main (String[] args) throws java.lang.Exception
	{
		// your code goes here
		
		int t=scn.nextInt();
		while(t-->0){
		    String s = scn.next();
		    checkString(s);
        }
        
        // while(t-->0){
        //     int l=scn.nextInt();
        //     int r=scn.nextInt();
        //     System.out.println(((r-l+1)*(r-l+1+1))/2); 
        // }
	}
}
