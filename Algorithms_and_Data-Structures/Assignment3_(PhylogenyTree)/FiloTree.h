/*
    Eric Paulz (epaulz)
    CPSC 2120
    Assignment 3: Phylogeny Tree
*/

#ifndef FILOTREE_H
#define FILOTREE_H

#include <iostream>
#include <string>
#include <vector>
#include <fstream>
#include <algorithm>
using namespace std;

struct FiloNode{
    string label;
    FiloNode *left, *right;

public:
    // default constructor
    FiloNode() : label("X"), left(nullptr), right(nullptr) {}
    // overloaded constructor
    FiloNode(string lab, FiloNode *L, FiloNode *R)
        : label(lab), left(L), right(R) {}

    void setLeft(FiloNode* l){
        left = l;
    }
    void setRight(FiloNode* r){
        right = r;
    }
};

class FiloTree{
private:
    FiloNode *root;
    
    // recursive helper for dump
    void preOrder(FiloNode *x, int d) const;

    // recursive helper for destructor
    void removeNode(FiloNode *x);

    // recursive helper for compare
    static bool compareNodes(FiloNode *a, FiloNode *b);

public:
    // takes strings and creates a complete binary tree of the specified
    // depth with the specified leaves in order. assume the array contains
    // 2^depth strings
    FiloTree(string leaves[], int depth);

    // prints out the tree in standard preorder style (one node perline)
    void dump() const;

    // note: the children of a node should be considered unordered
    static bool compare(const FiloTree &A, const FiloTree &B);

    // destructor
    ~FiloTree();

    // reads data from a file and creates the tree using the nearest-neighbor
    // algorithm (described in handout)
    FiloTree(string fileName);
};

#endif
