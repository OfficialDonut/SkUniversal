package us.donut.skuniversal.slimefun;

import ch.njol.skript.Skript;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.api.events.ResearchUnlockEvent;
import io.github.thebusybiscuit.slimefun4.core.researching.Research;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunPlugin;
import me.mrCookieSlime.Slimefun.Objects.Category;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import us.donut.skuniversal.SkUniversalEvent;

public class SlimefunHook implements SlimefunAddon {

    public final static SlimefunHook ADDON = new SlimefunHook();

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
        EventValues.registerEventValue(ResearchUnlockEvent.class, String.class, new Getter<String, ResearchUnlockEvent>() {
            public String get(ResearchUnlockEvent e) {
                return e.getResearch().getKey().getKey();
            }
        }, 0);
    }

    public static Category getCategory(String id) {
        for (Category category : SlimefunPlugin.getRegistry().getCategories()) {
            if (category.getKey().getKey().equalsIgnoreCase(id)) {
                return category;
            }
        }
        return null;
    }

    public static Research getResearch(String id) {
        for (Research research : SlimefunPlugin.getRegistry().getResearches()) {
            if (research.getKey().getKey().equalsIgnoreCase(id)) {
                return research;
            }
        }
        return null;
    }

    @NotNull
    @Override
    public JavaPlugin getJavaPlugin() {
        return Skript.getInstance();
    }

    @Nullable
    @Override
    public String getBugTrackerURL() {
        return null;
    }
}
