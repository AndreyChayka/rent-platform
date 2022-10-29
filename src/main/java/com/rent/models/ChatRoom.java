package com.rent.models;

import javax.persistence.*;

@Entity
@Table(name = "chat_room")
public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JoinColumn(name = "user1", nullable = false)
    private long user1;

    @JoinColumn(name = "user2", nullable = false)
    private long user2;

    public ChatRoom(long user1, long user2) {
        this.user1 = user1;
        this.user2 = user2;
    }

    public ChatRoom() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUser1() {
        return user1;
    }

    public void setUser1(long user1) {
        this.user1 = user1;
    }

    public long getUser2() {
        return user2;
    }

    public void setUser2(long user2) {
        this.user2 = user2;
    }
}
