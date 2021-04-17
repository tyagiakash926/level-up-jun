import java.util.Scanner;
public class l001{

    public static void abbervation(String str, String asf,int count){
        //write your code here
        if(str.length()==0){
            if(count==0){
                System.out.println(asf);
            }else{
                System.out.println(asf+count);
            }
            return;
        }
        if(count>0)
            abbervation(str.substring(1),asf+ count + str.charAt(0),0);
        else
            abbervation(str.substring(1),asf+str.charAt(0),0);
        
        abbervation(str.substring(1),asf,count+1);
    }


    public static void solve(){
        abbervation("pep","",0);
    }

	public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        solve();
        
    }
}