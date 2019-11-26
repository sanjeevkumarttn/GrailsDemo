package grailsdemo

class Link extends Resource{

    String linkUrl

    static constraints = {
       linkUrl(url: true)
    }

    public String toString(){
        return "id ${id}, topic: $topic, createdBy: $createdBy, linkUrl: $linkUrl"
    }
}
