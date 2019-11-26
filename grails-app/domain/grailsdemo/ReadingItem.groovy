package grailsdemo

import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

class ReadingItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id
    User user
    Boolean isRead
    Resource resource
    Date dateCreated
    Date lastUpdated



    static constraints = {
        user(nullable: false)
        resource(unique: ['user'], nullable: false)
        isRead(nullable: false)
    }

    static belongsTo = [resource:Resource, user:User]

    @Override
    public String toString() {
        return "ReadingItem{" +
                "id=" + id +
                ", user=" + user +
                ", isRead=" + isRead +
                ", resource=" + resource +
                ", dateCreated=" + dateCreated +
                ", lastUpdated=" + lastUpdated +
                '}';
    }
}
