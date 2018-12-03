d = {
    'Adam': 95,
    'Lisa': 85,
    'Bart': 59
}
print ('Adam:',d.get('Adam'))
print ('Lisa:',d['Lisa'])
print ('Bart:',d['Bart'])
print ('------------------')

for x in d:
	print(x+':',d.get(x))