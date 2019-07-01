package us.donut.skuniversal.autorank;

import ch.njol.skript.Skript;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import me.armar.plugins.autorank.Autorank;
import me.armar.plugins.autorank.api.API;
import me.armar.plugins.autorank.api.events.RequirementCompleteEvent;
import org.bukkit.entity.Player;
import us.donut.skuniversal.SkUniversalEvent;

public class AutorankHook {

    public static API autorankAPI = Autorank.getInstance().getAPI();

    static {
        Skript.registerEvent("Autorank - Requirement Completion", SkUniversalEvent.class, RequirementCompleteEvent.class, "[Autorank] requirement complet(e|ion)")
                .description("Called when a player completes a requirement.")
                .examples("on requirement complete:", "broadcast \"%player% has completed %event-string% requirement!\"");
        EventValues.registerEventValue(RequirementCompleteEvent.class, Player.class, new Getter<Player, RequirementCompleteEvent>() {
            public Player get(RequirementCompleteEvent e) {
                return e.getPlayer();
            }
        }, 0);
        EventValues.registerEventValue(RequirementCompleteEvent.class, String.class, new Getter<String, RequirementCompleteEvent>() {
            public String get(RequirementCompleteEvent e) {
                return e.getRequirement().getDescription();
            }
        }, 0);
    }

}
