import java.util.ArrayList;
public class question{

    public class ListNode{
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode midNode(ListNode node){
        if(node == null || node.next == null)
        {
            return node;
        }
        
        ListNode slow = node;
        ListNode fast = node;
        while(fast.next != null && fast.next.next != null)
        {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    //leetcode 876======================================>

    public ListNode midNode_(ListNode head)
    {
        if(head==null || head.next == null)
        {
            return head;
        }
        
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null)
        {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    //leetcode 206=======================================>

    public ListNode reverse_(ListNode head)
    {
        if(head == null || head.next == null)
        {
            return head;
        }

        ListNode prev = null;
        ListNode curr = head;
        while(curr != null)
        {
            ListNode forw = curr.next; // Backup
            curr.next = prev;          // connection
            prev = curr;
            curr = forw;
        }
        return prev;
    }

    // leetcode 234.=======================================

    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null)
            return true;

        ListNode mid = midNode(head);
        ListNode nhead = mid.next;
        mid.next = null;

        nhead = reverse_(nhead);

        ListNode c1 = head, c2 = nhead;
        boolean res = true;

        while (c1 != null && c2 != null) {
            if (c1.val != c2.val) {
                res = false;
                break;
            }
            c1 = c1.next;
            c2 = c2.next;
        }

        nhead = reverse_(nhead);
        mid.next = nhead;

        return res;
    }

    // Leetcode 143.========================================>

    public void reorderList(ListNode head) {
        if (head == null || head.next == null)
            return;

        ListNode mid = midNode(head);
        ListNode nhead = mid.next;
        mid.next = null;

        nhead = reverse_(nhead);
        ListNode c1 = head, c2 = nhead;

        while (c1 != null && c2 != null) {
            ListNode f1 = c1.next, f2 = c2.next; // backup

            c1.next = c2; // connection.
            c2.next = f1;

            c1 = f1; // move forward.
            c2 = f2;
        }
    }

    // Leetcode 19:============================================.

    public ListNode removeNthFromEnd(ListNode head, int n) { // min possible value of n : 1 and it is always valid.
        if (head == null || head.next == null)
            return null;

        ListNode c1 = head, c2 = head;
        while (n-- > 0)
            c2 = c2.next;

        if (c2 == null) // if we have to remove head.
            return head.next;

        while (c2.next != null) {
            c1 = c1.next;
            c2 = c2.next;
        }

        ListNode rnode = c1.next;
        c1.next = rnode.next;
        rnode.next = null;

        // delete rnode; // for c++.

        return head;
    }

    // leetcode merge2sorted list==========================>

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null)
            return l1 == null ? l2 : l1;

        ListNode head = new ListNode(-1); // dummy Node.
        ListNode prev = head;

        ListNode c1 = l1, c2 = l2;
        while (c1 != null && c2 != null) {
            if (c1.val <= c2.val) {
                prev.next = c1;
                prev = c1;
                c1 = c1.next;
            } else {
                prev.next = c2;
                prev = c2;
                c2 = c2.next;
            }
        }

        if (c2 != null)
            prev.next = c2;
        else
            prev.next = c1;

        return head.next;
    }

    //leetcode 23=============================================================>

    public ListNode mergeKLists02(ListNode[] lists, int li, int ri) { // O(Nlogk)
        if (li == ri)
            return lists[li];

        int mid = (li + ri) / 2;

        ListNode l1 = mergeKLists02(lists, li, mid);
        ListNode l2 = mergeKLists02(lists, mid + 1, ri);
        ListNode finalList = mergeTwoLists(l1, l2);

        return finalList;
    }

    public ListNode mergeKLists02(ListNode[] lists) {
        if (lists.length == 0)
            return null;

        return mergeKLists02(lists, 0, lists.length - 1);
    }

    //H.W order->reorder->order.========================>

    // public void Ro_To_O(ListNode head)
    // {
    //     if(head == null || head.next == null)
    //     {
    //         return;
    //     }
    //     ListNode mid = midNode(head);
    //     ListNode nhead = mid.next;
    //     mid.next = null;
    //     nhead = reverse_(nhead);
    //     ListNode c1 = head;
    //     ListNode c2 = nhead;
    //     while(c1 != null && c2 != null)
    //     {
    //         ListNode f1 = c1.next;
    //         ListNode f2 = c2.next;

    //         c1.next = c2;
    //         c2.next = f1;

    //         c1 = f1;
    //         c2 = f2;
    //     }
    // }
    // public ListNode reverseBetween(ListNode head, int m, int n) 
    // {
    //     ListNode c=head;
    //     while(m>2)
    //     {
    //         c=c.next;
    //         m--;
    //     }   
    //     ListNode nhead = reverse_(c.next);
        
    // }

     // Leetcode 141: linked-list-cycle.====================================

     public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null)
            return false;

        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast)
                break;
        }

        return fast == slow ? true : false;
    }

    // Leetcode 142.=====================================================
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null)
            return null;

        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast)
                break;
        }

        if (fast != slow)
            return null;

        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    // Leetcode 160=========================================================
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null)
            return null;

        ListNode curr = headA;
        while (curr.next != null)
            curr = curr.next;

        curr.next = headB; // cycle.
        ListNode ans = detectCycle(headA);
        curr.next = null; // destroy cycle.
        return ans;
    }

    ListNode oH = null, oT = null;
    ListNode tH = null, tT = null;

    public void addFirstNode(ListNode node) {
        if (tT == null) {
            tH = node;
            tT = node;
        } else {
            node.next = tH;
            tH = node;
        }
    }

    public int length(ListNode node) {
        int len = 0;
        while (node != null) {
            node = node.next;
            len++;
        }
        return len;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null || k == 0 || k == 1)
            return head;

        int len = length(head);
        if (len < k)
            return head;

        ListNode curr = head;
        while (curr != null && len >= k) {
            int temp = k;
            while (temp-- > 0) {
                ListNode rnode = curr;
                curr = curr.next;
                rnode.next = null;

                addFirstNode(rnode);
            }

            if (oH == null) {
                oH = tH;
                oT = tT;
            } else {
                oT.next = tH;
                oT = tT;
            }

            tH = null;
            tT = null;

            len -= k;
        }

        oT.next = curr;
        return oH;
    }

    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || head.next == null || n == m)
            return head;

        int i = 1;
        ListNode curr = head;
        ListNode prev = null;

        while (curr != null) {
            while (i >= m && i <= n) {
                ListNode rnode = curr;
                curr = curr.next;
                rnode.next = null;
                addFirstNode(rnode);
                i++;
            }

            if (i > n) {
                if (prev != null)
                    prev.next = tH;
                else
                    head = tH;
                tT.next = curr;
                break;
            }

            prev = curr;
            curr = curr.next;
            i++;
        }

        return head;
    }

    ListNode reverseLL(ListNode head) {
        if (head == null || head.next == null)
            return head;

        while (head != null) {
            ListNode rnode = head;
            head = head.next;
            rnode.next = null;
            addFirstNode(rnode);
        }

        return th;

    }

    //leetcode 328==========================================================.>

    ListNode OddEvenList(ListNode head){
        if(head==null || head.next==null) return head;
        ListNode odd=new ListNode(-1);
        ListNode oitr=odd;

        ListNode even=new ListNode(-1);
        ListNode eitr=even;

        while(head!=null){
            oitr.next=head;
            eitr.next=head.next;

            oitr=oitr.next;
            eitr=eitr.next;

            head=head.next;
            if(head!=null && head.next!=null) head=head.next;
            else break;
        }

        oitr.next=even.next;
        return odd.next;
    }

    // leetcode 61===========================================>

    public ListNode rotateRight(ListNode head, int k) {
        if(head==null || head.next==null || k==0) return head; 
          int size=0;
          ListNode curr=head,tail=null;
          while(curr!=null){
              tail=curr;
              curr=curr.next;
              size++;
          }
  
          k%=size;  //k in range
          if(k<0) k+=size;  // k become positive.
  
          if(k==0) return head;
  
          curr=head;
          int count=1;
          int n=size-k;
          
          while(count<n){
              curr=curr.next;
              count++;
          }
  
          tail.next=head;
          head=curr.next;
          curr.next=null;
          
          return head;  
      }

      //leetcode 2 =======================================.
      public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode c1 = l1;
        ListNode c2 = l2;
        ListNode sentinel = new ListNode(0);
        ListNode d = sentinel;
        int sum = 0;
        while (c1 != null || c2 != null) {
            sum /= 10;
            if (c1 != null) {
                sum += c1.val;
                c1 = c1.next;
            }
            if (c2 != null) {
                sum += c2.val;
                c2 = c2.next;
            }
            d.next = new ListNode(sum % 10);
            d = d.next;
        }
        if (sum / 10 == 1)
            d.next = new ListNode(1);
        return sentinel.next;
    }
    
    //leetcode 83 ==============================================>
      
    public ListNode deleteDuplicates(ListNode head) {
        if(head==null || head.next==null)
        {
            return head;
        }
        ListNode curr = head;
        ListNode ans = new ListNode(-1);
        ListNode pre = ans;
        if(curr.val==pre.val)
        {
            pre.next = curr;
            pre=curr;
            curr=curr.next;
            pre.next=null;
        }
        while(curr!=null)
        {
            ListNode f = curr.next;
            
            
            if(pre.val==curr.val)
            {
                
                curr=f;
            }
            else
            {
                pre.next = curr;
                pre=curr;
                pre.next=null;
                curr=f;
            }
        }
        return ans.next;
    }

    //leetcode 24 ======================================================>
    public ListNode swapPairs(ListNode head) {
        if(head==null || head.next==null)
        {
            return head;
        }
        ListNode curr = head;
        ListNode ans = new ListNode(-1);
        ListNode pre = ans;
        while(curr!=null)
        {
            ListNode f1 = curr.next;
            if(f1!=null)
            {
                ListNode f2 =f1.next;
                pre.next = f1;
                pre=f1;
                pre.next = curr;
                pre = curr;
                pre.next = null;
                curr= f2;
            }
            else
            {
                pre.next = curr;
                pre = curr;
                pre.next = null;
                curr = f1; 
            }
        }
        return ans.next;
    }
}