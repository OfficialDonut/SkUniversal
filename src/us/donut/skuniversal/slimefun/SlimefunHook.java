package us.donut.skuniversal.slimefun;

import ch.njol.skript.Skript;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import io.github.thebusybiscuit.slimefun4.api.events.ResearchUnlockEvent;
import me.mrCookieSlime.Slimefun.Objects.Category;
import org.bukkit.entity.Player;
import us.donut.skuniversal.SkUniversalEvent;

import java.util.HashMap;
import java.util.Map;

public class SlimefunHook {

    public static Map<String, Category> customCategories = new HashMap<>();

    static {
        Skript.registerEvent("Slimefun - Research Unlock", SkUniversalEvent.class, ResearchUnlockEvent.class,
                "[Slimefun] research unlock[ing]",
                "[Slimefun] unlock[ing] research")
                .description("Called when a player unlocks a Slimefun research.")
                .examples("on research unlock:", "\tsend \"You unlocked research!\"");
        EventValues.registerEventValue(ResearchUnlockEvent.class, Player.class, new Getter<Player, ResearchUnlockEvent>() {
            public Player get(ResearchUnlockEvent e) {
                return e.getPlayer();
            }
        }, 0);
        EventValues.registerEventValue(ResearchUnlockEvent.class, Number.class, new Getter<Number, ResearchUnlockEvent>() {
            public Number get(ResearchUnlockEvent e) {
                return e.getResearch().getID();
            }
        }, 0);
    }

}
