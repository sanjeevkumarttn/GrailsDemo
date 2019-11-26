package grailsdemo

import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id
    User createdBy
    Integer score
    Resource resource
    Date dateCreated
    Date lastUpdated

    static constraints = {
        resource(size: 1..5)
        resource unique: "createdBy"
    }

    static belongsTo = [resource:Resource]
}
