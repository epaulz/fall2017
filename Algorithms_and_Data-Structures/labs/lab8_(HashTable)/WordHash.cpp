/*
	Eric Paulz (epaulz)
	CPSC 2121
	Lab 8
*/

#include "WordHash.h"
#include <vector>

const double WordHash::LOAD_FACTOR = 0.7;   // set load factor limit

WordHash::WordHash(int arrayLength) :
    len(arrayLength), data(new WordCount[arrayLength]), count(0)
{}

WordHash::~WordHash(){ delete [] data; }

void WordHash::rehash(){
  vector<WordCount> temp;         // store data temporarily
  for(int i = 0; i < len; i++){
    if(data[i].getCount() > 0)
      temp.push_back(data[i]);
  }

  delete [] data;                 // clear data array
  len = len * 2 + 1;              // double size and add 1 (per instructions)
  data = new WordCount[len];      // reallocate array with new size

  for(unsigned int i = 0; i < temp.size(); i++){  // add old data to new array
    addWord(temp.at(i).getWord());                // with new hashes
  }
}

void WordHash::addWord(const string & word){
  bool found = false;
  int index;

  for(int i = 0; i < len; i++){     // check if word already found
    if(data[i].getWord() == word){  // in hash table
      found = true;
      index = i;
    }
  }

  if(found == true){                // if found, just increment count
    data[index].incrementCount();
  }
  else{
    int h = hashOf(word);           // create hash value for word
    h = h % len;                    // divide by len

    if(h < 0)                       // account for negative hash vals
      h *= -1;

    // increment hash by one until an empty position is found (linear probing)
    while(data[h].getCount() > 0 && (data[h].getWord() != word))
      h++;

    data[h].setWord(word);
    data[h].incrementCount();
    count++;
  }

  // after each add, calculate load factor.  if it is >= .7 then rehash
  if((static_cast<double>(count) / len) >= LOAD_FACTOR){
    rehash();
  }
}

// print out the hash table (no specified method of doing this in instructions)
void WordHash::printCommon() const{
  for(int i = 0; i < len; i++){
    if(data[i].getCount() > 0)
      cout << "HASH " << i << " = " << data[i].getWord() << ": " << data[i].getCount() << endl;
  }
}
