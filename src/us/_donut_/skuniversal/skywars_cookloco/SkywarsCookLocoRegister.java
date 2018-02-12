package us._donut_.skuniversal.skywars_cookloco;

import ak.CookLoco.SkyWars.events.ArenaJoinEvent;
import ak.CookLoco.SkyWars.events.ArenaLeaveEvent;
import ak.CookLoco.SkyWars.events.SkyPlayerDeathEvent;
import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import org.bukkit.entity.Player;
import us._donut_.skuniversal.SkUniversalEvent;

public class SkywarsCookLocoRegister {

    public SkywarsCookLocoRegister() {
        //Conditions
        Skript.registerCondition(CondInArena.class, "%player% is [currently] in [a] SkyWars arena");
        Skript.registerCondition(CondSpectating.class, "%player% is [currently] spectating [a] SkyWars [game]");
        Skript.registerCondition(CondHasKit.class, "%player% [currently] has [a] SkyWars kit");

        //Expressions
        Skript.registerExpression(ExprWins.class, Number.class, ExpressionType.COMBINED, "[the] [(amount|number) of] SkyWars wins of %player%", "%player%'s [(amount|number) of] SkyWars wins");
        Skript.registerExpression(ExprCoins.class, Number.class, ExpressionType.COMBINED, "[the] [(amount|number) of] SkyWars coins of %player%", "%player%'s [(amount|number) of] SkyWars coins");
        Skript.registerExpression(ExprSkyWarsKills.class, Number.class, ExpressionType.COMBINED, "[the] [(amount|number) of] SkyWars kills of %player%", "%player%'s [(amount|number) of] SkyWars kills");
        Skript.registerExpression(ExprDeaths.class, Number.class, ExpressionType.COMBINED, "[the] [(amount|number) of] SkyWars deaths of %player%", "%player%'s [(amount|number) of] SkyWars deaths");
        Skript.registerExpression(ExprArena.class, String.class, ExpressionType.COMBINED, "[the] [current] SkyWars arena of %player%", "%player%'s [current] SkyWars arena");
        Skript.registerExpression(ExprKit.class, String.class, ExpressionType.COMBINED, "[the] [current] SkyWars kit of %player%", "%player%'s [current] SkyWars kit");
        Skript.registerExpression(ExprAttacker.class, Player.class, ExpressionType.SIMPLE, "[the] SkyWars (attacker|killer)");
        Skript.registerExpression(ExprVictim.class, Player.class, ExpressionType.SIMPLE, "[the] SkyWars victim");
        Skript.registerExpression(ExprState.class, String.class, ExpressionType.COMBINED, "[the] [current] [game] state of [the] SkyWars arena [(named|with name)] %string%", "[the] SkyWars arena [(named|with name)] %string%'s [current] [game] state");
        Skript.registerExpression(ExprArenas.class, String.class, ExpressionType.SIMPLE, "[all of] [the] SkyWars arenas");
        Skript.registerExpression(ExprPlayers.class, Player.class, ExpressionType.SIMPLE, "[all of] [the] [alive] players in [the] SkyWars arena [(named|with name)] %string%");

        //Events
        Skript.registerEvent("SkyWars (CookLoco) - Player Join", SkUniversalEvent.class, ArenaJoinEvent.class, "SkyWars [arena] join")
                .description("Called when a player joins an arena.")
                .examples("on skywars arena join:", "\tbroadcast \"%player% joined arena %event-string%!\"");
        EventValues.registerEventValue(ArenaJoinEvent.class, Player.class, new Getter<Player, ArenaJoinEvent>() {
            public Player get(ArenaJoinEvent e) {
                return e.getPlayer().getPlayer();
            }
        }, 0);
        EventValues.registerEventValue(ArenaJoinEvent.class, String.class, new Getter<String, ArenaJoinEvent>() {
            public String get(ArenaJoinEvent e) {
                return e.getGame().getName();
            }
        }, 0);
        Skript.registerEvent("SkyWars (CookLoco) -Player Leave", SkUniversalEvent.class, ArenaLeaveEvent.class, "SkyWars [arena] leave")
                .description("Called when a player leaves an arena.")
                .examples("on skywars arena leave:", "\tbroadcast \"%player% left arena %event-string%!\"");
        EventValues.registerEventValue(ArenaLeaveEvent.class, Player.class, new Getter<Player, ArenaLeaveEvent>() {
            public Player get(ArenaLeaveEvent e) {
                return e.getPlayer().getPlayer();
            }
        }, 0);
        EventValues.registerEventValue(ArenaLeaveEvent.class, String.class, new Getter<String, ArenaLeaveEvent>() {
            public String get(ArenaLeaveEvent e) {
                return e.getGame().getName();
            }
        }, 0);
        Skript.registerEvent("SkyWars (CookLoco) - Player Death", SkUniversalEvent.class,SkyPlayerDeathEvent.class, "SkyWars [player] death")
                .description("Called when a player dies while playing skywars.\n\n" +
                        "**Event Expressions:**\n" +
                        "`[the] skywars (attacker|killer)`\n" +
                        "`[the] skywars victim`")
                .examples("on skywars death:", "\tbroadcast \"%the skywars killer% killed %the skywars victim%!\"");
        EventValues.registerEventValue(SkyPlayerDeathEvent.class, String.class, new Getter<String, SkyPlayerDeathEvent>() {
            public String get(SkyPlayerDeathEvent e) {
                return e.getGame().getName();
            }
        }, 0);
    }
}