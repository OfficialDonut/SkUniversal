package us.donut.skuniversal.bitcoin.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import org.bukkit.event.Event;
import us._donut_.bitcoin.BitcoinAPI;

import javax.annotation.Nullable;

@Name("Bitcoin - Amount In Bank")
@Description("Returns the amount of bitcoins in the bank.")
@Examples({"send \"%the amount of bitcoins in the bank%\""})
public class ExprAmountInBank extends SimpleExpression<Number> {

    static {
        Skript.registerExpression(ExprAmountInBank.class, Number.class, ExpressionType.SIMPLE, "[the] (amount|number) of bitcoins in [the] bank");
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
        return "the amount of bitcoins in the bank";
    }

    @Override
    @Nullable
    protected Number[] get(Event e) {
        return new Number[]{BitcoinAPI.getAmountInBank()};
    }

    @Override
    public void change(Event e, Object[] delta, Changer.ChangeMode mode){
        double amount = ((Number) delta[0]).doubleValue();
        if (mode == Changer.ChangeMode.SET) {
            BitcoinAPI.addToBank(amount - BitcoinAPI.getAmountInBank());
        } else if (mode == Changer.ChangeMode.ADD) {
            BitcoinAPI.addToBank(amount);
        } else if (mode == Changer.ChangeMode.REMOVE) {
            BitcoinAPI.removeFromBank(amount);
        }
    }

    @Override
    public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
        return (mode == Changer.ChangeMode.SET || mode == Changer.ChangeMode.REMOVE || mode == Changer.ChangeMode.ADD) ? CollectionUtils.array(Number.class) : null;
    }
}
