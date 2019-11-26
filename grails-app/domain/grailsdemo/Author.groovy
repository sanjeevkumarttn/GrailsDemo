package grailsdemo

@grails.rest.Resource(uri='/authors', formats=['json', 'xml'])

class Author {

    String name;

    static hasOne = [book: Book]
    //Book book

    static mapping = {
        books cascade: 'delete'
    }

    static constraints = {
        book nullable: true
    }
}
