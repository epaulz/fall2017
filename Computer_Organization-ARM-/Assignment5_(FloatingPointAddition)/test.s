/*
	Eric Paulz (epaulz) && Gabriel Ballou (gballou)
	CPSC 2310-002
	Assignment #5 - Floating Point Addition
	Due Date: Tuesday, December 5, 11:59:59pm
*/

num1   .req r0
num2   .req r1
exp1   .req r2
exp2   .req r3
frac1  .req r4
frac2  .req r5
temp   .req r6
shift  .req r7
hidden .req r8

  .file "fpAdd.s"
  .section .text
  .global fpAdd
  .type fpAdd, %function

/*
	function name: fpAdd
	description: simulates the process of IEEE 32-bit floating point addition
	
	input parameters: 
	  r0 - first number
	  r1 - second number
   return value:
	  r0 - result of addition
	local register usage:
	  r0 - contains first number & return value at the end
	  r1 - contains second number
	  r2 - exponent of first number
	  r3 - exponent of second number
	  r4 - fraction of first number
	  r5 - fraction of second number
	  r6 - temporary variable
	  r7 - value used to shift decimal point over
	  r8 - holds the hidden bit
*/	  

fpAdd:
  push {lr}
  
  // extract exponents from each number
  lsr exp1, num1, #23
  and exp1, exp1, #0xff
  
  lsr exp2, num2, #23
  and exp2, exp2, #0xff
  
  // load this number into a register because it is
  // too large for direct addressing
  ldr temp, =0x007fffff

  // extract fractions from each number
  and frac1, num1, temp
  and frac2, num2, temp

  // check which number is larger
  cmp exp1, exp2
  bge second_smaller

  // shift the decimal point over 	
  sub shift, exp2, exp1
  lsr frac1, frac1, shift

  mov exp1, exp2
  b continue

second_smaller:
  // shift the decimal point over
  sub shift, exp1, exp2
  lsr frac2, frac2, shift

continue:
  // add the two fractions together
  add frac1, frac1, frac2
  lsl frac1, frac1, #9
  lsr frac1, frac1, #9
 
  mov num1, #0

  // put fraction in the rightmost 23 bits, then the exponent
  // in the next 8 bits.  (we don't worry about the leftmost
  // bit because it will already be 0 and we are dealing with
  // strictly position numbers)
  lsl temp, exp1, #23
  orr num1, frac1, temp

.unreq num1
.unreq num2
.unreq exp1
.unreq exp2
.unreq frac1
.unreq frac2
.unreq temp
.unreq hidden

  pop {pc}
