package grailsdemo


class ApplicationInterceptor {

    ApplicationInterceptor() {
        match controller:'*', action:'*'
        //match controller:'user', action:'*'
    }

    boolean before() {

        log.debug 'In Interceptor: '+request.getRequestURI()
        log.debug 'params: '+params

      /*  if (!session.user && !actionName.equals('/')) {
            redirect(uri: '/')
            return false
        }*/

        true
    }

    boolean after() { true }

    void afterView() {
        // no-op
    }
    /*def filters = {
        loginCheck(controller: '*', action: '*') {
            before = {
                if (!session.user && !actionName.equals('index')) {
                    redirect(uri: '/')
                    return false
                }
            }
        }
    }*/
}
