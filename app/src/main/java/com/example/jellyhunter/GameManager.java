package com.example.jellyhunter;

import java.util.ArrayList;
import java.util.Random;

public class GameManager {
    private final ArrayList<Integer> obstacles = new ArrayList<>();
    private int spawnRate = 10; // safe in 1/spawnRate
    private int lives = 3;
    private int score;
    private int rows;
    private int cols;
    private int[][] grid;
    private Hero hero;

    public GameManager(int initialLives, int rows, int cols, Hero hero) {
        if (initialLives > 0 && initialLives <= 3)
            this.lives = initialLives;
        if (rows < 1 || cols < 1)
            throw new RuntimeException("grid too small");
        this.score = 1 - rows;
        this.rows = rows;
        this.cols = cols;
        this.hero = hero;
        this.grid = new int[rows][cols];

        this.grid[hero.getY()][hero.getX()] = hero.getImage();

        obstacles.add(R.drawable.img_coral);
        obstacles.add(R.drawable.img_jelly);
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
        this.score = 1 - rows;
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
        score++;
        return grid;
    }
    public boolean isHit() {
        if (obstacles.contains(grid[hero.getY()-1][hero.getX()])) {
            lives--;
            grid[hero.getY()][hero.getX()] = hero.getImage();
            return true;
        }
        return lives == 0; // in case of death don't act as hurt
    }
    private void generateObstacle() {
        if (new Random().nextInt(spawnRate) != 0) {
            int obs = obstacles.get(new Random().nextInt(obstacles.size()));
            int location = new Random().nextInt(cols);
            grid[0][location] = obs;
        }
    }
    public int getLives() {
        return lives;
    }
    public int getScore() {
        return Math.max(score, 0);
    }
}
