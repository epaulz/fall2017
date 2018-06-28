comment(` Eric Paulz (epaulz) 		  ')
comment(` CPSC 2311-003       		  ')
comment(` Lab 2							  ')
comment(` Due Date: September 8, 2017 ')

bipush(-40)				comment(` push -40 onto stack								 ')
istore_1				   comment(` store top of stack into local 1				 ')

label(loop)				comment(` start loop 										 ')
	
	iconst_1				comment(` push one onto stack as local var to print ')
	invokevirtual(1)  comment(` print i												 ')
		
	iload_1				comment(` push i onto stack								 ')
	bipush(9)			comment(` push nine onto stack							 ')
	imul					comment(` multiply i by nine								 ')
	bipush(5)			comment(` push five onto stack							 ')
	idiv					comment(` divide result of i*9 by 5						 ')
	bipush(32)			comment(` push 32 onto stack								 ')
	iadd					comment(` add 32 to result of (i*9)/5					 ')
	istore_2				comment(` store top of stack into local 2				 ')

	iconst_2				comment(` push two onto stack as local var to print ')
	invokevirtual(2)  comment(` print result of conversion					 ')
	

	iinc(1,10)			comment(` increment i by ten each loop					 ')
	iload_1				comment(` push current local i onto stack				 ')
	bipush(120)			comment(` put loop limit onto stack						 ')
	isub					comment(` subtract by limit 								 ')
	ifle(loop)			comment(` if i <=0, loop again							 ')

return
