package us._donut_.skuniversal.luckperms;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import us._donut_.skuniversal.SkUniversalEvent;

public class LuckPermsRegister {

    public LuckPermsRegister() {
        new LuckPermsListener();

        //Effects
        Skript.registerEffect(EffAddPrefix.class, "add %string% with priority [of] %number% to [the] [LuckPerm[s]] prefixes of %player%");
        Skript.registerEffect(EffRemovePrefix.class, "remove %string% with priority [of] %number% from [the] [LuckPerm[s]] prefixes of %player%");
        Skript.registerEffect(EffAddSuffix.class, "add %string% with priority [of] %number% to [the] [LuckPerm[s]] suffixes of %player%");
        Skript.registerEffect(EffRemoveSuffix.class, "remove %string% with priority [of] %number% from [the] [LuckPerm[s]] suffixes of %player%");

        //Expressions
        Skript.registerExpression(ExprPlayerPermissions.class, String.class, ExpressionType.COMBINED, "[the] [LuckPerm[s]] perm[ission][s] of [player] %player%", "[player] %player%'s [LuckPerm[s]] perm[ission][s]");
        Skript.registerExpression(ExprGroupOfPlayer.class, String.class, ExpressionType.COMBINED, "[the] [LuckPerm[s]] [primary] group of %player%", "%player%'s [primary] [LuckPerm[s]] group");
        Skript.registerExpression(ExprAllGroups.class, String.class, ExpressionType.SIMPLE, "[the] [names of] [all [of]] [the] [LuckPerm[s]] groups");
        Skript.registerExpression(ExprGroupPermissions.class, String.class, ExpressionType.COMBINED, "[the] perm[ission][s] of [the] [LuckPerm[s]] group [(named|with name)] %string%", "[the] [LuckPerm[s]] group [(named|with name)] %string%'s perm[ission][s]");
        Skript.registerExpression(ExprGroupWeight.class, Number.class, ExpressionType.COMBINED, "[the] (priority|weight) of [the] [LuckPerm[s]] group [(named|with name)] %string%", "[the] [LuckPerm[s]] group [(named|with name)] %string%'s (priority|weight)");
        Skript.registerExpression(ExprPlayerPrefix.class, String.class, ExpressionType.COMBINED, "[the] [active] [LuckPerm[s]] prefix of %player%", "%player%'s [active] [LuckPerm[s]] prefix");
        Skript.registerExpression(ExprPlayerSuffix.class, String.class, ExpressionType.COMBINED, "[the] [active] [LuckPerm[s]] suffix of %player%", "%player%'s [active] [LuckPerm[s]] suffix");

        //Events
        Skript.registerEvent("LuckPerms - Promote Event", SkUniversalEvent.class, BukkitUserPromoteEvent.class, "[LuckPerm[s]] [player] promot(e|ion)")
                .description("Called when a player is promoted.")
                .examples("on luckperms promote:", "\tbroadcast \"%event-player% was promoted from %event-string% to %luckperms group of player%!\"");
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
                .examples("on luckperms demote:", "\tbroadcast \"%event-player% was demoted from %event-string% to %luckperms group of player%!\"");
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
    }
}