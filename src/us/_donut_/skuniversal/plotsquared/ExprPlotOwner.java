package us._donut_.skuniversal.plotsquared;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import com.intellectualcrafters.plot.object.Plot;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

@Name("PlotSquared - Plot Owner")
@Description("Returns the owner of a plot.")
@Examples({"send \"%the owner of the plot with id (id of plot at player)%\""})
public class ExprPlotOwner extends SimpleExpression<OfflinePlayer> {

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
    public String toString(@Nullable Event e, boolean b) {
        return "owner of plot with ID " + id.toString(e, b);
    }

    @Override
    @Nullable
    protected OfflinePlayer[] get(Event e) {
        Plot plot = PlotSquaredRegister.getPlot(id.getSingle(e));
        return plot == null ? null : plot.getOwners().stream().map(Bukkit::getOfflinePlayer).toArray(OfflinePlayer[]::new);
    }

    public void change(Event e, Object[] delta, Changer.ChangeMode mode){
        OfflinePlayer player = (OfflinePlayer) delta[0];
        Plot plot = PlotSquaredRegister.getPlot(id.getSingle(e));
        if (plot == null)
            return;
        if (mode == Changer.ChangeMode.SET) {
            plot.setOwner(player.getUniqueId());
        }
    }

    @Override
    public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            return CollectionUtils.array(OfflinePlayer.class);
        }
        return null;
    }
}
