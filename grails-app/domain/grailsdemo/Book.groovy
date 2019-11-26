package grailsdemo

@grails.rest.Resource(uri='/books', formats=['json', 'xml'])

class Book {

    String name;
    Author author;
    List<Integer> price

    static constraints = {
    }
}
