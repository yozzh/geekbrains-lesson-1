package ru.geekbrains.classes;

import java.util.ArrayList;
import java.util.Arrays;

public class Team {
    private String name;
    private ArrayList<Participant> participants;

    public Team(String name, Participant[] participants) {
        this.name = name;
        this.participants = new ArrayList<>();
        this.participants.addAll(Arrays.asList(participants));
    }

    public String getName() {
        return name;
    }

    public ArrayList<Participant> getParticipants() {
        return participants;
    }

    public void addParticipant(Participant participant) {
        participants.add(participant);
    }

    public void showResults() {
        System.out.println("Here is the status of \"" + name + "\" team");
        for (Participant participant : participants) {
            System.out.println(participant);
        }
    }
}
