

#include <iostream>
#include "btree.cpp"

int main(int argc, const char * argv[])
{

    CBTree<int> *b= new CBTree<int>();
    
    for (int i =0; i<10000000; i++){
        b->insert(i);
    }

    
    b->display();
    
    std::cout << sizeof(b) << endl;
    return 0;
}

