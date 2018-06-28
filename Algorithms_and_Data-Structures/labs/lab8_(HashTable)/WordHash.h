/*
	Eric Paulz (epaulz)
	CPSC 2121
	Lab 8
*/

#ifndef WORDHASH_H
#define WORDHASH_H

#include "WordCount.h"
#include <string>
#include <iostream>

using namespace std;

class WordHash
{
   private:
      int len;
      WordCount *data;
      int count;

      static unsigned int hashOf(const string & word)
      {
          return hash<string>()(word);
      }
      static const double LOAD_FACTOR;
      void rehash( );

   public:
      WordHash(int arraylength);
      ~WordHash( );
      void addWord( const string & word);
      void printCommon() const;
};

#endif
