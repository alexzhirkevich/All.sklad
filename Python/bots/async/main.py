import telebot
import time
import asyncio

bot = telebot.AsyncTeleBot("1177045366:AAHjAKQRkxas7d6YWDe_bAwj8Mm508OgiUA")

@bot.message_handler(content_types=['text'])
@telebot.util.async_dec()
def answer(message):
    bot.send_message(message.chat.id, "Wait for 5 sec")
    time.sleep(30)
    bot.send_message(message.chat.id, "End")

bot.polling(none_stop=True)