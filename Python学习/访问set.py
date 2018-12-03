#由于上述set不能识别小写的名字，请改进set，使得 'adam' 和 'bart'都能返回True。
L = ['Adam', 'Lisa', 'Bart', 'Paul', 'adam', 'lisa', 'bart']
s  = set(L)
print (s)
print ("Bart' in s ?",'Bart' in s)
print ("bart' in s ?",'bart' in s)
print ("Bill' in s ?",'Bill' in s)