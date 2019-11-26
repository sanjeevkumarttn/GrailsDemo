package grailsdemo

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import grails.converters.JSON
import grails.gorm.transactions.Transactional
import grails.util.Holders
import groovy.io.FileType
import groovy.json.JsonBuilder
import groovy.json.JsonSlurper
import org.grails.config.NavigableMapPropertySource
import org.springframework.core.env.ConfigurableEnvironment
import org.springframework.core.env.MapPropertySource
import org.springframework.core.io.DefaultResourceLoader
import org.springframework.core.io.ResourceLoader
import org.yaml.snakeyaml.Yaml

@Transactional
class ReadConfigurationService {

    def grailsApplication
    private ResourceLoader defaultResourceLoader = new DefaultResourceLoader()


    def environmentPrepared(ConfigurableEnvironment environment) {
        String path = "/home/ttn/Desktop/Config"
        def dir = new File(path)

        dir.eachFileRecurse(FileType.FILES) { file ->
            MapPropertySource propertySource = null
            log.info("file name: " + file)
            org.springframework.core.io.Resource resource = defaultResourceLoader.getResource(file.getPath())

            if (resource.exists()) {
                if (finalLocation.endsWith('.groovy')) {
                    propertySource = loadGroovyConfig(resource, 'UTF-8')
                } else if (finalLocation.endsWith('.yml')) {
                    environment.activeProfiles
                    propertySource = loadYamlConfig(resource)
                } else {
                    // Attempt to load the config as plain old properties file (POPF)
                    propertySource = loadPropertiesConfig(resource)
                }
            } else {
                log.debug("Config file not found: " + file)
            }
        }
        if (propertySource?.getSource() && !propertySource.getSource().isEmpty()) {
            environment.propertySources.addFirst(propertySource)
        }
    }

    def read() {
        ObjectMapper objectMapper = new ObjectMapper();
        String path = "/home/ttn/Desktop/Config"
        def config = grailsApplication.config
        String text
        def dir = new File(path)
        // if(dir?.file){
        dir.eachFileRecurse(FileType.FILES) { file ->
            MapPropertySource propertySource = null
            log.info("file name: " + file)

            if (file.exists()) {
                org.springframework.core.io.Resource resource = defaultResourceLoader.getResource(file)
                if (finalLocation.endsWith('.groovy')) {
                    propertySource = loadGroovyConfig(resource, 'UTF-8')
                } else if (finalLocation.endsWith('.yml')) {
                    environment.activeProfiles
                    propertySource = loadYamlConfig(resource)
                } else {
                    // Attempt to load the config as plain old properties file (POPF)
                    propertySource = loadPropertiesConfig(resource)
                }
            } else {
                log.debug("Config file not found: "+file)
            }

            /*text = file.text
            if (file ==~ ~/.*(?<=\.)(yml|properties)/) {
                String data = convertYamlToJson(file.text)

                Map map = new JsonSlurper().parseText(data)
                Map flattenMap = convertToflattenMap(map)
                StringBuffer sb = new StringBuffer("")
                flattenMap.each {
                    if(it.value instanceof List){
                        sb.append(it.key+"[")
                        it.value.each {
                            sb.append("'"+it+"' ")
                        }
                        sb.append("]\n")
                    }else{
                        sb.append(it.key+"='"+it.value+"'\n")
                    }

                }
                text = sb //objectMapper.writeValueAsString(flattenMap);
                //text = data.replace(':', ' = ').replace('{', '').replace('}', '').replace(',', '\n')
            }

            config.merge(new ConfigSlurper().parse(text))*/
        }
        if (propertySource?.getSource() && !propertySource.getSource().isEmpty()) {
            environment.propertySources.addFirst(propertySource)
        }
        // }
        //return config
    }

    String convertYamlToJson(String yaml) {
        ObjectMapper yamlReader = new ObjectMapper(new YAMLFactory());
        Object obj = yamlReader.readValue(yaml, Object.class);

        ObjectMapper jsonWriter = new ObjectMapper();
        return jsonWriter.writeValueAsString(obj);
    }

    /*convertYamlToJson(File file){
        Yaml yaml = new Yaml();
        InputStream inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream("customer.yaml");
        Map<String, Object> obj = yaml.load(inputStream);
    }
*/

    private def convertToflattenMap(Map aMap, prefix = '') {
        aMap.inject([:]) { map, v ->
            def kstr = "$prefix${prefix ? '.' : ''}$v.key"
            if (v.value instanceof Map)
                map += convertToflattenMap(v.value, kstr)
            else
                map[kstr] = v.value
            map
        }
    }



    private MapPropertySource loadGroovyConfig(org.springframework.core.io.Resource resource, String encoding) {
       // log.info("Loading groovy config file", resource.URI)
        String configText = resource.inputStream.getText(encoding)
        Map properties = configText ? new ConfigSlurper(grails.util.Environment.current.name).parse(configText)?.flatten() : [:]
        new MapPropertySource(resource.filename, properties)
    }

    private NavigableMapPropertySource loadYamlConfig(org.springframework.core.io.Resource resource) {
        //log.info("Loading YAML config file {}", resource.URI)
        NavigableMapPropertySource propertySource = yamlPropertySourceLoader.load(resource.filename, resource, null) as NavigableMapPropertySource
        return propertySource
    }

    private MapPropertySource loadPropertiesConfig(org.springframework.core.io.Resource resource) {
       // log.info("Loading properties config file {}", resource.URI)
        Properties properties = new Properties()
        properties.load(resource.inputStream)
        new MapPropertySource(resource.filename, properties as Map)
    }
}
