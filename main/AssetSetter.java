package main;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import data.NPCData;
import entity.NPC.*;
import entity.object.*;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject(int randomMapIndex) {

        if (gp.currentMap == 0) {
            int[][] objectMap = loadObjectMap("/resource/maps/object_map" + randomMapIndex + ".txt", gp.maxWorldCol, gp.maxWorldRow);
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
                        gp.objects[gp.currentMap][objIndex++] = house;
                        break;
    
                        case 2: // Shipping Bin
                            OBJ_ShippingBin bin = new OBJ_ShippingBin(gp);
                            bin.worldX = col;
                            bin.worldY = row;
                            gp.objects[gp.currentMap][objIndex++] = bin;
                            break;
    
                        case 3: // Pond
                            OBJ_Pond pond = new OBJ_Pond(gp);
                            pond.worldX = col;
                            pond.worldY = row;
                            gp.objects[gp.currentMap][objIndex++] = pond;
                            break;
    
                        // Tambahkan case lain jika perlu
                    }
                }
            }
        }
        
        if (gp.currentMap == 1) {
            int[][] objectMap = loadObjectMap("/resource/maps/object_house.txt", gp.maxWorldCol, gp.maxWorldRow);
            int objIndex = 0;
    
            for (int row = 0; row < gp.maxWorldRow; row++) {
                for (int col = 0; col < gp.maxWorldCol; col++) {
                    int id = objectMap[col][row];
                    if (id == 0) continue;

                    switch (id) {
                        case 1:
                        OBJ_Bed_1 bed1 = new OBJ_Bed_1(gp);
                        bed1.worldX = col;
                        bed1.worldY = row;
                        gp.objects[gp.currentMap][objIndex++] = bed1;
                        break;

                        case 2:
                        OBJ_Bed_2 bed2 = new OBJ_Bed_2(gp);
                        bed2.worldX = col;
                        bed2.worldY = row;
                        gp.objects[gp.currentMap][objIndex++] = bed2;
                        break;

                        case 3:
                        OBJ_Bed_3 bed3 = new OBJ_Bed_3(gp);
                        bed3.worldX = col;
                        bed3.worldY = row;
                        gp.objects[gp.currentMap][objIndex++] = bed3;
                        break;

                        case 4:
                        OBJ_TV tv = new OBJ_TV(gp);
                        tv.worldX = col;
                        tv.worldY = row;
                        gp.objects[gp.currentMap][objIndex++] = tv;
                        break;

                        case 5:
                        OBJ_Stove stove = new OBJ_Stove(gp);
                        stove.worldX = col;
                        stove.worldY = row;
                        gp.objects[gp.currentMap][objIndex++] = stove;
                        break;
                    }
                }
            }
        }
        if (gp.currentMap == 2) {
            int[][] objectMap = loadObjectMap("/resource/maps/object_forest.txt", gp.maxWorldCol, gp.maxWorldRow);
            int objIndex = 0;

            for (int row = 0; row < gp.maxWorldRow; row++) {
                for (int col = 0; col < gp.maxWorldCol; col++) {
                    int id = objectMap[col][row];
                    if (id == 0) continue;

                    switch (id) {
                        case 1:
                        OBJ_Lake Lake = new OBJ_Lake(gp);
                        Lake.worldX = col;
                        Lake.worldY = row;
                        gp.objects[gp.currentMap][objIndex++] = Lake;
                        break;
                    }
                }
            }
        }
        if (gp.currentMap == 3) {
            int[][] objectMap = loadObjectMap("/resource/maps/object_mountain.txt", gp.maxWorldCol, gp.maxWorldRow);
            int objIndex = 0;

            for (int row = 0; row < gp.maxWorldRow; row++) {
                for (int col = 0; col < gp.maxWorldCol; col++) {
                    int id = objectMap[col][row];
                    if (id == 0) continue;

                    switch (id) {
                        case 1:
                        OBJ_Lake Lake = new OBJ_Lake(gp);
                        Lake.worldX = col;
                        Lake.worldY = row;
                        gp.objects[gp.currentMap][objIndex++] = Lake;
                        break;
                    }
                }
            }
        }
        if (gp.currentMap == 4) {
            int[][] objectMap = loadObjectMap("/resource/maps/object_ocean.txt", gp.maxWorldCol, gp.maxWorldRow);
            int objIndex = 0;

            for (int row = 0; row < gp.maxWorldRow; row++) {
                for (int col = 0; col < gp.maxWorldCol; col++) {
                    int id = objectMap[col][row];
                    if (id == 0) continue;

                    switch (id) {
                        case 1:
                        OBJ_Lake Lake = new OBJ_Lake(gp);
                        Lake.worldX = col;
                        Lake.worldY = row;
                        gp.objects[gp.currentMap][objIndex++] = Lake;
                        break;
                    }
                }
            }
        }
    }

    public void setNPC() {
        if (gp.currentMap == 5) {
            int[][] npcMap = loadNPCMap("/resource/maps/npc_map" + gp.currentMap + ".txt", gp.maxWorldCol, gp.maxWorldRow);
            int npcIndex = 0;

            for (int row = 0; row < gp.maxWorldRow; row++) {
                for (int col = 0; col < gp.maxWorldCol; col++) {
                    int id = npcMap[col][row];
                    if (id == 0) continue;

                    switch (id) {
                        case 1: 
                            Emily emily = (Emily) NPCData.getNPCByName("Emily");
                            emily.worldX = col;
                            emily.worldY = row;
                            gp.npcs[gp.currentMap][npcIndex++] = emily;
                            break;
                    }
                }
            }
        }
        else if (gp.currentMap == 6) {
            int[][] npcMap = loadNPCMap("/resource/maps/npc_map" + gp.currentMap + ".txt", gp.maxWorldCol, gp.maxWorldRow);
            int npcIndex = 0;

            for (int row = 0; row < gp.maxWorldRow; row++) {
                for (int col = 0; col < gp.maxWorldCol; col++) {
                    int id = npcMap[col][row];
                    if (id == 0) continue;

                    switch (id) {
                        case 1: 
                            MayorTadi mayorTadi = (MayorTadi) NPCData.getNPCByName("Mayor Tadi");
                            mayorTadi.worldX = col;
                            mayorTadi.worldY = row;
                            gp.npcs[gp.currentMap][npcIndex++] = mayorTadi;
                            break;
                    }
                }
            }
        }
        else if (gp.currentMap == 7) {
            int[][] npcMap = loadNPCMap("/resource/maps/npc_map" + gp.currentMap + ".txt", gp.maxWorldCol, gp.maxWorldRow);
            int npcIndex = 0;

            for (int row = 0; row < gp.maxWorldRow; row++) {
                for (int col = 0; col < gp.maxWorldCol; col++) {
                    int id = npcMap[col][row];
                    if (id == 0) continue;

                    switch (id) {
                        case 1: 
                            Caroline caroline = (Caroline) NPCData.getNPCByName("Caroline");
                            caroline.worldX = col;
                            caroline.worldY = row;
                            gp.npcs[gp.currentMap][npcIndex++] = caroline;
                            break;
                    }
                }
            }
        }
        else if (gp.currentMap == 8) {
            int[][] npcMap = loadNPCMap("/resource/maps/npc_map" + gp.currentMap + ".txt", gp.maxWorldCol, gp.maxWorldRow);
            int npcIndex = 0;

            for (int row = 0; row < gp.maxWorldRow; row++) {
                for (int col = 0; col < gp.maxWorldCol; col++) {
                    int id = npcMap[col][row];
                    if (id == 0) continue;

                    switch (id) {
                        case 1: 
                            Perry perry = (Perry) NPCData.getNPCByName("Perry");
                            perry.worldX = col;
                            perry.worldY = row;
                            gp.npcs[gp.currentMap][npcIndex++] = perry;
                            break;
                    }
                }
            }
        }
        else if (gp.currentMap == 9) {
            int[][] npcMap = loadNPCMap("/resource/maps/npc_map" + gp.currentMap + ".txt", gp.maxWorldCol, gp.maxWorldRow);
            int npcIndex = 0;

            for (int row = 0; row < gp.maxWorldRow; row++) {
                for (int col = 0; col < gp.maxWorldCol; col++) {
                    int id = npcMap[col][row];
                    if (id == 0) continue;

                    switch (id) {
                        case 1: 
                            Dasco dasco = (Dasco) NPCData.getNPCByName("Dasco");
                            dasco.worldX = col;
                            dasco.worldY = row;
                            gp.npcs[gp.currentMap][npcIndex++] = dasco;
                            break;
                    }
                }
            }
        }
        else if (gp.currentMap == 10) {
            int[][] npcMap = loadNPCMap("/resource/maps/npc_map" + gp.currentMap + ".txt", gp.maxWorldCol, gp.maxWorldRow);
            int npcIndex = 0;

            for (int row = 0; row < gp.maxWorldRow; row++) {
                for (int col = 0; col < gp.maxWorldCol; col++) {
                    int id = npcMap[col][row];
                    if (id == 0) continue;

                    switch (id) {
                        case 1: 
                            Abigail abigail = (Abigail) NPCData.getNPCByName("Abigail");
                            abigail.worldX = col;
                            abigail.worldY = row;
                            gp.npcs[gp.currentMap][npcIndex++] = abigail;
                            break;
                    }
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

    public int[][] loadNPCMap(String filePath, int maxCols, int maxRows) {
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
