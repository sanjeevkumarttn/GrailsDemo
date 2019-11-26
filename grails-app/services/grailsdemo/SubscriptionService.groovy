package grailsdemo


import grails.gorm.transactions.Transactional
import grailsdemo.enums.Seriousness

@Transactional
class SubscriptionService {

    def serviceMethod() {

    }

    void saveSubscription(Long id, String seriousness, User user){

            Subscription subscription = new Subscription(topic: Topic.get(id), seriousness: Seriousness.valueOf(seriousness), user: user)
           try {
               subscription.save()
           }catch(Exception e){
               e.printStackTrace()
           }
    }

    String deleteSubscription(Long id){
        String message = 'not found'
        Subscription subscription = Subscription.get(id)
        if(subscription){
            subscription.delete()
            message = 'success'
        }
        return message
        /*try {
            Subscription.load(id).delete()
        }catch(Exception e){
            e.printStackTrace()
        }*/
    }

    String updateSubscription(Long id, String seriousness) {
        Subscription subscription = Subscription.get(id)
        String message = 'not found'
        if(subscription){
            subscription.setSeriousness(Seriousness.stringToEnum(seriousness))
            message='success'
        }
        return message

        //subscription.save()
        //return  " "
    }

    /*def getAllSubscriptions(User user){
        return Subscription.findAllByUser(user)
    }*/
}
