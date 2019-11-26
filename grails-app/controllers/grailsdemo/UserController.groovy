package grailsdemo

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.wnameless.json.flattener.JsonFlattener
import com.github.wnameless.json.flattener.StringEscapePolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import grails.converters.JSON
import grails.util.Holders
import grailsdemo.cos.ResourceSearchCO
import grailsdemo.cos.SearchCO
import grailsdemo.enums.Visibility
import org.apache.groovy.util.Maps
import org.json.simple.parser.JSONParser
import org.springframework.context.ApplicationContext
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity

import java.util.stream.Collectors

class UserController {

    // def scaffold = User
    //ApplicationContext ctx
    TopicService topicService
    ReadingItemService readingItemService
    UserService userService

    def index() {
        println "in index"
        SearchCO searchCO = new SearchCO()
        List<ReadingItem> items = new ArrayList<>()
        if(params.q){
            searchCO.q=params.q
            searchCO.offset= params.offset == null ? 0 : params.offset
            searchCO.max= params.max == null ? 0 : params.max
            items = User.getUnReadResources(searchCO, (User)session.user)
        }else {
            searchCO.q=" "
            searchCO.offset=0
            searchCO.max=10
            items = User.getUnReadResources(searchCO, (User)session.user)
        }
        /*items.each {
            println it
        }*/
        session.topicSubscribed = topicService.getAllTopicByUser((User)session.user)
        //render "user dashboard : Welcome ${session.user.userName}"
        render view: "dashboard"
    }

    def showById(Long id){
        Topic topic = Topic.findById(id)
        if(topic){
            if(topic.visibility== Visibility.PUBLIC){
                render 'Success'
            }else {
                Subscription subscription = Subscription.findByTopicAndUser(topic, (User)session.getAttribute('user'))
                if(subscription){
                    render 'Success'
                }else {
                    flash.error = 'Topic not found'
                    redirect controller:'login', action:'index'
                }
            }
        }else {
            flash.error = 'Topic not found'
            redirect controller:'login', action:'index'
        }
    }

  /*  def showTopic(Long id){
        def topic = Topic.read(id)
        //println topic
        render topic
    }*/

    def showTopic(ResourceSearchCO co){
        def topic = Topic.read(co.topicId)
        //println topic
        render topic
    }

    def deleteResource(Long id){
       topicService.deleteResource(id)
        render "success"
    }

    def deleteTopic(Long id){
        topicService.deleteTopic(id)
        render "success"
    }

    def  readingItemChangeIsRead(Long id, Boolean isRead){
        if(!id && !isRead){
            render "enter id and isRead value."
        }else {
            Boolean isUpdated = readingItemService.changeIsRead(id, isRead, (User) session.user)
            if (isUpdated) {
                render "success"
            } else {
                render "error"
            }
        }
    }

    def image(Long id){
        User user = userService.getImage(id)
        println user
        if(user){
            if(user.photo){
                //def photosInstance = Photos.get(params.id)
                byte [] image = user.photo // byte array
                response.setHeader('Content-length', "${image.length}")
                response.contentType = 'image/*' // or the appropriate image content type
                response.outputStream << image
                response.outputStream.flush()
                render(response)
            }else{
                render("Image not found")
            }
        }else {
            flash.error = " User not exist"
        }
    }

    def validateIp() {
        log.info("client ip address: "+request.getRemoteAddr())
        def isFound = userService.validate(request.getRemoteAddr())
        def file = new File("grails-app/conf/config.json")

        if (file.exists()) {
            response.setContentType("application/octet-stream")
            response.setHeader("Content-disposition", "filename=${file.name}")
            response.outputStream << file.readBytes()
            return
        }

        return isFound
    }

    def configjson() {

        Map map = new HashMap<>( grailsApplication.config)
        Map flattenMapObj = new HashMap()
        flattenMapObj = flattenMap(map)

        Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
        String prettyJson = prettyGson.toJson(flattenMapObj);

        def contentType = "application/octet-stream"
        def filename = "config.json"
        response.setHeader("Content-Disposition", "attachment;filename=${filename}")
        response.outputStream << prettyJson.getBytes()
        render(contentType: contentType, text: list as JSON)
    }

    private def flattenMap( Map aMap, prefix='' ) {
        aMap.inject( [:] ) { map, v ->
            def kstr = "$prefix${ prefix ? '.' : '' }$v.key"
            if( v.value instanceof Map )
                map += flattenMap( v.value, kstr )
            else
                map[ kstr ] = v.value
            map
        }
    }

  /*  def getBean(){

        def dataSource = applicationContext.getBean("dataSource")//.beanFactory.registerSingleton("dataSource", dataSource)
        render dataSource
    }*/


}
