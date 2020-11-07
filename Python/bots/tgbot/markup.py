from telebot import types

#тексты кнопачег и сообщений
btnTextParaNow = "📚 След. пара"
btnTextParaToday = "📋 Пары сегодня"
btnTextParaTomorrow = "🗓 Пары завтра"
btnTextChooseDay = "📆 Выбрать день"
btnTextAdminPanel = "⚙️ Админ-панель"
btnTextAddAdmin = "➕ Дать админа"
textAddAdminReply = "➕ Введи имя пользователя. Для отмены закрой reply-сообщение и отправь любой текст"
btnTextDelAdmin = "➖ Удалить админа"
textDelAdminReply = "➖ Введи имя пользователя. Для отмены закрой reply-сообщение и отправь любой текст"
btnTextAdminList = "📃 Список админов"
btnTextBack ="🔙 Назад"
btnTextUserList = "📜 Список юзеров"
btnTextChangeParlist = "🖍 Изм. список пар"
btnTextGlobalMessage = "🌎 Сообщение всем"
textGlobalMessageReply = "🌎 Введи сообщение. Для отмены закрой reply-сообщение и отправь любой текст"
textChangeParaDay = "⚒ День недели?"
textChangeParaNumber = "⚒ Номер пары?"
textChangeParaReply =  '⚒ Введи новое название пары ("Пара (аудитория)" или "-", если пары нет). Для отмены закрой reply-сообщение и отправь любой текст'


#клавиатуры
markup = types.ReplyKeyboardMarkup(resize_keyboard=True)
admin_markup = types.ReplyKeyboardMarkup(resize_keyboard=True)
admin_panel_markup = types.ReplyKeyboardMarkup(resize_keyboard=True)
inline_markup_paraday = types.InlineKeyboardMarkup(row_width = 2)
inline_markup_paranumber = types.InlineKeyboardMarkup(row_width = 2)
inline_markup_chooseday = types.InlineKeyboardMarkup(row_width = 2)
forcereply = types.ForceReply(selective=True)

btn1 = types.KeyboardButton(btnTextParaNow)
btn2 = types.KeyboardButton(btnTextParaToday)
btn3 = types.KeyboardButton(btnTextParaTomorrow)
btn4 = types.KeyboardButton(btnTextChooseDay)
admin_btn = types.KeyboardButton(btnTextAdminPanel)
admin_btn1 = types.KeyboardButton(btnTextAddAdmin)
admin_btn2 = types.KeyboardButton(btnTextDelAdmin)
admin_btn3 = types.KeyboardButton(btnTextChangeParlist)
admin_btn4 = types.KeyboardButton(btnTextUserList)
admin_btn5 = types.KeyboardButton(btnTextAdminList)
admin_btn6 = types.KeyboardButton(btnTextBack)
admin_btn7 = types.KeyboardButton(btnTextGlobalMessage)
markup.add(btn1,btn2,btn3,btn4)
admin_markup.add(btn1,btn2,btn3,btn4,admin_btn)
admin_panel_markup.add(admin_btn1,admin_btn2,admin_btn3,admin_btn4,admin_btn5,admin_btn6,admin_btn7)
paraday_btn1= types.InlineKeyboardButton("Пн",callback_data='pn')
paraday_btn2= types.InlineKeyboardButton("Вт",callback_data='vt')
paraday_btn3= types.InlineKeyboardButton("Ср",callback_data='sr')
paraday_btn4= types.InlineKeyboardButton("Чт",callback_data='ch')
paraday_btn5= types.InlineKeyboardButton("Пт",callback_data='pt')
paraday_btn6= types.InlineKeyboardButton("Сб",callback_data='sb')
paraday_btn7 = types.InlineKeyboardButton('❌ Отмена',callback_data ="back1")
number_btn1= types.InlineKeyboardButton("1",callback_data='one')
number_btn2= types.InlineKeyboardButton("2",callback_data='two')
number_btn3= types.InlineKeyboardButton("3",callback_data='three')
number_btn4= types.InlineKeyboardButton("4",callback_data='four')
number_btn5= types.InlineKeyboardButton("5",callback_data='five')
number_btn6= types.InlineKeyboardButton("⬅️",callback_data = 'back2')
day_btn1 = types.InlineKeyboardButton("Пн",callback_data = '0')
day_btn2 = types.InlineKeyboardButton("Вт",callback_data = '1')
day_btn3 = types.InlineKeyboardButton("Ср",callback_data = '2')
day_btn4 = types.InlineKeyboardButton("Чт",callback_data = '3')
day_btn5 = types.InlineKeyboardButton("Пт",callback_data = '4')
day_btn6 = types.InlineKeyboardButton("Сб",callback_data = '5')
day_btn7 = types.InlineKeyboardButton("Полностью",callback_data = 'full')
inline_markup_paraday.add(paraday_btn1,paraday_btn2,paraday_btn3,paraday_btn4,paraday_btn5,paraday_btn6,paraday_btn7)
inline_markup_paranumber.add(number_btn1,number_btn2,number_btn3,number_btn4,number_btn5,number_btn6)
inline_markup_chooseday.add(day_btn1,day_btn2,day_btn3,day_btn4,day_btn5,day_btn6,day_btn7)