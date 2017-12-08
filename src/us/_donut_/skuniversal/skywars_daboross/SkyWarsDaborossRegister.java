package us._donut_.skuniversal.skywars_daboross;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import net.daboross.bukkitdev.skywars.api.events.GameEndEvent;
import net.daboross.bukkitdev.skywars.api.events.GameStartEvent;
import net.daboross.bukkitdev.skywars.api.events.PlayerLeaveQueueEvent;
import org.bukkit.entity.Player;
import us._donut_.skuniversal.SkUniversalEvent;

public class SkyWarsDaborossRegister {

    public static void registerSkyWarsDaboross() {
        //Conditions
        Skript.registerCondition(CondInQueue.class, "%player% is in [a] SkyWars [game] queue");
        Skript.registerCondition(CondQueueFull.class, "SkyWars [game] queue is full");

        //Effects
        Skript.registerEffect(EffRemoveFromQueue.class, "remove %player% from [the] SkyWars [game] queue");
        Skript.registerEffect(EffAddToQueue.class, "add %player% to [the] SkyWars [game] queue");

        //Expressions
        Skript.registerExpression(ExprQueuePlayers.class, Player.class, ExpressionType.SIMPLE, "[the] players in [the] SkyWars [game] queue");
        Skript.registerExpression(ExprNextArena.class, String.class, ExpressionType.SIMPLE, "[the] next SkyWars [game] arena");
        Skript.registerExpression(ExprStartPlayers.class, Player.class, ExpressionType.SIMPLE, "[the] start[ing] players [in [the] [SkyWars] game]");
        Skript.registerExpression(ExprAlivePlayers.class, Player.class, ExpressionType.SIMPLE, "[the] (last|alive|remaining) players [in [the] [SkyWars] game]");

        //Events
        Skript.registerEvent("SkyWars Game Start", SkUniversalEvent.class, GameStartEvent.class, "SkyWars game (start|begin)");
        Skript.registerEvent("SkyWars Game End", SkUniversalEvent.class, GameEndEvent.class, "SkyWars game (end|stop)");
        EventValues.registerEventValue(GameEndEvent.class, String.class, new Getter<String, GameEndEvent>() {
            public String get(GameEndEvent e) {
                return e.getGame().getArena().getArenaName();
            }
        }, 0);
        Skript.registerEvent("SkyWars Leave Queue", SkUniversalEvent.class, GameStartEvent.class, "SkyWars [game] leave queue");
        EventValues.registerEventValue(PlayerLeaveQueueEvent.class, Player.class, new Getter<Player, PlayerLeaveQueueEvent>() {
            public Player get(PlayerLeaveQueueEvent e) {
                return e.getPlayer();
            }
        }, 0);
    }
}
