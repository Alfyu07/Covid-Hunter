package map;

import gfx.SpriteLibrary;
import java.awt.Image;

public class Tile {
    private Image sprite;

    public  Tile(SpriteLibrary spriteLibrary){
        this.sprite = spriteLibrary.getImage("woodfloor");
    }

    public Image getSprite(){
        return sprite;
    }
}
