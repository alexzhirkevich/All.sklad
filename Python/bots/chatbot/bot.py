import telebot
import config
import sqlighter

bot = telebot.TeleBot(config.TOKEN)
database = sqlighter.SQLighter("database.db")

btnM = telebot.types.InlineKeyboardButton("🧔",callback_data = 0)
btnW = telebot.types.InlineKeyboardButton("👩‍🦰",callback_data= 1)
chooseSexMarkup = telebot.types.InlineKeyboardMarkup()
chooseSexMarkup.add(btnM,btnW)

@bot.message_handler(commands = ["start","help"])
def hello(message):
    if (not database.user_exists(message.from_user.id)):
        database.add_user(message.from_user.id)
    bot.send_message(message.chat_id,"Укажите ваш пол",reply_markup=chooseSexMarkup)

@bot.callback_query_handler
def callback(call):
    if (call.data in [0,1]):
        if (call.message.text == "Укажите ваш пол"):
            database.set_sex(call.message.chat_id,call.data)
            _id = call.message.chat_id
            bot.delete_message(call.message.chat_id,call.message.id)
            bot.send_message(_id,"Укажите пол желаемого собеседника",reply_markup=chooseSexMarkup)
        else:
            database.set_partner_sex(call.message.chat_id,call.data)
            _id = call.message.chat_id
            bot.delete_message(call.message.chat_id,call.message.id)
            bot.send_message(_id,"[/find] - начать поиск")


if __name__ == "__main__":
    while True:
        try:
            bot.polling(none_stop=True,timeout=30)
        except Exception as e:
            print(repr(e))
            continue

        
