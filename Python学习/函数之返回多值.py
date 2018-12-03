#一元二次方程的定义是：ax² + bx + c = 0

#请编写一个函数，返回一元二次方程的两个解。

#注意：Python的math包提供了sqrt()函数用于计算平方根。

import math

def quadratic_equation(a,b,c):
	t = math.sqrt(b * b - 4 * a * c)
	return (-b + t)/(2 * a) , (-b - t)/(2 * a)

print ("2x^2+3x+0=0的解为:",quadratic_equation(2, 3, 0))
print ("x^2-6x+5=0的解为:",quadratic_equation(1, -6, 5))