package grailsdemo.cos

import grailsdemo.User
import grailsdemo.enums.Visibility

class TopicVO {

    Long id;
    String name;
    Visibility visibility;
    Long count;
    User createdBy;


    @Override
    public String toString() {
        return "TopicVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", visibility=" + visibility +
                ", count=" + count +
                ", createdBy=" + createdBy +
                '}';
    }
}
