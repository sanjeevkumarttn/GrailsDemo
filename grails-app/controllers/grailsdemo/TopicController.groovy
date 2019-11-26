package grailsdemo

import grails.converters.JSON
import grailsdemo.cos.ResourceSearchCO
import grailsdemo.cos.TopicVO
import grailsdemo.enums.Visibility

class TopicController {

    //def scaffold = true

    TopicService topicService

    def index() {
        List<TopicVO> topicVOS = Topic.getTrendingTopics()
        topicVOS.each {println it}
        render topicVOS as JSON
    }

    def save(String name, String visibility ){
        if(session.user) {
            Topic topic = new Topic(name: name, visibility: Visibility.stringToEnum(visibility.toString()), createdBy: session.user)
            if (topic.save()) {
                //render 'success'
                flash.message="Topic created"
            } else {
                log.error 'error saving topic'
                flash.error="Error in creating topic"
            }
            redirect controller: "user", action: "index"
        }else {
            redirect(controller: "login", action: "index")
        }
    }

    def getTopicsOfUser(){
        return topicService.getAllTopicByUser((User)session.user)
    }

    def update(Long id, String visibility){
        if(topicService.updateTopic(id, Visibility.stringToEnum(visibility))){
            flash.message = "Updated"
        }else{
            flash.error = "error"
        }
    }

}
