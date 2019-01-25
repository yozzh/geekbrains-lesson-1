package ru.geekbrains.classes;

import ru.geekbrains.classes.animals.Cat;
import ru.geekbrains.classes.animals.Dog;
import ru.geekbrains.classes.obstacles.Obstacle;
import ru.geekbrains.classes.obstacles.Cross;
import ru.geekbrains.classes.obstacles.Wall;
import ru.geekbrains.classes.obstacles.Water;
import ru.geekbrains.classes.robots.Robot;

public class Application {
    public static void main(String[] args) {
        ru.geekbrains.classes.Participant[] participants = new ru.geekbrains.classes.Participant[] {
            new Cat("Mrs. Meow", 10, 30, 0),
            new Dog("Mr. Peanutbutter", 20, 5, 15),
            new Dog("Mr. Peanutbutter II", 10, 4, 5),
            new Robot("mr. Robot", 1, 2,3)
        };

        Obstacle[] obstacles = new Obstacle[] {
            new Cross(5),
            new Wall(3),
            new Water(7)
        };

        for(ru.geekbrains.classes.Participant participant: participants) {
            for (Obstacle obstacle: obstacles) {
                obstacle.doIt(participant);

                if (!participant.isOnDistance()) {
                    break;
                }
            }
        }
    }
}
