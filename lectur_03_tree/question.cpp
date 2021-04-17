#include<iostream>
#include<vector>
#include<algorithm>
#include<stack>
#include<queue>

using namespace std;

class TreeNode {
    public:
      int val;
      TreeNode *left;
      TreeNode *right;
      TreeNode() {}
      TreeNode(int val) { this->val = val; }
      TreeNode(int val, TreeNode *left, TreeNode *right) {
          this->val = val;
          this->left = left;
          this->right = right;
      }
  };

// leetcode 1373 =========================>
int maxSum = 0;
// isBSt=1/0, maxEle,minEle,Sum
vector<int> maxSumBST_(TreeNode *root)
{
    if (root == nullptr)
    {
        return {1, (int)-1e8, (int)1e8, 0};
    }

    vector<int> la = maxSumBST_(root->left);
    vector<int> ra = maxSumBST_(root->right);

    bool isBST = la[0] == 1 && ra[0] == 1 && la[1] < root->val && root->val < ra[2];
    int sum = la[3] + ra[3] + root->val;
    if (isBST)
        maxSum = max(maxSum, sum);

    return {isBST ? 1 : 0, max(root->val, max(la[1], ra[1])), min(root->val, min(la[2], ra[2])), sum};
}

// isBSt=1/0, maxEle,minEle,Sum,maxSum   // pre quest another approach
vector<int> maxSumBST_02(TreeNode *root)
{
    if (root == nullptr)
    {
        return {1, (int)-1e8, (int)1e8, 0, 0};
    }

    vector<int> la = maxSumBST_02(root->left);
    vector<int> ra = maxSumBST_02(root->right);

    bool isBST = la[0] == 1 && ra[0] == 1 && la[1] < root->val && root->val < ra[2];
    int sum = la[3] + ra[3] + root->val;

    int maxSum = 0;
    if (isBST)
        maxSum = max(sum, max(la[4], ra[4]));
    else
        maxSum = max(la[4], ra[4]);

    return {isBST ? 1 : 0, max(root->val, max(la[1], ra[1])), min(root->val, min(la[2], ra[2])), sum, maxSum};
}

int maxSumBST(TreeNode *root)
{
    // maxSumBST_(root);
    // return maxSum;

    return maxSumBST_(root)[4];
}

//Leetcode 113.============================================================

bool hasPathSum(TreeNode *root, int sum)
{
    if (root == nullptr)
        return false;
    if (root->left == nullptr && root->right == nullptr && sum - root->val == 0)
        return true;

    return hasPathSum(root->left, sum - root->val) || hasPathSum(root->right, sum - root->val);
}

//Leetcode 114.==========================================================

vector<vector<int>> ans;
vector<int> res;

void pathSum_(TreeNode *root, int sum)
{
    if (root == nullptr)
        return;
    if (root->left == nullptr && root->right == nullptr && sum - root->val == 0)
    {
        vector<int> base(res);
        base.push_back(root->val);
        ans.push_back(res);

        // ans.push_back(res);
        // ans.back().push_back(root->val);

        // res.push_back(root->val);
        // ans.push_back(res);
        // res.pop_back();

        return;
    }

    res.push_back(root->val);

    pathSum_(root->left, sum - root->val);
    pathSum_(root->right, sum - root->val);

    res.pop_back();
}

vector<vector<int>> pathSum(TreeNode *root, int sum)
{
    pathSum_(root, sum);
    return ans;
}

//Leetcode 98.==============================================================

int prevBSTVal = -1e8;
bool isValidBST(TreeNode *root)
{
    if (root == nullptr)
        return true;

    if (!isValidBST(root->left))
        return false;

    if (prevBSTVal >= root->val)
        return false;
    prevBSTVal = root->val;

    if (!isValidBST(root->right))
        return false;

    return true;
}

//Leetcode 99.================================================================

TreeNode *a = nullptr;
TreeNode *b = nullptr;
TreeNode *prevNode = nullptr;

// meaning of true: mujhai 2 fluctuation mil gyi hai.
// meaning of false: !(mujhai 2 fluctuation mil gyi hai).
bool recoverTree_(TreeNode *root)
{

    if (root == nullptr)
        return false;

    if (recoverTree_(root->left))
        return true;

    if (prevNode != nullptr && prevNode->val > root->val)
    {
        b = root;
        if (a == nullptr)
            a = prevNode;
        else
            return true;
    }

    prevNode = root;
    if (recoverTree_(root->right))
        return true;

    return false;
}

void recoverTree(TreeNode *root)
{
    recoverTree_(root);
    if (a != nullptr)
    {
        int temp = a->val;
        a->val = b->val;
        b->val = temp;
    }
}

// geeks for geeks max sum leaf to leaf:

int maxSumLTL = -1e8; // max sum leaf to leaf.
int leafToLeafPathSum(TreeNode *node)
{
    if (node == nullptr)
        return -1e8;

    int lPathSum = leafToLeafPathSum(node->left);
    int rPathSum = leafToLeafPathSum(node->right);

    if (node->left == nullptr && node->right == nullptr)
        return node->val;

    if (node->left != nullptr && node->right != nullptr)
        maxSumLTL = max(maxSumLTL, lPathSum + rPathSum + node->val);
    return max(lPathSum, rPathSum) + node->val;
}

//Leetcode 124.===============================================================

int maxSumNTN = -1e8; // max sum leaf to leaf.
int nodeToNodePathSum_(TreeNode *node)
{
    if (node == nullptr)
        return 0;

    int lPathSum = nodeToNodePathSum_(node->left);
    int rPathSum = nodeToNodePathSum_(node->right);

    int max_ = max(lPathSum, rPathSum);
    maxSumNTN = max(max(maxSumNTN, node->val), max(lPathSum + rPathSum + node->val, max_ + node->val));
    return max(max_ + node->val, node->val);
}

int maxPathSum(TreeNode *root)
{
    nodeToNodePathSum_(root);
    return maxSumNTN;
}

//Leetcode 236.=======================================================================

TreeNode *lca = nullptr;
bool lowestCommonAncestor_(TreeNode *root, TreeNode *p, TreeNode *q)
{
    if (root == nullptr)
        return false;

    bool selfFound = root == p || root == q;

    bool leftFound = lowestCommonAncestor_(root->left, p, q);
    if (lca != nullptr)
        return true;

    bool rightFound = lowestCommonAncestor_(root->right, p, q);
    if (lca != nullptr)
        return true;

    if ((selfFound && leftFound) || (selfFound && rightFound) || (rightFound && leftFound))
        lca = root;

    return selfFound || leftFound || rightFound;
}

TreeNode *lowestCommonAncestor(TreeNode *root, TreeNode *p, TreeNode *q)
{
    lowestCommonAncestor_(root, p, q);
    return lca;
}

class BSTIterator
{
public:
    stack<TreeNode*> st;

    BSTIterator(TreeNode *root)
    {
        insertLeftMost(root);
    }

    void insertLeftMost(TreeNode *root)
    {
        TreeNode *curr = root;
        while (curr != nullptr)
        {
            st.push(curr);
            curr = curr->left;
        }
    }

    int next()
    {
        TreeNode *rnode = st.top();
        st.pop();
        insertLeftMost(rnode->right);

        return rnode->val;
    }

    bool hasNext()
    {
        return st.size() != 0;
    }

    // while(obj.hasNext()) cout<<obj.next()<<endl;
};

// convert BT in in DLL in INORDER traversal::========================>

// https://practice.geeksforgeeks.org/problems/binary-tree-to-dll/1

TreeNode *prevDLL = nullptr, *head = nullptr;
void DLL(TreeNode *node)
{
    if (node == nullptr)
        return;

    DLL(node->left);
    if (head == nullptr)
        head = node;
    else
    {
        prevDLL->right = node;
        node->left = prevDLL;
    }

    prevDLL = node;
    DLL(node->right);
}

TreeNode *bToDLL(TreeNode *root)
{
    prevDLL = nullptr;
    head = nullptr;
    DLL(root);
    return head;
}

//Leetcode : 426 lOCKED problem:

TreeNode *treeToDoublyList(TreeNode *root)
{
    if (root == nullptr)
        return root;

    prevDLL = nullptr;
    head = nullptr;
    DLL(root);

    prevDLL->right = head;
    head->left = prevDLL;

    return head;
}

//leetcode 889 psot and pre se tree:
 TreeNode *buildTree(vector<int> &preorder, int psi, int pei, vector<int> &postorder, int ppsi, int ppei) // si=starting index, ei = end index.
{
    if (psi > pei)
        return nullptr;

    if (psi == pei)
        return new TreeNode(preorder[psi]);

    TreeNode *node = new TreeNode(preorder[psi]);
    int idx = ppsi;
    while (postorder[idx] != preorder[psi + 1])
        idx++;

    int tel = idx - ppsi + 1;

    node->left = buildTree(preorder, psi + 1, psi + tel, postorder, ppsi, idx);
    node->right = buildTree(preorder, psi + tel + 1, pei, postorder, idx + 1, ppei - 1);

    return node;
}

TreeNode *constructFromPrePost(vector<int> &pre, vector<int> &post)
{
    int n = pre.size();
    return buildTree(pre, 0, n - 1, post, 0, n - 1);
}

// leetcode 106
TreeNode* buildTree(vector<int>& inorder, vector<int>& postorder) {
        int n = inorder.size();
        return buildTree_(postorder,0,n-1,inorder,0,n-1);
    }
    TreeNode *buildTree_(vector<int> &postorder, int psi, int pei, vector<int> &inorder, int isi, int iei)
    {
        if(psi>pei)
        {
            return nullptr;
        }
        TreeNode* node = new TreeNode(postorder[pei]);
        int idx = isi;
        while(inorder[idx]!=postorder[pei])
        {
            idx++;
        }
        int tel = idx-isi;
        node->left = buildTree_(postorder,psi,psi+tel-1,inorder,isi,idx-1);
        node->right = buildTree_(postorder,psi+tel,pei-1,inorder,idx+1,iei);
        return node;
        
    }
//leetcode 105
TreeNode* buildTree(vector<int>& preorder, vector<int>& inorder) {
         int n = preorder.size();
        return buildTree_(preorder,0,n-1,inorder,0,n-1);
    }
    TreeNode* buildTree_(vector<int>& preorder,int psi,int pei,vector<int>& inorder,int isi,int iei)
    {
        if(psi>pei)
        {
            return nullptr;
        }
        TreeNode *node = new TreeNode(preorder[psi]);
        int idx = isi;
        while(inorder[idx]!=preorder[psi])
            idx++;
        int tel =idx-isi;
        node->left = buildTree_(preorder,psi+1,psi+tel,inorder,isi,idx-1);
        node->right = buildTree_(preorder,psi+tel+1,pei,inorder,idx+1,iei);
        return node;
    }
//leetcode 968
// -1 : need a camera.
//  0 : is a camera
//  1 : dosen't need a camera.

int camera = 0;
int minCameraCover_(TreeNode *root)
{
    if (root == nullptr)
        return 1;
    if (root->left == nullptr && root->right == nullptr)
        return -1;

    int lans = minCameraCover_(root->left);
    int rans = minCameraCover_(root->right);

    if (lans == -1 || rans == -1)
    {
        camera++;
        return 0;
    }
    else if (lans == 0 || rans == 0)
        return 1;

    return -1;
}

int minCameraCover(TreeNode *root)
{
    if (root = nullptr)
        return 0;

    if (minCameraCover_(root) == -1)
        camera++;

    return camera;
}

//leetcode 230
void leftMost(TreeNode *node, stack<TreeNode *> &st)
{
    while (node != nullptr)
    {
        st.push(node);
        node = node->left;
    }
}

int kthSmallest(TreeNode *root, int k)
{
    stack<TreeNode *> st;
    leftMost(root, st);

    int ans = -1;
    while (k-- > 0)
    {
        TreeNode *rnode = st.top();
        st.pop();
        leftMost(rnode->right, st);
        
        ans = rnode->val;
    }
    return ans;
}
//leetcode 662
 int widthOfBinaryTree(TreeNode* root) {
        if( !root ) return 0;
        queue<pair<TreeNode*, unsigned int> > q;
        q.push({root, 1 });
        unsigned int ans = 0;
        while(q.size()!=0){
            int size = q.size();
            if (size == 1){
                q.push({q.front().first,1});
                q.pop();
            }
            ans = max(q.back().second - q.front().second + 1,ans);
        
            while( size-->0 ){
                auto node = q.front().first;
                auto  idx = q.front().second;
                q.pop();
                if( node->left )
                    q.push({node->left, idx*2});
                if( node->right )
                    q.push({node->right, idx*2+1});
            }
            
        }
        return ans;
    }
//leetcode 145
 vector<int> postorderTraversal(TreeNode* root) {
        vector<int> ans ;
        if(root==nullptr)
            return ans;
        
        postorderTraversal_(root,ans);
        return ans;
    }
    void postorderTraversal_(TreeNode* root,vector<int>& ans)
    {
        TreeNode* curr = root;
        stack<TreeNode*> st;
        while(curr!=nullptr || !st.empty())
        {
            if(curr!=nullptr)
            {
                st.push(curr);
                curr=curr->left;
            }
            else
            {
                TreeNode* temp =  st.top()->right;
                if(temp==nullptr)
                {
                    temp = st.top();
                    st.pop();
                    ans.push_back(temp->val);
                    while (!st.empty() && temp == st.top()->right) {
                        temp = st.top(); 
                        st.pop();
                        ans.push_back(temp->val);                 
                    }
                }
                else
                {
                    curr=temp;
                }
            }
        }
    }
//leetcodde 100
 bool isSameTree(TreeNode* p, TreeNode* q) {
        if(p==nullptr && q==nullptr)
            return true;
        if((p==nullptr && q!=nullptr) || (p!=nullptr && q==nullptr))
            return false;
        if(p->val!=q->val)
            return false;
        return isSameTree(p->left,q->left) &&  isSameTree(p->right,q->right);
    }
//101
bool isSymmetric_(TreeNode* p,TreeNode* q)
    {
        if(p==nullptr && q==nullptr)
            return true;
        if((p!=nullptr && q==nullptr)||(p==nullptr && q!=nullptr))
            return false;
        if(p->val!=q->val)
            return false;
        return isSymmetric_(p->left,q->right)&&isSymmetric_(p->right,q->left);
    }
    bool isSymmetric(TreeNode* root) {
        if(root==nullptr)
            return true;
        if(root->left==nullptr && root->right==nullptr)
            return true;
        return isSymmetric_(root->left,root->right);
    }
//110
bool isBalanced(TreeNode* root) {
        pair<int,int> ans = isBalanced_(root);
        return ans.first>1?false:true;
    }
    pair<int,int> isBalanced_(TreeNode* root)
    {
        if(root==nullptr)
            return {0,-1};
        pair<int,int> lp = isBalanced_(root->left);
        pair<int,int> rp = isBalanced_(root->right);
         int bal = abs(lp.second-rp.second);
         int height = max(lp.second,rp.second)+1;
        
        return {max(bal,max(lp.first,rp.first)),height};
    }
//111
// public int minDepth(TreeNode node) {
//         if(node==null)
//             return 0;
//         int ld = minDepth(node.left);
//         int rd = minDepth(node.right);
//         if(node.left!=null && node.right!=null)
//         {
//             return Math.min(ld,rd)+1;
//         }
//         else if(node.left==null && node.right!=null)
//         {
//             return rd+1;
//         }
//         else if(node.left!=null && node.right==null)
//         {
//             return ld+1;
//         }
//         else
//         {
//             return 1;
//         }
//     }

//129
int sumNumbers(TreeNode* root) {
        return sumNumbers_(root,0);
    }
    int sumNumbers_(TreeNode* root,int res)
    {
        if(root==nullptr)
            return res;
       
        int lans = sumNumbers_(root->left,(res*10)+root->val);
        int rans = sumNumbers_(root->right,(res*10)+root->val);
        
        if(root->left!=nullptr && root->right!=nullptr)
            return lans+rans;
        else if(root->left==nullptr && root->right==nullptr)
            return lans;
        else
            return root->left==nullptr?rans:lans;
    }
//226
TreeNode* invertTree(TreeNode* root) {
        return invertTree_(root);
    }
    TreeNode* invertTree_(TreeNode* root)
    {
        if(root==nullptr)
            return nullptr;
        TreeNode* node =new TreeNode(root->val);
        node->left = invertTree_(root->right);
        node->right = invertTree_(root->left);
        return node;
    }
//617
TreeNode* mergeTrees(TreeNode* t1, TreeNode* t2) {
        TreeNode* ans = mergeTrees_(t1,t2);
        return ans;
    }
    TreeNode* mergeTrees_(TreeNode* t1,TreeNode* t2)
    {
        if(t1==nullptr && t2==nullptr)
            return nullptr;
        if(t1==nullptr || t2==nullptr)
        {
            if(t1==nullptr)
            {
                TreeNode* node = new TreeNode(t2->val);
                node->left = mergeTrees_(t1,t2->left);
                node->right = mergeTrees_(t1,t2->right);
                return node;
            }
            else
            {
                TreeNode* node = new TreeNode(t1->val);
                node->left = mergeTrees_(t1->left,t2);
                node->right = mergeTrees_(t1->right,t2);
                return node;
            }
            
        }   
        TreeNode* node=new TreeNode(t1->val+t2->val);
        node->left = mergeTrees_(t1->left,t2->left);
        node->right = mergeTrees_(t1->right,t2->right);
        return node;
    }
//965
int pre;
    bool isUnivalTree(TreeNode* root) {
        if(root==nullptr)
            return true;
        pre = root->val;
        return isUnivalTree_(root);
        
    }
    bool isUnivalTree_(TreeNode* root)
    {
        if(root==nullptr)
            return true;
        if(pre!=root->val)
            return false;
        return  isUnivalTree_(root->left) && isUnivalTree_(root->right);
    }
//1302
int deepestLeavesSum(TreeNode* root) {
        if(root==nullptr)
            return 0;
        queue<TreeNode*> que;
        int sum=0;
        que.push(root);
        while(que.size()!=0)
        {
            sum = 0; 
            int size = que.size();
            while(size-->0)
            {
                TreeNode* rnode = que.front();
                sum+=rnode->val;
                que.pop();
                if(rnode->left!=nullptr)
                    que.push(rnode->left);
                if(rnode->right!=nullptr)
                    que.push(rnode->right);
            }
        }
        return sum;
    }

//404
    int sumOfLeft(TreeNode* root , int sum , char id)
{
if(root==nullptr)
return 0;
if(root->left==nullptr && root->right==nullptr && id=='L')
return root->val;
else if(root->left==nullptr && root->right==nullptr && id=='R')
return 0;

  sum += (sumOfLeft(root->left , sum , 'L')) + (sumOfLeft(root->right , sum , 'R') ) ;
 return sum;
}
int sumOfLeftLeaves(TreeNode* root) {
int sum=0;
char id;
return sumOfLeft(root , sum , id);

}
