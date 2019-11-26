package grailsdemo



class DemoTagLib {
    UserService userService;
    def assetResourceLocator


   // static defaultEncodeAs = [taglib:'html']
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]
    /*def emoticon = { attrs, body ->
        out << body() << (attrs.happy == 'true' ? " :-)" : " :-(")
        *//*if(!attrs.happy){
            out << body()
        }*//*
    }

    def repeat = { attrs, body ->
        def var = attrs.var ?: "num"
        attrs.times?.toInteger()?.times { num ->
            out << body((var):num)
        }
    }*/

    def userImage = { attrs, body ->
        if(attrs.user.photo){
            out << "<img src=\"data:image/png; base64,${attrs.user.photo.encodeBase64()}\" width=\"64px\" height=\"64px\" />"
        }else{
           def image = assetResourceLocator.findAssetForURI('profile/image.jpeg')
           //print "----"+image.properties
            out << "<img src=\"data:image/png; base64,${image.getByteArray().encodeBase64()}\" width=\"64px\" height=\"64px\" />"
        }
        //out << "<img src=\"data:image/png; base64,${resource.createdBy.photo.encodeBase64()}\"/>"

    }
}
