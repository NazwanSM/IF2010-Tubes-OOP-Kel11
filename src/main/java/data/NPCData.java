package data; 

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;
import main.GamePanel;
import entity.npc.Abigail;
import entity.npc.Caroline;
import entity.npc.Dasco;
import entity.npc.Emily;
import entity.npc.MayorTadi;
import entity.npc.NPC;
import entity.npc.NPCEasterEgg1;
import entity.npc.NPCEasterEgg2;
import entity.npc.NPCEasterEgg3;
import entity.npc.NPCEasterEgg4;
import entity.npc.NPCEasterEgg5;
import entity.npc.NPCEasterEgg6;
import entity.npc.Perry;

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
        addNPC(new NPCEasterEgg1(gp));
        addNPC(new NPCEasterEgg2(gp));
        addNPC(new NPCEasterEgg3(gp));
        addNPC(new NPCEasterEgg4(gp));
        addNPC(new NPCEasterEgg5(gp));
        addNPC(new NPCEasterEgg6(gp));
        
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