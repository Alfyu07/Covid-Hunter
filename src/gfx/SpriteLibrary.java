package gfx;

import java.awt.*;
import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class SpriteLibrary {


    public Map<String, SpriteSet> units;
    private Map<String, Image> tiles;

    public SpriteLibrary(){
        units = new HashMap<>();
        tiles = new HashMap<>();
        loadSpriteFromDisk();
    }

    private void loadSpriteFromDisk(){
        loadUnits("/sprites/units");
        loadTiles("/sprites/tiles");
    }

    private void loadUnits(String path) {
        String[] folderNames = getFolderNames(path);

        for (String folderName : folderNames){
            SpriteSet spriteSet = new SpriteSet();
            String pathFolder = path + "/" + folderName;
            String[] sheetsInFolder = getImagesInFolder(pathFolder);

            for(String sheetName : sheetsInFolder){
                spriteSet.addSheet(
                        sheetName.substring(0, sheetName.length() -4),
                        ImageUtils.loadImage(pathFolder + "/" + sheetName)
                );
            }
            units.put(folderName, spriteSet);
        }
    }

    private void loadTiles(String path){
        String [] ImagesInFolder = getImagesInFolder(path);
        for(String fileName : ImagesInFolder){
            tiles.put(
                    fileName.substring(0,fileName.length()-4),
                    ImageUtils.loadImage(path + "/" + fileName)
            );
        }
    }

    private String[] getImagesInFolder(String basePath) {
        URL resource = SpriteLibrary.class.getResource(basePath);
        File file = new File(resource.getFile());

        return file.list((current, name) -> new File(current, name).isFile());
    }

    private String[] getFolderNames(String basePath) {
        URL resource = SpriteLibrary.class.getResource(basePath);
        File file = new File(resource.getFile());

        return file.list((current, name) -> new File(current, name).isDirectory());
    }

    public SpriteSet getUnit(String name) {
        return units.get(name);
    }

    public Image getTile(String name){
        return tiles.get(name);
    }
}
