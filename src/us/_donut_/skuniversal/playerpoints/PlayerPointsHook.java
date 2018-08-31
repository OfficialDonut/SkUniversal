package us._donut_.skuniversal.playerpoints;

import ch.njol.skript.Skript;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import org.black_ixx.playerpoints.PlayerPoints;
import org.black_ixx.playerpoints.PlayerPointsAPI;
import org.black_ixx.playerpoints.event.PlayerPointsChangeEvent;
import org.black_ixx.playerpoints.event.PlayerPointsResetEvent;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import us._donut_.skuniversal.SkUniversalEvent;

public class PlayerPointsHook {

    public static PlayerPointsAPI playerPointsAPI = ((PlayerPoints)Bukkit.getServer().getPluginManager().getPlugin("PlayerPoints")).getAPI();

    static {
        Skript.registerEvent("PlayerPoints - Points Change", SkUniversalEvent.class, PlayerPointsChangeEvent.class, "[Player][ ]Point[s] [bal[ance]] chang(e|ing)")
                .description("Called when the points of a player changes.")
                .examples("on player points change:", "\tbroadcast \"%event-player%'s points have changed by %event-number%!\"");
        EventValues.registerEventValue(PlayerPointsChangeEvent.class, OfflinePlayer.class, new Getter<OfflinePlayer, PlayerPointsChangeEvent>() {
            public OfflinePlayer get(PlayerPointsChangeEvent e) {
                return Bukkit.getOfflinePlayer(e.getPlayerId());
            }
        }, 0);
        EventValues.registerEventValue(PlayerPointsChangeEvent.class, Number.class, new Getter<Number, PlayerPointsChangeEvent>() {
            public Number get(PlayerPointsChangeEvent e) {
                return e.getChange();
            }
        }, 0);

        Skript.registerEvent("PlayerPoints - Points Reset", SkUniversalEvent.class, PlayerPointsResetEvent.class, "[Player][ ]Point[s] [bal[ance]] reset[ting]")
                .description("Called when the points of a player resets.")
                .examples("on player points reset:", "\tbroadcast \"%event-player%'s points have been reset!\"");
        EventValues.registerEventValue(PlayerPointsResetEvent.class, OfflinePlayer.class, new Getter<OfflinePlayer, PlayerPointsResetEvent>() {
            public OfflinePlayer get(PlayerPointsResetEvent e) {
                return Bukkit.getOfflinePlayer(e.getPlayerId());
            }
        }, 0);
        EventValues.registerEventValue(PlayerPointsResetEvent.class, Number.class, new Getter<Number, PlayerPointsResetEvent>() {
            public Number get(PlayerPointsResetEvent e) {
                return e.getChange();
            }
        }, 0);
    }

}