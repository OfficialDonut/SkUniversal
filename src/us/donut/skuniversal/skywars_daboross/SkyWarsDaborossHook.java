package us.donut.skuniversal.skywars_daboross;

import ch.njol.skript.Skript;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import net.daboross.bukkitdev.skywars.api.SkyWars;
import net.daboross.bukkitdev.skywars.api.events.GameEndEvent;
import net.daboross.bukkitdev.skywars.api.events.GameStartEvent;
import net.daboross.bukkitdev.skywars.api.events.PlayerLeaveQueueEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import us.donut.skuniversal.SkUniversalEvent;

public class SkyWarsDaborossHook {

    public static SkyWars skyWars = (SkyWars) Bukkit.getPluginManager().getPlugin("SkyWars");

    static {
        Skript.registerEvent("SkyWars (Daboross) - Game Start", SkUniversalEvent.class, GameStartEvent.class, "SkyWars game (start|begin)")
                .description("Called when a game starts.\n\n" +
                        "**Event Expressions:**\n" +
                        "`[the] start[ing] players [in [the] [SkyWars] game]`")
                .examples("on skywars game start:", "\tbroadcast \"Arena %event-string% is starting!\"");

        Skript.registerEvent("SkyWars (Daboross) - Game End", SkUniversalEvent.class, GameEndEvent.class, "SkyWars game (end|stop)")
                .description("Called when a game ends.\n\n" +
                        "**Event Expressions:**\n" +
                        "`[the] (last|alive|remaining) players [in [the] [SkyWars] game]`")
                .examples("on skywars game end:", "\tbroadcast \"Arena %event-string% has ended!\"");
        EventValues.registerEventValue(GameEndEvent.class, String.class, new Getter<String, GameEndEvent>() {
            public String get(GameEndEvent e) {
                return e.getGame().getArena().getArenaName();
            }
        }, 0);

        Skript.registerEvent("SkyWars (Daboross) - Leave Queue", SkUniversalEvent.class, PlayerLeaveQueueEvent.class, "SkyWars [game] leave queue")
                .description("Called when a player leaves a game queue.")
                .examples("on skywars leave queue:", "\tbroadcast \"%player% left the queue!\"");
        EventValues.registerEventValue(PlayerLeaveQueueEvent.class, Player.class, new Getter<Player, PlayerLeaveQueueEvent>() {
            public Player get(PlayerLeaveQueueEvent e) {
                return e.getPlayer();
            }
        }, 0);
    }

}
