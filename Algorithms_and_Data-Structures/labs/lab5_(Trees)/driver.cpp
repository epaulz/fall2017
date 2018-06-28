/*
  Eric Paulz
  CPSC 2121
  Lab 5
*/

#include <iostream>
using namespace std;
#include "TreeNode.h"

int main()
{
      // Create tree by hand here.
     TreeNode * t1 = new TreeNode("A");
     TreeNode * t2 = new TreeNode("B");
     TreeNode * t3 = new TreeNode("C");
     TreeNode * t4 = new TreeNode("D");
     TreeNode * t5 = new TreeNode("E");
     TreeNode * t6 = new TreeNode("F");
     TreeNode * t7 = new TreeNode("G");

     TreeNode *p1 = new TreeNode("A");
     TreeNode *p2 = new TreeNode("I");
     TreeNode *p3 = new TreeNode("B");
     TreeNode *p4 = new TreeNode("O");
     TreeNode *p5 = new TreeNode("H");
     TreeNode *p6 = new TreeNode("P");
     TreeNode *p7 = new TreeNode("H");
     TreeNode *p8 = new TreeNode("O");
     TreeNode *p9 = new TreeNode("B");
     TreeNode *p10 = new TreeNode("I");
     TreeNode *p11 = new TreeNode("A");

     cout << *t1 << " " << *t2 << " " << *t3 << " " << *t4;
     cout << " " << *t5 << " " << *t6 << " " << *t7 << endl;

     t1->setLeft(t2);
     t1->setRight(t3);
     t2->setLeft(t4);
     t2->setRight(t5);
     t3->setLeft(t6);
     t3->setRight(t7);

     cout << "Postorder should be DEBFGCA" << endl;
     t1->postorder( );
     cout << endl;
     cout << "Preorder should be ABDECFG" << endl;
     t1->preorder( );
     cout << endl << endl;

     cout << *p1 << " " << *p2 << " " << *p3 << " " << *p4 << " " << *p5 << " ";
     cout << *p6 << " " << *p7 << " " << *p8 << " " << *p9  << " " << *p10;
     cout << " " << *p11 << endl;

     p1->setLeft(p2);
     p1->setRight(p3);
     p2->setLeft(p4);
     p2->setRight(p5);
     p3->setLeft(p6);
     p3->setRight(p7);
     p4->setLeft(p8);
     p4->setRight(p9);
     p5->setLeft(p10);
     p5->setRight(p11);

     cout << "Postorder: ";
     p1->postorder();
     cout << endl;
     cout << "Preorder: ";
     p1->preorder();
     cout << endl;

     delete t1;
     delete t2;
     delete t3;
     delete t4;
     delete t5;
     delete t6;
     delete t7;

     delete p1;
     delete p2;
     delete p3;
     delete p4;
     delete p5;
     delete p6;
     delete p7;
     delete p8;
     delete p9;
     delete p10;
     delete p11;

     return 0;
}
