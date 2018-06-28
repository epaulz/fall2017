#include <stdio.h>

unsigned int fpAdd( unsigned int, unsigned int );

int main()
{
   float x,y,z;
   unsigned int a,b,c;

   union
   {
      float f;
      unsigned int i;
   } u;

   //x = 1.68;
   //y = .355;
   //x = .26;
	//y = .75;
	x = 125;
	y = .5;
   z = x + y;

   u.f = x;
   a = u.i;

   u.f = y; b = u.i;
   c = fpAdd( a, b );
   u.f = z;

   printf("%f + %f = %f\nresult is: 0x%08x\nsimulated: 0x%08x\n",
           x, y, z, u.i, c);

   return 0;
}
