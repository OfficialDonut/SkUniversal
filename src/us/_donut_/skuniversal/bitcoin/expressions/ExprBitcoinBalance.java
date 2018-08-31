package us._donut_.skuniversal.bitcoin.expressions;

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
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

import static us._donut_.skuniversal.bitcoin.BitcoinHook.*;

@Name("Bitcoin - Balance of Player")
@Description("Returns the bitcoin balance of a player.")
@Examples({"send \"%the bitcoin balance of player%\""})
public class ExprBitcoinBalance extends SimpleExpression<Number> {

    static {
        Skript.registerExpression(ExprBitcoinBalance.class, Number.class, ExpressionType.COMBINED,
                "[the] bitcoin bal[ance] of %offlineplayer%",
                "%offlineplayer%'s bitcoin bal[ance]");
    }

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
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult pr) {
        player = (Expression<OfflinePlayer>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "the bitcoin balance of player " + player.toString(e, b);
    }

    @Override
    @Nullable
    protected Number[] get(Event e) {
        return new Number[]{bitcoinAPI.getBalance(player.getSingle(e).getUniqueId())};
    }

    @Override
    public void change(Event e, Object[] delta, Changer.ChangeMode mode){
        double amount = ((Number) delta[0]).doubleValue();
        if (mode == Changer.ChangeMode.SET) {
            bitcoinAPI.setBalance(player.getSingle(e).getUniqueId(), amount);
        } else if (mode == Changer.ChangeMode.ADD) {
            bitcoinAPI.deposit(player.getSingle(e).getUniqueId(), amount);
        } else if (mode == Changer.ChangeMode.REMOVE) {
            bitcoinAPI.withdraw(player.getSingle(e).getUniqueId(), amount);
        }
    }

    @Override
    public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
        return (mode == Changer.ChangeMode.SET || mode == Changer.ChangeMode.REMOVE || mode == Changer.ChangeMode.ADD) ? CollectionUtils.array(Number.class) : null;
    }
}
