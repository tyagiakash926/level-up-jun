import java.util.ArrayList;
public class l002_BST{
    public static class Node{
        int data = 0;
        Node left = null;
        Node right = null;
        public Node(int data)
        {
            this.data = data;
        }
    }
    public static Node constructTree(int[] arr,int si,int ei)
    {
        if(si>ei)
        {
            return null;
        }
        int mid = (si+ei)/2;
        Node node = new Node(arr[mid]);
        node.left = constructTree(arr,si,mid-1);
        node.right = constructTree(arr,mid+1,ei);
        return node;
    }

    public static void display(Node node)
    {
        if(node==null)
        return;
        String str ="";
        str += node.left != null ? node.left.data : ".";
        str += " <- " + node.data + " -> ";
        str += node.right != null ? node.right.data : ".";
        System.out.println(str);

        display(node.left);
        display(node.right);
    }
    
    public static int height(Node node)
    {
        return node==null?-1:Math.max(height(node.left),height(node.right))+1;
    }

    public static int size(Node node)
    {
        return node==null?0:size(node.left)+size(node.right)+1;
    }

    public static boolean find(Node node,int data)   // no space
    {
        Node curr = node;
        while(curr!=null)
        {
            if(curr.data==data)
            return true;
            if (curr.data>data)
            curr = curr.left;
            else
            curr = curr.right;
        }
        return false;
    }

    public static boolean find_rec(Node node,int data)  // recursion use stack space
    {
        if(node ==null)
        return false;
        if(node.data == data)
        return true;
        else if(node.data > data)
        return find_rec(node.left,data);
        else
        return find_rec(node.right,data);
    }

    public static ArrayList<Node> rootToNodePath(Node node,int data)
    {
        ArrayList<Node> ans = new ArrayList<>();
        Node curr = node;
        while (curr != null) {
            if (curr.data == data) {
                ans.add(curr);
                break;
            } else if (curr.data > data) {
                ans.add(curr);
                curr = curr.left;
            } else {
                ans.add(curr);
                curr = curr.right;
            }
        }
        return ans;
    }

    public static int maximum(Node node)
    {
        Node curr = node;
        while(curr.right!=null)
        curr = curr.right;
        return curr.data;
    }

    public static int minimum(Node node)
    {
        Node curr = node;
        while(curr.left!=null)
        curr = curr.left;
        return curr.data;
    }

    //leetcode 235======================>
    public static Node LCA(Node node, int p, int q) {
        Node curr = node;
        while (curr != null) {
            if (curr.data > p && curr.data > q)
                curr = curr.left;
            else if (curr.data < p && curr.data < q)
                curr = curr.right;
            else
                return find(curr, p) && find(curr, q) ? curr : null;
        }
        return null;
    }

    //using recursion pre ques
    // public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    //     return lowestCommonAncestor_(root,p,q);
        
    // }
    // public TreeNode lowestCommonAncestor_(TreeNode root,TreeNode p,TreeNode q)
    // {
    //     if(root==null)
    //         return null;
    //     if((root.val>=p.val && root.val<=q.val) || (root.val<=p.val && root.val>=q.val))
    //         return root;
    //     else if(p.val>=root.val && q.val>=root.val)
    //         return lowestCommonAncestor_(root.right,p,q);
    //     else
    //         return lowestCommonAncestor_(root.left,p,q);
        
    // }


    public static void searchInRange(Node node,int a,int b,ArrayList<Integer> ans)
    {
        if(node == null)
        return;
        searchInRange(node.left,a,b,ans);
        if(node.data>=a && node.data<=b)
        ans.add(node.data);
        searchInRange(node.right,a,b,ans);
    }

    
    static int idx = 0;

    public static Node constructTreeUsingPreOrder(int[] arr, int leftboundary, int rightboundary) {
        if (idx == arr.length || arr[idx] < leftboundary || arr[idx] > rightboundary)
            return null;

        Node node = new Node(arr[idx++]);
        node.left = constructTreeUsingPreOrder(arr, leftboundary, node.data);
        node.right = constructTreeUsingPreOrder(arr, node.data, rightboundary);
        return node;
    }

    public static Node constructTreeUsingPreOrder(int[] arr) {
        idx = 0;
        return constructTreeUsingPreOrder(arr, -10000000, 10000000);
    }

    public static Node constructTreeUsingPostOrder(int[] arr, int leftboundary, int rightboundary) {
        if (idx == -1 || arr[idx] < leftboundary || arr[idx] > rightboundary)
            return null;

        Node node = new Node(arr[idx--]);
        node.right = constructTreeUsingPostOrder(arr, node.data, rightboundary);
        node.left = constructTreeUsingPostOrder(arr, leftboundary, node.data);
        return node;
    }

    public static Node constructTreeUsingPostOrder(int[] arr) {
        idx = arr.length - 1;
        return constructTreeUsingPostOrder(arr, -10000000, 10000000);
    }


    public static int heightTreeUsingPreOrder(int[] arr, int leftboundary, int rightboundary) {
        if (idx == arr.length || arr[idx] < leftboundary || arr[idx] > rightboundary)
            return -1;

        int data = arr[idx];
        idx++;
        int lh = heightTreeUsingPreOrder(arr, leftboundary, data);
        int rh = heightTreeUsingPreOrder(arr, data, rightboundary);
        return Math.max(lh, rh) + 1;
    }

    public static int heightTreeUsingPreOrder(int[] arr) {
        idx = 0;
        return heightTreeUsingPreOrder(arr, -10000000, 10000000);
    }

    //pred succesor using recursion===========================>
    public static void predsucc(Node node,int data)
    {
        if(node==null)
            return;
        if(node.data==data)
        {

        }
        if(node.data > data)
            succ = node;
            predsucc(node.left,data);
        if(node.data < data)
            pre = node;
            predsucc(node.right,data);
        
    
    }

    public static void predSuccInBST(Node node, int data) {
        Node curr = node;
        Node pred = null;
        Node succ = null;
        while (curr != null) {
            if (data < curr.data) {
                succ = curr;
                curr = curr.left;
            } else if (data > curr.data) {
                pred = curr;
                curr = curr.right;
            } else {

                Node tPred = curr.left;
                if (tPred != null) {
                    while (tPred.right != null)
                        tPred = tPred.right;
                    pred = tPred;
                }

                Node tSucc = curr.right;
                if (tSucc != null) {
                    while (tSucc.left != null)
                        tSucc = tSucc.left;
                    succ = tSucc;
                }
            }

        }

    }

    public static Node addData(Node root,int data)
    {
        if(root==null)
        {
            return new Node(data);
        }
        if(data<root.data)
        root.left=addData(root.left,data);
        else
        root.right=addData(root.right,data);
        return root;
    }

    public static Node removeData(Node root,int data)
    {
        if(root==null)
        return null;
        if (data < root.data)
            root.left = removeData(root.left, data);
        else if (data > root.data)
            root.right = removeData(root.right, data);
        else if(data==root.data)
        {
            if(root.left==null || root.right==null)
            return root.left!=null?root.left:root.right;
            int maxEle = maximum(root.left);
            root.data = maxEle;
            root.left = removeData(root.left, maxEle);
        }
        return root;
    }


    public static void set1(ArrayList<Integer> ans) {
        int[] ar = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++)
            ar[i] = ans.get(i);

        Node node = constructTreeUsingPreOrder(ar);
        // System.out.println(heightTreeUsingPreOrder(ar));

        //Node node = constructTreeUsingPostOrder(ar);
        display(node);

    }

    public static void solve() {
        int[] arr = new int[15];
        for (int i = 0; i < arr.length; i++)
            arr[i] = (i + 1) * 10;

        Node root = constructTree(arr, 0, arr.length - 1);
        display(root);
        // System.out.println(height(root));

        ArrayList<Integer> ans = new ArrayList<>();
        // preOrder(root, ans);
        // postOrder(root, ans);
        set1(ans);

        // Node root = null;
        // for (int ele : arr)
        //     root = addData(root, ele);

        // display(root);
        // int i = 0;
        // for (int ele : arr) {
        //     System.out.println(root.data + " ");
        //     root = removeData(root, ele);
        // }
        // display(root);
    }

    public static void main(String[] args) {
        solve();
    }

    }

