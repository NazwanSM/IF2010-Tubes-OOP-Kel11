package main;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import data.NPCData;
import entity.npc.Abigail;
import entity.npc.Caroline;
import entity.npc.Dasco;
import entity.npc.Emily;
import entity.npc.MayorTadi;
import entity.npc.Perry;
import entity.npc.NPCEasterEgg1;
import entity.npc.NPCEasterEgg2;
import entity.npc.NPCEasterEgg3;
import entity.npc.NPCEasterEgg4;
import entity.npc.NPCEasterEgg5;
import entity.npc.NPCEasterEgg6;
import entity.object.OBJ_Bed_1;
import entity.object.OBJ_Bed_2;
import entity.object.OBJ_Bed_3;
import entity.object.OBJ_Cashier;
import entity.object.OBJ_House;
import entity.object.OBJ_Lake;
import entity.object.OBJ_Pond;
import entity.object.OBJ_ShippingBin;
import entity.object.OBJ_Stove;
import entity.object.OBJ_TV;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject(int randomMapIndex) {

        if (gp.currentMap == 0) {
            int[][] objectMap = loadObjectMap("/maps/object_map" + randomMapIndex + ".txt", gp.maxWorldCol, gp.maxWorldRow);
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
            int[][] objectMap = loadObjectMap("/maps/object_house.txt", gp.maxWorldCol, gp.maxWorldRow);
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
            int[][] objectMap = loadObjectMap("/maps/object_forest.txt", gp.maxWorldCol, gp.maxWorldRow);
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
            int[][] objectMap = loadObjectMap("/maps/object_mountain.txt", gp.maxWorldCol, gp.maxWorldRow);
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
            int[][] objectMap = loadObjectMap("/maps/object_ocean.txt", gp.maxWorldCol, gp.maxWorldRow);
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
        if (gp.currentMap == 5) {
            int[][] objectMap = loadObjectMap("/maps/object_store.txt", gp.maxWorldCol, gp.maxWorldRow);
            int objIndex = 0;

            for (int row = 0; row < gp.maxWorldRow; row++) {
                for (int col = 0; col < gp.maxWorldCol; col++) {
                    int id = objectMap[col][row];
                    if (id == 0) continue;

                    switch (id) {
                        case 1: 
                            OBJ_Cashier cashier = new OBJ_Cashier(gp);
                            cashier.worldX = col;
                            cashier.worldY = row;
                            gp.objects[gp.currentMap][objIndex++] = cashier;
                            break;
                    }
                }
            }
        }
    }

    public void setNPC() {
        if (gp.currentMap == 5) {
            int[][] npcMap = loadNPCMap("/maps/npc_map" + gp.currentMap + ".txt", gp.maxWorldCol, gp.maxWorldRow);
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
            int[][] npcMap = loadNPCMap("/maps/npc_map" + gp.currentMap + ".txt", gp.maxWorldCol, gp.maxWorldRow);
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
            int[][] npcMap = loadNPCMap("/maps/npc_map" + gp.currentMap + ".txt", gp.maxWorldCol, gp.maxWorldRow);
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
            int[][] npcMap = loadNPCMap("/maps/npc_map" + gp.currentMap + ".txt", gp.maxWorldCol, gp.maxWorldRow);
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
            int[][] npcMap = loadNPCMap("/maps/npc_map" + gp.currentMap + ".txt", gp.maxWorldCol, gp.maxWorldRow);
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
            int[][] npcMap = loadNPCMap("/maps/npc_map" + gp.currentMap + ".txt", gp.maxWorldCol, gp.maxWorldRow);
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
        else if (gp.currentMap == 11) {
            int[][] npcMap = loadNPCMap("/maps/npc_map" + gp.currentMap + ".txt", gp.maxWorldCol, gp.maxWorldRow);
            int npcIndex = 0;

            for (int row = 0; row < gp.maxWorldRow; row++) {
                for (int col = 0; col < gp.maxWorldCol; col++) {
                    int id = npcMap[col][row];
                    if (id == 0) continue;

                    switch (id) {
                        case 1: 
                            NPCEasterEgg1 egg1 = (NPCEasterEgg1) NPCData.getNPCByName("NPCEasterEgg1");
                            egg1.worldX = col;
                            egg1.worldY = row;
                            gp.npcs[gp.currentMap][npcIndex++] = egg1;
                            break;
                        case 2:
                            NPCEasterEgg2 egg2 = (NPCEasterEgg2) NPCData.getNPCByName("NPCEasterEgg2");
                            egg2.worldX = col;
                            egg2.worldY = row;
                            gp.npcs[gp.currentMap][npcIndex++] = egg2;
                            break;
                        case 3:
                            NPCEasterEgg3 egg3 = (NPCEasterEgg3) NPCData.getNPCByName("NPCEasterEgg3");
                            egg3.worldX = col;
                            egg3.worldY = row;
                            gp.npcs[gp.currentMap][npcIndex++] = egg3;
                            break;
                        case 4:
                            NPCEasterEgg4 egg4 = (NPCEasterEgg4) NPCData.getNPCByName("NPCEasterEgg4");
                            egg4.worldX = col;
                            egg4.worldY = row;
                            gp.npcs[gp.currentMap][npcIndex++] = egg4;
                            break;
                        case 5:
                            NPCEasterEgg5 egg5 = (NPCEasterEgg5) NPCData.getNPCByName("NPCEasterEgg5");
                            egg5.worldX = col;
                            egg5.worldY = row;
                            gp.npcs[gp.currentMap][npcIndex++] = egg5;
                            break;
                        case 6:
                            NPCEasterEgg6 egg6 = (NPCEasterEgg6) NPCData.getNPCByName("NPCEasterEgg6");
                            egg6.worldX = col;
                            egg6.worldY = row;
                            gp.npcs[gp.currentMap][npcIndex++] = egg6;
                            break;
                        default:
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
