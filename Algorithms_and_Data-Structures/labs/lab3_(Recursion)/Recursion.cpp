/*
	Eric Paulz (epaulz)
	CPSC 2121-004
	Lab 3
*/

#include "Recursion.h"

int main(int argc, char *argv[]){
  if(argc != 2)
    cout << "Usage: " << argv[0] << " <filename>\n";  //check for input file
  else{
    ifstream inFile(argv[1]);
      if(!inFile.is_open()){     //check that file opened correctly
        cout << "Could not open the file.\n";
        return 1;
      }
      else{
        Recursion rec(inFile);  //create Recursion object
        rec.print();            //call print method
      }
  }
  return 0;
}


Recursion::Recursion(ifstream& input){
  int i, j;
  char temp;

  for(i = 0; i < 10; i++){
    for(j = 0; j < 10; j++){    //read file into array
      input >> temp;
      if(temp == '.')           //convert '.' to 0 and
        grid[i][j] = 0;         //everything else to 1
      else
        grid[i][j] = 1;
    }
  }
}

void Recursion::print(){
  int i, j;
  int localGrid[10][10];
  copy(grid, grid+10, localGrid);   //make a local copy of the array

  cout << endl;
  for(i = 0; i < 10; i++){          //print the original grid
    for(j = 0; j < 10; j++){
      cout << localGrid[i][j];
    }
    cout << endl;
  }

  cout << endl;

  int group = 1;
  for(i = 0; i < 10; i++){          //read through the grid looking 1's
    for(j = 0; j < 10; j++){
      if(grid[i][j] == 1){
        cout << "Group " << group << ": ";
        printGroupWith(i, j);       //if 1 found, call recursive function
        group++;
        cout << endl;
      }
    }
  }
  cout << endl;
}

void Recursion::printGroupWith(int row, int col){

  if(grid[row][col] != 1){  // base case
    return;
  }

  grid[row][col] = 0;       //clear grid position so that recursion
                            //won't operate on it infinitely

  cout << "(" << row << "," << col << ")  ";  //print group coordinates

  printGroupWith(row+1, col);
  printGroupWith(row-1, col);
  printGroupWith(row, col+1);
  printGroupWith(row, col-1);
}
