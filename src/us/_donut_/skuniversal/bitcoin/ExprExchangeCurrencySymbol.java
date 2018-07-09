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

@Name("Bitcoin - Exchange Currency Symbol")
@Description("Returns the exchange currency symbol (e.g. $).")
@Examples({"send \"the value of 1 bitcoin is: %bitcoin exchange currency symbol%%bitcoin value%\""})
public class ExprExchangeCurrencySymbol extends SimpleExpression<String> {

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
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "the exchange currency symbol";
    }

    @Override
    @Nullable
    protected String[] get(Event e) {
        return new String[]{Bitcoin.getAPI().getExchangeCurrencySymbol()};
    }
}