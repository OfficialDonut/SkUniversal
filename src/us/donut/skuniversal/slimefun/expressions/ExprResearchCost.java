package us.donut.skuniversal.slimefun.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import us.donut.skuniversal.slimefun.SlimefunHook;

import javax.annotation.Nullable;

@Name("Slimefun - Research Cost")
@Description("Returns the cost of a Slimefun research")
@Examples({"send \"%cost of the slimefun research with ID \"cool_research\"%\""})
public class ExprResearchCost extends SimpleExpression<Number> {

    static {
        Skript.registerExpression(ExprResearchCost.class, Number.class, ExpressionType.COMBINED, "[the] (cost|level) of [the] [Slimefun] research [with ID] %string%");
    }

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
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult pr) {
        id = (Expression<String>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean arg1) {
        return "the cost of the Slimefun research with ID " + id.getSingle(e);
    }

    @Override
    @Nullable
    protected Number[] get(Event e) {
        if (id.getSingle(e) == null) return null;
        return new Number[]{SlimefunHook.getResearch(id.getSingle(e)).getCost()};
    }
}
