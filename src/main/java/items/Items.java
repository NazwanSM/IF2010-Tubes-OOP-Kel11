package items;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
public abstract class Items {
    protected String name;
    protected String type;
    protected BufferedImage itemImage;
    protected BufferedImage bnwItemImage;

    public Items(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setType(String type) {
        this.type = type;
    }

    public BufferedImage getItemImage() {
        return itemImage;
    }

    public BufferedImage getBnwItemImage() {
        return bnwItemImage;
    }

    protected void loadImage(String imagePath) {
        try {
            InputStream is = getClass().getResourceAsStream(imagePath);
            this.itemImage = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    protected void loadBnwImage(String imagePath) {
        try {
            InputStream is = getClass().getResourceAsStream(imagePath);
            this.bnwItemImage = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Items items = (Items) obj;
        return name.equals(items.name); // Bandingkan berdasarkan nama
    }

    @Override
    public int hashCode() {
        return name.hashCode(); // Gunakan hashCode dari nama
    }
}