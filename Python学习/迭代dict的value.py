#给定一个dict：

#d = { 'Adam': 95, 'Lisa': 85, 'Bart': 59, 'Paul': 74 }

#请计算所有同学的平均分。

d = {'Adam': 95, 'Lisa': 85, 'Bart': 59, 'Paul': 74}
print (d.values())
num = 0
for i in d.values():
	num = num + i
print ('平均分：', num/len(d))