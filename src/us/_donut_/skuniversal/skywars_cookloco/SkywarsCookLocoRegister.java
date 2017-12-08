package us._donut_.skuniversal.skywars_cookloco;

import ak.CookLoco.SkyWars.events.SkyPlayerArenaJoinEvent;
import ak.CookLoco.SkyWars.events.SkyPlayerArenaLeaveEvent;
import ak.CookLoco.SkyWars.events.SkyPlayerDeathEvent;
import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import org.bukkit.entity.Player;
import us._donut_.skuniversal.SkUniversalEvent;

public class SkywarsCookLocoRegister {

    public static void registerSkyWarsCookLoco() {
        //Conditions
        Skript.registerCondition(CondInArena.class, "%player% is [currently] in [a] SkyWars arena");
        Skript.registerCondition(CondSpectating.class, "%player% is [currently] spectating [a] SkyWars [game]");
        Skript.registerCondition(CondHasKit.class, "%player% [currently] has [a] SkyWars kit");

        //Expressions
        Skript.registerExpression(ExprWins.class, Number.class, ExpressionType.COMBINED, "[the] [(amount|number) of] SkyWars wins of %player%", "%player%'s [(amount|number) of] SkyWars wins");
        Skript.registerExpression(ExprCoins.class, Number.class, ExpressionType.COMBINED, "[the] [(amount|number) of] SkyWars coins of %player%", "%player%'s [(amount|number) of] SkyWars coins");
        Skript.registerExpression(ExprKills.class, Number.class, ExpressionType.COMBINED, "[the] [(amount|number) of] SkyWars kills of %player%", "%player%'s [(amount|number) of] SkyWars kills");
        Skript.registerExpression(ExprDeaths.class, Number.class, ExpressionType.COMBINED, "[the] [(amount|number) of] SkyWars deaths of %player%", "%player%'s [(amount|number) of] SkyWars deaths");
        Skript.registerExpression(ExprArena.class, String.class, ExpressionType.COMBINED, "[the] [current] SkyWars arena of %player%", "%player%'s [current] SkyWars arena");
        Skript.registerExpression(ExprKit.class, String.class, ExpressionType.COMBINED, "[the] [current] SkyWars kit of %player%", "%player%'s [current] SkyWars kit");
        Skript.registerExpression(ExprAttacker.class, Player.class, ExpressionType.SIMPLE, "[the] SkyWars (attacker|killer)");
        Skript.registerExpression(ExprVictim.class, Player.class, ExpressionType.SIMPLE, "[the] SkyWars victim");
        Skript.registerExpression(ExprState.class, String.class, ExpressionType.COMBINED, "[the] [current] [game] state of [the] SkyWars arena [(named|with name)] %string%", "[the] SkyWars arena [(named|with name)] %string%'s [current] [game] state");
        Skript.registerExpression(ExprArenas.class, String.class, ExpressionType.SIMPLE, "[all of] [the] SkyWars arenas");
        Skript.registerExpression(ExprPlayers.class, Player.class, ExpressionType.SIMPLE, "[all of] [the] [alive] players in SkyWars arenas [(named|with name)] %string%");

        //Events
        Skript.registerEvent("SkyWars Player Join", SkUniversalEvent.class, SkyPlayerArenaJoinEvent.class, "SkyWars [arena] join");
        EventValues.registerEventValue(SkyPlayerArenaJoinEvent.class, Player.class, new Getter<Player, SkyPlayerArenaJoinEvent>() {
            public Player get(SkyPlayerArenaJoinEvent e) {
                return e.getPlayer().getPlayer();
            }
        }, 0);
        EventValues.registerEventValue(SkyPlayerArenaJoinEvent.class, String.class, new Getter<String, SkyPlayerArenaJoinEvent>() {
            public String get(SkyPlayerArenaJoinEvent e) {
                return e.getGame().getName();
            }
        }, 0);
        Skript.registerEvent("SkyWars Player Leave", SkUniversalEvent.class,SkyPlayerArenaLeaveEvent.class, "SkyWars [arena] leave");
        EventValues.registerEventValue(SkyPlayerArenaLeaveEvent.class, Player.class, new Getter<Player, SkyPlayerArenaLeaveEvent>() {
            public Player get(SkyPlayerArenaLeaveEvent e) {
                return e.getPlayer().getPlayer();
            }
        }, 0);
        EventValues.registerEventValue(SkyPlayerArenaLeaveEvent.class, String.class, new Getter<String, SkyPlayerArenaLeaveEvent>() {
            public String get(SkyPlayerArenaLeaveEvent e) {
                return e.getGame().getName();
            }
        }, 0);
        Skript.registerEvent("SkyWars Player Death", SkUniversalEvent.class,SkyPlayerDeathEvent.class, "SkyWars [player] death");
        EventValues.registerEventValue(SkyPlayerDeathEvent.class, String.class, new Getter<String, SkyPlayerDeathEvent>() {
            public String get(SkyPlayerDeathEvent e) {
                return e.getGame().getName();
            }
        }, 0);
    }
}