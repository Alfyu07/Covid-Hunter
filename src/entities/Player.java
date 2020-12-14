package entities;

import controller.EntityController;
import core.Position;
import core.Vector2D;
import entities.humanoid.Humanoid;
import entities.humanoid.action.BlowBubble;
import entities.humanoid.action.WalkInDirection;
import entities.humanoid.effect.Isolated;
import entities.humanoid.effect.Sick;
import game.Game;
import gfx.AnimationManager;
import state.State;
import gfx.SpriteLibrary;
import state.ingame.GameState;

import java.util.Comparator;
import java.util.Optional;

public class Player extends Humanoid {

    private NPC target;
    private double targetRange;
    private SelectionCircle selectionCircle;


    public Player(EntityController entityController, SpriteLibrary spriteLibrary, SelectionCircle selectionCircle){
        super(entityController, spriteLibrary);
        this.selectionCircle = selectionCircle;
        this.targetRange = Game.SPRITE_SIZE;
        this.animationManager = new AnimationManager(spriteLibrary.getSpriteSet("matt"));

        setPosition(new Position(Game.SPRITE_SIZE * 7, 0));
        perform(new WalkInDirection(new Vector2D(0,1)));
    }

    @Override
    public void update(State state) {
        super.update(state);
        handleTarget(state);

        handleInput(state);
    }

    private void handleInput(State state){
        if(entityController.isRequestingAction()){
            if(target != null){
                if(action.isEmpty()){
                    GameState gameState = (GameState) state;
                    gameState.applyToScore(target.isAffectedBy(Sick.class) ? 250 : -250);
                }
                perform(new BlowBubble(target));
            }
        }
    }

    private void handleTarget(State state) {
        Optional<NPC> closestNPC = findClosestNPC(state);
        if(closestNPC.isPresent()){
            NPC npc = closestNPC.get();

            if(!npc.equals(target)){
                selectionCircle.parent(npc);
                target = npc;
            }
        }else{
            selectionCircle.clearParent();
            target = null;
        }
    }

    private Optional<NPC> findClosestNPC(State state) {
        return state.getGameObjectsOfClass(NPC.class).stream()
                .filter(npc -> getPosition().distanceTo(npc.getPosition()) <targetRange)
                .filter(npc -> isFacing(npc.getPosition()))
                .filter(npc -> !npc.isAffectedBy(Isolated.class))
                .min(Comparator.comparingDouble(npc -> position.distanceTo(npc.getPosition())));
    }

}
