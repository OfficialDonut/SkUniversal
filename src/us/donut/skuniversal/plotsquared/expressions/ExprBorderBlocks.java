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
import com.plotsquared.core.location.Location;
import com.plotsquared.core.plot.Plot;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.event.Event;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

import static us.donut.skuniversal.plotsquared.PlotSquaredHook.getPlot;

@Name("PlotSquared - Plot Border Blocks")
@Description("Returns the border blocks of a plot.")
@Examples({"set the border blocks at height 65 of the plot with id (id of plot at player) to dirt"})
public class ExprBorderBlocks extends SimpleExpression<Block> {

    static {
        Skript.registerExpression(ExprBorderBlocks.class, Block.class, ExpressionType.COMBINED, "[(all [[of] the]|the)] border blocks at (height|y[-value]) %number% of [the] [PlotSquared] plot [with ID] %string%");
    }

    private Expression<String> id;
    private Expression<Number> borderHeight;

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends Block> getReturnType() {
        return Block.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult pr) {
        borderHeight = (Expression<Number>) e[0];
        id = (Expression<String>) e[1];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "border blocks of plot of with id " + id.toString(e, b);
    }

    private Location getNECorner(Location... corners) {
        outer:
        for (Location corner : corners) {
            for (Location otherCorner : corners) {
                if (corner.getX() < otherCorner.getX() || corner.getZ() > otherCorner.getZ()) continue outer;
            }
            return corner;
        }
        return null;
    }

    private Location getNWCorner(Location... corners) {
        outer:
        for (Location corner : corners) {
            for (Location otherCorner : corners) {
                if (corner.getX() > otherCorner.getX() || corner.getZ() > otherCorner.getZ()) continue outer;
            }
            return corner;
        }
        return null;
    }

    private Location getSECorner(Location... corners) {
        outer:
        for (Location corner : corners) {
            for (Location otherCorner : corners) {
                if (corner.getX() < otherCorner.getX() || corner.getZ() < otherCorner.getZ()) continue outer;
            }
            return corner;
        }
        return null;
    }

    private Location getSWCorner(Location... corners) {
        outer:
        for (Location corner : corners) {
            for (Location otherCorner : corners) {
                if (corner.getX() > otherCorner.getX() || corner.getZ() < otherCorner.getZ()) continue outer;
            }
            return corner;
        }
        return null;
    }

    @Override
    @Nullable
    protected Block[] get(Event e) {
        Plot plot;
        if (id.getSingle(e) == null || borderHeight.getSingle(e) == null || (plot = getPlot(id.getSingle(e))) == null) return null;
        List<Block> blocks = new ArrayList<>();
        Location[] corners = plot.getCorners();
        Location northEastCorner = getNECorner(corners);
        Location northWestCorner = getNWCorner(corners);
        Location southEastCorner = getSECorner(corners);
        Location southWestCorner = getSWCorner(corners);
        if (plot.getCorners().length == 4 && northEastCorner != null && northWestCorner != null && southEastCorner != null && southWestCorner != null) {
            northEastCorner.add(1, borderHeight.getSingle(e).intValue(), -1);
            northWestCorner.add(-1, borderHeight.getSingle(e).intValue(), -1);
            southEastCorner.add(1, borderHeight.getSingle(e).intValue(), 1);
            southWestCorner.add(-1, borderHeight.getSingle(e).intValue(), 1);
            for (double x = northWestCorner.getX(); x < northEastCorner.getX() + 1; x++) {
                blocks.add(new org.bukkit.Location(Bukkit.getWorld(northWestCorner.getWorldName()), x, borderHeight.getSingle(e).intValue(), northWestCorner.getZ()).getBlock());
            }
            for (double z = northEastCorner.getZ(); z < southEastCorner.getZ() + 1; z++) {
                blocks.add(new org.bukkit.Location(Bukkit.getWorld(northEastCorner.getWorldName()), northEastCorner.getX(), borderHeight.getSingle(e).intValue(), z).getBlock());
            }
            for (double z = northWestCorner.getZ(); z < southWestCorner.getZ() + 1; z++) {
                blocks.add(new org.bukkit.Location(Bukkit.getWorld(northWestCorner.getWorldName()), northWestCorner.getX(), borderHeight.getSingle(e).intValue(), z).getBlock());
            }
            for (double x = southWestCorner.getX(); x < southEastCorner.getX() + 1; x++) {
                blocks.add(new org.bukkit.Location(Bukkit.getWorld(southWestCorner.getWorldName()), x, borderHeight.getSingle(e).intValue(), southWestCorner.getZ()).getBlock());
            }
            return blocks.toArray(new Block[0]);
        }
        return null;
    }
}