package grailsdemo

class UrlMappings {
    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }


        "/readJson"(controller: 'readConfiguration', action: 'readFile')
        "/link"(resource: "link")
        "/readingItem/changeIsRead"(controller: 'user', action: 'readingItemChangeIsRead')
        "/topic/show/"(controller: 'user', action: 'showTopic')
        "/resource/delete"(controller: 'user', action: 'deleteResource')
        "/topic/delete"(controller: 'user', action: 'deleteTopic')
        //"/loginHandler"(controller: 'login', action: 'loginHandler')
        "/login"(controller: 'login', action: 'loginHandler')
        "/register"(controller: 'login', action: 'register')
        "/logout"(controller: 'login', action: 'logout')
        "/"(view:"/index")
        //"/hello"(view: "/user/dashboard")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
