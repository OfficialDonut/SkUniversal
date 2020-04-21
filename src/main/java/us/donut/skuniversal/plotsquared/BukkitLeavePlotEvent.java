package us.donut.skuniversal.plotsquared;

import com.plotsquared.bukkit.player.BukkitPlayer;
import com.plotsquared.core.events.PlayerLeavePlotEvent;
import com.plotsquared.core.plot.Plot;
import org.bukkit.entity.Player;

public class BukkitLeavePlotEvent extends BukkitPlotEvent {

    private PlayerLeavePlotEvent event;

    public BukkitLeavePlotEvent(PlayerLeavePlotEvent event) {
        this.event = event;
    }

    public Plot getPlot() {
        return event.getPlot();
    }

    public Player getPlayer() {
        return ((BukkitPlayer) event.getPlotPlayer()).player;
    }
}
