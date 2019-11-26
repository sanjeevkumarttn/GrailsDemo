package grailsdemo



class BootStrap {

    InitialConfigService initialConfigService

    def init = { servletContext ->
       /* List<User> users = initialConfigService.createUser()
        List<Topic> topics = initialConfigService.createTopics()
        initialConfigService.createResources()
        initialConfigService.subscribeTopics()
        initialConfigService.createReadingItems()
        initialConfigService.createResourceRating()*/
        //initialConfigService.createReadingItems()

    }



    def destroy = {
    }
}
