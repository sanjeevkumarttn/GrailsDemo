package grailsdemo

import grails.converters.JSON
import groovy.json.JsonSlurper

class ReadConfigurationController {

    ReadConfigurationService readConfigurationService

    def index() { }

    def readFile(){
        def cfg = readConfigurationService.environmentPrepared()
        Map map = new HashMap<>(cfg)
        map.each {
            println it.key+" : "+it.value
        }
        render "OK"
    }
}
