from selenium import webdriver
from selenium.webdriver.common.action_chains import ActionChains
import time

if __name__ == "__main__":

    brows = webdriver.Chrome()
    brows.implicitly_wait(5)
    brows.get("https://edufpmi.bsu.by/login/index.php")
    brows.find_element_by_css_selector("#username").send_keys("fpm.zhirkeviAY")
    brows.find_element_by_css_selector("#password").send_keys("1923147")
    brows.find_element_by_id("loginbtn").click()
    brows.get("https://edufpmi.bsu.by/mod/bigbluebuttonbn/view.php?id=8842")
    brows.find_element_by_id("join_button_input").click()


    time.sleep(3)
    brows.switch_to_window(brows.window_handles[1])
    brows.find_element_by_xpath("//button[@aria-label='Только слушать']").click()

    # for i in range(5):
    #     time.sleep(0.5)
    #     brows.switch_to_window(brows.window_handles[i+1])
    #     brows.find_element_by_xpath("//button[@aria-label='Только слушать']").click()
    #     brows.switch_to_window(brows.window_handles[0])
    #     brows.find_element_by_id("join_button_input").click()
    input()