/*
	Eric Paulz (epaulz)
	CPSC 2311-003
	Lab 8
*/

	 .file "sum.s"
    .text
    .align  2
    .global main
    .type   main, %function

/* main() sums 10 values from stdin and prints the sum */
main:
    push {lr}    // save lr
    
    sub sp, sp, #4
	 mov r4, #0   // sum
	 mov r5, #0   // i

loop:
	 cmp r5, #10
	 beq done
	 add r5, r5, #1

	 ldr r0, =rdfmt
	 mov r1, sp
	 bl scanf
	 
	 add r0, sp, #0
	 ldr r1, [r0]

	 add r4, r4, r1
	 
	 b loop

done:
	 mov r1, r4
	 ldr r0, =prtfmt
	 bl printf

	 add sp, sp, #4
    pop {pc}         // put lr in pc    

.section    .rodata
    rdfmt:        .asciz "%d"
    prtfmt:       .asciz "sum is %d\n"
