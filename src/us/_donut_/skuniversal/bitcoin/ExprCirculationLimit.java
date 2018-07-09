package us._donut_.skuniversal.bitcoin;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import us._donut_.bitcoin.Bitcoin;
import javax.annotation.Nullable;

@Name("Bitcoin - Circulation Limit")
@Description("Returns the bitcoin circulation limit.")
@Examples({"send \"%the bitcoin circulation limit%\""})
public class ExprCirculationLimit extends SimpleExpression<Number> {

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
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "the bitcoin circulation limit";
    }

    @Override
    @Nullable
    protected Number[] get(Event e) {
        return new Number[]{Bitcoin.getAPI().getCirculationLimit()};
    }
}