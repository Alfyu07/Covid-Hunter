package gfx;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class SpriteLibrary {

    private final static String PATH_TO_UNITS = "/sprites/units";

    public Map<String, SpriteSet> units;

    public SpriteLibrary(){
        units = new HashMap<>();
        loadSpriteFromDisk();
    }

    private void loadSpriteFromDisk(){
        String[] folderNames = getFolderNames(PATH_TO_UNITS);

        for (String folderName : folderNames){
            SpriteSet spriteSet = new SpriteSet();
            String pathFolder = PATH_TO_UNITS + "/" + folderName;
            String[] SheetsInFolder = getSheetsInFolder(pathFolder);

            for(String sheetName : SheetsInFolder){
                spriteSet.addSheet(sheetName.substring(0, sheetName.length() -4),
                        ImageUtils.loadImage(pathFolder + "/" + sheetName));
            }
            units.put(folderName, spriteSet);
        }
    }

    private String[] getSheetsInFolder(String basePath) {
        URL resource = SpriteLibrary.class.getResource(basePath);
        File file = new File(resource.getFile());

        return file.list((current, name) -> new File(current, name).isFile());
    }

    private String[] getFolderNames(String basePath) {
        URL resource = SpriteLibrary.class.getResource(basePath);
        File file = new File(resource.getFile());

        return file.list((current, name) -> new File(current, name).isDirectory());
    }

}
