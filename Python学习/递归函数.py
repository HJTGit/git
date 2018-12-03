#阶乘
def fact(n):
	if n == 1:
		return 1
	else:
		return n * fact(n-1)

print (fact(5))

#汉诺塔 (http://baike.baidu.com/view/191666.htm) 的移动也可以看做是递归函数。
#我们对柱子编号为a, b, c，将所有圆盘从a移到c可以描述为：
#如果a只有一个圆盘，可以直接移动到c；
#如果a有N个圆盘，可以看成a有1个圆盘（底盘） + (N-1)个圆盘，首先需要把 (N-1) 个圆盘移动到 b，
#然后，将 a的最后一个圆盘移动到c，再将b的(N-1)个圆盘移动到c。
#请编写一个函数，给定输入 n, a, b, c，打印出移动的步骤：
#move(n, a, b, c)
#例如，输入 move(2, 'A', 'B', 'C')，打印出：

#A --> B
#A --> C
#B --> C
#做个假设比如n=3（即a中有123三个圆盘n>1）时
#那么就把12先看作一个整体(即N-1)，先把1,2通过c移到b中即move(n-1, a, c, b)
#这样我们就可以把最大的数字3从a移到c了即输出print a, '-->', c
#接着就是12已经在b中了，3在c中而a并没有圆盘，
#这时候我们输出move(n-1, b, a, c)把a和b位置调换就是正确的了，
#即把b中的12通过a移到c中（3已经在c中我们就不鸟它了）。
#就这样一直递归到n==1时，我们再输出print a, '-->', c，然后结束。
print('汉诺塔')
def move(n, a, b, c):
	if n == 1:
		print (a, '----->', c)
		return
	else:
		move(n-1, a, c, b)#先看作一个整体(即N-1)，通过c移到b
		print (a, '----->', c)#这样我们就可以把最大的数字从a移到c了
		move(n-1, b, a, c)
n = input("请输入塔A的数量:")
move(int(n), 'AAA', 'BBB', 'CCC')

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		