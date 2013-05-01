#include <iostream>
#include <iomanip>
using namespace std;


template<class T> class CBTree
{
    
private:
    static const int M = 3;
    static const int KEY_MAX = 2*M-1;
    static const int KEY_MIN = M-1;
    static const int CHILD_MAX1 = KEY_MAX+1;
    static const int CHILD_MIN = KEY_MIN+1;

    
    struct Node
    {
        bool isLeaf;
        int keyNum;
        T keyValue[KEY_MAX];
        Node *pChild[CHILD_MAX1];
        
        Node(bool b=true, int n=0)
        :isLeaf(b), keyNum(n){}
    };
public:
    CBTree(){
        m_pRoot = NULL; // empty tree
    }
    ~CBTree(){
    
    }
    
    bool insert(const T &key){
        if(false){
            return false;
        }
        else{
            if(!m_pRoot){
                m_pRoot =new Node();
            }
            if(m_pRoot->keyNum == KEY_MAX){             // create new root
                Node * pNode  =new Node();
                pNode-> isLeaf = false;
                pNode->pChild[0] = m_pRoot;
                splitChild(pNode, 0, m_pRoot);
                m_pRoot = pNode;                       //update the root node
                
            }
            insertNonFull(m_pRoot, key);
            return true;
        }
    }
    
    
    void splitChild(Node *pParent, int nChildIndex, Node *pChild)
    {
        //将pChild分裂成pLeftNode和pChild两个节点
        Node *pRightNode = new Node();//分裂后的右节点
        pRightNode->isLeaf = pChild->isLeaf;
        pRightNode->keyNum = KEY_MIN;
        int i;
        for (i=0; i<KEY_MIN; ++i)//拷贝关键字的值
        {
            pRightNode->keyValue[i] = pChild->keyValue[i+CHILD_MIN];
        }
        if (!pChild->isLeaf)  //如果不是叶子节点，拷贝孩子节点指针
        {
            for (i=0; i<CHILD_MIN; ++i)
            {
                pRightNode->pChild[i] = pChild->pChild[i+CHILD_MIN];
            }
        }
        
        pChild->keyNum = KEY_MIN;  //更新左子树的关键字个数
        
        for (i=pParent->keyNum; i>nChildIndex; --i)//将父节点中的nChildIndex后的所有关键字的值和子树指针向后移一位
        {
            pParent->pChild[i+1] = pParent->pChild[i];
            pParent->keyValue[i] = pParent->keyValue[i-1];
        }
        ++pParent->keyNum;  //更新父节点的关键字个数
        pParent->pChild[nChildIndex+1] = pRightNode;  //存储右子树指针
        pParent->keyValue[nChildIndex] = pChild->keyValue[KEY_MIN];//把节点的中间值提到父节点
        
    }

    void insertNonFull(Node *pNode, const T &key)
    {
        int i = pNode->keyNum;  //get the number of key
        if (pNode->isLeaf)      //is node a leaf
        {
            while (i>0&&key<pNode->keyValue[i-1]){   //backward to find the position where to insert
                pNode->keyValue[i] = pNode->keyValue[i-1];  // forward the each node where Node.keyValue > key
                --i;
            }
            pNode->keyValue[i] = key;  // insert the key
            ++pNode->keyNum; // increase the number of key
        }
        else//pNode是内节点
        {
            while(i>0&&key<pNode->keyValue[i-1])   // backward to find out the sub tree on which key to be inserted
                --i;
            Node *pChild = pNode->pChild[i];  // get the sub tree
            if (pChild->keyNum==KEY_MAX)  // is the tree full ?
            {
                splitChild(pNode, i, pChild);//split the tree into two sub trees
                if(key>pNode->keyValue[i])   // is the key > lefttree.keyValue[max] ?
                    pChild = pNode->pChild[i+1];
            }
            insertNonFull(pChild, key);  //insert to the non full tree
        }
    }

    
    void displayInConcavo(Node *pNode, int count)const
    {
        if (pNode!=NULL)
        {
            int i, j;
            for (i=0; i<pNode->keyNum; ++i)
            {
                if (!pNode->isLeaf)
                {
                    displayInConcavo(pNode->pChild[i], count-2);
                }
                for (j=count; j>=0; --j)
                {
                    cout<<"-";
                }
                cout<<pNode->keyValue[i]<<endl;
            }
            if (!pNode->isLeaf)
            {
                displayInConcavo(pNode->pChild[i], count-2);
            }
        }
    }
    
    void display()const{
        displayInConcavo(m_pRoot,KEY_MAX*10);
    }
    
private:
    Node *m_pRoot;
};

