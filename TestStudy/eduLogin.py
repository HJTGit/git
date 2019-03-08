from selenium import webdriver
import time
browser = webdriver.Chrome()
url = 'http://edu.childevp.com'
browser.get(url)
name = browser.find_element_by_xpath('/html/body/div/div/div/div[2]/form/div[1]/div/div/input')#找到搜索框
name.send_keys('ceshi1')#传送入关键词
pwd = browser.find_element_by_xpath('/html/body/div/div/div/div[2]/form/div[2]/div/div/input')
pwd.send_keys('123456')
button = browser.find_element_by_xpath('/html/body/div/div/div/div[2]/form/div[3]/div/button[1]')#找到搜索按钮
button.click()
#browser.set_window_size(1366,768)
browser.maximize_window()
time.sleep(1)
browser.find_element_by_xpath('/html/body/div/div[2]/div/div[1]/ul/li[5]/div').click()#找到评估工具按钮,并点击
time.sleep(1)
browser.find_element_by_xpath('/html/body/div/div[2]/div/div[1]/ul/li[5]/ul/li[2]').click()#点击评估报告
time.sleep(1)
browser.find_element_by_xpath('/html/body/div/div[2]/div/div[3]/div[2]/div/div[1]').click()#点击幼儿评估报告
time.sleep(1)
browser.find_element_by_xpath('/html/body/div/div[2]/div/div[3]/div[2]/div/div/div[3]/div/div[1]/div[3]/div').click()#进入报告详情页
time.sleep(1)
browser.find_element_by_xpath('//*[@id="warp_container"]/div[1]/div/div[2]/div/div/div/div[2]/div[1]/input').click()
time.sleep(1)
browser.find_element_by_xpath('/html/body/div[2]/div[1]/div[1]/ul/div/li[2]/span').click()
time.sleep(1)

#browser.close()