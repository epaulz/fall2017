/*
	Eric Paulz (epaulz) & Alex Wessinger (awessin)
	CPSC 2310-002
	Program 4
	Due Date: Monday, November 13, 11:59:59 pm

	Description:  the 'encode' function takes in an input string,
					  output string, key string and an integer as
					  parameters.  based on the integer given, it
					  will either create an encoded message or
					  decode an encrypted message using the key.
*/

// rename registers
input_str  .req r0
output_str .req r1
key_str    .req r2
switch     .req r3
i          .req r4
j		     .req r5
curr_char  .req r6
key_char   .req r7

		.file "encode.s"
		.section .text
		.global encode
		.type encode, %function

encode:
	push {r4-r7, lr}  // save registers
	mov i, #0
	mov j, #0

loop:
	ldrb curr_char, [input_str, i]  //load one byte of input string to curr_char
	ldrb key_char, [key_str, j]     //load one byte of key string to key_char

	// if char from input_str is '\0' then we are done
	cmp curr_char, #0x00
	beq done
	
	// if char from input_str OR from key_str is a space, use
	// the char from input_str
	cmp curr_char, #0x20
	beq next_char
	cmp key_char, #0x20
	beq next_char
	
	// if char from key_str is '\0' then reset j counter to zero
	cmp key_char, #0x00
	bne id_switch
	
	mov j, #0
	ldrb key_char, [key_str, j]

// identify if we need to encode or decode
id_switch:
	cmp switch, #1
	beq decode_proc

encode_proc:
	// subtract 'a' from both
	sub curr_char, curr_char, #0x60
	sub key_char, key_char, #0x60    
	

	add curr_char, curr_char, key_char     // add them together
	cmp curr_char, #26                     // compare to 26
	ble add_a                              // if less or equal, continue

	sub curr_char, curr_char, #26				// if greater, add 26 and continue
	b add_a

// same as encode_proc except check if it's less than or equal to zero.
// if so, add 26 and continue
decode_proc:
	sub curr_char, curr_char, #0x60			
	sub key_char, key_char, #0x60

	sub curr_char, curr_char, key_char
	cmp curr_char, #0
	bge add_a

	add curr_char, curr_char, #26

// add 'a' back to curr_char
add_a:
	add curr_char, curr_char, #0x60

// place curr_char into output_str, increment counters, then loop
next_char:
	strb curr_char, [output_str, i]

	add i, i, #1
	add j, j, #1
	b loop

done:
	// this strb will place '\0' at the end of the string when
	// the loop is finished
	strb curr_char, [output_str, i]

	.unreq input_str
	.unreq output_str
	.unreq key_str
	.unreq switch
	.unreq i
	.unreq j
	.unreq curr_char
	.unreq key_char

	pop {r4-r7, pc}
