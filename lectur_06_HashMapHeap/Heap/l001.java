import java.util.ArrayList;
public class l001{
    public static int height(int[] arr,int vidx){
        if(vidx>=arr.length) return -1;
        int leftheight = height(arr,(2*vidx)+1);
        int rightheight = height(arr,(2*vidx)+2);
        return Math.max(leftheight,rightheight)+1;
    }

    public static class Heap{
        ArrayList<Integer> arr = new ArrayList<>();
        boolean isMax = true;
        public Heap(int[] data){
            constructHeap(data);
        }


        public Heap(int[] data,boolean isMax){
            this.isMax = isMax;      // agar this nhi lageyge toh isMax = isMax iska kya mtlb kuch nhi default heap ban jayegi. this lga dege
            constructHeap(data);     // toh pta pdega this maane class ke local variable ko represent kr rha h.                         
        }

        public int compareTo(int a,int b){
            if(isMax){
                return arr.get(a) - arr.get(b);
            }
            else{
                return arr.get(b) - arr.get(a);
            }
        }


        public void constructHeap(int[] data){      //O(N)
            for(int ele:data) arr.add(ele);
            int n = this.arr.size();

            for(int i=n-1;i>=0;i--){
                downheapify(i);
            }
        }

        private void upheapify(int ci){          //O(logn)
            int pi = (ci-1)*2;
            if(pi>0 && compareTo(ci,pi)>0){
                swap(ci,pi);
                upheapify(pi);
            }
        }

        private void downheapify(int pi){         //O(logn)
            int maxidx = pi;
            int lci = 2*pi + 1;
            int rci = 2*pi + 2;
            if(lci<arr.size() && compareTo(lci,maxidx)>0)
                maxidx = lci;
            if(rci<arr.size() && compareTo(rci,maxidx)>0)
                maxidx = rci;
            
            if(maxidx!=pi){
                swap(pi,maxidx);
                downheapify(maxidx);
            }
        }

        private void swap(int a,int b){         //O(1)
            int val1 = arr.get(a);
            int val2 = arr.get(b);

            this.arr.set(a,val2);
            this.arr.set(b,val1);
        }

        public int size(){     //O(1)
            return this.arr.size();
        }

        public boolean isEmpty(){   // O(1)
            return this.arr.size()==0;
        }

        public void push(int val){    //O(logn)
            this.arr.add(val);
            int n = size();
            upheapify(n-1);
        }

        public int top(){   // O(1)
            return arr.get(0);
        }

        public int pop(){    //O(logn)
            swap(0,arr.size()-1);
            
            int n=size();
            int rv = this.arr.remove(n-1);

            downheapify(0);

            return rv;
        }
    }


    public static void solve(){
        int[] arr = { 10, 20, 30, -2, -3, -4, 5, 6, 7, 8, 9, 22, 11, 13 };
        Heap pq=new Heap(arr,false);



        while(pq.size()!=0){
            
            // for(int ele: pq.arr) System.out.print(ele + " ");
            // System.out.println();

            System.out.print(pq.pop() + " ");
        }

    }

    public static void main(String[] args){
        solve();
    }
}