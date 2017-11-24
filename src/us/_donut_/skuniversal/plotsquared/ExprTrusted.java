package us._donut_.skuniversal.plotsquared;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import com.intellectualcrafters.plot.api.PlotAPI;
import com.intellectualcrafters.plot.object.Plot;
import com.intellectualcrafters.plot.object.PlotId;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ExprTrusted extends SimpleExpression<OfflinePlayer>{

    private PlotAPI plot = new PlotAPI();
    private Expression<String> id;

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends OfflinePlayer> getReturnType() {
        return OfflinePlayer.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult pr) {
        id = (Expression<String>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean arg1) {
        return "trusted players of plot with id " + id.getSingle(e);
    }

    @Override
    @Nullable
    protected OfflinePlayer[] get(Event e) {
        if (id.getSingle(e) != null) {
            List<OfflinePlayer> trusted = new ArrayList<>();
            PlotId plotId = PlotId.fromString(id.getSingle(e));
            if (plotId == null) {
                Skript.error("Invalid plot ID, please refer to the syntax");
                return null;
            } else {
                for (Plot aPlot : plot.getAllPlots()) {
                    if (aPlot.getId().equals(plotId)) {
                        for (UUID p : aPlot.getTrusted()) {
                            trusted.add(Bukkit.getOfflinePlayer(p));
                        }
                        return trusted.toArray(new OfflinePlayer[trusted.size()]);
                    }
                }
                Skript.error("Invalid plot ID, please refer to the syntax");
                return null;
            }
        } else {
            Skript.error("Must provide a string, please refer to the syntax");
            return null;
        }
    }

    @Override
    public void change(Event e, Object[] delta, Changer.ChangeMode mode){
        OfflinePlayer player = (OfflinePlayer) delta[0];
        if (mode == Changer.ChangeMode.ADD) {
            PlotId plotId = PlotId.fromString(id.getSingle(e));
            for (Plot aPlot : plot.getAllPlots()) {
                if (aPlot.getId().equals(plotId)) {
                    aPlot.addTrusted(player.getUniqueId());
                }
            }
        } else if (mode == Changer.ChangeMode.REMOVE) {
            PlotId plotId = PlotId.fromString(id.getSingle(e));
            for (Plot aPlot : plot.getAllPlots()) {
                if (aPlot.getId().equals(plotId)) {
                    aPlot.removeTrusted(player.getUniqueId());
                }
            }
        }
    }
    @Override
    public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.REMOVE || mode == Changer.ChangeMode.ADD) {
            return CollectionUtils.array(OfflinePlayer.class);
        }
        return null;
    }
}
