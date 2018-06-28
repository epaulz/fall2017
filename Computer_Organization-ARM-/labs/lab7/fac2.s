/*
	Eric Paulz (epaulz)
	CPSC 2311-003
	Lab 7
*/
	.global	fact
fact:
	mov   r1, #1       @store 1 in r1 

here:
	cmp   r0, #1       @compares r0 to 1
	beq   there        @if equal, call 'there' function

	mul   r1, r0, r1   @else, multiply r0 * r1 and store in r1
	sub   r0, r0, #1   @subtract 1 from r0 and store in r0 
	b     here         @branch always to 'here' 

there:
	mov   r0, r1       @store r1 in r0

	bx    lr

	.size	fact, .-fact

	.align   2
	.section .rodata
fmt_str:
	.ascii  "The factorial of %d is %d\012\000"

	.text
	.align  2
	.global main
main:
	push  {lr}

	mov r0, #10 // i
	mov r4, r0
	bl fact
	mov r2, r0
	mov r1, r4

	ldr r0, =fmt_str
	bl printf

	pop  {pc}

	.size    main, .-main
	.ident   "GCC: (Ubuntu/Linaro 4.6.3-1ubuntu5) 4.6.3"
	.section .note.GNU-stack,"",%progbits
