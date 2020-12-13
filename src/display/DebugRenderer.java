package display;

import core.CollisionBox;
import entities.humanoid.Humanoid;
import game.state.State;
import ui.UIText;

import java.awt.Color;
import java.awt.Graphics;
import java.util.stream.Collectors;

public class DebugRenderer {
    public void render(State state, Graphics g){
        Camera camera = state.getCamera();
        state.getGameObjects().stream()
                .filter(gameObject -> camera.isInView(gameObject))
                .map(gameObject -> gameObject.getCollisionBox())
                .forEach(collisionBox -> drawCollisionBox(collisionBox, g, camera));

        drawEffects(state,g);
    }
    private void drawEffects(State state, Graphics g){
        Camera camera = state.getCamera();
        state.getGameObjectsOfClass(Humanoid.class).stream()
                .forEach(humanoid -> {
                    UIText effectsText = new UIText(
                            humanoid.getEffects().stream().map(effect -> effect.getClass().getSimpleName())
                            .collect(Collectors.joining(","))
                    );
                    effectsText.update(state);

                    g.drawImage(
                            effectsText.getSprite(),
                            humanoid.getPosition().intX() - camera.getPosition().intX(),
                            humanoid.getPosition().intY() - camera.getPosition().intY(),
                            null
                    );
                });
    }

    private void drawCollisionBox(CollisionBox collisionBox, Graphics g, Camera camera){
        g.setColor(Color.red);
        g.drawRect(
                (int) collisionBox.getBounds().getX() - camera.getPosition().intX(),
                (int) collisionBox.getBounds().getY() - camera.getPosition().intY(),
                (int) collisionBox.getBounds().getWidth(),
                (int) collisionBox.getBounds().getHeight()
        );
    }
}
