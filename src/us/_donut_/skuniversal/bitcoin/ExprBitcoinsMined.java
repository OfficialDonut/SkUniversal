package us._donut_.skuniversal.bitcoin;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import us._donut_.bitcoin.Bitcoin;
import javax.annotation.Nullable;

@Name("Bitcoin - Amount Mined")
@Description("Returns the amount of bitcoins mined by a player.")
@Examples({"send \"%the amount of bitcoins mined by player%\""})
public class ExprBitcoinsMined extends SimpleExpression<Number> {

    private Expression<OfflinePlayer> player;

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
        player = (Expression<OfflinePlayer>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "the amount of bitcoins mined by player " + player.toString(e, b);
    }

    @Override
    @Nullable
    protected Number[] get(Event e) {
        return new Number[]{Bitcoin.getAPI().getBitcoinsMined(player.getSingle(e).getUniqueId())};
    }
}
