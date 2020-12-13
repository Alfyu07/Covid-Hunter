package entities;

import controller.NPCController;
import core.Direction;
import core.Vector2D;
import gfx.AnimationManager;
import gfx.SpriteLibrary;
import gfx.SpriteSet;

public class Bubble extends MovingEntity{

    private boolean holding;

    public Bubble(NPCController npcController, SpriteLibrary spriteLibrary) {
        super(npcController, spriteLibrary);

        this.animationManager = new AnimationManager(new SpriteSet(spriteLibrary.getImage("bubble")), false);

    }

    @Override
    protected void handleCollision(GameObject other) {}

    @Override
    protected void handleMotion() {
        if(!holding){
            motion.add(new Vector2D(0,-0.5));
        }
        holding = false;
        direction = Direction.S;
    }

    public void hold(){
        holding = true;
    }
    @Override
    protected String decideAnimation() {
        return "default";
    }


    public void insert(GameObject gameObject){
        this.position = gameObject.getPosition();
        this.renderOffset = gameObject.getRenderOffset();
        this.collisionBoxOffset = renderOffset;
        gameObject.parent(this);
    }
}
