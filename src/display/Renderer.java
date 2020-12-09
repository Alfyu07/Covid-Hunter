package display;


import game.Game;
import game.state.State;
import map.Tile;

import java.awt.Graphics;

public class Renderer {

    public void render(State state, Graphics g){
        renderMap(state, g);
        Camera camera = state.getCamera();
        state.getGameObjects().forEach(gameObject -> g.drawImage(
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
        Tile[][] tiles = state.getGameMap().getTiles();
        Camera camera = state.getCamera();
        for(int x = 0; x<tiles.length; x++){
            for (int y = 0; y<tiles[0].length; y++){
                g.drawImage(tiles[x][y].getSprite(),
                        x* Game.SPRITE_SIZE - camera.getPosition().intX()  ,
                        y * Game.SPRITE_SIZE - camera.getPosition().intY() ,
                        null);
            }
        }

    }
}
