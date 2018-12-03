#针对下面的set，给定一个list，对list中的每一个元素，
#如果在set中，就将其删除，如果不在set中，就添加进去。
#s = set(['Adam', 'Lisa', 'Paul'])
#L = ['Adam', 'Lisa', 'Bart', 'Paul']

s = set(['Adam', 'Lisa', 'Paul'])
L = ['Adam', 'Lisa', 'Bart', 'Paul']

for name in L:
	if name in s:
		s.remove(name)
	else:
		s.add(name)
print (s)