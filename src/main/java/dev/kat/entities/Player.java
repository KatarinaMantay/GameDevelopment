package dev.kat.entities;

import dev.kat.enums.Direction;
import dev.kat.enums.PlayerAction;
import dev.kat.enums.SpriteAtlas;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Set;
import dev.kat.utilities.LoadSave;

import static dev.kat.utilities.HelperMethods.canMoveHere;


public class Player extends Entity {
    private BufferedImage[][] animations;
    private int animationTick, animationIndex;
    private PlayerAction playerAction = PlayerAction.IDLE;
    private Direction playerDirection = Direction.NONE;
    private boolean moving = false;
    private boolean attacking = false;
    private int[][] lvlData;

    private final Set<Direction> pressedDirections;

    public Player(float x, float y, int width, int height) {
        super(x, y, width, height);
        loadAnimations();
        pressedDirections = new HashSet<>();
    }

    public void update() {
        updateDirection();
        updateHitbox();
        setAnimation();
        updatePosition();
        updateAnimationTick();
    }

    public void render(Graphics g) {
        g.drawImage(
                animations[playerAction.getValue()][animationIndex],
                (int)x,
                (int)y,
                120,
                80,
                null
        );
        drawHitbox(g);
    }

    private void updateDirection() {
        if (!pressedDirections.isEmpty()) {
            if (pressedDirections.contains(Direction.UP)) {
                setDirection(Direction.UP);
            } else if (pressedDirections.contains(Direction.DOWN)) {
                setDirection(Direction.DOWN);
            } else if (pressedDirections.contains(Direction.LEFT)) {
                setDirection(Direction.LEFT);
            } else if (pressedDirections.contains(Direction.RIGHT)) {
                setDirection(Direction.RIGHT);
            }
        } else {
            setDirection(Direction.NONE);
            moving = false;
        }
    }

    public void setDirection(Direction direction) {
        this.playerDirection = direction;
        moving = direction != Direction.NONE;
    }

    public Direction getDirection() {
        return playerDirection;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    private void updateAnimationTick() {
        animationTick++;
        if (animationTick >= playerAction.getAnimationSpeed()) {
            animationTick = 0;
            animationIndex++;
            if (animationIndex >= playerAction.getSpriteAmount()) {
                animationIndex = 0;

                if (playerAction == PlayerAction.ATTACK_1) {
                    attacking = false;
                    playerAction = PlayerAction.IDLE;
                }
            }
        }
    }

    private void setAnimation() {
        if (attacking && playerAction != PlayerAction.ATTACK_1) {
            animationIndex = 0;
            playerAction = PlayerAction.ATTACK_1;
        }
        else if (moving && playerAction != PlayerAction.RUNNING) {
            animationIndex = 0;
            playerAction = PlayerAction.RUNNING;
        }
        else if (!moving && playerAction != PlayerAction.IDLE && !attacking) {
            animationIndex = 0;
            playerAction = PlayerAction.IDLE;
        }
    }

    private void updatePosition() {
        if (!moving) return;

        float xSpeed = 0;
        float ySpeed = 0;

        switch (playerDirection) {
            case LEFT -> xSpeed = -1;
            case RIGHT -> xSpeed = 1;
            case UP -> ySpeed = -1;
            case DOWN -> ySpeed = 1;
        }

        // Check next position collision
        if (canMoveHere((int) (hitbox.x + xSpeed), (int) (hitbox.y + ySpeed), hitbox.width, hitbox.height, lvlData)) {
            // Update hitbox position
            hitbox.x += xSpeed;
            hitbox.y += ySpeed;

            // Update actual position
            x += xSpeed;
            y += ySpeed;
        } else {
            moving = false; // Stop moving if bump into a wall
        }
    }

    private void loadAnimations() {
            BufferedImage image = LoadSave.getSpriteAtlas(SpriteAtlas.PLAYER_ATLAS);
            animations = new BufferedImage[PlayerAction.values().length][];
            for (PlayerAction action : PlayerAction.values()) {
                int actionIndex = action.getValue();
                animations[actionIndex] = new BufferedImage[action.getSpriteAmount()];
                for (int i = 0; i < action.getSpriteAmount(); i++) {
                    animations[actionIndex][i] = image.getSubimage(i * 64, actionIndex * 40, 64, 40);
                }
            }
    }

    public void loadLevelData(int[][] lvlData) {
        this.lvlData = lvlData;
    }

    public void resetDir() {
        pressedDirections.clear();
    }

    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
    }

    public void setUp(boolean up) {
        if (up) {
            pressedDirections.add(Direction.UP);
        } else {
            pressedDirections.remove(Direction.UP);
        }
        updateDirection();
    }

    public void setDown(boolean down) {
        if (down) {
            pressedDirections.add(Direction.DOWN);
        } else {
            pressedDirections.remove(Direction.DOWN);
        }
        updateDirection();
    }

    public void setLeft(boolean left) {
        if (left) {
            pressedDirections.add(Direction.LEFT);
        } else {
            pressedDirections.remove(Direction.LEFT);
        }
        updateDirection();
    }

    public void setRight(boolean right) {
        if (right) {
            pressedDirections.add(Direction.RIGHT);
        } else {
            pressedDirections.remove(Direction.RIGHT);
        }
        updateDirection();
    }

    public boolean isUp() {
        return pressedDirections.contains(Direction.UP);
    }

    public boolean isDown() {
        return pressedDirections.contains(Direction.DOWN);
    }

    public boolean isLeft() {
        return pressedDirections.contains(Direction.LEFT);
    }

    public boolean isRight() {
        return pressedDirections.contains(Direction.RIGHT);
    }
}