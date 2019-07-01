package us.donut.skuniversal.plotsquared;

import ch.njol.skript.Skript;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import com.github.intellectualsites.plotsquared.api.PlotAPI;
import com.github.intellectualsites.plotsquared.bukkit.events.PlayerClaimPlotEvent;
import com.github.intellectualsites.plotsquared.bukkit.events.PlayerEnterPlotEvent;
import com.github.intellectualsites.plotsquared.bukkit.events.PlayerLeavePlotEvent;
import com.github.intellectualsites.plotsquared.bukkit.events.PlotDeleteEvent;
import com.github.intellectualsites.plotsquared.plot.object.Plot;
import com.github.intellectualsites.plotsquared.plot.object.PlotId;
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

        Skript.registerEvent("PlotSquared - Enter Plot", SkUniversalEvent.class, PlayerEnterPlotEvent.class,
                "[PlotSquared] plot enter[ing]",
                "[PlotSquared] enter[ing] plot")
                .description("Called when a player enters a plot.")
                .examples("on plot enter:", "\tbroadcast \"%player% has entered plot %event-string%!\"");
        EventValues.registerEventValue(PlayerEnterPlotEvent.class, String.class, new Getter<String, PlayerEnterPlotEvent>() {
            public String get(PlayerEnterPlotEvent e) {
                return e.getPlot().getId().toString();
            }
        }, 0);
        EventValues.registerEventValue(PlayerEnterPlotEvent.class, Player.class, new Getter<Player, PlayerEnterPlotEvent>() {
            public Player get(PlayerEnterPlotEvent e) {
                return e.getPlayer();
            }
        }, 0);

        Skript.registerEvent("PlotSquared - Leave Plot", SkUniversalEvent.class, PlayerLeavePlotEvent.class,
                "[PlotSquared] plot (exit[ing]|leav(e|ing))",
                "[PlotSquared] (exit[ing]|leav(e|ing)) plot")
                .description("Called when a player enters a plot.")
                .examples("on plot enter:", "\tbroadcast \"%player% has entered plot %event-string%!\"");
        EventValues.registerEventValue(PlayerLeavePlotEvent.class, String.class, new Getter<String, PlayerLeavePlotEvent>() {
            public String get(PlayerLeavePlotEvent e) {
                return e.getPlot().getId().toString();
            }
        }, 0);
        EventValues.registerEventValue(PlayerLeavePlotEvent.class, Player.class, new Getter<Player, PlayerLeavePlotEvent>() {
            public Player get(PlayerLeavePlotEvent e) {
                return e.getPlayer();
            }
        }, 0);

    }

}