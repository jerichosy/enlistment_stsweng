package com.orangeandbronze.enlistment;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import java.util.Objects;

import static org.apache.commons.lang3.Validate.*;

class Room {
    private final String roomName;
    private final int roomCapacity;
    Room(String roomName, int roomCapacity) {
        notBlank(roomName);
        notNull(roomCapacity);
        if (roomCapacity < 0){
            throw new IllegalArgumentException(
                    "roomCapacity should be non-negative, was:" + roomCapacity);
        }
        isTrue(StringUtils.isAlphanumeric(roomName),
                "roomName must be alphanumeric, was:" + roomName);
        this.roomCapacity = roomCapacity;
        this.roomName = roomName;
    }

    int getRoomCapacity(){
        return this.roomCapacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(roomName, room.roomName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomName, roomCapacity);
    }
}
