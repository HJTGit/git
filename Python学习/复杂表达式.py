#在生成的表格中，对于没有及格的同学，请把分数标记为红色。

#提示：红色可以用 <td style="color:red"> 实现。

def generate_tr(name, score):
	if score < 60:
		return '<tr><td>%s</td><td style="color:red">%s</td></tr>' % (name, score)
	else:
		return '<tr><td>%s</td><td>%s</td></tr>' % (name, score)
		
d = { 'Adam': 95, 'Lisa': 85, 'Bart': 59}
tds = [generate_tr(name, score) for name, score in d.items()]
print ('<table border="1">')
print ('<tr><th>Name</th><th>Score</th><tr>')
print ('\n'.join(tds))
print ('</table>')