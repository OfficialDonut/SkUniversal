package us._donut_.skuniversal.plotsquared;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.Location;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

@Name("PlotSquared - Plot ID at location")
@Description("Returns the ID of a plot at a location.")
@Examples({"send \"%the ID of the plot at player%\""})
public class ExprPlotID extends SimpleExpression<String> {

    private Expression<Location> loc;

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult pr) {
        loc = (Expression<Location>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "id of plot at location " + loc.toString(e, b);
    }

    @Override
    @Nullable
    protected String[] get(Event e) {
        return new String[]{PlotSquaredRegister.plotAPI.getPlot(loc.getSingle(e)).getId().toString()};
    }

}