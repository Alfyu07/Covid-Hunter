package display;


import core.Position;
import game.Game;
import game.state.State;
import map.GameMap;
import map.Tile;

import java.awt.Graphics;

public class Renderer {

    public void render(State state, Graphics g){
        renderMap(state, g);
        renderGameObject(state,g);
        renderUI(state, g);
    }

    private void renderUI(State state, Graphics g) {
        state.getUiContainers().forEach(uiContainer ->
                g.drawImage(
                        uiContainer.getSrite(),
                        uiContainer.getPosition().intX(),
                        uiContainer.getPosition().intY(),
                        null
                ));
    }

    private void renderGameObject(State state, Graphics g){
        Camera camera = state.getCamera();
        state.getGameObjects().stream()
                .filter(gameObject -> camera. isInView(gameObject))
                .forEach(gameObject -> g.drawImage(
                        gameObject.getSprite(),
                        gameObject.getPosition().intX() -
                                camera.getPosition().intX() -
                                gameObject.getSize().getWidth()/2,
                        gameObject.getPosition().intY() -
                                camera.getPosition().intY() -
                                gameObject.getSize().getHeight()/2,
                        null
                ));
    }
    private void renderMap(State state, Graphics g) {
        GameMap map = state.getGameMap();
        Camera camera = state.getCamera();

        Position start = map.getStartView(camera);
        Position end = map.getEndView(camera);


        for(int x = start.intX(); x < end.intX(); x++){
            for (int y = start.intY(); y < end.intY(); y++){
                g.drawImage(map.getTiles()[x][y].getSprite(),
                        x* Game.SPRITE_SIZE - camera.getPosition().intX()  ,
                        y * Game.SPRITE_SIZE - camera.getPosition().intY() ,
                        null);
            }
        }

    }
}
