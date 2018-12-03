#请定义一个 square_of_sum 函数，它接受一个list，返回list中每个元素平方的和。

def square_of_sum(L):
	sum = 0
	for x in L:
		n = x * x
		sum = sum + n
	return sum
print (square_of_sum([1, 2, 3, 4, 5]))
print (square_of_sum([-5, 0, 5, 15, 25]))