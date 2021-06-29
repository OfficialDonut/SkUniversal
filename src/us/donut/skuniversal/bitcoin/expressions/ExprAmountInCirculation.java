package us.donut.skuniversal.bitcoin.expressions;

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
import us._donut_.bitcoin.BitcoinAPI;

import javax.annotation.Nullable;

@Name("Bitcoin - Amount In Circulation")
@Description("Returns the amount of bitcoins in circulation.")
@Examples({"send \"%the amount of bitcoins in circulation%\""})
public class ExprAmountInCirculation extends SimpleExpression<Number> {

    static {
        Skript.registerExpression(ExprAmountInCirculation.class, Number.class, ExpressionType.SIMPLE, "[the] [total] (amount|number) of bitcoins [in circulation]");
    }

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
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "the amount of bitcoins in circulation";
    }

    @Override
    @Nullable
    protected Number[] get(Event e) {
        return new Number[]{BitcoinAPI.getAmountInCirculation()};
    }
}
