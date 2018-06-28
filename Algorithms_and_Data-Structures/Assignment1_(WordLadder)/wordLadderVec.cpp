/*
  Eric Paulz
  CPSC 2120-
  Assignment 1 - Word Ladders
*/

#include "stringQueueVec.h"
#include "radixSortVec.h"

#include <fstream>
#include <algorithm>
#include <iostream>

//function stub
void wordLadder(vector<string> &dict, vector<string> &found, vector<bool> &letters,
                string &start, string &goal, int &goalSteps, int &actualSteps,
                int index, size_t &end, bool &success);

int main(int argc, char *argv[]){
  string inputString = "";
  vector<string> dictionary;

  //check for proper usage
  if(argc != 5){
    cout << "Usage " << argv[0] << " <dictFilename> <startWord> <goalWorld> <steps>\n";
    exit(1);
  }

  //open file and make sure it opens properly
  else{
    ifstream inFile(argv[1]);
    if(!inFile.good()){
      cout << "Could not open file " << argv[1] << endl;
      exit(1);
    }
    else{
      //read file data into dictionary vector
      while(getline(inFile, inputString)){
        dictionary.push_back(inputString);
      }
    }
  }

  //print the dictionary
  cout << "ORIGINAL DICTIONARY:\n";
  for(size_t i = 0; i < dictionary.size(); i++){
    cout << dictionary.at(i) << endl;
  }

  //sort dictionary
  radixSort(dictionary);

  //print the sorted dictionary
  cout << "\nSORTED DICTIONARY:\n";
  for(size_t i = 0; i < dictionary.size(); i++){
    cout << dictionary.at(i) << endl;
  }
  cout << endl;

  //assign command line arguments to variables
  string startWord = argv[2];
  string goalWord = argv[3];
  int goalSteps = stoi(argv[4]);  //string-to-int

  //other variables needed for wordLadder function
  int actualSteps = 0;
  size_t end = 0;

  vector<string> foundWords;
  foundWords.push_back(argv[2]);

  //letters 0-3
  //true if letter is solved, false otherwise
  vector<bool> letters(4, false);

  //find letters that are already a match so they won't be changed
  for(int i = 0; i < 4; i++){
    if(startWord.at(i) == goalWord.at(i))
      letters.at(i) = true;
  }

  bool success = false;

  //call function to solve the word ladder
  wordLadder(dictionary, foundWords, letters, startWord, goalWord, goalSteps,
             actualSteps, 3, end, success);

  //print the result
  cout << foundWords.at(0);
  for(size_t i = 1; i < foundWords.size(); i++){
    cout << " -> " << foundWords.at(i);
  }

  if(success == true && actualSteps <= goalSteps){
    cout << "\n\nWord ladder solved in " << actualSteps << " step(s).\n";
  }
  else{
    cout << "\n\nCould not solve the word ladder in the specified ";
    cout << "amount of steps.\n";
  }

  return 0;
}

//recursive function
void wordLadder(vector<string> &dict, vector<string> &found, vector<bool> &letters,
                string &start, string &goal, int &goalSteps, int &actualSteps,
                int index, size_t &end, bool &success){

  if(start == goal || actualSteps > goalSteps){   //base case
    return;
  }
  else if(index < 0){
    index = 3;
  }

  for(char j = 'a'; j <= 'z'; j++){
    string temp = start;
    if(letters.at(index) == false){   //if letter is not solved for
      temp.at(index) = j;

      //if word is found in dictionary but not already part of the solution
      if((find(dict.begin(), dict.end(), temp) != dict.end()) &&
         !(find(found.begin(), found.end(), temp) != found.end())){

        found.push_back(temp);
        if(temp == goal){
          actualSteps++;
          success = true;
          if(goal.at(index) == j)
            letters.at(index) = true;
          return;
        }
        else{
          actualSteps++;
          if(goal.at(index) == j)
            letters.at(index) = true;
          wordLadder(dict, found, letters, temp, goal, goalSteps, actualSteps,
                     index-1, end, success);
          return;
        }
      }
    }
  }

  //sets a limit in case no solution is possible
  if(end > (dict.size() * 4)){
    return;
  }
  else{
    end++;
    wordLadder(dict, found, letters, start, goal, goalSteps, actualSteps,
               index-1, end, success);
  }
}
