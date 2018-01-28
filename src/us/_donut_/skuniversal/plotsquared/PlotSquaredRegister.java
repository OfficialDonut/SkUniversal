package us._donut_.skuniversal.plotsquared;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import com.plotsquared.bukkit.events.PlayerClaimPlotEvent;
import com.plotsquared.bukkit.events.PlotDeleteEvent;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Block;
import us._donut_.skuniversal.SkUniversalEvent;

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
        Skript.registerExpression(ExprBorderBlocks.class, Block.class, ExpressionType.SIMPLE, "[the] border blocks at (height|y[-value]) %number% of [the] [PlotSquared] plot [with id] %string%");

        //Events
        Skript.registerEvent("PlotSquared - Plot Delete", SkUniversalEvent.class, PlotDeleteEvent.class, "[PlotSquared] plot (delet(e|ion)|remov(e|al))")
                .description("Called when a plot is deleted.")
                .examples("on plot delete:", "\tbroadcast \"Plot %event-string% was deleted!\"");
        EventValues.registerEventValue(PlotDeleteEvent.class, String.class, new Getter<String, PlotDeleteEvent>() {
            public String get(PlotDeleteEvent e) {
                return e.getPlotId().toString();
            }
        }, 0);
        Skript.registerEvent("PlotSquared - Plot Claim", SkUniversalEvent.class, PlayerClaimPlotEvent.class, "[PlotSquared] plot claim[ing]")
                .description("Called when a plot is claimed.")
                .examples("on plot claim:", "\tbroadcast \"Plot %event-string% was claimed!\"");
        EventValues.registerEventValue(PlayerClaimPlotEvent.class, String.class, new Getter<String, PlayerClaimPlotEvent>() {
            public String get(PlayerClaimPlotEvent e) {
                return e.getPlot().getId().toString();
            }
        }, 0);
    }
}