package us._donut_.skuniversal.cannons;

import at.pavlov.cannons.Cannons;
import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;

import javax.annotation.Nullable;
import java.util.UUID;

public class CannonLoadTime extends SimpleExpression<Number> {

    private Expression<String> id;

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Number> getReturnType() {
        return Number.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult pr) {
        id = (Expression<String>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean arg1) {
        return "the load time of the cannon with ID " + id.getSingle(e);
    }

    @Override
    @Nullable
    protected Number[] get(Event e) {
        if (id.getSingle(e) != null) {
            if (Cannons.getPlugin().getCannon(UUID.fromString(id.getSingle(e))) != null) {
                return new Number[]{ Cannons.getPlugin().getCannon(UUID.fromString(id.getSingle(e))).getCannonDesign().getLoadTime() };
            } else {
                return null;
            }
        } else {
            Skript.error("Must provide a string, please refer to the syntax");
            return null;
        }
    }

}
