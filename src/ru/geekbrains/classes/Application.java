package ru.geekbrains.classes;

import ru.geekbrains.classes.animals.Cat;
import ru.geekbrains.classes.animals.Dog;
import ru.geekbrains.classes.obstacles.Course;
import ru.geekbrains.classes.obstacles.Obstacle;
import ru.geekbrains.classes.obstacles.Cross;
import ru.geekbrains.classes.obstacles.Wall;
import ru.geekbrains.classes.obstacles.Water;
import ru.geekbrains.classes.robots.Robot;

public class Application {
    public static void main(String[] args) {
        Participant[] participants = {
            new Cat("Mrs. Meow", 10, 30, 0),
            new Dog("Mr. Peanut Butter", 20, 5, 15),
            new Dog("Mr. Pickles", 10, 4, 5),
            new Robot("Bender", 6, 2, 3)
        };
        Team team = new Team("Greate Team", participants);

        Obstacle[] obstacles = {
            new Cross(5),
            new Wall(3),
            new Water(7)
        };

        Course course = new Course(obstacles);
        course.doIt(team);

        team.showResults();
        team.showWinners();
    }
}
