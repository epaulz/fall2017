/*
  Eric Paulz
  CPSC 2121
  Lab 5
*/

#include "TreeNode.h"

#include <stack>

TreeNode::TreeNode(string str) : value(str), lchild(nullptr), rchild(nullptr) {}

void TreeNode::setLeft(TreeNode* l){
  lchild = l;
}

void TreeNode::setRight(TreeNode* r){
  rchild = r;
}

// works
void TreeNode::postorder() const{
  //base case
  if(lchild == nullptr && rchild == nullptr){
    cout << value;
    return;
  }

  lchild->postorder();
  rchild->postorder();
  cout << value;
}

void TreeNode::preorder() const{
  //base case
  if(lchild == nullptr && rchild == nullptr){
    cout << value;
    return;
  }

  cout << value;
  lchild->preorder();
  rchild->preorder();
}

ostream & operator<<(ostream & out, const TreeNode & currNode){
  out << currNode.value;
  return out;
}
