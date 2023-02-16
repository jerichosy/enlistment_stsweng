package com.orangeandbronze.enlistment;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

import static org.apache.commons.lang3.Validate.*;

class Room {
    private final String roomName;
    private final int roomCapacity;

    private final Collection<Schedule> takenTimeSlots = new HashSet<>();

    Room(String roomName, int roomCapacity, Collection<Schedule> takenTimeSlots) {
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
        this.takenTimeSlots.addAll(takenTimeSlots);
    }

    int getRoomCapacity(){
        return this.roomCapacity;
    }

    Collection<Schedule> getTakenTimeSlots() {
        return this.takenTimeSlots;
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
