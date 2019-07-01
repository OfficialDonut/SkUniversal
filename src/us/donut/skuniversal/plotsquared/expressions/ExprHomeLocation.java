package us.donut.skuniversal.plotsquared.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.github.intellectualsites.plotsquared.plot.object.Plot;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

import static us.donut.skuniversal.plotsquared.PlotSquaredHook.*;

@Name("PlotSquared - Plot Home")
@Description("Returns the home location of a plot.")
@Examples({"send \"%the home location of the plot with id (id of plot at player)%\""})
public class ExprHomeLocation extends SimpleExpression<Location> {

    static {
        Skript.registerExpression(ExprHomeLocation.class, Location.class, ExpressionType.COMBINED, "[the] home loc[ation] of [the] [PlotSquared] plot [with ID] %string%");
    }

    private Expression<String> id;

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Location> getReturnType() {
        return Location.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult pr) {
        id = (Expression<String>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "home location of plot with ID " + id.toString(e, b);
    }

    @Override
    @Nullable
    protected Location[] get(Event e) {
        Plot plot;
        if (id.getSingle(e) == null || (plot = getPlot(id.getSingle(e))) == null) return null;
        com.github.intellectualsites.plotsquared.plot.object.Location home = plot.getHome();
        return new Location[]{new Location(Bukkit.getWorld(home.getWorld()), home.getX(), home.getY(), home.getZ(), home.getYaw(), home.getPitch())};
    }

}