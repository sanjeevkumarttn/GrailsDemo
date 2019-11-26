package grailsdemo

class SubscriptionController {

    //def scaffold = true

    SubscriptionService subscriptionService

    def index() { }

    def save(Long id, String seriousness){
        if(session.user){
            subscriptionService.saveSubscription(id, seriousness, (User)session.user)
            render 'success'

        }else {
            redirect(controller: "login", action: "index")
        }
    }

    def delete(Long id){
        try {
            render subscriptionService.deleteSubscription(id)

        }catch(Exception e){
            e.printStackTrace()
        }

    }

    def update(Long id, String seriousness){
        try {
            render subscriptionService.updateSubscription(id, seriousness)
        }catch(Exception e){
            e.printStackTrace()
        }
    }
}
