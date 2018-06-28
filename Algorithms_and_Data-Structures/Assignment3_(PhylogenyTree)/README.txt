/*
    Eric Paulz (epaulz)
    CPSC 2120
    Assignment 3: Phylogeny Tree
*/

---RUNNING TIMES---

1. FiloTree(string leaves[], int depth);
Best Case: O(1) - if leaves array is empty and root is set to nullptr
Worst Case: O(n) - just for loops (none nested) creating nodes and populating vectors

2. void dump() const;
O(n) - it will visit all 'n' nodes recursively and print them out

3. static bool compare(const FiloTree &A, const FiloTree &B);
O(n^2) - it must visit all 'n' nodes in EACH tree

4. ~FiloTree();
O(n) - like dump, it will visit all 'n' nodes and delete each one

5. FiloTree(string fileName);
O(n^2) - it has to open the file and read in the data no matter what.
		   there are a few nested for loops.
