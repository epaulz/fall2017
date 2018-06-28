/*
	Eric Paulz (epaulz)
	CPSC 2310-002
	Assignment 3
	Due Date: October 24, 2017 11:59:59 PM

   Function Name: "compute"
	Description: Performs arithmetic on 4 numbers passed in by main

	r0 = a
	r1 = b
	r2 = c
	r3 = d
*/

	 .file "compute.s"
    .text
    .global compute
    .type compute, %function

compute:
   push {lr}
    
	mul r0, r1, r0       // multiply a*b
	mla r0, r2, r3, r0   // multiply c*d, then add a*b and store in r0
   
	pop {pc}
