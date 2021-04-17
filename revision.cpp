#include<iostream>
#include<vector>
#include <algorithm>
#include <queue>
#include<string>

using namespace std;
// int coinChangePermutationInfi(vector<int> &arr,int tar,string ans){
//     if(tar==0){
//         cout<<ans<<endl;
//         return 1;
//     }
//     int count =0;
//     for(int i=0;i<arr.size();i++){
//         if(tar-arr[i]>=0) count += coinChangePermutationInfi(arr,tar-arr[i],ans+to_string(arr[i]));
//     }
//     return count;
// }
// int coinChangeCombinationInfi(vector<int> &arr,int tar,int idx,string ans){
//     if(tar==0||idx==arr.size()){
//         if(tar==0){
//             cout<<ans<<endl;
//             return 1;
//         }
//         return 0;
//     }
//     int count =0;
//     for(int i=idx;i<arr.size();i++){
//         if(tar-arr[i]>=0) count += coinChangeCombinationInfi(arr,tar-arr[i],i,ans+to_string(arr[i]));
//     }
//     return count;
// }
// int coinChangeCombinationSingle(vector<int> &arr,int tar,int idx,string ans){
//     if(tar==0||idx==arr.size()){
//         if(tar==0){
//             cout<<ans<<endl;
//             return 1;
//         }
//         return 0;
//     }
//     int count =0;
//     for(int i=idx;i<arr.size();i++){
//         if(tar-arr[i]>=0) count += coinChangeCombinationSingle(arr,tar-arr[i],i+1,ans+to_string(arr[i]));
//     }
//     return count;
// }
// int coinChangePermutationSingle(vector<int> &arr,int tar,string ans,int idx){
//     if(tar==0||idx==arr.size()){
//         if(tar==0){
//             cout<<ans<<endl;
//             return 1;
//         }
//         return 0;
//     }
//     int count =0;
//     for(int i=0;i<arr.size();i++){
//         if(arr[i]>0 && tar-arr[i]>=0){
//             int ele = arr[i];
//             arr[i]=-arr[i];
//             count += coinChangePermutationSingle(arr,tar-ele,ans+to_string(ele),idx+1);
//             arr[i] = ele;
//         }     
//     }
//     return count;
// }

// int coinChangeCombinationSingleSub(vector<int> &arr,int tar,int idx,string ans){
//      if(idx==arr.size() || tar==0){
//         if(tar==0){
//             cout<<ans<<endl;
//             return 1;
//         }
//         return 0;
//     }
//     int count=0;
//     if(tar-arr[idx]>=0) count+=coinChangeCombinationSingleSub(arr,tar-arr[idx],idx+1,ans+to_string(arr[idx]));
//     count += coinChangeCombinationSingleSub(arr,tar,idx+1,ans);
//     return count;
// }
// int coinChangeCombinationInfiSub(vector<int> &arr,int tar,int idx,string ans){
//      if(idx==arr.size() || tar==0){
//         if(tar==0){
//             cout<<ans<<endl;
//             return 1;
//         }
//         return 0;
//     }
//     int count=0;
//     if(tar-arr[idx]>=0) count+=coinChangeCombinationInfiSub(arr,tar-arr[idx],idx,ans+to_string(arr[idx]));
//     count += coinChangeCombinationInfiSub(arr,tar,idx+1,ans);
//     return count;
// }
// int coinChangePermuatationInfiSub(vector<int> &arr,int tar,int idx,string ans){
//      if(idx==arr.size() || tar==0){
//         if(tar==0){
//             cout<<ans<<endl;
//             return 1;
//         }
//         return 0;
//     }
//     int count=0;
//     if(tar-arr[idx]>=0) count+=coinChangePermuatationInfiSub(arr,tar-arr[idx],0,ans+to_string(arr[idx]));
//     count += coinChangePermuatationInfiSub(arr,tar,idx+1,ans);
//     return count;
// }

// int coinChangePermuatationSingleSub(vector<int> &arr,int tar,int idx,string ans){
//      if(idx==arr.size() || tar==0){
//         if(tar==0){
//             cout<<ans<<endl;
//             return 1;
//         }
//         return 0;
//     }
//     int count=0;
//     if(arr[idx]>0 && tar-arr[idx]>=0){
//         int ele = arr[idx];
//         arr[idx]=-arr[idx];
//         count+=coinChangePermuatationSingleSub(arr,tar-ele,0,ans+to_string(ele));
//         arr[idx]=-arr[idx];
//     } 
//     count += coinChangePermuatationSingleSub(arr,tar,idx+1,ans);
//     return count;
// }

// vector<string> subsequence(string s){
//     if(s.length()==0) return {""};
//     char ch = s[0];
//     string rs = s.substr(1,s.length()-1);
//     vector<string> rr = subsequence(rs);
//     vector<string> myans;
//     for(string s1:rr){
//         myans.push_back(s1);
//         myans.push_back(s1+ch);
//     }
//     return myans;
// }
// vector<string> codes={":;/", "abc", "def", "ghi", "jkl", "mno",
//     "pqrs", "tuv", "wxyz", "&*%", "#@$"};

// vector<string> keypad(string s){
//     if(s.length()==0) return {""};
//     char ch = s[0];
//     string rs = s.substr(1,s.length()-1);
//     vector<string> rr = keypad(rs);
//     vector<string> myans;
//     string code = codes[ch-'0'];
//     for(int i=0;i<code.length();i++){
//         for(string ele:rr)
//             myans.push_back(code[i]+ele);
//     }
//     return myans;
// }

// vector<string> keypad02(string s){
//     if(s.length()==0) return {""};
//     char ch = s[0];
//     string rs = s.substr(1,s.length()-1);
//     vector<string> rr1 = keypad02(rs);
//     vector<string> myans;
//     string code = codes[ch-'0'];
//     for(int i=0;i<code.length();i++){
//         for(string ele:rr1)
//             myans.push_back(code[i]+ele);
//     }
//     if(s.length()>1){
//         int dd = (s[0]-'0')*10 + (s[1]-'0');
//         if(dd==10){
//             rs = s.substr(2,s.length()-2);
//             vector<string> rr2 = keypad02(rs);
//             code = codes[dd];
//             for(int i=0;i<code.length();i++){
//                 for(string ele:rr2)
//                     myans.push_back(code[i]+ele);
//             }
//         }
//     }
//     return myans;
// }


int main(){
    // coin exchange addition========================>
    // vector<int> arr={2,3,5,7};
    // int tar =10;
    // vector<bool> vis(6,false);
    // cout<<coinChangePermutationInfi(arr,tar,"");
    // cout<<coinChangeCombinationInfi(arr,tar,0,"");
    // cout<<coinChangeCombinationSingle(arr,tar,0,"");
    // cout<<coinChangePermutationSingle(arr,tar,"",0);
    // cout<<coinChangeCombinationSingleSub(arr,tar,0,"");
    // cout<<coinChangeCombinationInfiSub(arr,tar,0,"");
    // cout<<coinChangePermuatationInfiSub(arr,tar,0,"");
    // cout<<coinChangePermuatationSingleSub(arr,tar,0,"");
    // cout<<kqueenCombintion1D(0,3,"",6,0);

    // simple basic edition=================================>
    // vector<string> ans = subsequence("abc");
    // vector<string> ans = keypad("123"); 
    // vector<string> ans = keypad02("101"); 

    // flood fill edition==================================>
    


    for(string ele : ans) cout<<ele<<" ";
}