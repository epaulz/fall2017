/*
  Eric Paulz
  CPSC 2120-
  Assignment 1 - Word Ladders
  */

#include "radixSortVec.h"
#include "stringQueueVec.h"

void radixSort(vector<string> & list){
  vector<stringQueueVec> alphabet(26);

  //add words to appropriate queue
  for(int i = 3; i >= 0; i--){
    char curr = 'a';
    while(curr <= 'z' && !list.empty()){
      if(list.at(0).at(i) == curr){
        alphabet.at((int)curr - 97).enqueue(list.at(0));  //97 == 'a'
        list.erase(list.begin());
        curr = 'a';
      }
      else
        curr++;
    }

    //repopulate list with sorted words
    for(int k = 0; k < 26; k++){
      while(!alphabet.at(k).isEmpty()){
        list.push_back(alphabet.at(k).dequeue());
      }
    }
  }
}
