package us.donut.skuniversal.plotsquared;

import ch.njol.skript.Skript;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import com.plotsquared.core.api.PlotAPI;
import com.plotsquared.core.plot.Plot;
import com.plotsquared.core.plot.PlotId;
import org.bukkit.entity.Player;
import us.donut.skuniversal.SkUniversalEvent;

import javax.annotation.Nullable;

public class PlotSquaredHook {

    public static PlotAPI plotAPI = new PlotAPI();

    public static Plot getPlot(@Nullable String id) {
        if (id == null) return null;
        PlotId plotId = PlotId.fromString(id);
        for (Plot plot : plotAPI.getAllPlots()) {
            if (plot.getId().equals(plotId)) return plot;
        }
        return null;
    }

    static {
        plotAPI.registerListener(new PlotSquaredListener());

        Skript.registerEvent("PlotSquared - Plot Delete", SkUniversalEvent.class, BukkitPlotDeleteEvent.class, "[PlotSquared] plot (delet(e|ion)|remov(e|al))")
                .description("Called when a plot is deleted.")
                .examples("on plot delete:", "\tbroadcast \"Plot %event-string% was deleted!\"");
        EventValues.registerEventValue(BukkitPlotDeleteEvent.class, String.class, new Getter<String, BukkitPlotDeleteEvent>() {
            public String get(BukkitPlotDeleteEvent e) {
                return e.getPlotId().toString();
            }
        }, 0);

        Skript.registerEvent("PlotSquared - Plot Claim", SkUniversalEvent.class, BukkitClaimPlotEvent.class, "[PlotSquared] plot claim[ing]")
                .description("Called when a plot is claimed.")
                .examples("on plot claim:", "\tbroadcast \"Plot %event-string% was claimed!\"");
        EventValues.registerEventValue(BukkitClaimPlotEvent.class, String.class, new Getter<String, BukkitClaimPlotEvent>() {
            public String get(BukkitClaimPlotEvent e) {
                return e.getPlot().getId().toString();
            }
        }, 0);

        Skript.registerEvent("PlotSquared - Enter Plot", SkUniversalEvent.class, BukkitEnterPlotEvent.class,
                "[PlotSquared] plot enter[ing]",
                "[PlotSquared] enter[ing] plot")
                .description("Called when a player enters a plot.")
                .examples("on plot enter:", "\tbroadcast \"%player% has entered plot %event-string%!\"");
        EventValues.registerEventValue(BukkitEnterPlotEvent.class, String.class, new Getter<String, BukkitEnterPlotEvent>() {
            public String get(BukkitEnterPlotEvent e) {
                return e.getPlot().getId().toString();
            }
        }, 0);
        EventValues.registerEventValue(BukkitEnterPlotEvent.class, Player.class, new Getter<Player, BukkitEnterPlotEvent>() {
            public Player get(BukkitEnterPlotEvent e) {
                return e.getPlayer();
            }
        }, 0);

        Skript.registerEvent("PlotSquared - Leave Plot", SkUniversalEvent.class, BukkitLeavePlotEvent.class,
                "[PlotSquared] plot (exit[ing]|leav(e|ing))",
                "[PlotSquared] (exit[ing]|leav(e|ing)) plot")
                .description("Called when a player enters a plot.")
                .examples("on plot enter:", "\tbroadcast \"%player% has entered plot %event-string%!\"");
        EventValues.registerEventValue(BukkitLeavePlotEvent.class, String.class, new Getter<String, BukkitLeavePlotEvent>() {
            public String get(BukkitLeavePlotEvent e) {
                return e.getPlot().getId().toString();
            }
        }, 0);
        EventValues.registerEventValue(BukkitLeavePlotEvent.class, Player.class, new Getter<Player, BukkitLeavePlotEvent>() {
            public Player get(BukkitLeavePlotEvent e) {
                return e.getPlayer();
            }
        }, 0);

    }

}