package main;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import object.*;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        int[][] objectMap = loadObjectMap("/resource/maps/object_map.txt", gp.maxWorldCol, gp.maxWorldRow);
        int objIndex = 0;

        for (int row = 0; row < gp.maxWorldRow; row++) {
            for (int col = 0; col < gp.maxWorldCol; col++) {
                int id = objectMap[col][row];
                if (id == 0) continue;

                switch (id) {
                    case 1: // House
                    OBJ_House house = new OBJ_House(gp);
                    house.worldX = col;
                    house.worldY = row;
                    gp.objects[objIndex++] = house;
                    break;

                    case 2: // Shipping Bin
                        OBJ_ShippingBin bin = new OBJ_ShippingBin(gp);
                        bin.worldX = col;
                        bin.worldY = row;
                        gp.objects[objIndex++] = bin;
                        break;

                    case 3: // Pond
                        OBJ_Pond pond = new OBJ_Pond(gp);
                        pond.worldX = col;
                        pond.worldY = row;
                        gp.objects[objIndex++] = pond;
                        break;

                    // Tambahkan case lain jika perlu
                }
            }
        }
    }

    public int[][] loadObjectMap(String filePath, int maxCols, int maxRows) {
        int[][] map = new int[maxCols][maxRows];
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            int row = 0;
            while ((line = br.readLine()) != null && row < maxRows) {
                String[] numbers = line.trim().split("\\s+");
                for (int col = 0; col < numbers.length && col < maxCols; col++) {
                    map[col][row] = Integer.parseInt(numbers[col]);
                }
                row++;
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
            return map;
    }
    
}
