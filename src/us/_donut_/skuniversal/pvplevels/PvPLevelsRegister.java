package us._donut_.skuniversal.pvplevels;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import org.bukkit.Bukkit;
import us._donut_.skuniversal.SkUniversal;

public class PvPLevelsRegister {
    public static void registerPvPLevels() {
        if (Bukkit.getServer().getPluginManager().getPlugin("PvPLevels") != null) {
            SkUniversal.hookedPlugins.add("PvPLevels");

            //Expressions
            Skript.registerExpression(ExprLevel.class, Number.class, ExpressionType.COMBINED, "[the] pvp level of %offlineplayer%", "%offlineplayer%'s pvp level");
            Skript.registerExpression(ExprXp.class, Number.class, ExpressionType.COMBINED, "[the] pvp (xp|experience) of %offlineplayer%", "%offlineplayer%'s pvp (xp|experience)");
            Skript.registerExpression(ExprKills.class, Number.class, ExpressionType.COMBINED, "[the] [(number|amount) of] pvp kills of %offlineplayer%", "%offlineplayer%'s [(number|amount) of] pvp kills");
            Skript.registerExpression(ExprDeaths.class, Number.class, ExpressionType.COMBINED, "[the] [(number|amount) of] pvp deaths of %offlineplayer%", "%offlineplayer%'s [(number|amount) of] pvp deaths");
            Skript.registerExpression(ExprKDR.class, Number.class, ExpressionType.COMBINED, "[the] pvp (kdr|kill death ratio) of %offlineplayer%", "%offlineplayer%'s pvp (kdr|kill death ratio)");
            Skript.registerExpression(ExprXpRequired.class, Number.class, ExpressionType.COMBINED, "[the] [amount of] pvp (xp|experience) (required|needed) for %player% [to level up]", "%player%'s [amount of] pvp (xp|experience) (required|needed) [to level up]");
            Skript.registerExpression(ExprProgress.class, String.class, ExpressionType.COMBINED, "[the] pvp [(xp|experience)] progress of %offlineplayer%", "%offlineplayer%'s pvp [(xp|experience)] progress");
        }
    }
}