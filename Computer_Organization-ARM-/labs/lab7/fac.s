/*
	Eric Paulz (epaulz)
	CPSC 2311-003
	Lab 7
*/
	.global	fact
fact:
    push  {r1, lr}        @pushes r1 and lr onto the stack by making a local copy

    cmp   r0, #1          @compare r0 to 1
    beq   done            @if equal, branch to 'done'

    mov  r1, r0           @store r0 in r1
    sub  r0, r0, #1       @subtract r0 by 1 and store in r0
    bl   fact             @call 'fact' function

    mul  r0, r1, r0       @multiply r0 by r1 and store in r0

done:
    pop  {r1, pc}         @pop r1 and pc from the stack
    .size    fact, .-fact

    .align   2
    .section .rodata
fmt_str:
    .ascii   "The factorial of %d is %d\012\000"

    .text
    .align   2
    .global  main
main:
    push {lr}             @ save the link register so we can jump back to 
	
    mov r0, #10 // i
	 mov r1, r0
	 bl fact
	 mov r2, r0

	 ldr r0, =fmt_str
	 bl printf

	 pop  {pc}             @ pop the top of the stack

	.size    main, .-main
	.ident   "GCC: (Ubuntu/Linaro 4.6.3-1ubuntu5) 4.6.3"
	.section .note.GNU-stack,"",%progbits
