package grailsdemo

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import groovy.json.JsonOutput
import grails.gorm.transactions.Transactional
import org.apache.commons.validator.routines.InetAddressValidator
import grails.util.Holders

@Transactional
class UserService {

    def grailsApplication

    final String CONFIG_FILE_PATH = "grails-app/conf/application.groovy"
    final String CREATE_JSON_FILE_PATH = "grails-app/conf/config.json"

    def validate(String ip) {
        if(InetAddressValidator.getInstance().isValidInet4Address(ip)){

           println Holders.config.app.ip_list
            if(ip in Holders.config.app.ip_list){
                log.info "Ip address found"
                createJSON()
                return true
            }else {
                log.info "Ip address not found"
            }
        } else {
            log.info "Invalid Ip"
        }
        return false
    }

    def createJSON(){
        /*StringBuilder fileContent = new StringBuilder(new File(CONFIG_FILE_PATH).text)
        fileContent.insert(0, "{ ")
        fileContent.append(" }")
        String jsonString = fileContent.toString().replace('=',':').replace('\n',', ')

        def jsonObject = new  org.json.JSONObject(jsonString)

        def json_beauty = JsonOutput.prettyPrint(jsonObject.toString())
        println(json_beauty)*/
        Map map = new HashMap<>( grailsApplication.config)
        Map flattenMapObj = new HashMap()
        flattenMapObj = flattenMap(map)

        Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
        String prettyJson = prettyGson.toJson(flattenMapObj);
        println prettyJson
        //def json_beauty = JsonOutput.prettyPrint(list.toString())

        try {
            File file = new File(CREATE_JSON_FILE_PATH)
            file.write(prettyJson)
        }catch(Exception e){
            log.error(e.printStackTrace())
        }
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

    def getImage(Long id){
        return User.get(id)
    }


    def register(params, byte[] image ){
        User user = new User(params)
        user.active = true;
        user.admin = false;
        user.photo=image
      try{
          user.save(failOnError:true)

      }catch(Exception e){
          println "Exception:: "+e
      }
        return user
    }
}
