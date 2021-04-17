#include <iostream>
using namespace std;

class Linkedlist
{
    class Node
    {
        public:
        int data = 0;
        Node *next = nullptr;

        Node(int data)
        {
            this->data = data;
        }
    };

    int sz=0;
    Node *head = nullptr;
    Node *tail = nullptr;

    //  BAsic Functions.=================================
    public:
        int size()
        {
            return this->sz;
        }

        bool isEmpty()
        {
            return this->sz==0;
        }

    private:
        Node *getNodeAt(int idx)
        {
            Node *curr = this->head;
            while(idx>0)
            {
                curr = curr->next;
                idx--;
            }
            return curr;
        }

    public:
        //AddFunctions.============================>
        
        void addFirstNode(Node* node)
        {
            if(this->sz==0)
            {
                this->head = node;
                this->tail = node;
            }
            else
            {
                node->next = this->head;
                this->head = node;
            }
            this->sz++;
        }
        
        //removeFunctions.=========================>

        //getFunctions.============================>
};

void solve()
{
    // Linkedlist ll;
    // cout<<ll.size()<<endl;
    // cout<<ll.isEmpty()<<endl;
}

int main()
{
    solve();
    return 0;
}