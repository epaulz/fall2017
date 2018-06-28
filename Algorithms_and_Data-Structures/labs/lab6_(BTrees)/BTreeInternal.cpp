// BTreeInternal.h wdg 2014
#include "BTreeNode.h"


BTreeInternal::BTreeInternal() : currChildren(0)
{
}

BTreeInternal *BTreeInternal::insert(int item, int &newKey)
{
      int pos=0;
      while(pos<currChildren-1 && item>=marker[pos+1])
         pos++;
      int addedKey;
      BTreeNode *split = child[pos]->insert(item,addedKey);

      if(split!=nullptr) {
         for(int i = currChildren-1; i>=pos+1; i--) {
             marker[i+1] = marker[i];
             child[i+1] = child[i];
         }
         child[pos+1] = split;
         marker[pos+1] = addedKey;
         currChildren++;

         if(currChildren>MAX) {
            BTreeInternal *sibling = new BTreeInternal();
            for(int i=0; i<(MAX+1)/2;  i++)
	      sibling->child[i] = child[i+(MAX+2)/2];
            for(int i=1; i<(MAX+1)/2;  i++)
	      sibling->marker[i] = marker[i+(MAX+2)/2];
            sibling->currChildren = (MAX+1)/2;
            currChildren -= (MAX+1)/2;
            newKey = marker[ (MAX+2)/2 ];
            return sibling;
         }
      }
      // if reach here
      return nullptr;
}

void BTreeInternal::dump(int depth)
{
      indent(depth);
      cout << "(";
      for(int i=1; i<currChildren; i++)
        cout << marker[i] << ",";
      cout <<")" << endl;
      for(int i=0; i<currChildren; i++)
          child[i]->dump(depth+1);
}
