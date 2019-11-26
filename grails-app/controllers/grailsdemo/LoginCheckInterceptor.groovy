package grailsdemo


class LoginCheckInterceptor {

    /*LoginCheckInterceptor() {
        matchAll().excludes(controller: 'login', action: '*')
    }*/

    boolean before() {
       /* if(!session.user){
            redirect controller: 'login' , action:'index'
            return false
        }*/
        true
    }

    boolean after() { true }

    void afterView() {
        // no-op
    }
}
