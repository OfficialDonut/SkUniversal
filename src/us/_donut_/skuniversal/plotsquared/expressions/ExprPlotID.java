package us._donut_.skuniversal.plotsquared.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.Location;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

import static us._donut_.skuniversal.plotsquared.PlotSquaredHook.*;

@Name("PlotSquared - Plot ID at location")
@Description("Returns the ID of a plot at a location.")
@Examples({"send \"%the ID of the plot at player%\""})
public class ExprPlotID extends SimpleExpression<String> {

    static {
        Skript.registerExpression(ExprPlotID.class, String.class, ExpressionType.COMBINED, "[the] [ID of [the]] PlotSquared] plot at %location%");
    }

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
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult pr) {
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
        if (loc.getSingle(e) == null) return null;
        return new String[]{plotAPI.getPlot(loc.getSingle(e)).getId().toString()};
    }

}