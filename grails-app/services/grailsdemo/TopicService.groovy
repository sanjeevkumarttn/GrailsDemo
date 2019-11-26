package grailsdemo

import grails.gorm.transactions.Transactional
import grailsdemo.enums.Visibility
import org.hibernate.ObjectNotFoundException

import java.util.stream.Collectors

@Transactional
class TopicService {


    void deleteTopic(Long id) {
        try {

            Topic.load(id)
            .delete(flush: true)
        } catch (Exception e) {
            e.printStackTrace()
        }
    }


    void deleteResource(Long id) {
        try {
            Resource.load(id).delete()
        } catch (ObjectNotFoundException ex) {
            ex.printStackTrace()
        }
    }

    List<Topic> getAllTopicByUser(User user){
       List<Topic> topics = Subscription.findAllByUser(user).collect {it.topic}
        /*def topics = subscriptions.collect {
            it.topic
        }*/

        return topics
    }

    Boolean updateTopic(Long id, Visibility visibility){
        Topic topic = Topic.get()
        if(topic){
            topic.visibility=visibility
            return true
        }
        return false
    }
}
