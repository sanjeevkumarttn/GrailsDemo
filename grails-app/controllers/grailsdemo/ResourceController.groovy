package grailsdemo

import grailsdemo.cos.RatingInfoVO
import grailsdemo.cos.ResourceSearchCO
import grailsdemo.enums.Visibility

class ResourceController {

    //def scaffold = true

    ResourceService resourceService;

    def index() {

    }

   /* def search(Long id, String visibility){
        Visibility visibility1 = Visibility.stringToEnum(visibility)
        ResourceSearchCO co = new ResourceSearchCO(topicId: id, visibility: visibility1)
        List<Resource> resources = Resource.search(co).list()
        println resources;
        " "
    }*/

    def search(String q){
        ResourceSearchCO co = new ResourceSearchCO(q: q, visibility: Visibility.PUBLIC)
        List<Resource> resources = Resource.search(co).list()
        render resources.toString();
        " "
    }

    def show(){
        Resource resource = Resource.get(5)
        RatingInfoVO ratingInfoVO = resource.getRatingInfoVo()
        render "Total Votes: "+ratingInfoVO.totalVotes+", Total Score: "+ratingInfoVO.totalScore+", Average Score: "+ratingInfoVO.averageScore
    }

    def topPost(){
        resourceService.topPost()
    }

    def save(Long topic, String description, String linkUrl){
        Topic topic1 = Topic.get(topic)
        Boolean success = resourceService.saveResource((User)session.user, topic1, description, linkUrl)
        if(success){
            flash.message = "Resource created."
        }else{
            flash.error = "Error in creating resource"
        }
        redirect controller: "user", action: "index"
    }

    def update(Long id, String description){
        if(resourceService.updateResource(id, description)){
            flash.message = "Updated"
        }else{
            flash.error = "error"
        }
        redirect controller: "user", action: "index"
    }
}
