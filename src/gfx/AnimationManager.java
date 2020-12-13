package gfx;

import core.Direction;
import game.Game;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class AnimationManager {
    private SpriteSet spriteSet;
    private String currentAnimationName;
    private BufferedImage currentAnimationSheet;
    private int updatesPerFrame;
    private int currentFrameTime;
    private int frameIndex;
    private int directionIndex;

    private boolean looping;

    public AnimationManager(SpriteSet spriteSet, boolean looping) {
        this.spriteSet = spriteSet;
        this.updatesPerFrame = 20;
        this.frameIndex = 0;
        this.currentFrameTime = 0;
        this.directionIndex = 0;
        currentAnimationName = "";
        playAnimation("stand");
        this.looping = looping;
    }

    public AnimationManager(SpriteSet spriteSet){
        this(spriteSet, true);
    }



    public void  update(Direction direction){
        currentFrameTime++;
        directionIndex = direction.getAnimationRow();
        if(currentFrameTime >= updatesPerFrame){
            currentFrameTime = 0;
            frameIndex++;

            int animationSize = currentAnimationSheet.getWidth() / Game.SPRITE_SIZE;
            if(frameIndex >= animationSize ){
                frameIndex = looping ? 0 : animationSize - 1;
            }
        }
    }

    public Image getSprite(){
        return currentAnimationSheet.getSubimage(
                frameIndex * Game.SPRITE_SIZE,
                directionIndex * Game.SPRITE_SIZE,
                Game.SPRITE_SIZE,
                Game.SPRITE_SIZE
        );
    }

    public void playAnimation(String name){
        if(!name.equals(currentAnimationName)){
            this.currentAnimationSheet = (BufferedImage) spriteSet.get(name);
            currentAnimationName = name;
            frameIndex = 0;
        }
    }
}
