import sqlite3
class SQLighter:
    
    def __init__(self, database_file, check_same_thread = False):
        self.__connection = sqlite3.connect(database_file, check_same_thread = check_same_thread)
        self.__cursor = self.__connection.cursor()

    def user_exists(self, tg_id = None, username = None):
        with self.__connection:
            if (tg_id):
                return bool(len( self.__cursor.execute("SELECT * FROM `userlist` WHERE `tg_id` = ?", (tg_id,)).fetchall()))
            elif (username):
                 return bool(len( self.__cursor.execute("SELECT * FROM `userlist` WHERE `username` = ?", (username,)).fetchall()))
            else:
                 return False
    
    def add_user(self, tg_id, username, chat_id):
        with self.__connection:
            return self.__cursor.execute("INSERT INTO `userlist` (`tg_id`, `username`,`chat_id`) VALUES(?,?,?)", (tg_id,username,chat_id))

    def set_chat_valid(self, tg_id, chat_valid = True):
        with self.__connection:
            return self.__cursor.execute("UPDATE `userlist` SET `chat_valid` = ? WHERE `tg_id` = ?", (chat_valid, tg_id))

    def is_admin(self, tg_id = None, username = None):
        with self.__connection:
            if (tg_id):
                if (self.user_exists(tg_id = tg_id)):
                    return bool(self.__cursor.execute("SELECT `admin` FROM `userlist` WHERE `tg_id` = ?",(tg_id,)).fetchone()[0])
            elif (username):
                if (self.user_exists(username = username)):
                    return bool(self.__cursor.execute("SELECT `admin` FROM `userlist` WHERE `username` = ?",(username,)).fetchone()[0])
            return False

    def get_all_usernames(self):
         with self.__connection:
            result_list = []
            for list in self.__cursor.execute("SELECT `username` FROM `userlist`").fetchall():
                result_list.insert(len(result_list),list[0])
            return result_list

    def get_all_admins(self):
         with self.__connection:
            result_list = []
            for list in self.__cursor.execute("SELECT `username` FROM `userlist` WHERE `admin` = ?", (True,)).fetchall():
                result_list.insert(len(result_list),list[0])
            return result_list

    def get_all_chat_ids(self, valid = True):
        with self.__connection:
            result_list = []
            for list in self.__cursor.execute("SELECT `chat_id` FROM `userlist` WHERE `chat_valid` = ?",(valid,)).fetchall():
                result_list.insert(len(result_list),list[0])
            return result_list

    def get_username(self, tg_id):
        with self.__connection:
            if (tg_id and self.user_exists(tg_id = tg_id)):
                return self.__cursor.execute("SELECT `username` from `userlist` WHERE `tg_id`=?",(tg_id,)).fetchone()[0]
            else:
                return None

    def set_username(self, tg_id, new_username):
        with self.__connection:
            if (tg_id and self.user_exists(tg_id = tg_id)):
                 return self.__cursor.execute("UPDATE `userlist` SET `username` = ? WHERE `tg_id` = ?", (new_username, tg_id))
            else:
                 return False

    def get_tg_id(self, username):
        with self.__connection:
            if (username and (self.user_exists(username = username))):
                return self.__cursor.execute("SELECT `tg_id` from `userlist` WHERE `username`=?",(username,)).fetchone()[0]
            else:
                return 0

    def get_chat_id(self, tg_id = None, username = None):
        with self.__connection:
            if (tg_id):
                if (self.user_exists(tg_id = tg_id)):
                    return self.__cursor.execute("SELECT `chat_id` from `userlist` WHERE `tg_id`=?",(tg_id,)).fetchone()[0]
                else:
                    return 0
            elif (username):
                if (self.user_exists(username = username)):
                    return self.__cursor.execute("SELECT `chat_id` from `userlist` WHERE `username`=?",(username,)).fetchone()[0]
                else:
                    return 0
            else:
                return 0
    
    def set_admin(self, tg_id = None, username = None, is_admin = False):
        with self.__connection:
            if (tg_id):
                return self.__cursor.execute("UPDATE `userlist` SET `admin` = ? WHERE `tg_id` = ?", (is_admin, tg_id))
            elif(username):
                return self.__cursor.execute("UPDATE `userlist` SET `admin` = ? WHERE `username` = ?", (is_admin, username))
            else:
                return False

    def set_notifications_enabled(self, tg_id, enabled):
        with self.__connection:
            return self.__cursor.execute("UPDATE `userlist` SET `allow_notifications` = ? where `tg_id` = ?", (enabled, tg_id))
    
    def get_notifications_enabled(self,tg_id):
        with self.__connection:
            return bool(self.__cursor.execute("SELECT `allow_notifications` from `userlist` WHERE `tg_id` = ?",(tg_id,)).fetchone()[0])

    def __del__(self):
        self.__connection.close()