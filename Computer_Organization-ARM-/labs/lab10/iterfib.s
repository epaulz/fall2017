/*
	Eric Paulz (epaulz)
	CPSC 2311-003
	Lab 10

	QUESTIONS:
	1. 46
	2. Yes, it still displays correctly because we are using small numbers
	   that do not affect the sign of the binary code.
	3. fib.s average:      8.126 seconds
		iterfib.s average:  0.146 seconds
		
		The iterative version is 7.98 seconds or about 557 times faster that
		the recursive version.
*/

	.text
	.align	2
fib:
	push    {r4}     @ save any register used besides {r0-r3}

	@ CODE HERE
	mov r1, #0
	mov r2, #1

loop:
	cmp r0, #0
	beq done
	
	add r3, r1, r2
	mov r1, r2
	mov r2, r3
	sub r0, r0, #1
	b loop

done:

	mov r0, r1
	pop    {r4}      @ restore any register used besides {r0-r3}
	bx     lr        @ return to caller

test:
    push   {r4, lr}

    @ call fib on given number
	mov    r4, r0
	bl     fib

	@ print the answer
	mov    r1, r4
	mov    r2, r0
	ldr    r0, =.LC0
	bl     printf

	pop    {r4, pc}
	
	.global main
main:
	push   {r4, lr}

	@ call 4 test cases

	mov    r0, #0
	bl     test

	mov    r0, #1
	bl     test

	mov    r0, #6
	bl     test

	mov    r0, #10
	bl     test

	pop    {r4,  pc}

	.section .rodata
	.align 2
.LC0:
	.ascii "Element %d (zero-based) in the Fibonacci sequence is %d\012\000"
