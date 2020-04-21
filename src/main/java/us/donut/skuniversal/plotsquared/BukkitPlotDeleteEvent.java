package us.donut.skuniversal.plotsquared;

import com.plotsquared.core.events.PlotDeleteEvent;
import com.plotsquared.core.plot.PlotId;
import org.bukkit.event.Cancellable;

public class BukkitPlotDeleteEvent extends BukkitPlotEvent implements Cancellable {

    private PlotDeleteEvent event;

    public BukkitPlotDeleteEvent(PlotDeleteEvent event) {
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

    public PlotId getPlotId() {
        return event.getPlotId();
    }
}
