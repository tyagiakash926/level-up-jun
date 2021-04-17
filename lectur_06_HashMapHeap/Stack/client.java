public class client{
    public static void main(String[] args) throws Exception{
        int[] arr = {10,20,30,40};
        Dstack st = new Dstack(arr);
        st.push(100);
        st.push(110);
        st.push(120);
        st.push(130);
        System.out.println(st.top());

        System.out.println(st);
    }
}