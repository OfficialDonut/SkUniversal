package us.donut.skuniversal.prisonmines.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import net.lightshard.prisonmines.mine.Mine;
import org.bukkit.Location;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

import static us.donut.skuniversal.prisonmines.PrisonMinesHook.mineAPI;

@Name("PrisonMines - Mine at Location")
@Description("Returns the name of a mine at a location.")
@Examples({"send \"%the mine at player%\""})
public class ExprMineAtLoc extends SimpleExpression<String> {

    static {
        Skript.registerExpression(ExprMineAtLoc.class, String.class, ExpressionType.COMBINED, "[the] [name of [the]] [PrisonMines] mine at %location%");
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
        return "name of mine at location " + loc.toString(e, b);
    }

    @Override
    @Nullable
    protected String[] get(Event e) {
        Mine mine;
        if (loc.getSingle(e) == null || (mine = mineAPI.getByLocation(loc.getSingle(e))) == null) return null;
        return new String[]{mine.getName()};
    }
}