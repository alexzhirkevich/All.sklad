import telebot
from datetime import datetime, timezone, timedelta
import time
import threading
import sqlighter
import config
import markup
import sys
import os



bot = telebot.TeleBot(config.TOKEN)
deltatime = timedelta(hours = 3, minutes = 0, seconds = 0)
deltaday = timedelta(hours = 24, minutes = 0, seconds = 0)

db = sqlighter.SQLighter("db.db")

answer_day = dict()
answer_parnumber = dict()
par_source = list()

pn = ['1', '2', '3', '4', '5']
vt = list(pn)
sr = list(pn)
ch = list(pn)
pt = list(pn)
sb = list(pn)
numbers = ["1️⃣", "2️⃣", "3️⃣", "4️⃣", "5️⃣"]
times = ["08.15 - 09.35", "09.45 - 11.05", "11.15 - 12.35", "13.00 - 14.20", "14.30 - 15.50"]

#проки

def restart():
	os.system('python "restarter.py"')
	sys.exit()

def get_month(month):
	try:
		str_month = ''
		if month == 1:
			str_month = 'января'
		elif month == 2:
			str_month = 'февраля'
		elif month == 3:
			str_month = 'марта'
		elif month == 4:
			str_month = 'апреля'
		elif month == 5:
			str_month == 'мая'
		elif month == 6:
			str_month = 'июня'
		elif month == 7:
			str_month = 'июля'
		elif month == 8:
			str_month = 'августа'
		elif month == 9:
			str_month = 'сентября'
		elif month == 10:
			str_month = 'октября'
		elif month == 11:
			str_month = 'ноября'
		else:
			str_month = 'декабря'
		return str_month
	except Exception as e:
		print("get_month: " + repr(e))
		if (db.get_chat_id(username = config.MAIN_ADMIN_USERNAME) != 0):
			bot.send_message(db.get_chat_id(username = config.MAIN_ADMIN_USERNAME), "❗️❌🆘❌❗️ Возможно пизда рулям:\n get_month: " + repr(e))

def get_weekday(weekday):
	try:
		str_weekday = ''
		if weekday == 0:
			str_weekday = 'Понедельник'
		elif weekday == 1:
			str_weekday = 'Вторник'
		elif weekday == 2:
			str_weekday = 'Среда'
		elif weekday == 3:
			str_weekday = 'Четверг'
		elif weekday == 4:
			str_weekday = 'Пятница'
		elif weekday == 5:
			str_weekday = 'Суббота'
		else:
			str_weekday = 'Воскресенье'
		return str_weekday
	except Exception as e:
		print("get_weekday: " + repr(e))
		if (db.get_chat_id(username = config.MAIN_ADMIN_USERNAME)!= 0):
			bot.send_message(db.get_chat_id(username = config.MAIN_ADMIN_USERNAME), "❗️❌🆘❌❗️ Возможно пизда рулям:\n get_weekday: " + repr(e))

def get_day_par_list(date):
	try:
		if date.day == (datetime.now(timezone.utc)+deltatime).day:
			msg = "Сегодня " +  str(date.day) + " " + get_month(date.month) + ", " + get_weekday(datetime.weekday(date)) + '\nСегодняшние пары:\n'
		else:
			msg = "Завтра " + str(date.day) + " " + get_month(date.month) + ", " + get_weekday(datetime.weekday(date)) + '\nЗавтрашние пары:\n'

		week = datetime.weekday(date)

		if (week == 0):
			for i in pn:
				if (i != '-'):
					msg += (i +'\n')
		elif (week == 1):
			for i in vt:
				if (i != '-'):
					msg += (i +'\n')
		elif (week == 2):
			for i in sr:
				if (i != '-'):
					msg += (i +'\n')
		elif (week == 3):
			for i in ch:
				if (i != '-'):
					msg += (i +'\n')
		elif (week == 4):
			for i in pt:
				if (i != '-'):
					msg += (i +'\n')
		elif (week == 5):
			for i in sb:
				if (i != '-'):
					msg += (i +'\n')
		else:
			if (date.day == (datetime.now(timezone.utc)+deltatime).day):
				msg = "Сегодня пар нет"
			else:
				msg = "Завтра пар нет"
		return msg
	except Exception as e:
		print("get_day_par_list: " + repr(e))
		if (db.get_chat_id(username = config.MAIN_ADMIN_USERNAME)!= 0):
			bot.send_message(db.get_chat_id(username = config.MAIN_ADMIN_USERNAME), "❗️❌🆘❌❗️ Возможно пизда рулям:\n get_day_par_list: " + repr(e))

def get_first_pair_index(par_list):
	try:
		for i in range(0, len(par_list)):
			if par_list[i]!= '-':
				return i
		return (-1)
	except Exception as e:
		print("get_first_pair_index: " + repr(e))
		if (db.get_chat_id(username = config.MAIN_ADMIN_USERNAME)!= 0):
			bot.send_message(db.get_chat_id(username = config.MAIN_ADMIN_USERNAME), "❗️❌🆘❌❗️ Возможно пизда рулям:\n get_first_pair_index: " + repr(e))

def parlist(weekday):
	try:
		if (weekday == 0):
			return pn
		elif (weekday == 1):
			return vt
		elif (weekday == 2):
			return sr
		elif (weekday == 3):
			return ch
		elif (weekday == 4):
			return pt
		elif (weekday == 5):
			return sb
		else:
			return None
	except Exception as e:
		print("parlist: " + repr(e))
		if (db.get_chat_id(username = config.MAIN_ADMIN_USERNAME)!= 0):
			bot.send_message(db.get_chat_id(username = config.MAIN_ADMIN_USERNAME), "❗️❌🆘❌❗️ Возможно пизда рулям:\n parlist: " + repr(e))

def get_para_now():
	try:
		current_time = datetime.time(datetime.now(timezone.utc) + deltatime)
		par_number = 0
		if (datetime.weekday(datetime.now(timezone.utc)+deltatime) == 6):
			return ("Сегодня нет пар")

		if (current_time.hour < 8 or (current_time.hour == 8 and current_time.minute <= 20)):
			par_number = 0
		elif (current_time.hour < 9 or (current_time.hour == 9 and current_time.minute <= 50)):
			par_number = 1
		elif (current_time.hour < 11 or (current_time.hour == 11 and current_time.minute <= 20)):
			par_number = 2
		elif (current_time.hour < 13 or (current_time.hour == 13 and current_time.minute <= 5)):
			par_number = 3
		elif (current_time.hour < 14 or (current_time.hour == 14 and current_time.minute <= 35)):
			par_number = 4
		else:
			return ("Сегодня пар больше не будет")

		par_list = parlist(datetime.weekday(datetime.now(timezone.utc)+deltatime))

		if (par_number>len(par_list)):
			return ("Сегодня пар больше не будет")
		elif (par_list[par_number] == '-'):
			for i in range(par_number+1, len(par_list)):
				if (par_list[i]!= '-') and (get_first_pair_index(par_list) != -1):
					if (get_first_pair_index(par_list) == par_number):
						return (f"Первая пара сегодня:\n{par_list[i]}")
					else:
						return (f"Следующая пара:\n{par_list[i]}")
			return ("Сегодня пар больше не будет")
		elif (par_number == get_first_pair_index(par_list)):
			return (f"Первая пара сегодня:\n{par_list[par_number]}")
		else:
			return (f"Следующая пара:\n{par_list[par_number]}")

		return ("Сегодня пар больше не будет")
	except Exception as e:
		print("get_para_now: " + repr(e))
		if (db.get_chat_id(username = config.MAIN_ADMIN_USERNAME)!= 0):
			bot.send_message(db.get_chat_id(username = config.MAIN_ADMIN_USERNAME), "❗️❌🆘❌❗️ Возможно пизда рулям:\n get_para_now: " + repr(e))

def check_new_user(message):
	try:
		if (not db.user_exists(message.from_user.id)):
			if (message.from_user.username):
				username = message.from_user.username
			else:
				username = "user" + str(message.from_user.id)
			db.add_user(message.from_user.id, username, message.chat.id)
		else:
			db.set_chat_valid(message.from_user.id, True)

	except Exception as e:
		print("check_new_user: " + repr(e))
		if (db.get_chat_id(username = config.MAIN_ADMIN_USERNAME)!= 0):
			bot.send_message(db.get_chat_id(username = config.MAIN_ADMIN_USERNAME), "❗️❌🆘❌❗️ Возможно пизда рулям:\n check_new_user: " + repr(e))

def update_user_data(message):
	try:
		if (message.from_user.username):
			name = message.from_user.username
		else:
			name = "user" + str(message.from_user.id)
		if (not db.user_exists(tg_id = message.from_user.id)):
			db.add_user(message.from_user.id, name, message.chat.id)
		else:
			if (db.get_username(message.from_user.id) != name):
				db.set_username(message.from_user.id, name)
		db.set_chat_valid(message.from_user.id, True)

	except Exception as e:
		print("update_user_data: " + repr(e))
		if (db.get_chat_id(username = config.MAIN_ADMIN_USERNAME)!= 0):
			bot.send_message(db.get_chat_id(username = config.MAIN_ADMIN_USERNAME), "❗️❌🆘❌❗️ Возможно пизда рулям:\n update_user_data: " + repr(e))

def read_parlist():
	try:

		with open("parlist.txt", "r", True, "utf-8") as parlist_file:
			for  day in (pn, vt, sr, ch, pt, sb):
				for para in range(0, 5):
					newpara = parlist_file.readline()
					par_source.insert(len(par_source), newpara)
					newpara = newpara[:-1]
					if (newpara ==  '-'):
						day[para] = newpara
					else:
						day[para] = numbers[para] + ' | ' + times[para] + ' | ' + newpara
	except Exception as e:
		print("read_parlist: " + repr(e))
		if (db.get_chat_id(username = config.MAIN_ADMIN_USERNAME) != 0):
			bot.send_message(db.get_chat_id(username = config.MAIN_ADMIN_USERNAME), "❗️❌🆘❌❗️ Возможно пизда рулям:\n read_parlist: " + repr(e))

def add_admin(username, changer_username):
	try:
		db.set_admin(username = username, is_admin = True)
		print("Admin added: " + username + " By: @" + changer_username)
		if (db.get_chat_id(username = username) != 0):
			bot.send_message(db.get_chat_id(username = username), "@" + changer_username + " выдал вам админ-права", reply_markup = markup.admin_markup)
	except Exception as e:
		print("add_admin: " + repr(e))
		if (db.get_chat_id(username = config.MAIN_ADMIN_USERNAME) != 0):
			bot.send_message(db.get_chat_id(username = config.MAIN_ADMIN_USERNAME), "❗️❌🆘❌❗️ Возможно пизда рулям:\n add_admin: " + repr(e))

def remove_admin(username, changer_username):
	try:
		db.set_admin(username = username, is_admin = False)
		print("Admin removed: " + username + " By: @" + changer_username)
		if (db.get_chat_id(username = username) != 0):
			bot.send_message(db.get_chat_id(username = username), "@" + changer_username + " снял с вас админ-права", reply_markup = markup.markup)
	except Exception as e:
		print("add_admin: " + repr(e))
		if (db.get_chat_id(username = config.MAIN_ADMIN_USERNAME) != 0):
			bot.send_message(db.get_chat_id(username = config.MAIN_ADMIN_USERNAME), "❗️❌🆘❌❗️ Возможно пизда рулям:\n add_admin: " + repr(e))

def get_cur_markup(id):
	try:
		if (db.is_admin(tg_id = id)):
			return markup.admin_markup
		else:
			return markup.markup
	except Exception as e:
		print("remove_admin: " + repr(e))
		if (db.get_chat_id(username = config.MAIN_ADMIN_USERNAME) != 0):
			bot.send_message(db.get_chat_id(username = config.MAIN_ADMIN_USERNAME), "❗️❌🆘❌❗️ Возможно пизда рулям:\n get_cur_markup: " + repr(e))

def global_message(msg, parseMode = "HTML", show_prefix = True, start = 0):
	index = start
	try:
		if (show_prefix):
			msg_start = "[❗️]: "
		else:
			msg_start = ' '

		ids = db.get_all_chat_ids()
		if (start>=len(ids)):
			return
		for i in range(start,len(ids)):
			index = i
			if not (not show_prefix and not db.get_notifications_enabled(ids[i])):
				bot.send_message(ids[i], msg_start + msg, parse_mode = parseMode, reply_markup = get_cur_markup(ids[i]))

	except Exception as e:
		if (index < len(db.get_all_chat_ids())):
			db.set_chat_valid(tg_id = ids[index], chat_valid = False)
			global_message(msg, index)

def pair_exists(weekday, par_number):
	try:
		par_list = parlist(weekday)
		if (par_list):
			return (par_list[par_number-1] != '-')
		return False
	except Exception as e:
		print("pair_exists: " + repr(e))
		if (db.get_chat_id(username = config.MAIN_ADMIN_USERNAME) != 0):
			bot.send_message(db.get_chat_id(username = config.MAIN_ADMIN_USERNAME), "❗️❌🆘❌❗️ Возможно пизда рулям:\n pair_exists: " + repr(e))

def start_notifications():
	try:
		t  = (datetime.now(timezone.utc) + deltatime)
		while True:
			try:
				t  = (datetime.now(timezone.utc) + deltatime)
				if ((t.hour == 8 and t.minute == 0 and pair_exists(datetime.weekday(t), 1))
					or (t.hour == 9 and t.minute == 35 and pair_exists(datetime.weekday(t), 2))
					or (t.hour == 11 and t.minute == 5 and pair_exists(datetime.weekday(t), 3))
					or (t.hour == 12 and t.minute == 35 and pair_exists(datetime.weekday(t), 4))
					or (t.hour == 14 and t.minute == 20 and pair_exists(datetime.weekday(t), 5))):
					global_message(get_para_now(),show_prefix = False)
				time.sleep(60)
			except:
				time.sleep(60)
				continue

	except Exception as e:
		print("start_notifications: " + repr(e))
		if (db.get_chat_id(username = config.MAIN_ADMIN_USERNAME) != 0):
			bot.send_message(db.get_chat_id(username = config.MAIN_ADMIN_USERNAME), "❗️❌🆘❌❗️ Возможно пизда рулям:\n start_notifications: " + repr(e))

def bot_pooling():
	while True:
		try:
			print("Bot started...")
			bot.polling(timeout = 30,none_stop=True)
		except:
			print("[!] RESTART [!]")
			time.sleep(3)
			continue
	bot.stop_polling()
	


######################################################################################################################################################
@bot.message_handler(commands = ['start', 'help'])
@telebot.util.async_dec()
def send_welcome(message):
	try:
		if (message.from_user.username):
			print(f"Got command from user: @{message.from_user.username} | Command: {message.text}")
		else:
			print(f"Got command from user: {str(message.from_user.id)} | Command: {message.text}")

		if (message.text == "/start"):
			check_new_user(message)

		with open('sticker.tgs', 'rb') as sticker:
			bot.send_sticker(message.chat.id, sticker)

		bot.send_message(message.chat.id, f"Привет, <i><b>{message.from_user.first_name}</b></i>! 😉 В конце каждой пары я буду напоминать тебе, где будет следующая. Напоминания можно отключить - /off и включить снова - /on. Вот, что я ещё умею:", parse_mode = 'HTML', reply_markup = get_cur_markup(message.from_user.id))
		print("Reply seccess\n")
	except Exception as e:
		print("send_welcome: " + repr(e))
		if (db.get_chat_id(username = config.MAIN_ADMIN_USERNAME) != 0):
			bot.send_message(db.get_chat_id(username = config.MAIN_ADMIN_USERNAME), "❗️❌🆘❌❗️ Возможно пизда рулям:\n send_welcome: " + repr(e))
######################################################################################################################################################
@bot.message_handler(commands = ['on', 'off'])
@telebot.util.async_dec()
def notifications(message):
	try:
		if (message.from_user.username):
			print(f"Got command from user: @{message.from_user.username} | Command: {message.text}")
		else:
			print(f"Got command from user: {str(message.from_user.id)} | Command: {message.text}")

		if (message.text == '/on'):
			db.set_notifications_enabled(message.from_user.id, True)
		else:
			db.set_notifications_enabled(message.from_user.id, False)

		bot.send_message(message.chat.id, "Настройки сохранены", reply_markup = get_cur_markup(message.from_user.id))
		print("Reply seccess\n")

	except Exception as e:
		print("notifications: " + repr(e))
		if (db.get_chat_id(username = config.MAIN_ADMIN_USERNAME) != 0):
			bot.send_message(db.get_chat_id(username = config.MAIN_ADMIN_USERNAME), "❗️❌🆘❌❗️ Возможно пизда рулям:\n notifications: " + repr(e))
######################################################################################################################################################
@bot.message_handler(content_types = ['text'])
@telebot.util.async_dec()
def text_message(message):

	update_user_data(message)

	try:
		if (message.from_user.username):
			if (message.reply_to_message):
				print(f"Got message from user: @{message.from_user.username} | Text: {message.text} | Reply To: {message.reply_to_message.text}")
			else:
				print(f"Got message from user: @{message.from_user.username} | Text: {message.text}")
		else:
			if (message.reply_to_message):
				print(f"Got message from user: @{str(message.from_user.id)} | Text: {message.text} | Reply To: {message.reply_to_message.text}")
			else:
				print(f"Got message from user: @{str(message.from_user.id)} | Text: {message.text}")

	#reply
		if (message.reply_to_message):
			#ДОБАВЛЕНИЕ АДМИНА ОТВЕТ
			if (db.is_admin(tg_id = message.from_user.id) and (message.reply_to_message.text == markup.textAddAdminReply)):
				if (not db.user_exists(username = message.text)):
					bot.send_message(message.chat.id, message.text + " не найден в списке пользователей", reply_markup = markup.admin_panel_markup)
				elif (db.is_admin(username = message.text)):
					bot.send_message(message.chat.id, message.text + " уже является админом", reply_markup = markup.admin_panel_markup)
				else:
					add_admin(message.text, db.get_username(message.from_user.id))
					if (db.get_chat_id(username = config.MAIN_ADMIN_USERNAME) != 0):
						bot.send_message(db.get_chat_id(username = config.MAIN_ADMIN_USERNAME), "❗️ Admin added: " + message.text + " By: @" + db.get_username(message.from_user.id))
					bot.send_message(message.chat.id, message.text + " выданы админ-права", reply_markup = markup.admin_panel_markup)
			#УДАЛЕНИЕ АДМИНА ОТВЕТ
			elif ((db.is_admin(tg_id = message.from_user.id)) and (message.reply_to_message.text == markup.textDelAdminReply)):
				if (not db.user_exists(username = message.text)):
					bot.send_message(message.chat.id, message.text + " не найден в списке пользователей", reply_markup = markup.admin_panel_markup)
				elif (message.text == config.MAIN_ADMIN_USERNAME):
					bot.send_message(message.chat.id, "Хорошая попытка", reply_markup = get_cur_markup(message.from_user.id))
				elif (not db.is_admin(username = message.text)):
					bot.send_message(message.chat.id, message.text + " не является админом", reply_markup = markup.admin_panel_markup)
				else:
					remove_admin(message.text, db.get_username(message.from_user.id))
					if (db.get_chat_id(username = config.MAIN_ADMIN_USERNAME)!= 0):
						bot.send_message(db.get_chat_id(username = config.MAIN_ADMIN_USERNAME), "❗️ Admin removed: " + message.text+" By: @" + db.get_username(message.from_user.id))
					if (message.from_user.id == db.get_tg_id(message.text)):
						bot.send_message(message.chat.id, "Вы самовыпилились из админ-команды", reply_markup = markup.markup)
					else:
						bot.send_message(message.chat.id, message.text + " больше не админ", reply_markup = markup.admin_panel_markup)
			#ИЗМЕНЕНИЕ СПИСКА ПАР
			elif ((db.is_admin(tg_id = message.from_user.id)) and (message.reply_to_message.text == markup.textChangeParaReply)):
				if ((message.chat.id in answer_day.keys()) and (message.chat.id in answer_parnumber.keys())):
					par_list = parlist(answer_day[message.chat.id])
					if (message.text == '-'):
						par_list[answer_parnumber[message.chat.id]] = '-'
						par_source[answer_day[message.chat.id] * 5 + answer_parnumber[message.chat.id]] = '-\n'
					else:
						par_list[answer_parnumber[message.chat.id]] = f"{numbers[answer_parnumber[message.chat.id]]} | {times[answer_parnumber[message.chat.id]]} | {message.text}"
						par_source[answer_day[message.chat.id] * 5 + answer_parnumber[message.chat.id]] = message.text + '\n'

					with open('parlist.txt', 'w', True, 'utf-8') as parlist_file:
						parlist_file.writelines(par_source)
						parlist_file.close()
						answer_day.pop(message.chat.id)
						answer_parnumber.pop(message.chat.id)

					print("Parlist changed by @" + db.get_username(message.from_user.id))
					if (db.get_chat_id(username = config.MAIN_ADMIN_USERNAME)!= 0):
						bot.send_message(db.get_chat_id(username = config.MAIN_ADMIN_USERNAME), "❗️ Parlist changed by @" + db.get_username(message.from_user.id), reply_markup = markup.admin_panel_markup)
					bot.send_message(message.chat.id, "✅ Пара изменена", reply_markup = markup.admin_panel_markup)
				else:
					bot.send_message(message.chat.id, "❌ Ошибка", reply_markup = markup.admin_panel_markup)
			#ГЛОБАЛЬНОЕ СООБЩЕНИЕ ОТВЕТ
			elif ((db.is_admin(tg_id = message.from_user.id))  and (message.reply_to_message.text == markup.textGlobalMessageReply)):
				global_message(message.text)
				print("Global msg: " + message.text + "\nFrom: @" + db.get_username(message.from_user.id))
				if (db.get_chat_id(username = config.MAIN_ADMIN_USERNAME) != 0):
					bot.send_message(db.get_chat_id(username = config.MAIN_ADMIN_USERNAME), "❗️ Global msg: " + message.text + "\nFrom: @" + db.get_username(message.from_user.id))
			else:
				bot.send_message(message.chat.id, "Выбери действие снизу", reply_markup = get_cur_markup(message.from_user.id))
	#сообщения
		#СЛЕДУЮЩАЯ ПАРА
		elif (message.text == markup.btnTextParaNow):
			bot.send_message(message.chat.id, get_para_now(), reply_markup = get_cur_markup(message.from_user.id))
		#ПРЫ СЕГОДНЯ
		elif (message.text == markup.btnTextParaToday):
			bot.send_message(message.chat.id, get_day_par_list(datetime.now(timezone.utc) + deltatime), reply_markup = get_cur_markup(message.from_user.id))
		#ПАРЫ ЗАВТРА
		elif (message.text == markup.btnTextParaTomorrow):
			bot.send_message(message.chat.id, get_day_par_list(datetime.now(timezone.utc) + deltatime + deltaday), reply_markup = get_cur_markup(message.from_user.id))
		#ВЫБРАТЬ ДЕНЬ
		elif (message.text == markup.btnTextChooseDay):
			bot.reply_to(message, text = "День", reply_markup = markup.inline_markup_chooseday)
		#АДМИН ПАНЕЛЬ
		elif (message.text == markup.btnTextAdminPanel):
			if (db.is_admin(tg_id = message.from_user.id)):
				bot.send_message(message.chat.id, "Ты вошёл в админ-панель. Выбирай действие", reply_markup = markup.admin_panel_markup)
			else:
				bot.send_message(message.chat.id, "Выбери действие снизу", reply_markup = markup.markup)
		#ДОБАВИТЬ АДМИНА
		elif (message.text == markup.btnTextAddAdmin):
			if (db.is_admin(tg_id = message.from_user.id)):
				bot.send_message(message.chat.id, markup.textAddAdminReply, reply_markup = markup.forcereply)
			else:
				bot.send_message(message.chat.id, "Выбери действие снизу", reply_markup = markup.markup)
		#УДАЛИТЬ АДМИНА
		elif (message.text == markup.btnTextDelAdmin):
			if (db.is_admin(tg_id = message.from_user.id)):
				bot.send_message(message.chat.id, markup.textDelAdminReply, reply_markup = markup.forcereply)
			else:
				bot.send_message(message.chat.id, "Выбери действие снизу", reply_markup = markup.markup)
		#СПИСОК ЮЗЕРОВ
		elif (message.text == markup.btnTextUserList):
			if (db.is_admin(tg_id = message.from_user.id)):
				bot.send_message(message.chat.id, "📗 Юзеры:\n@" + "\n@".join(db.get_all_usernames()), reply_markup = markup.admin_panel_markup)
			else:
				bot.send_message(message.chat.id, "Выбери действие снизу", reply_markup = markup.markup)
		#СПИСОК АДМИНОВ
		elif (message.text == markup.btnTextAdminList):
			if (db.is_admin(tg_id = message.from_user.id)):
				bot.send_message(message.chat.id, "📘 Админы:\n@"+"\n@".join(db.get_all_admins()), reply_markup = markup.admin_panel_markup)
			else:
				bot.send_message(message.chat.id, "Выбери действие снизу", reply_markup = markup.markup)
		#ИЗМЕНЕНИЕ СПИСКА ПАР
		elif (message.text == markup.btnTextChangeParlist):
			if (db.is_admin(tg_id = message.from_user.id)):
				bot.send_message(message.chat.id, markup.textChangeParaDay, reply_markup = markup.inline_markup_paraday)
			else:
				bot.send_message(message.chat.id, "Выбери действие снизу", reply_markup = markup.markup)
		#ГЛОБАЛЬНОЕ СООБЩЕНИЕ
		elif (message.text == markup.btnTextGlobalMessage):
			if (db.is_admin(tg_id = message.from_user.id)):
				bot.send_message(message.chat.id, markup.textGlobalMessageReply, reply_markup = markup.forcereply)
			else:
				bot.send_message(message.chat.id, "Выбери действие снизу", reply_markup = markup.markup)
		#ЛЮБОЙ ДРУГОЙ ТЕКСТ
		else:
			bot.send_message(message.chat.id, "Выбери действие снизу", reply_markup = get_cur_markup(message.from_user.id))
		print("Reply seccess\n")
	except Exception as e:
		print("text_message: " + repr(e))
		if (db.get_chat_id(username = config.MAIN_ADMIN_USERNAME) != 0):
			bot.send_message(db.get_chat_id(username = config.MAIN_ADMIN_USERNAME), "❗️❌🆘❌❗️ Возможно пизда рулям:\n text_message: " + repr(e))
######################################################################################################################################################
@bot.callback_query_handler(func = lambda call:True)
@telebot.util.async_dec()
def callback_inline(call):
	try:
		print("Got callback-message from user: @" + db.get_username(call.message.chat.id) + " Data: "+ call.data)
		if (call.data == 'pn'):
			answer_day[call.message.chat.id] = 0
			bot.edit_message_text(chat_id = call.message.chat.id, message_id = call.message.message_id, text = markup.textChangeParaNumber, reply_markup = markup.inline_markup_paranumber)
			bot.answer_callback_query(call.id, show_alert = False)
		elif (call.data == 'vt'):
			answer_day[call.message.chat.id] = 1
			bot.edit_message_text(chat_id = call.message.chat.id, message_id = call.message.message_id, text = markup.textChangeParaNumber, reply_markup = markup.inline_markup_paranumber)
			bot.answer_callback_query(call.id, show_alert = False)
		elif (call.data == 'sr'):
			answer_day[call.message.chat.id] = 2
			bot.edit_message_text(chat_id = call.message.chat.id, message_id = call.message.message_id, text = markup.textChangeParaNumber, reply_markup = markup.inline_markup_paranumber)
			bot.answer_callback_query(call.id, show_alert = False)
		elif (call.data == 'ch'):
			answer_day[call.message.chat.id] = 3
			bot.edit_message_text(chat_id = call.message.chat.id, message_id = call.message.message_id, text = markup.textChangeParaNumber, reply_markup = markup.inline_markup_paranumber)
			bot.answer_callback_query(call.id, show_alert = False)
		elif (call.data == 'pt'):
			answer_day[call.message.chat.id] = 4
			bot.edit_message_text(chat_id = call.message.chat.id, message_id = call.message.message_id, text = markup.textChangeParaNumber, reply_markup = markup.inline_markup_paranumber)
			bot.answer_callback_query(call.id, show_alert = False)
		elif (call.data == 'sb'):
			answer_day[call.message.chat.id] = 5
			bot.edit_message_text(chat_id = call.message.chat.id, message_id = call.message.message_id, text = markup.textChangeParaNumber, reply_markup = markup.inline_markup_paranumber)
			bot.answer_callback_query(call.id, show_alert = False)
		elif(call.data == 'one'):
			answer_parnumber[call.message.chat.id] = 0
			id = call.message.chat.id
			bot.answer_callback_query(call.id, show_alert = False)
			bot.delete_message(call.message.chat.id, call.message.message_id)
			bot.send_message(id, markup.textChangeParaReply, reply_markup = markup.forcereply)
		elif(call.data == 'two'):
			answer_parnumber[call.message.chat.id] = 1
			id = call.message.chat.id
			bot.answer_callback_query(call.id, show_alert = False)
			bot.delete_message(call.message.chat.id, call.message.message_id)
			bot.send_message(id, markup.textChangeParaReply, reply_markup = markup.forcereply)
		elif(call.data == 'three'):
			answer_parnumber[call.message.chat.id] = 2
			id = call.message.chat.id
			bot.answer_callback_query(call.id, show_alert = False)
			bot.delete_message(call.message.chat.id, call.message.message_id)
			bot.send_message(id, markup.textChangeParaReply, reply_markup = markup.forcereply)
		elif(call.data == 'four'):
			answer_parnumber[call.message.chat.id] = 3
			id = call.message.chat.id
			bot.answer_callback_query(call.id, show_alert = False)
			bot.delete_message(call.message.chat.id, call.message.message_id)
			bot.send_message(id, markup.textChangeParaReply, reply_markup = markup.forcereply)
		elif(call.data == 'five'):
			answer_parnumber[call.message.chat.id] = 4
			id = call.message.chat.id
			bot.answer_callback_query(call.id, show_alert = False)
			bot.delete_message(call.message.chat.id, call.message.message_id)
			bot.send_message(id, markup.textChangeParaReply, reply_markup = markup.forcereply)
		elif(call.data == 'back1'):
			id = call.message.chat.id
			bot.delete_message(call.message.chat.id, call.message.message_id)
			bot.send_message(id, "Действие отменено", reply_markup = markup.admin_panel_markup)
			bot.answer_callback_query(call.id, show_alert = False)
		elif(call.data == 'back2'):
			bot.edit_message_text(chat_id = call.message.chat.id, message_id = call.message.message_id, text = markup.textChangeParaDay, reply_markup = markup.inline_markup_paraday)
			bot.answer_callback_query(call.id, show_alert = False)
		elif(call.data in ('0' ,'1' , '2' , '3' , '4' , '5')):
			para_list = parlist(int(call.data))
			msg = get_weekday(int(call.data)) + ':\n'
			for para in para_list:
				if (para != '-'):
					msg += (para+'\n')
			if (not msg):
				msg = "Сегодня пар нет"
			id = call.message.chat.id
			bot.delete_message(call.message.chat.id, call.message.message_id)
			bot.send_message(id, msg)
			bot.answer_callback_query(call.id, show_alert = False)
		elif(call.data == 'full'):
			msg = ''
			i = 0
			for day in (pn, vt, sr, ch, pt, sb):
				msg += (get_weekday(i) +':\n')
				i += 1
				for para in day:
					if (para != '-'):
						msg += (para+'\n')
			if (not msg):
				msg = "Список пустой"
			id = call.message.chat.id
			bot.delete_message(call.message.chat.id, call.message.message_id)
			bot.send_message(id, msg)
			bot.answer_callback_query(call.id, show_alert = False)


		print("Reply seccess\n")

	except Exception as e:
		print("callback_inline: " + repr(e))
		if (db.get_chat_id(username = config.MAIN_ADMIN_USERNAME) != 0):
			bot.send_message(db.get_chat_id(username = config.MAIN_ADMIN_USERNAME), "❗️❌🆘❌❗️ Возможно пизда рулям:\n callback_inline: " + repr(e))
######################################################################################################################################################
if __name__ == "__main__":
	try:
		
		read_parlist()

		with open("pid.txt","w") as pid:
			pid.write(str(os.getpid()))

		if (db.user_exists(username = config.MAIN_ADMIN_USERNAME) and not db.is_admin(username = config.MAIN_ADMIN_USERNAME)):
			add_admin(config.MAIN_ADMIN_USERNAME, "System")


		t1 = threading.Thread(target = bot_pooling)
		t1.start()

		start_notifications()

	except Exception as e:
		print("__main__: " + repr(e))
		if (db.get_chat_id(username = config.MAIN_ADMIN_USERNAME) != 0):
			bot.send_message(db.get_chat_id(username = config.MAIN_ADMIN_USERNAME), "❗️❌🆘❌❗️ Возможно пизда рулям:\n __main__: " + repr(e))