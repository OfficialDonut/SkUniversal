package us._donut_.skuniversal.bitcoin;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import us._donut_.bitcoin.Bitcoin;
import javax.annotation.Nullable;

@Name("Bitcoin - Balance of Player")
@Description("Returns the bitcoin balance of a player.")
@Examples({"send \"%the bitcoin balance of player%\""})
public class ExprBitcoinBalance extends SimpleExpression<Number> {

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
    public String toString(@Nullable Event e, boolean arg1) {
        return "the bitcoin balance of player " + player.getSingle(e);
    }

    @Override
    @Nullable
    protected Number[] get(Event e) {
        if (player.getSingle(e) != null) {
            return new Number[]{Bitcoin.getAPI().getBalance(player.getSingle(e).getUniqueId())};
        } else {
            Skript.error("Must provide a player, please refer to the syntax");
            return null;
        }
    }

    @Override
    public void change(Event e, Object[] delta, Changer.ChangeMode mode){
        Number amount = (Number) delta[0];
        if (mode == Changer.ChangeMode.SET) {
            Bitcoin.getAPI().setBalance(player.getSingle(e).getUniqueId(), amount.doubleValue());
        } else if (mode == Changer.ChangeMode.ADD) {
            Bitcoin.getAPI().deposit(player.getSingle(e).getUniqueId(), amount.doubleValue());
        } else if (mode == Changer.ChangeMode.REMOVE) {
            Bitcoin.getAPI().withdraw(player.getSingle(e).getUniqueId(), amount.doubleValue());
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
