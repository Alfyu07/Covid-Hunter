package display;

import core.CollisionBox;
import game.state.State;

import java.awt.Color;
import java.awt.Graphics;

public class DebugRenderer {
    public void render(State state, Graphics g){
        Camera camera = state.getCamera();
        state.getGameObjects().stream()
                .filter(gameObject -> camera.isInView(gameObject))
                .map(gameObject -> gameObject.getCollisionBox())
                .forEach(collisionBox -> drawCollisionBox(collisionBox, g, camera));
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