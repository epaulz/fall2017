/*
  Eric Paulz (epaulz)
  CPSC 2311-003
  Lab 12
*/
  
#include <stdio.h>
#include <math.h>  // gcc T.c -lm

int main()
{
  int sign, exp, fraction, significand;
  int i;
  float f;
  float prev = 0;

  for (i = 0; i < 256; i++)
  {
    /* begin your code */

    sign = (i>>7)&0x1;  // leftmost bit
    exp = (i>>3)&0xf;   // next 4 bits
    fraction = i&0x7;   // last 3 bits

    int isNormal = 1;
    if(exp == 0){       // denormal case
      isNormal = 0;
      exp = -7;
    }
    else{               // normal case
      exp -= 8;
    }

    significand = (isNormal<<3) | fraction;
    exp -= 3;

    f = significand * pow(2,exp);
    if(sign == 1)
      f *= -1;

    /* end your code */

    printf ("%02x => %08x = %+11.6f (spacing = %+11.6f)\n",
            i, *(int*) &f, f, prev-f);
    prev = f;
  }

  return 0;
}
