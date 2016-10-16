package pl.budyn.tetris.service;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import pl.budyn.tetris.runtime.Log;

import java.io.File;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hlibe on 02.09.2016.
 */
public class ResourceManager {

    private static final String TAG = "ResourceManager";

    private static ResourceManager instance = null;

    private Map<String, Texture> texturesCache = new HashMap<>();
    private Map<FontKey, BitmapFont> fontsCache = new HashMap<>();

    //Constructor
    private ResourceManager() {
        load();
    }
    public static ResourceManager getInstance() {
        if(instance == null) {
            instance = new ResourceManager();
        }
        return instance;
    }
    private void load(){
        try {
            preloadImages();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        preloadFonts();
    }

    //Textures
    //TODO: It won't work on android! Wrong implementation!
    private void preloadImages() throws URISyntaxException {
        String[] fileList;
        File file = new File(ClassLoader.getSystemResource("textures").toURI());
        fileList = file.list();
        for (String name: fileList) {
            preloadImage(name);
        }
    }
    private void preloadImage(String imageName) {
        imageName = imageName.replaceAll(".png", "");
        Texture texture = new Texture(Gdx.files.internal("textures/" + imageName + ".png"));
        texturesCache.put(imageName, texture);
        Log.info(TAG, "Texture loaded: " + imageName + ".png");
    }
    public Texture getImageResource(String imageName) {
        if(!texturesCache.containsKey(imageName)){
            Log.err(TAG, "Can't find image: " + imageName);
        } else {
            return texturesCache.get(imageName);
        }
        return texturesCache.get("texture_blue");
    }

    //Fonts
    private class FontKey{
        private String name;
        private int size;

        public FontKey(String name, int size) {
            this.name = name;
            this.size = size;
        }

        public String getName() {
            return name;
        }

        public int getSize() {
            return size;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            FontKey fontKey = (FontKey) o;

            if (size != fontKey.size) return false;
            return name != null ? name.equals(fontKey.name) : fontKey.name == null;

        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + size;
            return result;
        }
    }
    private void preloadFonts(){

    }
    private void preloadFont(String fontName, int fontSize){
        FontKey key = new FontKey(fontName, fontSize);
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/" + fontName + ".ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = fontSize;
        BitmapFont font = generator.generateFont(parameter);
        fontsCache.put(key, font);
        Log.err(TAG, "Font loaded " + fontName + " in size " + fontSize);
    }
    public BitmapFont getFontResource(String fontName, int fontSize){
        FontKey key = new FontKey(fontName, fontSize);
        if(!fontsCache.containsKey(key)){
            preloadFont(fontName, fontSize);
        }
        return fontsCache.get(key);
    }
}
