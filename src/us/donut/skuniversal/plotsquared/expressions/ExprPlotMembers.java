package us.donut.skuniversal.plotsquared.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import com.github.intellectualsites.plotsquared.plot.object.Plot;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

import static us.donut.skuniversal.plotsquared.PlotSquaredHook.*;

@Name("PlotSquared - Plot Members")
@Description("Returns the members of a plot.")
@Examples({"send \"%the members of the plot with id (id of plot at player)%\""})
public class ExprPlotMembers extends SimpleExpression<OfflinePlayer> {

    static {
        Skript.registerExpression(ExprPlotMembers.class, OfflinePlayer.class, ExpressionType.COMBINED, "[(all [[of] the]|the)] members of [the] [PlotSquared] plot [with ID] %string%");
    }

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
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult pr) {
        id = (Expression<String>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "members of plot of with id " + id.toString(e, b);
    }

    @Override
    @Nullable
    protected OfflinePlayer[] get(Event e) {
        Plot plot;
        if (id.getSingle(e) == null || (plot = getPlot(id.getSingle(e))) == null) return null;
        return plot.getMembers().stream().map(Bukkit::getOfflinePlayer).toArray(OfflinePlayer[]::new);
    }

    @Override
    public void change(Event e, Object[] delta, Changer.ChangeMode mode){
        Plot plot;
        if (id.getSingle(e) == null || (plot = getPlot(id.getSingle(e))) == null) return;
        OfflinePlayer player = (OfflinePlayer) delta[0];
        if (mode == Changer.ChangeMode.ADD) {
            plot.addMember(player.getUniqueId());
        } else if (mode == Changer.ChangeMode.REMOVE) {
            plot.removeMember(player.getUniqueId());
        }
    }
    @Override
    public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
        return (mode == Changer.ChangeMode.REMOVE || mode == Changer.ChangeMode.ADD) ? CollectionUtils.array(OfflinePlayer.class) : null;
    }
}