package grailsdemo.enums;

public enum Seriousness {

    CASUAL, SERIOUS, VERY_SERIOUS;

    static Seriousness stringToEnum(String str){
        return valueOf(Seriousness.class, str.toUpperCase())
    }
}