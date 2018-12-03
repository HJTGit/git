#月份也可以用set表示，请设计一个set并判断用户输入的月份是否有效。
#月份可以用字符串'Jan', 'Feb', ...表示。
months = set(['January','February','March','April','May','June','July','August','September','October','November','December'])

x1 = input("请输入月份：")

if x1 in months:
    print (x1+":"+'ok')
else:
    print (x1+":"+'error')
