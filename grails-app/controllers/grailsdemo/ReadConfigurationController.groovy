package grailsdemo

import grails.converters.JSON
import groovy.json.JsonSlurper
import org.springframework.core.env.Environment

class ReadConfigurationController {
    Environment environment
    ReadConfigurationService readConfigurationService

    def index() { }

    def readFile(){
        def cfg = readConfigurationService.environmentPrepared(environment)
        Map map = new HashMap<>(cfg)
        map.each {
            println it.key+" : "+it.value
        }
        render "OK"
    }
}
