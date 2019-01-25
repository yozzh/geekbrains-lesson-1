package ru.geekbrains.classes.obstacles;

import ru.geekbrains.classes.Participant;
import ru.geekbrains.classes.Team;

import java.util.Arrays;
import java.util.List;

public class Course {
    private List<Obstacle> obstacles;

    public Course(Obstacle... obstacles) {
        this.obstacles = Arrays.asList(obstacles);
    }

    public List<Obstacle> getObstacles() {
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
