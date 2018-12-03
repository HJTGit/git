sum = 0
x = 0
while True:
	x = x + 1
	if x > 100:
		break
	if x % 2 == 0:
		continue
	sum = sum + x
print ("100以内奇数和为:", sum)