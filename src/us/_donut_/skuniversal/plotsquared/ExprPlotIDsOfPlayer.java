package us._donut_.skuniversal.plotsquared;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.intellectualcrafters.plot.api.PlotAPI;
import com.intellectualcrafters.plot.object.Plot;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class ExprPlotIDsOfPlayer extends SimpleExpression<String> {

    private Expression<OfflinePlayer> player;

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult pr) {
        player = (Expression<OfflinePlayer>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean arg1) {
        return "ids of plots of player " + player.getSingle(e);
    }

    @Override
    @Nullable
    protected String[] get(Event e) {
        PlotAPI plot = new PlotAPI();
        if (player.getSingle(e) != null) {
            List<String> ids = new ArrayList<>();
            for (Plot aPlot : plot.getPlotSquared().getPlots(player.getSingle(e).getUniqueId())) {
                ids.add(aPlot.getId().toString());
            }
            return ids.toArray(new String[ids.size()]);
        } else {
            Skript.error("Must provide a player, please refer to the syntax");
            return null;
        }
    }

}