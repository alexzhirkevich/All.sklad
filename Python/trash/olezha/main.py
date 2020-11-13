import speech_recognition as sr
from selenium import webdriver
from selenium.webdriver.common.action_chains import ActionChains
import time
from fuzzywuzzy import fuzz 
import requests
import os
from typing import ByteString
import threading
import webbrowser
import pyttsx3 as pt

engine = pt.init()

def reg_na_progu():
    
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

    input()

def say(what:ByteString):
    engine.say(what)
    engine.runAndWait()


def processCommand(command:ByteString):
   
    print(command)
    if (fuzz.ratio(command,"олежа включи социологию")>80 or fuzz.ratio(command,"включи социологию")>80):
        reg_na_progu()
    elif (fuzz.ratio(command,"олежа выключи комп")>80 or fuzz.ratio(command,"выключи комп")>80):
        os.system("shutdown /s")
        say("выключение через 1 минуту")

    elif (fuzz.ratio(command,"олежа открой YouTube")>80 or fuzz.ratio(command,"открой YouTube")>80):
        webbrowser.get(using='windows-default').open_new_tab('https://youtube.com')
        say("подавись")
    
    

def main():
    rec = sr.Recognizer()

    with sr.Microphone() as source:
        while(True):
            speech = rec.listen(source)
            try:
                command = rec.recognize_google(speech,language="ru-RU")
                
                processCommand(command)
                
            except:
                print("error")

if __name__ == "__main__":
    main()