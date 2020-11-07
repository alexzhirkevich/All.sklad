import sqlite3
class SQLighter:
    
    def __init__(self, database_file, check_same_thread = False):
        self.__connection = sqlite3.connect(database_file, check_same_thread = check_same_thread)
        self.__cursor = self.__connection.cursor()

    def user_exists(self, tg_id):
        with self.__connection:
            if (tg_id):
                return bool(len( self.__cursor.execute("SELECT * FROM `database` WHERE `tg_id` = ?", (tg_id,)).fetchall()))
            else:
                 return False

    def get_sex(self,tg_id):
        with self.__connection:
            if (tg_id):
                return self.__cursor.execute("SELECT `sex` FROM `database` WHERE `tg_id`=?",(tg_id)).fetchone()[0]
    
    def set_sex(self,tg_id,sex):
        with self.__connection:
            if (tg_id):
                return self.__cursor.execute("UPDATE `database` SET `sex` = ? WHERE `tg_id` = ?",(sex,tg_id))

    def get_partner_sex(self,tg_id):
        with self.__connection:
            if (tg_id):
                return self.__cursor.execute("SELECT `partner_sex` FROM `database` WHERE `tg_id`=?",(tg_id)).fetchone()[0]
    
    def set_partner_sex(self,tg_id,sex):
        with self.__connection:
            if (tg_id):
                return self.__cursor.execute("UPDATE `database` SET `partner_sex` = ? WHERE `tg_id` = ?",(sex,tg_id))            
    
    def add_user(self, tg_id):
        with self.__connection:
            if (tg_id):
                return self.__cursor.execute("INSERT INTO `database` (`tg_id`) VALUES(?)", (tg_id))

    def set_chat_valid(self, tg_id, chat_valid = True):
        with self.__connection:
            if (tg_id):
                return self.__cursor.execute("UPDATE `database` SET `chat_valid` = ? WHERE `tg_id` = ?", (chat_valid, tg_id))

    def get_all_chat_ids(self, valid = True):
        with self.__connection:
            result_list = []
            for list in self.__cursor.execute("SELECT `chat_id` FROM `database` WHERE `chat_valid` = ?",(valid,)).fetchall():
                result_list.insert(len(result_list),list[0])
            return result_list

    def __del__(self):
        self.__connection.close()