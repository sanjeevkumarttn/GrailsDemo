package grailsdemo

import grails.gorm.transactions.Transactional

@Transactional
class ReadingItemService {

    def serviceMethod() {

    }

    Boolean changeIsRead(long id, boolean isRead, User user) {
        println id+" "+isRead+" "+user
        Boolean result = ReadingItem.executeUpdate("UPDATE ReadingItem r SET r.isRead=:isRead WHERE r.id=:id and r.user=:user",[isRead:isRead, id:id, user:user])
        return result
    }
}
