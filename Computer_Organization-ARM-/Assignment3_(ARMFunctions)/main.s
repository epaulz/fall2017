/*
	Eric Paulz (epaulz)
	CPSC 2310-002
	Assignment 3
	Due Date: October 24, 2017 11:59:59 PM

	Description: This program reads in 4 integers from the user, calls a
					 function to perform some arithmetic on the numbers, and
				  	 then prints out the result.

	r4 = i
	r5 = sp
	r0-r3 = a-d
	r6 = temporary variable to store result of "compute"
*/

	 .file "main.s"
    .text
	 .global main
    .type main, %function

main:
   push {lr}

	sub sp, sp, #16	 //reserve space for 4 integers on stack
	mov r4, #0  		 // i = 0
	mov r5, sp			 // assign stack pointer to r5

loop:						 // loop to read in 2 numbers at a time
	ldr r0, =rdfmt		 // load read format string into r0
	mov r1, r5			 // r1 contains pointer to sp
	add r5, r5, #4     // increment sp
	mov r2, r5         // r1 contains pointer to sp + 4
	add r5, r5, #4     // increment sp
	bl scanf				 // call scanf to get user input

   add r4, r4, #1     // increment i
   cmp r4, #2			 // compare to loop condition
	blt loop				 // exit loop once it has run twice

	sub r5, r5, #4
	ldr r3, [r5]		 // load registers with values from the stack
	sub r5, r5, #4		 // decrement sp
	ldr r2, [r5]
	sub r5, r5, #4
	ldr r1, [r5]
	sub r5, r5, #4
	ldr r0, [r5]

	bl compute		    // call compute function

   mov r6, r0         // save returned value in local variable

   ldr r0, =prtfmt1   // load registers for printf
   ldr r1, [r5]
   add r5, r5, #4
   ldr r2, [r5]
   add r5, r5, #4
	ldr r3, [r5]
	add r5, r5, #4
   bl printf			 // call printf

   ldr r0, =prtfmt2
   ldr r1, [r5]
   add r5, r5, #4
   mov r2, r6
   bl printf

	add sp, sp, #16    // give back memory
	pop {pc}


    .size main, .-main
    .section .rodata
rdfmt:   .asciz "%d %d"
prtfmt1: .asciz "\n%d*%d + %d"  // break up print strings because printf
prtfmt2: .asciz "*%d = %d\n\n"  // only uses r0-r3
