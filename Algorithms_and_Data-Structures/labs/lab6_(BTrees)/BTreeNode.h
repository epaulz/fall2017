// BTreeNode.h wdg 2014
#ifndef BTREENODE_H
#define BTREENODE_H
#include <iostream>
using namespace std;

class BTreeNode
{
  public:
    static const int MAX=3;
    virtual BTreeNode *insert(int item, int &newKey) = 0;
    virtual void dump(int depth) = 0;
    static void indent(int depth)
    {
      for(int i=0; i<depth; i++)
         cout << "...";
    }
};

class BTreeLeaf : public BTreeNode
{
  private:
    int value[MAX+1];  // need to go temporarily over
    int elements;

  public:
    BTreeLeaf();
    BTreeLeaf *insert(int item, int &newKey);
    void dump(int depth);
};

class BTreeInternal : public BTreeNode
{

   private:
     BTreeNode * child[MAX+1];
     int marker[MAX+1];
     int currChildren;
     // note: marker[0] is not used
     // child[0] - marker[1] - child[1] - marker[2] - child[2] etc

     friend class BTree;

   public:
     BTreeInternal();
     BTreeInternal *insert(int item, int &newKey);
     void dump(int depth);

};

#endif
