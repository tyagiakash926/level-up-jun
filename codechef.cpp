// JAI SHREE RAM

#include<bits/stdc++.h>
using namespace std;



int firstMissingPositive(vector<int>& na) {
    int n=na.size();
    for(int i=0 ; i<n ; i++)
    {
        while(na[i]>0 and na[i]<=n and na[i]!=na[na[i]-1])
            swap(na[i],na[na[i]-1]);
    }
    for(int i=0 ; i<n ; i++) if(na[i] != i+1) return i+1;
    return n+1;
}
int main() {
    
        int t;
        cin>>t;
        while(t-->0){
            int n,k;
            cin>>n>>k;
            vector<int> arr(n,0);
            for(int i=0;i<n;i++){
                cin>>arr[i];
            }
            int maximum = 0;
            int ans=n;
            unordered_map<int,int> map;
            vector<int> na;
            // cout<<"HIII";
            
            for(int i=0;i<n;i++){
                map[arr[i]]++;
                if(arr[i]>maximum) maximum = arr[i];
                na.push_back(arr[i]);
            }
           
            int missing = firstMissingPositive(na);
            // cout<<missing;
            
            while(k-->0){
                int ele=0;
                if(missing==n+1){
                    ele = round((missing+maximum)/2);
                }else{
                    ele = round((missing+maximum)/2.0);
                }
                if(ele>maximum) maximum = ele;
                if(map.find(ele)!=map.end()){
                    break;
                }else{
                    ans++;
                    if(k>0){
                        na.push_back(ele);
                        missing = firstMissingPositive(na);
                    }
                }
            }
            cout<<ans<<endl;
        }
	return 0;
}

