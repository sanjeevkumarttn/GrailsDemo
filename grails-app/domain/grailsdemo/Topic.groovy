package grailsdemo

import grailsdemo.cos.TopicVO
import grailsdemo.enums.Visibility
import org.hibernate.criterion.CriteriaSpecification
import org.hibernate.transform.Transformers

import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@grails.rest.Resource(uri = '/topics' )
class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id
    String name;
    Visibility visibility
    User createdBy
    Date dateCreated
    Date lastUpdated
    static hasMany = [subscriptions: Subscription, resources: Resource]

    public String toString() {
        return "name: $name, visibility: $visibility, createdBy: $createdBy.name "
    }

    static constraints = {
        name(unique: ['createdBy'], blank: false, nullable: false)
        visibility(nullable: false)
        createdBy(nullable: false)
    }

    static mapping = {
        sort name: 'asc'
    }

    static belongsTo = [createdBy: User]


    /*def afterInsert() {
        log.info 'start afterInsert'
        Subscription.withNewSession { session ->
           // session.flushMode = FlushMode.MANUAL
            try {
                Subscription subscription = new Subscription(user: this.createdBy, seriousness: Seriousness.VERY_SERIOUS, topic: this)
                if (!subscription.save()) {
                    log.error "Error in Subscription : ${topic.errors.allErrors}"WithNewSession
                }
            }finally{
                //session.flushMode = FlushMode.AUTO
            }
        }
        log.info 'end afterInsert'

    }*/

    static List<TopicVO> getTrendingTopics(){
        List<TopicVO> topicVOs = new ArrayList<>()

       /* List<TopicVO> trendingTopics = Resource.createCriteria().list {
            resultTransformer(org.hibernate.transform.Transformers.aliasToBean(TopicVO.class))
            //createAlias('topicVO','topicVO')
            topic{
                eq('visibility', Visibility.PUBLIC)
                projections {
                    groupProperty('id', "id")
                    groupProperty('name', "name")
                    count('id', "count")
                    property('visibility', "visibility")
                    property('createdBy', "createdBy")
                }
            }
        }*/


        List<TopicVO> trendingTopics = Resource.createCriteria().list(max:5) {
            createAlias("topic", "top")
            eq("top.visibility", Visibility.PUBLIC)
            projections {
                groupProperty("topic")
                property("top.name", "name")
                count("id", "count")
                property("top.id", "id")
                property("top.visibility", "visibility")
                property("top.createdBy", "createdBy")
            }
            order('count', "desc")
            resultTransformer(Transformers.aliasToBean(TopicVO.class))
        }
        //println(trendingTopics)
       /* trendingTopics.each {
            println it
        }*/

        return trendingTopics
    }
}
