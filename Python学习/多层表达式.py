#利用 3 层for循环的列表生成式，
#找出对称的 3 位数。例如，121 就是对称数，因为从右到左倒过来还是 121。

print ([i*100+m*10+n for i in range(1, 10) for m in range(0, 10) for n in range(0, 10) if i == n])