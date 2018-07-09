package us._donut_.skuniversal.slimefun;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.mrCookieSlime.Slimefun.Objects.Research;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

@Name("Slimefun - Research Cost")
@Description("Returns the cost of a Slimefun research")
@Examples({"send \"%cost of the slimefun research with id 2048%\""})
public class ExprResearchCost extends SimpleExpression<Number> {

    private Expression<Integer> id;

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
        id = (Expression<Integer>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean arg1) {
        return "the cost of the Slimefun research with id " + id.getSingle(e);
    }

    @Override
    @Nullable
    protected Number[] get(Event e) {
        if (id.getSingle(e) != null) {
            return new Number[]{Research.getByID(id.getSingle(e)).getCost()};
        } else {
            Skript.error("Must provide an integer, please refer to the syntax");
            return null;
        }
    }
}
