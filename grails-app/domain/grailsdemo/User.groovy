package grailsdemo

import grailsdemo.cos.SearchCO
import org.hibernate.FlushMode
import org.hibernate.validator.constraints.Email

import javax.persistence.Column
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.Valid
import javax.validation.constraints.*
import javax.validation.constraints.NotNull
import java.sql.Blob
// @grails.rest.Resource(uri='/api/users', formats=['json', 'xml'])
class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id
    String firstName
    String lastName
    String email
    String userName
    String password
    byte[] photo
    Boolean active
    Boolean admin
    Date dateCreated
    Date lastUpdated
    String confirmPassword
    static transients = ['name', 'confirmPassword']
    static hasMany = [topics: Topic, subscriptions: Subscription, readingItems: ReadingItem, resources: Resource]

    public String toString() {
        return "Name: ${name}, email: ${email}, username: ${userName}, password: ${password}"
    }

    static constraints = {
        email(unique: true, blank: false, email: true, nullable: false)
        password (nullable: false, blank: false, minSize: 5)
        firstName(nullable: false, blank: false)
        lastName(nullable: false, blank: false)
        active(nullable: true)
        admin(nullable: true)
        photo(nullable: true)
        userName(unique: true, blank: false, nullable: false)
        //confirmPassword bindable: true, nullable: true, blank: true

        /*confirmPassword validator: { val, obj ->
            withNewSession { session ->
                session.flushMode = FlushMode.MANUAL
                try {
                    if (!findByEmail(obj.email)) {
                        if (!val || val != obj.password) {
                            return ['password not matched!']
                        }
                    }
                }finally {
                    session.setFlushMode(FlushMode.AUTO);
                }

            }
        }*/
    }

    static mapping = {
//        version: false
        photo sqlType: "longblob"
        sort id : 'desc'
    }


    String getName() {
        return "$firstName $lastName"
    }

    static List<ReadingItem> getUnReadResources(SearchCO searchCO, User user){
        /*if(searchCO.q){
            List<ReadingItem> unReadResources = ReadingItem.findAllByResourceInListAndUser(Resource.findAllByDescription("%${q}%"), user)
            return unReadResources
        }*/

        List<ReadingItem> result = ReadingItem.createCriteria().list {
            createAlias("resource","r")
            and{
                ilike("r.description", "%${searchCO.q}%")
                eq("user", user)
            }

            maxResults searchCO.max
            firstResult searchCO.offset
        }
        return result
    }
}
