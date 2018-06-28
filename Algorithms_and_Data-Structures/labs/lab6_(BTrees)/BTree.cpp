// BTree.cpp - wdg - 2014
#include <iostream>
using namespace std;
#include "BTree.h"

BTree::BTree( ) : root(nullptr)
{
}

void BTree::insert(int item)
{
      if( root==nullptr )
         root = new BTreeLeaf();
      int newKey;
      BTreeNode *split = root->insert(item, newKey);

      if(split!=nullptr) {
         cout << "Splitting root " << endl;
         BTreeInternal *newRoot = new BTreeInternal();
         newRoot->child[0] = root;
         newRoot->child[1] = split;
         newRoot->marker[1] = newKey;
         newRoot->currChildren = 2;
         root = newRoot;
      }
}

void BTree::dump()
{
      root->dump(0);
}
