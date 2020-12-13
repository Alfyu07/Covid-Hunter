package gfx;

import java.awt.*;
import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class SpriteLibrary {


    public Map<String, SpriteSet> spriteSets;
    private Map<String, Image> images;

    public SpriteLibrary(){
        spriteSets = new HashMap<>();
        images = new HashMap<>();
        loadSpriteFromDisk();
    }

    private void loadSpriteFromDisk(){
        loadSpriteSets("/sprites/units");
        loadImages("/sprites/tiles");
        loadImages("/sprites/effects");

    }

    private void loadSpriteSets(String path) {
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
            spriteSets.put(folderName, spriteSet);
        }
    }

    private void loadImages(String path){
        String [] ImagesInFolder = getImagesInFolder(path);
        for(String fileName : ImagesInFolder){
            images.put(
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

    public SpriteSet getSpriteSet(String name) {
        return spriteSets.get(name);
    }

    public Image getImage(String name){
        return images.get(name);
    }
}
