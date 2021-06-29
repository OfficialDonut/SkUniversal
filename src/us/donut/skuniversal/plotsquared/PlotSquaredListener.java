package us.donut.skuniversal.plotsquared;

import com.google.common.eventbus.Subscribe;
import com.plotsquared.core.events.PlayerClaimPlotEvent;
import com.plotsquared.core.events.PlayerEnterPlotEvent;
import com.plotsquared.core.events.PlayerLeavePlotEvent;
import com.plotsquared.core.events.PlotDeleteEvent;
import org.bukkit.Bukkit;

public class PlotSquaredListener {

    @Subscribe
    public void onPlotDelete(PlotDeleteEvent e) {
        Bukkit.getPluginManager().callEvent(new BukkitPlotDeleteEvent(e));
    }

    @Subscribe
    public void onPlotClaim(PlayerClaimPlotEvent e) {
        Bukkit.getPluginManager().callEvent(new BukkitClaimPlotEvent(e));
    }

    @Subscribe
    public void onPlotEnter(PlayerEnterPlotEvent e) {
        Bukkit.getPluginManager().callEvent(new BukkitEnterPlotEvent(e));
    }

    @Subscribe
    public void onPlotLeave(PlayerLeavePlotEvent e) {
        Bukkit.getPluginManager().callEvent(new BukkitLeavePlotEvent(e));
    }
}
