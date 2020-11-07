import VideoCapture as vc
import telebot
from telebot import types
from PIL import Image
import datetime
import os
import time
from capture import Capture
import requests
import playsound

MAIN_ADMIN_USERNAME = "alexzhirkevich"


btnPhoto = "📸 Фото с камеры"
btnClip = "📹 Видео с камеры"
btnMic = "🎤 Отправить голосовуху"
strBusy = "Камера занята"
strSendAudio = "Запиши голосовуху или отправь звук"

cap = Capture()
TOKEN = "1177045366:AAHjAKQRkxas7d6YWDe_bAwj8Mm508OgiUA"

bot = telebot.AsyncTeleBot(TOKEN)

forceReply = types.ForceReply()
btn = types.ReplyKeyboardMarkup(resize_keyboard=True)
btn.add(types.KeyboardButton(btnPhoto))
btn.add(types.KeyboardButton(btnClip))
btn.add(types.KeyboardButton(btnMic))

allowed_usernames = []



@bot.message_handler(commands = ['start', 'help'])
def qq(message):
    msg = f"Привет, {message.from_user.first_name}!"
    kb = None
    if (message.from_user.username and message.from_user.username in allowed_usernames):
        kb = btn
    else:
        msg += " У тебя нет доступа к этой камере"
    bot.send_message(message.chat.id,msg,reply_markup = kb)

@bot.message_handler(content_types=["text"])
def qr(message):

    if (message.reply_to_message and message.from_user.username in allowed_usernames):
        if (message.reply_to_message.text == "Секунд"):
            if (cap.busy):
                bot.send_message(message.chat.id,strBusy,reply_markup = btn)
                return
            sec = int(message.text)
            if (sec<1 or sec>60):
                bot.send_message(message.chat.id, "Количество секунд - от 1 до 60" ,reply_markup = btn)
                return
            t = datetime.datetime.now().time()
            name = str(t.hour) + str(t.minute) + str(t.second) + str(t.microsecond) + ".mp4"
            bot.send_message(message.chat.id,"Запись начата",reply_markup = btn)
            cap.clip(name,sec)
            with open(name,"rb") as v:
                bot.send_video(message.chat.id,v,reply_markup = btn)
                time.sleep(3)
            os.remove(name)
            return

    if (message.from_user.username and message.from_user.username in allowed_usernames):
        if (message.text == btnPhoto):
            if (cap.busy):
                bot.send_message(message.chat.id,strBusy,reply_markup = btn)
                return
            bot.send_photo(message.chat.id,cap.screen(),reply_markup=btn)
        elif (message.text == btnClip):
            bot.send_message(message.chat.id,"Секунд",reply_markup = forceReply)
        elif (message.text == btnMic):
            bot.send_message(message.chat.id,strSendAudio,reply_markup = forceReply)
        else:
            bot.send_message(message.chat.id,"?",reply_markup = btn)
    else:
        bot.send_message(message.chat.id,"У тебя нет доступа к этой камере")


@bot.message_handler(content_types = ['voice','audio'])
def audio(message):
    if (message.reply_to_message):
        if (message.reply_to_message.text == strSendAudio):
            if (message.voice):
                file_info = bot.get_file(message.voice.file_id)
                file_info.wait()
                file = requests.get('https://api.telegram.org/file/bot{0}/{1}'.format(TOKEN, file_info.result.file_path))

                with open('voice.mp3','wb') as f:
                    f.write(file.content)
                
                playsound.playsound('voice.mp3')



if __name__ == "__main__":
    with open("allowed_usernames.txt") as f:    
        allowed_usernames = f.readlines()

    bot.polling()