package us._donut_.skuniversal.combatlog;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import me.iiSnipez.CombatLog.Events.PlayerCombatLogEvent;
import me.iiSnipez.CombatLog.Events.PlayerTagEvent;
import me.iiSnipez.CombatLog.Events.PlayerUntagEvent;
import org.bukkit.entity.Player;
import us._donut_.skuniversal.SkUniversalEvent;

public class CombatLogRegister {

    public static void registerCombatLog() {
        //Conditions
        Skript.registerCondition(CondTagged.class, "%player% is [currently] [CombatLog] tagged");

        //Expressions
        Skript.registerExpression(ExprTaggedPlayer.class, Player.class, ExpressionType.SIMPLE, "[the] [CombatLog] tagged [player]");
        Skript.registerExpression(ExprTagger.class, Player.class, ExpressionType.SIMPLE, "[the] [CombatLog] tagger");
        Skript.registerExpression(ExprTaggedPlayers.class, Player.class, ExpressionType.SIMPLE, "[the] [current[ly]] [CombatLog] tagged players");

        //Events
        Skript.registerEvent("CombatLog Tag", SkUniversalEvent.class, PlayerTagEvent.class, "[CombatLog] [player] tag");
        Skript.registerEvent("CombatLog Un-tag", SkUniversalEvent.class, PlayerUntagEvent.class, "[CombatLog] [player] un[-]tag");
        EventValues.registerEventValue(PlayerUntagEvent.class, Player.class, new Getter<Player, PlayerUntagEvent>() {
            public Player get(PlayerUntagEvent e) {
                return e.getPlayer();
            }
        }, 0);
        Skript.registerEvent("Combat Log", SkUniversalEvent.class, PlayerCombatLogEvent.class, "[player] combat[ ]log");
        EventValues.registerEventValue(PlayerCombatLogEvent.class, Player.class, new Getter<Player, PlayerCombatLogEvent>() {
            public Player get(PlayerCombatLogEvent e) {
                return e.getPlayer();
            }
        }, 0);
    }
}
