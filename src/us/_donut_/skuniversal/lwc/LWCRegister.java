package us._donut_.skuniversal.lwc;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import us._donut_.skuniversal.SkUniversal;

public class LWCRegister {
    public static void registerLWC() {
        if (Bukkit.getServer().getPluginManager().getPlugin("LWC") != null) {
            SkUniversal.hookedPlugins.add("LWC");

            //Conditions
            Skript.registerCondition(CondProtected.class, "%block% is (locked|protected) [by LWC]");
            Skript.registerCondition(CondAccess.class, "%player% (has access to|can access) [LWC] [(locked|protected)]] %block%");
            Skript.registerCondition(CondProtectable.class, "%block% is [LWC] (lockable|protectable)");

            //Effects
            Skript.registerEffect(EffRemoveProtection.class, "(remove|delete) [LWC] (protection|lock) from %block%");

            //Expressions
            Skript.registerExpression(ExprOwner.class, OfflinePlayer.class, ExpressionType.COMBINED, "[the] [LWC] owner of %block%", "%block%'s [LWC] owner");
            Skript.registerExpression(ExprProtectionNumber.class, Number.class, ExpressionType.COMBINED, "[the] [(number|amount) of] [LWC] protections of %player%", "%player%'s [(number|amount) of] [LWC] protections");
        }
    }
}