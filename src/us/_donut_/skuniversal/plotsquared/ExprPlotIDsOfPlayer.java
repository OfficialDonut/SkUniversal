package us._donut_.skuniversal.plotsquared;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

@Name("PlotSquared - Plot IDs of Player")
@Description("Returns the plot IDs of a player.")
@Examples({"send \"%the plot IDs of player%\""})
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
    public String toString(@Nullable Event e, boolean b) {
        return "ids of plots of player " + player.toString(e, b);
    }

    @Override
    @Nullable
    protected String[] get(Event e) {
        return PlotSquaredRegister.plotAPI.getPlotSquared().getPlots(player.getSingle(e).getUniqueId()).stream().map(plot -> plot.getId().toString()).toArray(String[]::new);
    }

}