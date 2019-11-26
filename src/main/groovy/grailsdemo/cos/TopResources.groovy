package grailsdemo.cos

import grailsdemo.Topic
import grailsdemo.User

class TopResources {
    Long id
    String description
    User createdBy
    Long totalVotes
    Topic topic


    @Override
    public String toString() {
        return "TopResources{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", createdBy=" + createdBy +
                ", totalVotes=" + totalVotes +
                ", topic=" + topic +
                '}';
    }
}


