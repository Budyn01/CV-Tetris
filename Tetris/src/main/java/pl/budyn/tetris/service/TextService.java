package pl.budyn.tetris.service;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

/**
 * Created by hlibe on 06.09.2016.
 */
public class TextService {

    private static TextService instance = null;

    private ResourceManager resourceManager = ResourceManager.getInstance();
    private GlyphLayout glyphLayout = new GlyphLayout();

    public enum Alignment {
        CENTER,
        LEFT,
        RIGHT;
    }
    public static class Config{
        private String fontName;
        private int fontSize;
        private Alignment alignment;
        private Color color;

        public Config() {
            this.fontName = "arial";
            this.fontSize = 12;
            this.alignment = Alignment.CENTER;
            this.color = Color.BLACK;
        }

        public String getFontName() {
            return fontName;
        }

        public void setFontName(String fontName) {
            this.fontName = fontName;
        }

        public int getFontSize() {
            return fontSize;
        }

        public void setFontSize(int fontSize) {
            this.fontSize = fontSize;
        }

        public Alignment getAlignment() {
            return alignment;
        }

        public void setAlignment(Alignment alignment) {
            this.alignment = alignment;
        }

        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
        }
    }

    private TextService(){}
    public static TextService getInstance() {
        if(instance == null){
            instance = new TextService();
        }
        return instance;
    }

    public void renderText(Batch batch, String text, int x, int y, Config config){
        BitmapFont font = resourceManager.getFontResource(config.getFontName(), config.getFontSize());
        glyphLayout.setText(font, text);
        float textWidth = glyphLayout.width;
        int actualPositionX = x;
        switch (config.getAlignment()) {
            case CENTER:
                actualPositionX -= textWidth/2;
                break;
            case RIGHT:
                actualPositionX -= textWidth;
                break;
        }
        batch.begin();
        font.setColor(Color.WHITE);
        font.draw(batch, glyphLayout, actualPositionX, y);
        batch.end();
    }
    public void renderText(Batch batch, String text, int x, int y){
        renderText(batch, text, x, y, new Config());
    }
}
