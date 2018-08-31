package us._donut_.skuniversal.combatlog;

import ch.njol.skript.Skript;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import me.iiSnipez.CombatLog.CombatLog;
import me.iiSnipez.CombatLog.Events.PlayerCombatLogEvent;
import me.iiSnipez.CombatLog.Events.PlayerTagEvent;
import me.iiSnipez.CombatLog.Events.PlayerUntagEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import us._donut_.skuniversal.SkUniversalEvent;

public class CombatLogHook {

    public static CombatLog combatLog = ((CombatLog) Bukkit.getPluginManager().getPlugin("CombatLog"));

    static {
        Skript.registerEvent("CombatLog - Tag", SkUniversalEvent.class, PlayerTagEvent.class, "[CombatLog] [player] tag")
                .description("Called when a player enters combat." +
                        "**Event Expressions:**\n" +
                        "`[the] [CombatLog] tagged [player]`\n" +
                        "`[the] [CombatLog] tagger`\n")
                .examples("on tag:", "\tbroadcast \"%player% is now in combat!\"");

        Skript.registerEvent("CombatLog - Un-tag", SkUniversalEvent.class, PlayerUntagEvent.class, "[CombatLog] [player] un[-]tag")
                .description("Called when a player leaves combat.")
                .examples("on tag:", "\tbroadcast \"%player% is no longer in combat!\"");
        EventValues.registerEventValue(PlayerUntagEvent.class, Player.class, new Getter<Player, PlayerUntagEvent>() {
            public Player get(PlayerUntagEvent e) {
                return e.getPlayer();
            }
        }, 0);

        Skript.registerEvent("CombatLog - Combat Log", SkUniversalEvent.class, PlayerCombatLogEvent.class, "[player] combat[ ]log")
                .description("Called when a player combat logs.")
                .examples("on tag:", "\tbroadcast \"%player% has combat logged!\"");
        EventValues.registerEventValue(PlayerCombatLogEvent.class, Player.class, new Getter<Player, PlayerCombatLogEvent>() {
            public Player get(PlayerCombatLogEvent e) {
                return e.getPlayer();
            }
        }, 0);
    }

}
