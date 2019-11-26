package grailsdemo

class Document extends Resource{

    String filePath

    static constraints = {
    }

    public String toString(){
        return "id ${id}, topic: $topic, createdBy: $createdBy, filepath: $filePath"
    }
}
