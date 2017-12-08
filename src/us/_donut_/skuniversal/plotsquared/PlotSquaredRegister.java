package us._donut_.skuniversal.plotsquared;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;

public class PlotSquaredRegister {

    public static void registerPlotSquared() {
        //Conditions
        Skript.registerCondition(CondInPlot.class, "%player% is in [a] [PlotSquared] plot");

        //Expressions
        Skript.registerExpression(ExprAllPlotIDs.class, String.class, ExpressionType.SIMPLE, "[the] IDs of [all] [the] [PlotSquared] plots");
        Skript.registerExpression(ExprPlotIDsOfPlayer.class, String.class, ExpressionType.COMBINED, "[the] IDs of [all] [the] [PlotSquared] plots of %offlineplayer%", "[the] IDs of [all] %offlineplayer%'s [PlotSquared] plots");
        Skript.registerExpression(ExprPlotID.class, String.class, ExpressionType.SIMPLE, "[the] ID of [the] [PlotSquared] plot at %location%");
        Skript.registerExpression(ExprHomeLocation.class, Location.class, ExpressionType.SIMPLE, "[the] home loc[ation] of [the] [PlotSquared] plot [with id] %string%");
        Skript.registerExpression(ExprPlotOwner.class, OfflinePlayer.class, ExpressionType.SIMPLE, "[the] owner[s] of [the] [PlotSquared] plot [with id] %string%");
        Skript.registerExpression(ExprPlotMembers.class, OfflinePlayer.class, ExpressionType.SIMPLE, "[the] members of [the] [PlotSquared] plot [with id] %string%");
        Skript.registerExpression(ExprPlotBiome.class, String.class, ExpressionType.SIMPLE, "[the] biome of [the] [PlotSquared] plot [with id] %string%");
        Skript.registerExpression(ExprPlotRating.class, Number.class, ExpressionType.SIMPLE, "[the] [average] rating of [the] [PlotSquared] plot [with id] %string%");
        Skript.registerExpression(ExprTrusted.class, OfflinePlayer.class, ExpressionType.SIMPLE, "[the] trusted players of [the] [PlotSquared] plot [with id] %string%");
        Skript.registerExpression(ExprDenied.class, OfflinePlayer.class, ExpressionType.SIMPLE, "[the] denied players of [the] [PlotSquared] plot [with id] %string%");
    }
}