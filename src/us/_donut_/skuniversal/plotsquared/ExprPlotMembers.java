package us._donut_.skuniversal.plotsquared;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import com.intellectualcrafters.plot.api.PlotAPI;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ExprPlotMembers extends SimpleExpression<OfflinePlayer> {

    private PlotAPI plot = new PlotAPI();
    private Expression<Location> loc;

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
        loc = (Expression<Location>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean arg1) {
        return "members of plot of at location " + loc.getSingle(e);
    }

    @Override
    @Nullable
    protected OfflinePlayer[] get(Event e) {
        if (loc.getSingle(e) != null) {
            List<OfflinePlayer> members = new ArrayList<>();
            for (UUID p : plot.getPlot(loc.getSingle(e)).getMembers()) {
                members.add(Bukkit.getOfflinePlayer(p));
            }
            return members.toArray(new OfflinePlayer[members.size()]);
        } else {
            Skript.error("Must provide a location, please refer to the syntax");
            return null;
        }
    }

    @Override
    public void change(Event e, Object[] delta, Changer.ChangeMode mode){
        OfflinePlayer player = (OfflinePlayer) delta[0];
        if (mode == Changer.ChangeMode.ADD) {
            plot.getPlot(loc.getSingle(e)).addMember(player.getUniqueId());
        } else if (mode == Changer.ChangeMode.REMOVE) {
            plot.getPlot(loc.getSingle(e)).removeMember(player.getUniqueId());
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