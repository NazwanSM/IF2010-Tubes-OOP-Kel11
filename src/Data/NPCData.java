package data; 

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;
import entity.NPC.*;
import main.GamePanel;

public class NPCData {

    private static final List<NPC> allNPC = new ArrayList<>();
    private static final Map<String, NPC> npcByName = new HashMap<>();
    private static boolean initialized = false;

    public static void initialize(GamePanel gp) {
        if (initialized) return;
        
        addNPC(new MayorTadi(gp));
        addNPC(new Caroline(gp));
        addNPC(new Perry(gp));
        addNPC(new Dasco(gp));
        addNPC(new Emily(gp));
        addNPC(new Abigail(gp));
        
        initialized = true;
    }

    private static void addNPC(NPC npc) {
        allNPC.add(npc);
        npcByName.put(npc.getNPCName().toLowerCase(), npc);
    }

    public static List<NPC> getAllNPC() {
        return Collections.unmodifiableList(allNPC);
    }

    public static NPC getNPCByName(String name) {
        if (name == null) {
            return null;
        }
        return npcByName.get(name.toLowerCase());
    }
}