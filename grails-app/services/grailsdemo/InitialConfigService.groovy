package grailsdemo


import grails.gorm.transactions.Transactional
import grailsdemo.enums.Seriousness
import grailsdemo.enums.Visibility

@Transactional
class InitialConfigService {

    def serviceMethod() {

    }


    List<User> createUser() {
        List<User> users = []
        List<User> toSave = [
                new User(firstName: "Sanjeev", lastName: "Kumar", email: "k96sanjeev@gmail.com", userName: "sanjeev12", password: "sanjeev", active: true, admin: false),
                new User(firstName: "Admin", lastName: "Admin", email: "admin@gmail.com", userName: "admin", password: "admin", active: true, admin: true)
        ]
        toSave.each {
            User user = User.find(it)
            if (user == null) {
                if (it.save(failOnError: true)) {
                    users.add(it)
                    log.info "User ${it} saved successfully"
                } else {
                    log.error "Error saving user : ${it.errors.allErrors}"
                }
            }
        }
        return users
    }

    List<Topic> createTopics() {
        def users = User.findAll()
        def topicList = []
        //println users
        users.each { user ->

                println "topics for user: " + user
                (1..5).each {
                    //println "${user} : ${it}"
                    Topic topic = new Topic(name: "topic${it}", visibility: Visibility.PUBLIC, createdBy: user)
                    topic.addToSubscriptions(new Subscription(user: user, seriousness: Seriousness.VERY_SERIOUS, topic: topic))
                    if (topic.save()) {
                        topicList.add(topic)
                        log.info "Topic ${topic} created for ${user} successfully"
                    } else {
                        log.error "Error Creating Topic : ${topic.errors.allErrors}"
                    }
                }

        }
        return topicList
    }

    void createResources() {
        List<Topic> topics = Topic.findAll()

        topics.each {
            topic ->

                Link linkResource = new Link(createdBy: topic.createdBy, topic: topic, description: "this is link ${topic.id} of topic ${topic}", linkUrl: "https://grails.org/")

                if (linkResource.save()) {
                    log.info "Link ${linkResource} created successfully"
                } else {
                    log.error "Error Creating Link : ${linkResource.errors.allErrors}"
                }

                Document documentResource = new Document(createdBy: topic.createdBy, topic: topic, description: "this is document ${topic.id} of topic ${topic.name}", filePath: "http://${topic}/document${topic.id}")

                if (documentResource.save()) {
                    log.info "Document ${documentResource} created successfully"
                } else {
                    log.error "Error Creating Document : ${documentResource.errors.allErrors}"
                }
        }

    }

    void subscribeTopics() {
        List<User> users = User.findAll();
        List<Topic> topics = Topic.findAll()
        topics.each {
            topic ->
                users.each {
                    user ->
                        if (topic.createdBy.id != user.id) {

                            Subscription subscription = Subscription.findOrSaveWhere(user: user, seriousness: Seriousness.VERY_SERIOUS, topic: topic)
                        }
                }
        }
    }

    void createReadingItems() {
        List<User> users = User.findAll()
        users.each {
            user ->
                //println "$user.email"
               // List<Resource> resources = Resource.findAllByTopicInListAndCreatedByNotEqual(Topic.findAllBySubscriptionsInList(Subscription.findAllByUser(user)), user)
                List<Resource> resources = Resource.findAll("FROM Resource as r where r.createdBy.id!=${user.id}")
                //resources.each { println "Resource: ${it}" }
                resources.each {
                    resource->
                        //println "$it.createdBy.email"
                        Subscription subscription = Subscription.findByUserAndTopic(user, resource.topic)
                        println "Subscription.findByUserAndTopic: ${subscription}"
                        if (subscription != null) {
                            //ReadingItem item = new ReadingItem(user: user, resource: it, isRead: false)
                            ReadingItem item = ReadingItem.findOrSaveWhere(user: user, resource: resource, isRead: false)
                            println "$item"
                            log.info "$item added to ReadingItem"
                        }
                }
        }
    }

    void createResourceRating(){
        List<ReadingItem> unreadItems = ReadingItem.findAllByIsRead(false)
        unreadItems.each {
            item->
                Rating rating = new Rating(createdBy: item.user, resource: item.resource, score: (item.id)%5==0 ?1:(item.id)%5)

                if (rating.save()) {
                    log.info "Rating ${rating} created successfully"
                } else {
                    log.error "Error Creating Rating : ${rating.errors.allErrors}"
                }
        }
    }
}
