#接受可变参数的 average() 函数

def average(*args):
	s = 0.0
	for n in args:
		s = s + n
	if len(args) == 0:
		return s
	else:
		return s/len(args)
	
print (average())
print (average(1, 2))
print (average(1, 2, 2, 3, 4))