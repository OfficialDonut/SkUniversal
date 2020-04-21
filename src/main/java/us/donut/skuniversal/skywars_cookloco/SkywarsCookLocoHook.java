package us.donut.skuniversal.skywars_cookloco;

import ak.CookLoco.SkyWars.events.ArenaJoinEvent;
import ak.CookLoco.SkyWars.events.ArenaLeaveEvent;
import ak.CookLoco.SkyWars.events.SkyPlayerDeathEvent;
import ch.njol.skript.Skript;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import org.bukkit.entity.Player;
import us.donut.skuniversal.SkUniversalEvent;

public class SkywarsCookLocoHook {

    static {
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
        Skript.registerEvent("SkyWars (CookLoco) - Player Leave", SkUniversalEvent.class,ArenaLeaveEvent.class, "SkyWars [arena] leave")
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
        Skript.registerEvent("SkyWars (CookLoco) - Player Death", SkUniversalEvent.class, SkyPlayerDeathEvent.class, "SkyWars [player] death")
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