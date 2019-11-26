package grailsdemo

class LoginController {

    UserService userService
    //def scaffold = User
    /* static allowedMethods = [
             index: ["GET"],
             register: ["POST", "PUT"]
     ]
     */

    def index() {

        if (session.user) {
            redirect(controller: "user", action: "index")
        } else {
            /*StringBuilder result = new StringBuilder();
            result.append("Failure..! Login first.").append('\n\n').append("---------Top posts-------").append('\n');*/

            def topResources = Resource.getTopRatings()

            def recentPostList = Resource.findAll("from Resource as r order by r.dateCreated desc", [max: 5])
            /* topResources.each {
                 result.append('\n');
                 result.append("id: ${it[0]}, total votes: ${it[1]}, description: ${it[2]} | ")
             }*/
          /*  User user
            if(chainModel?.user){
                println chainModel?.user
                user = chainModel?.user
            }*/
            //return new ModelAndView("/index", [resources: topResources, recentPosts: recentPostList])
            render (view: "/index", model: [resources: topResources, recentPosts: recentPostList, user: chainModel?.user])
        }
    }

    def logout() {
        session.invalidate()
        redirect(action: "index")
    }

    def loginHandler(String userName, String password) {

        User user = User.findByUserNameAndPassword(userName, password)

        if (user) {
            if (user.active) {
                session.setAttribute("user", user)
            } else {
                flash.error = 'Your account is not active'
            }
        } else {
            flash.error = 'User not found'
        }
        redirect(action: "index")
    }

    def register() {

        def uploadedFile = request.getFile('photo')
        byte[] image = uploadedFile.getBytes();
        def user = userService.register(params, image)
        if(user.hasErrors()){
            flash.error = "Something Wrong"
        }else{
            flash.message = "Registration Success !"
            session.user = user
        }


        chain (action: "index", model: [user: user])
       /* if(!user.save(flush:true)){
            chain (action: "index", model: [user: user])
        }else{
            redirect action: index()
        }
*/
        //chain action: 'index', model: [user : user]
//        if (user) {
//            //  flash.user = user
//            flash.message = "Success"
//        } else {
//            flash.error = "Something Wrong"
//        }

        /*User user = new User(params)
        user.active = true;
        user.admin = false;
        user.photo=bytes
        if(user.validate()){
            user.save()
        }*/

        /*User user = User.findByEmail(params.email)
        //println user
        if(!user){
            if(!params.confirmPassword){
                flash.error='confirm password can\'t be blank !'
            }else if(params.confirmPassword != params.password){
                flash.error = 'Both password should be same !'
            }else {
                user = new User(params)
                user.active = true;
                user.admin = false;
                user.photo=bytes
            }
        }

        try {
            user.validate()

            if(user.hasErrors()){
                user.errors.allErrors.each{
                    println it
                }
            }else{
                user.save(failOnError:true)
                flash.message = 'success !'
            }

        }
        catch (Exception e){
            //log.error "Error: ${user.errors.allErrors}"
            log.error(e.printStackTrace())
            flash.error = 'There is some issue to create your profile, please try again.'
        }*/
        //[user:user]
       // chain action: 'index', model: [user : user]
        //redirect(action: "index")
    }

    def update(String email, String password) {
        User user = User.findByEmail(email)
        if (user) {
            user.password = password
            println User.findByEmail(email)
            if (user.save()) {
                render 'success'
            }

        } else {
            render 'user not exist.'
        }
    }

}
