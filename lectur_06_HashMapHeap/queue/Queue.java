public class Queue{
    public static class queue{
        private int[] arr;
        private int f;
        private int b;
        private int Elements;
        private int Totalsize;

        queue(){
            assignSize(7);
        }
        queue(int n){
            assignSize(n);
        }

        protected void assignSize(int n){
            this.arr = new int[n];
            this.f = 0;
            this.b = 0;
            this.Elements =0;
            this.Totalsize = n; 
        }

        public boolean isEmpty(){
            return this.Elements==0;
        }

        public int size(){
            return this.Elements;
        }

        @Override
        public String toString(){
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            for(int i=0;i<this.Elements;i++){
                int idx = (this.f + i)% this.Totalsize;;
                sb.append(this.arr[idx]);
                if(i!=this.Elements-1)
                sb.append(",");
            }
            sb.append("]");
            return sb.toString();
        }

        public void push(int val){
            if(this.Elements == this.Totalsize)
                return;
            this.arr[this.b] = val;
            this.b = (this.b+1) % this.Totalsize;
            this.Elements++;
        }
        
        public int front(){
            if(this.Elements==0)
                return -1;
            return this.arr[this.f];
        }

        public int pop(){
            if(this.Elements==0)
                return -1;
            int rv = this.arr[this.f];
            f = (this.f+1) % this.Totalsize;
            this.Elements--;
            return rv;
        }
    }
    
    public static void solve()
    {
        queue que = new queue();
        que.push(10);
        que.push(20);
        que.push(30);
        que.push(40);
        que.push(50);

        que.pop();
        que.pop();
        que.push(60);
        que.push(70);
        que.push(80);
        que.push(90);
        que.push(90);


        System.out.println(que);
    }

    public static void  main(String[] args)
    {
        solve();

    }
}