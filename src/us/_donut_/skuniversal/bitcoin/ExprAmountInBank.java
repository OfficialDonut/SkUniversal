package us._donut_.skuniversal.bitcoin;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import org.bukkit.event.Event;
import us._donut_.bitcoin.Bitcoin;
import javax.annotation.Nullable;

@Name("Bitcoin - Amount In Bank")
@Description("Returns the amount of bitcoins in the bank.")
@Examples({"send \"%the amount of bitcoins in the bank%\""})
public class ExprAmountInBank extends SimpleExpression<Number> {

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
        return "the amount of bitcoins in the bank";
    }

    @Override
    @Nullable
    protected Number[] get(Event e) {
        return new Number[]{Bitcoin.getAPI().getAmountInBank()};
    }

    @Override
    public void change(Event e, Object[] delta, Changer.ChangeMode mode){
        Number amount = (Number) delta[0];
        if (mode == Changer.ChangeMode.SET) {
            Bitcoin.getAPI().addToBank(amount.doubleValue() - Bitcoin.getAPI().getAmountInBank());
        } else if (mode == Changer.ChangeMode.ADD) {
            Bitcoin.getAPI().addToBank(amount.doubleValue());
        } else if (mode == Changer.ChangeMode.REMOVE) {
            Bitcoin.getAPI().removeFromBank(amount.doubleValue());
        }
    }

    @Override
    public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET || mode == Changer.ChangeMode.REMOVE || mode == Changer.ChangeMode.ADD) {
            return CollectionUtils.array(Number.class);
        }
        return null;
    }
}
