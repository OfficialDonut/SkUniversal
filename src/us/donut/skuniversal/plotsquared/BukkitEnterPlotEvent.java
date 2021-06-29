package us.donut.skuniversal.plotsquared;

import com.plotsquared.bukkit.player.BukkitPlayer;
import com.plotsquared.core.events.PlayerEnterPlotEvent;
import com.plotsquared.core.plot.Plot;
import org.bukkit.entity.Player;

public class BukkitEnterPlotEvent extends BukkitPlotEvent {

    private PlayerEnterPlotEvent event;

    public BukkitEnterPlotEvent(PlayerEnterPlotEvent event) {
        this.event = event;
    }

    public Plot getPlot() {
        return event.getPlot();
    }

    public Player getPlayer() {
        return ((BukkitPlayer) event.getPlotPlayer()).player;
    }
}
