// BTreeLeaf.h wdg 2014
#include "BTreeNode.h"

BTreeLeaf::BTreeLeaf() : elements(0)
{}

BTreeLeaf * BTreeLeaf::insert(int item, int& newKey)
    {
       int pos=elements-1;
       while(pos>=0 && item<value[pos]) {
         value[pos+1]=value[pos];
         pos--;
       }
       value[pos+1] = item;
       elements++;
       if(elements<=MAX)
          return nullptr;
       else {
         BTreeLeaf *sibling = new BTreeLeaf();
         for(int i=0; i<(MAX+1)/2; i++)
	     sibling->value[i] = value[i + (MAX+2)/2];
         sibling->elements = (MAX+1)/2;
         elements -= (MAX+1)/2;
         newKey = sibling->value[0];
         return sibling;
       }
    }

void BTreeLeaf::dump(int depth)
{
      indent(depth);
      if( elements> 0 ) {
        for(int i=0; i<elements; i++)
           cout << value[i] << ",";
      }
      else
         cout << "Empty";
      cout << endl;
}
