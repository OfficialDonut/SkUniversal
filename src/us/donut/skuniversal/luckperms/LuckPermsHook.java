package us.donut.skuniversal.luckperms;

import ch.njol.skript.Skript;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import us.donut.skuniversal.SkUniversalEvent;

public class LuckPermsHook {

    public static LuckPerms luckpermsAPI = LuckPermsProvider.get();

    static {
        new LuckPermsListener();

        Skript.registerEvent("LuckPerms - Promote Event", SkUniversalEvent.class, BukkitUserPromoteEvent.class, "[LuckPerm[s]] [player] promot(e|ion)")
                .description("Called when a player is promoted.")
                .examples("on luckperms promote:", "\tbroadcast \"%event-offlineplayer% was promoted from %event-string% to %luckperms group of player%!\"");
        EventValues.registerEventValue(BukkitUserPromoteEvent.class, OfflinePlayer.class, new Getter<OfflinePlayer, BukkitUserPromoteEvent>() {
            public OfflinePlayer get(BukkitUserPromoteEvent e) {
                return Bukkit.getOfflinePlayer(e.getPlayer().getUniqueId());
            }
        }, 0);
        EventValues.registerEventValue(BukkitUserPromoteEvent.class, String.class, new Getter<String, BukkitUserPromoteEvent>() {
            public String get(BukkitUserPromoteEvent e) {
                return e.getOldGroup();
            }
        }, 0);

        Skript.registerEvent("LuckPerms - Demote Event", SkUniversalEvent.class, BukkitUserDemoteEvent.class, "[LuckPerm[s]] [player] demot(e|ion)")
                .description("Called when a player is demoted.")
                .examples("on luckperms demote:", "\tbroadcast \"%event-offlineplayer% was demoted from %event-string% to %luckperms group of player%!\"");
        EventValues.registerEventValue(BukkitUserDemoteEvent.class, OfflinePlayer.class, new Getter<OfflinePlayer, BukkitUserDemoteEvent>() {
            public OfflinePlayer get(BukkitUserDemoteEvent e) {
                return Bukkit.getOfflinePlayer(e.getPlayer().getUniqueId());
            }
        }, 0);
        EventValues.registerEventValue(BukkitUserDemoteEvent.class, String.class, new Getter<String, BukkitUserDemoteEvent>() {
            public String get(BukkitUserDemoteEvent e) {
                return e.getOldGroup();
            }
        }, 0);

        Skript.registerEvent("LuckPerms - Group Change", SkUniversalEvent.class, BukkitGroupChangeEvent.class, "[LuckPerm[s]] [player] group change")
                .description("Called when a player's group changes.")
                .examples("on luckperms group change:", "\tbroadcast \"group of %event-offlineplayer% changed from %old group% to %new group%!\"");
        EventValues.registerEventValue(BukkitGroupChangeEvent.class, OfflinePlayer.class, new Getter<OfflinePlayer, BukkitGroupChangeEvent>() {
            public OfflinePlayer get(BukkitGroupChangeEvent e) {
                return Bukkit.getOfflinePlayer(e.getPlayer().getUniqueId());
            }
        }, 0);
        EventValues.registerEventValue(BukkitGroupChangeEvent.class, String.class, new Getter<String, BukkitGroupChangeEvent>() {
            public String get(BukkitGroupChangeEvent e) {
                return e.getOldGroup();
            }
        }, 0);
    }

}