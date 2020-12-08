package display;


import game.Game;
import game.state.State;
import map.Tile;

import java.awt.Graphics;

public class Renderer {

    public void render(State state, Graphics g){
        renderMap(state, g);
        state.getGameObjects().forEach(gameObject -> g.drawImage(
                gameObject.getSprite(),
                gameObject.getPosition().intX(),
                gameObject.getPosition().intY(),
                null
        ));
    }

    private void renderMap(State state, Graphics g) {
        Tile[][] tiles = state.getGameMap().getTiles();

        for(int x = 0; x<tiles.length; x++){
            for (int y = 0; y<tiles[0].length; y++){
                g.drawImage(tiles[x][y].getSprite(),
                        x* Game.SPRITE_SIZE,
                        y * Game.SPRITE_SIZE,
                        null);
            }
        }

    }
}
