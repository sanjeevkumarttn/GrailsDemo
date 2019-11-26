package grailsdemo.enums;

public enum Visibility {
    PRIVATE, PUBLIC

    static Visibility stringToEnum(String str){

        return valueOf(Visibility.class, str.toUpperCase())
    }
}
