package ru.geekbrains.classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Team {
    private String name;
    private List<Participant> participants;

    public Team(String name, Participant... participants) {
        this.name = name;
        this.participants = Arrays.asList(participants);
    }

    public String getName() {
        return name;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public void addParticipant(Participant participant) {
        participants.add(participant);
    }

    private void printResults(List<Participant> participants) {
        for (Participant participant : participants) {
            System.out.println(participant);
        }
        System.out.println();
    }

    public void showResults() {
        System.out.println("Here is the status of \"" + name + "\" team");
        printResults(participants);
    }

    public void showWinners() {
        System.out.println("Here is winners of the last course");
        printResults(participants.stream().filter(Participant::isOnCourse).collect(Collectors.toList()));
    }
}
