package ru.geekbrains.classes.obstacles;

import ru.geekbrains.classes.Participant;
import ru.geekbrains.classes.Team;

import java.util.ArrayList;
import java.util.Arrays;

public class Course {
    private ArrayList<Obstacle> obstacles;

    public Course(Obstacle[] obstacles) {
        this.obstacles = new ArrayList<Obstacle>();
        this.obstacles.addAll(Arrays.asList(obstacles));
    }

    public ArrayList<Obstacle> getObstacles() {
        return obstacles;
    }

    public void doIt(Team team) {
        for (Participant participant : team.getParticipants()) {
            System.out.println(participant.getName() + " is on course!");
            for (Obstacle obstacle: obstacles) {
                obstacle.doIt(participant);

                if (!participant.isOnCourse()) {
                    break;
                }
            }
            System.out.println();
        }
    }
}
