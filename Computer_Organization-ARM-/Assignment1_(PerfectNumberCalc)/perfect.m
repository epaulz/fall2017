comment(` Eric Paulz -- epaulz@clemson.edu         ')
comment(` CPSC 2310-002										')
comment(` Program #1											')
comment(` Due Date: Wed., September 6, 11:59:59 pm ')
comment(` This program determines whether or not   ')
comment(` an entered number is a perfect number.   ')

word(N,25)
word(temp,0)
word(sum,0)
word(zero,0)
word(one,1)
word(two,2)
word(i,1)

label(start)

label(loop)

	load(N)			   comment(` if i > N / 2, i cannot be a divisor    ')
	div(two)				comment(` of N. end loop.								 ')
	sub(i)
	blt0(done)

	load(N)		      comment(` divide N by i. 							    ')  
	div(i)	         comment(` if (N / i = temp && temp * i = N) then ')
	mul(i)            comment(` i is a divisor of N. 						 ')
	sub(N)
	beq0(is_divisor)  comment(` branch to is_divisor						 ')

	load(i)				comment(` if i is not a divisor increment		    ')
	add(one)				comment(` and return to start of loop            ')
	store(i)

	ba(loop)

label(is_divisor)
	
	load(sum)			comment(` add i to sum									 ')
	add(i)
	store(sum)

	load(i)
	add(one)				comment(` increment i									 ')
	store(i)

	ba(loop)

label(done)

	load(sum)			comment(` if sum - N = 0 then sum = N				 ')
	sub(N)				comment(` N is a perfect number.  return 1		 ')
	bne0(false)			comment(` else, return 0								 ')

	print(one)
	halt

label(false)

	print(zero)
	halt

	end(start)		   comment(` start execution at label start         ')
	
