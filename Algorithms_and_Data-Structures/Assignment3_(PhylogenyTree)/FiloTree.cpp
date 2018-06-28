/*
    Eric Paulz (epaulz)
    CPSC 2120
    Assignment 3: Phylogeny Tree
*/

#include "FiloTree.h"

FiloTree::FiloTree(string leaves[], int depth){
  // check for an empty array
  if(leaves[0] == ""){
    root = nullptr;
  }
  else{
    // compute 2^d
    int numLeaves = 1;
    for(int i = 0; i < depth; i++){
      numLeaves *= 2;
    }

    // compute (2^d+1)-1
    int numNodes = 1;
    for(int i = 0; i < depth+1; i++){
      numNodes *= 2;
    }
    numNodes -= 1;

    // all nodes in the tree
    vector<FiloNode*> node_list;

    for(int i = 0; i < numNodes-numLeaves; i++){
      //FiloNode *temp = new FiloNode();
      node_list.push_back(new FiloNode());
    }
    for(int i = 0; i < numLeaves; i++){
      //FiloNode *temp = new FiloNode(leaves[i], nullptr, nullptr);
      node_list.push_back(new FiloNode(leaves[i], nullptr, nullptr));
    }
    
	 // link the nodes using level ordering
    for(int i = 0; i < numNodes-numLeaves; i++){
      node_list.at(i)->setLeft(node_list.at(2*i+1));
      node_list.at(i)->setRight(node_list.at(2*i+2));
    }

    root = node_list.at(0);
  }
}

// destructor
FiloTree::~FiloTree(){
  removeNode(root);
}
// recursive helper for destructor
void FiloTree::removeNode(FiloNode *x){
  if(x == nullptr)
    return;
  removeNode(x->left);
  removeNode(x->right);
  delete x;
}

void FiloTree::dump() const {
  if(root == nullptr){
    cout << "***Empty tree***\n";
    return;
  }
  else{
    preOrder(root, 0);
    cout << endl;
  }
}
// recursive helper for dump
void FiloTree::preOrder(FiloNode *x, int d) const{
  if(x->left == nullptr && x->right == nullptr){
    for(int i = 0; i < d; i++){
      cout << "...";
    }
    cout << x->label << endl;
    return;
  }
  for(int i = 0; i < d; i++){
    cout << "...";
  }
  cout << x->label << endl;
  preOrder(x->left, d+1);
  preOrder(x->right, d+1);
}

bool FiloTree::compare(const FiloTree &A, const FiloTree &B) {
   return compareNodes(A.root, B.root);
}
// recursive helper for compare
bool FiloTree::compareNodes(FiloNode *a, FiloNode *b){
  if(a == nullptr && b == nullptr)
    return true;
  if(a != nullptr && b != nullptr){
    if(a->left == nullptr && a->right == nullptr && b->left == nullptr && b->right == nullptr){
      return (a->label == b->label);
    }
    else
      return ((compareNodes(a->left, b->left) || compareNodes(a->left, b->right))
              && (compareNodes(a->right, b->right) || compareNodes(a->right, b->left)));
  }
  return false;
}

FiloTree::FiloTree(string fileName){
  int n;
  vector<vector<double>> distances;
  vector<string> leaves;

  // open file and read in data
  ifstream myfile(fileName);
  if(myfile.is_open()){
    myfile >> n;

    leaves.resize(n);
    distances.resize(n, vector<double>(n));

    for(int i = 0; i < n; i++){
      myfile >> leaves.at(i);
    }
    for(int i = 0; i < n; i++){
      for(int j = 0; j < n; j++){
        myfile >> distances.at(i).at(j);
      }
    }
    myfile.close();
  }
  else
    cout << "Unable to open file '" << fileName << "'\n";

  vector<FiloNode*> nodes;
  vector<FiloNode*> tempNodes(n);
  vector<bool> merged(n, false);
  vector<int> ignore;
  int count = 0;

  while(count < n-1){
    double min = 100000000;
    int row, col;

    // find minimum value and save the row and column
    for(int i = 0; i < n; i++){
      if(!(find(ignore.begin(), ignore.end(), i) != ignore.end())){
        for(int j = 0; j < n; j++){
          if(distances.at(i).at(j) < min && distances.at(i).at(j) > 0 &&
            !(find(ignore.begin(), ignore.end(), j) != ignore.end())){
                min = distances.at(i).at(j);
                row = i;
                col = j;
          }
        }
      }
    }

    // create nodes and link them based on whether or not they have
    // already been merged previously
    if(merged.at(row) == false && merged.at(col) == false){
      nodes.push_back(new FiloNode(to_string(count+1), new FiloNode(leaves.at(row), nullptr, nullptr),
                                   new FiloNode(leaves.at(col), nullptr, nullptr)));
      tempNodes.at(row) = nodes.at(count);
      merged.at(row) = true;
      merged.at(col) = true;
      ignore.push_back(col);
    }
    else if(merged.at(row) == true && merged.at(col) == false){
      nodes.push_back(new FiloNode(to_string(count+1), new FiloNode(leaves.at(col), nullptr, nullptr), tempNodes.at(row)));
      tempNodes.at(row) = nodes.at(count);
      merged.at(col) = true;
      ignore.push_back(col);
    }
    else if(merged.at(row) == false && merged.at(col) == true){
      nodes.push_back(new FiloNode(to_string(count+1), new FiloNode(leaves.at(row), nullptr, nullptr), tempNodes.at(col)));
      tempNodes.at(row) = nodes.at(count);
      merged.at(row) = true;
      ignore.push_back(col);
    }
    else{
      nodes.push_back(new FiloNode(to_string(count+1), tempNodes.at(row), tempNodes.at(col)));
      tempNodes.at(row) = nodes.at(count);
      ignore.push_back(col);
    }

    // replace used distances with averages
    for(int i = 0; i < n; i++){
      if(i != row && !(find(ignore.begin(), ignore.end(), i) != ignore.end())){
        distances.at(i).at(row) = (distances.at(i).at(row) + distances.at(i).at(col)) / 2;
        distances.at(row).at(i) = distances.at(i).at(row);
      }
    }

    count++;
  }

  if(n == 0)
    root = nullptr;
  else{
    reverse(nodes.begin(), nodes.end());
    root = nodes.at(0);
  }
}
