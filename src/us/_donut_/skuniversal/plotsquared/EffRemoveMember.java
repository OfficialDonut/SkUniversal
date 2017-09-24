package us._donut_.skuniversal.plotsquared;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.intellectualcrafters.plot.api.PlotAPI;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

public class EffRemoveMember extends Effect {

    private Expression<OfflinePlayer> player;
    private Expression<Location> loc;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult p) {
        player = (Expression<OfflinePlayer>) e[0];
        loc = (Expression<Location>) e[1];
        return true;
    }

    @Override
    public String toString(@Nullable Event paramEvent, boolean paramBoolean) {
        return "remove member";
    }

    @Override
    protected void execute(Event e) {
        PlotAPI plot = new PlotAPI();
        plot.getPlot(loc.getSingle(e)).removeMember(player.getSingle(e).getUniqueId());
    }
}
