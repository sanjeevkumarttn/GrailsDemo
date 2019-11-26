package grailsdemo

import grailsdemo.cos.RatingInfoVO
import grailsdemo.cos.ResourceSearchCO
import grailsdemo.cos.TopResources
import org.hibernate.sql.JoinType
import org.hibernate.transform.Transformers
import org.hibernate.transform.AliasToBeanResultTransformer

import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@grails.rest.Resource(uri='/resources')
abstract class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id
    String description
    User createdBy
    Topic topic
    Date dateCreated
    Date lastUpdated
    static transients = ['resourceInfo']
    static hasMany = [ratings: Rating, readingItems: ReadingItem]

    static belongsTo = [createdBy: User, topic: Topic]

    static mapping = {
        description type: 'text'
    }

    static constraints = {
    }

    static namedQueries = {

        search { ResourceSearchCO co ->
            //if(co.topicId && co.visibility){

            topic {
                eq('name', co.q)
            }
            // }
        }

        /*search{ ResourceSearchCO co->
            if(co.topicId && co.visibility){

                topic{
                    and {
                        eq('id', co.topicId)
                        eq('visibility', co.visibility)
                    }
               }
            }
        }*/
    }

    RatingInfoVO getRatingInfoVo() {
        RatingInfoVO ratingInfoVO = new RatingInfoVO()
        def result = Resource.createCriteria().get {
            eq('id', this.id)

            ratings {
                projections {
                    sum("score")
                    avg("score")
                    count("score")
                }
            }
        }

        ratingInfoVO.totalScore = result[0] as Integer
        ratingInfoVO.averageScore = result[1] as Integer
        ratingInfoVO.totalVotes = result[2] as Integer
        //log.info 'totalVotes: ' + totalVotes
        return ratingInfoVO
    }

    static def getTopRatings(){
       def results= Rating.createCriteria().list(max:5) {
            createAlias("resource","r")
            projections {
                groupProperty("r.id")
                property("r.id", "id")
                property("r.topic", "topic")
                property("r.description", "description")
                property("r.createdBy", "createdBy")
                sum("score", "totalVotes")
            }
           and{
               order("totalVotes", "desc")
               order('r.lastUpdated', "desc")
           }
            resultTransformer(org.hibernate.transform.Transformers.aliasToBean(TopResources.class))
        } as List<TopResources>

        return results

    }

}
