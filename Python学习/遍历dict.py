#请用 for 循环遍历如下的dict，打印出 name: score 来。
d = {
	'Adam' : 95,
	'Lisa' : 85,
	'Bart' : 59
}
for key in d:
	print (key + ":",d[key])
print ('----------------')
for key in d:
	print (key + ":"+ str(d[key]))
print ('----------------')
