package us._donut_.skuniversal.bitcoin.expressions;

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
import javax.annotation.Nullable;

import static us._donut_.skuniversal.bitcoin.BitcoinHook.*;

@Name("Bitcoin - Value")
@Description("Returns the current bitcoin value.")
@Examples({"send \"%the current bitcoin value%\""})
public class ExprBitcoinValue extends SimpleExpression<Number> {

    static {
        Skript.registerExpression(ExprBitcoinValue.class, Number.class, ExpressionType.SIMPLE, "[the] [current] bitcoin (value|worth)");
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
        return "the current bitcoin value";
    }

    @Override
    @Nullable
    protected Number[] get(Event e) {
        return new Number[]{bitcoinAPI.getBitcoinValue()};
    }
}