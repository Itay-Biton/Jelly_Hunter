package com.example.jellyhunter.gameUtils;

import com.example.jellyhunter.R;

import java.util.ArrayList;
import java.util.Random;

public class GameManager {
    private final ArrayList<Integer> obstacles = new ArrayList<>();
    private final ArrayList<Integer> jellyfish = new ArrayList<>();
    private int obstaclesSpawnRate = 10; // safe in 1/obstaclesSpawnRate
    private int jellyfishSpawnRate = 5; // get jelly in 1/jellyfishSpawnRate
    private int lives = 3;
    private int jelly_score;
    private int meter_score;
    private int rows;
    private int cols;
    private int[][] grid;
    private Hero hero;

    public GameManager(int initialLives, int rows, int cols, Hero hero) {
        if (initialLives > 0 && initialLives <= 3)
            this.lives = initialLives;
        if (rows < 1 || cols < 1)
            throw new RuntimeException("grid too small");
        this.meter_score = 1 - rows;
        this.jelly_score = 0;
        this.rows = rows;
        this.cols = cols;
        this.hero = hero;
        this.grid = new int[rows][cols];

        this.grid[hero.getY()][hero.getX()] = hero.getImage();

        obstacles.add(R.drawable.img_coral);
        obstacles.add(R.drawable.img_seeweed);

        jellyfish.add(R.drawable.img_jelly);
        jellyfish.add(R.drawable.img_jelly1);
        jellyfish.add(R.drawable.img_jelly2);
    }

    public void restartGame(int initialLives, int[] pos) {
        this.hero.setLocation(pos);
        for (int r=0; r<rows; r++)
            for (int c=0; c<cols; c++)
                if (r == hero.getY() && c == hero.getX())
                    grid[r][c] = hero.getImage();
                else
                    grid[r][c] = 0;
        this.lives = initialLives;
        this.meter_score = 1 - rows;
        this.jelly_score = 0;
    }
    public Hero getHero() {
        return hero;
    }
    public int[][] move(String dir) {
        int[] origin = {hero.getY(), hero.getX()};
        switch (dir) {
            case "left":
                if (hero.getX()-1 >= 0)
                    hero.setX(hero.getX() - 1);
                break;

            case "right":
                if (hero.getX()+1 < cols)
                    hero.setX(hero.getX() + 1);
                break;

            default:
                break;
        }
        grid[origin[0]][origin[1]] = 0;
        grid[hero.getY()][hero.getX()] = hero.getImage();
        return new int[][] {origin, hero.getLocation()};
    }
    public int[][] updateGame() {
        for (int r=rows-2; r>0; r--)
            System.arraycopy(grid[r-1], 0, grid[r], 0, cols);
        for (int c=0; c<cols; c++)
            grid[0][c]=0;
        generateObstacle();
        generateJelly();
        meter_score++;
        return grid;
    }
    public int isHit() {
        if (obstacles.contains(grid[hero.getY()-1][hero.getX()])) {
            lives--;
            grid[hero.getY()][hero.getX()] = hero.getImage();
            return 1;
        }
        else if (jellyfish.contains(grid[hero.getY()-1][hero.getX()])) {
            jelly_score++;
            grid[hero.getY()][hero.getX()] = hero.getImage();
            return 2;
        }
        return 0;
    }
    private void generateObstacle() {
        if (new Random().nextInt(obstaclesSpawnRate) != 0) {
            int obs = obstacles.get(new Random().nextInt(obstacles.size()));
            int location = new Random().nextInt(cols);
            grid[0][location] = obs;
        }
    }
    private void generateJelly() {
        if (new Random().nextInt(jellyfishSpawnRate) == 0) {
            int obs = jellyfish.get(new Random().nextInt(jellyfish.size()));
            int location = new Random().nextInt(cols);
            grid[0][location] = obs;
        }
    }
    public int getLives() {
        return lives;
    }
    public int getMeterScore() {
        return Math.max(meter_score, 0);
    }
    public int getJellyScore() {
        return Math.max(jelly_score, 0);
    }

}
