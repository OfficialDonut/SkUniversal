package us._donut_.skuniversal.prisonmines;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import net.lightshard.prisonmines.MineAPI;
import org.bukkit.Location;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

public class ExprMineAtLoc extends SimpleExpression<String> {

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
    public String toString(@Nullable Event e, boolean arg1) {
        return "name of mine at location " + loc.getSingle(e);
    }

    @Override
    @Nullable
    protected String[] get(Event e) {
        if (loc.getSingle(e) != null) {
            MineAPI.PrisonMinesAPI prisonMines = new MineAPI.PrisonMinesAPI();
            return new String[]{prisonMines.getByLocation(loc.getSingle(e)).getName()};
        } else {
            Skript.error("Must provide a location, please refer to the syntax");
            return null;
        }
    }
}