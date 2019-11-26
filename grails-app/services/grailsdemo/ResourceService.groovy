package grailsdemo

import grails.gorm.transactions.Transactional
import grailsdemo.cos.RatingInfoVO

@Transactional
class ResourceService {

    def serviceMethod() {

    }

    public void topPost(){
        //Resource.findAllByRatings()
        Resource.getTopRatings()
    }

    public Boolean saveResource(User user, Topic topic, String description, String linkUrl){
        Resource resource = new Link()
        resource.setLinkUrl(linkUrl)
        resource.setTopic(topic)
        resource.setDescription(description)
        resource.setCreatedBy(user)
        if(resource.save(flush:true, )) {
            return true
        }
        return false
    }

    Boolean updateResource(Long id , String description){
        try {
            Resource resource = Resource.get(id);
            if(resource){
                resource.description = description;
                return true
            }
        }catch(Exception e){
            e.printStackTrace()
        }
        return false
    }


}
