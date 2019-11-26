package grailsdemo

import grailsdemo.enums.Seriousness

import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id
    Topic topic
    User user
    Seriousness seriousness
    Date dateCreated
    Date lastUpdated

    static constraints = {
        topic unique: "user"
    }

    static mapping = {
        seriousness defaultValue: Seriousness.SERIOUS
        user fetch: 'join'
        topic fetch: 'join'
    }

    static belongsTo = [user:User, topic:Topic]
}
