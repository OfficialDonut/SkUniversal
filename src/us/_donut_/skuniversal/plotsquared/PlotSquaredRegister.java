package us._donut_.skuniversal.plotsquared;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import us._donut_.skuniversal.SkUniversal;

public class PlotSquaredRegister {

    public static void registerPlotSquared() {
        if (Bukkit.getServer().getPluginManager().getPlugin("PlotSquared") != null) {
            SkUniversal.hookedPlugins.add("PlotSquared");

            //Conditions
            Skript.registerCondition(CondInPlot.class, "%player% is in [a] [PlotSquared] plot");

            //Expressions
            Skript.registerExpression(ExprPlayerHomeLoc.class, Location.class, ExpressionType.COMBINED, "[the] home loc[ation] of [the] [PlotSquared] plot of %player%", "[the] home loc[ation] of %player%'s [PlotSquared] plot");
            Skript.registerExpression(ExprHomeLoc.class, Location.class, ExpressionType.SIMPLE, "[the] home loc[ation] of [the] [PlotSquared] plot at %location%");
            Skript.registerExpression(ExprPlotOwner.class, OfflinePlayer.class, ExpressionType.SIMPLE, "[the] owner of [the] [PlotSquared] plot at %location%");
            Skript.registerExpression(ExprPlotMembers.class, OfflinePlayer.class, ExpressionType.SIMPLE, "[the] members of [the] [PlotSquared] plot at %location%");
            Skript.registerExpression(ExprPlotBiome.class, String.class, ExpressionType.SIMPLE, "[the] biome of [the] [PlotSquared] plot at %location%");
            Skript.registerExpression(ExprPlotRating.class, Number.class, ExpressionType.SIMPLE, "[the] [average] rating of [the] [PlotSquared] plot at %location%");
            Skript.registerExpression(ExprTrusted.class, OfflinePlayer.class, ExpressionType.SIMPLE, "[the] trusted players of [the] [PlotSquared] plot at %location%");
            Skript.registerExpression(ExprDenied.class, OfflinePlayer.class, ExpressionType.SIMPLE, "[the] denied players of [the] [PlotSquared] plot at %location%");
        }
    }
}