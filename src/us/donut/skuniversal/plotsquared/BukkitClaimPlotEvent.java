package us.donut.skuniversal.plotsquared;

import com.plotsquared.core.events.PlayerClaimPlotEvent;
import com.plotsquared.core.plot.Plot;
import org.bukkit.event.Cancellable;

public class BukkitClaimPlotEvent extends BukkitPlotEvent implements Cancellable {

    private PlayerClaimPlotEvent event;

    public BukkitClaimPlotEvent(PlayerClaimPlotEvent event) {
        this.event = event;
    }

    @Override
    public boolean isCancelled() {
        return event.getEventResult() == com.plotsquared.core.events.Result.DENY;
    }

    @Override
    public void setCancelled(boolean isCancelled) {
        event.setEventResult(isCancelled ? com.plotsquared.core.events.Result.DENY : com.plotsquared.core.events.Result.ACCEPT);
    }

    public Plot getPlot() {
        return event.getPlot();
    }
}
