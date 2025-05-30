package world.environment; 

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Lighting {

    GamePanel gp;
    BufferedImage darknessFilter; 
    public float filterAlpha = 0f; 

    public final int DAY = 0;
    public final int DUSK = 1;
    public final int NIGHT = 2;
    public final int DAWN = 3;
    public int dayState = DAY;

    private final int JAM_SIANG_MULAI = 6;    
    private final int JAM_SENJA_MULAI = 17;   
    private final int JAM_MALAM_MULAI = 23;  
    private final int JAM_FAJAR_MULAI = 4;    

    private final float RAIN_DARKNESS_FACTOR = 0.3f;

    public Lighting(GamePanel gp) {
        this.gp = gp;
        createDarknessFilter(); 
    }

    private void createDarknessFilter() {
        darknessFilter = new BufferedImage(gp.screenWidth, gp.screenHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = (Graphics2D) darknessFilter.getGraphics();
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        g2.dispose();
    }

    public void resetDay() {
        dayState = DAY;
        filterAlpha = 0f;
    }

    public void update() {
        if (gp.farm == null ||
            gp.farm.getGameClock() == null ||
            gp.farm.getWeather() == null) {
            return; 
        }

        GameClock clock = gp.farm.getGameClock();
        Weather weather = gp.farm.getWeather();
        int currentHour = clock.getHours();
        boolean isRaining = weather.isRainy(); 

        float targetAlpha = 0f;

        if (currentHour >= JAM_SIANG_MULAI && currentHour < JAM_SENJA_MULAI) {
            dayState = DAY;
            targetAlpha = 0f; 
        } else if (currentHour >= JAM_SENJA_MULAI && currentHour < JAM_MALAM_MULAI) {
            dayState = DUSK;
            float progress = (float)(currentHour * 60 + clock.getMinutes() - JAM_SENJA_MULAI * 60) /
                             (float)((JAM_MALAM_MULAI - JAM_SENJA_MULAI) * 60);
            targetAlpha = Math.min(0.7f, progress * 0.7f);
        } else if (currentHour >= JAM_MALAM_MULAI || currentHour < JAM_FAJAR_MULAI) {
            dayState = NIGHT;
            targetAlpha = 0.7f; 
        } else if (currentHour >= JAM_FAJAR_MULAI && currentHour < JAM_SIANG_MULAI) {
            dayState = DAWN;
            float progressTowardDay = (float)(currentHour * 60 + clock.getMinutes() - JAM_FAJAR_MULAI * 60) /
                                      (float)((JAM_SIANG_MULAI - JAM_FAJAR_MULAI) * 60);
            targetAlpha = Math.max(0f, 0.7f - (progressTowardDay * 0.7f));
        }

        if (isRaining) {
            targetAlpha += RAIN_DARKNESS_FACTOR;
            if (targetAlpha > 0.7f) {
                targetAlpha = 0.7f;
            }
        }
    
        float transitionSpeed = 0.01f; 
        if (filterAlpha < targetAlpha) {
            filterAlpha += transitionSpeed;
            if (filterAlpha > targetAlpha) {
                filterAlpha = targetAlpha;
            }
        } else if (filterAlpha > targetAlpha) {
            filterAlpha -= transitionSpeed;
            if (filterAlpha < targetAlpha) {
                filterAlpha = targetAlpha;
            }
        }
    }

    public void draw(Graphics2D g2) {
        if (filterAlpha > 0.0f) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, filterAlpha));
            g2.drawImage(darknessFilter, 0, 0, null); 
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        }
    }

    
}