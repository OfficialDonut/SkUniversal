package us._donut_.skuniversal.autorank;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.armar.plugins.autorank.Autorank;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

public class ExprGlobalTime extends SimpleExpression<Number> {

    private Expression<Player> player;

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
        player = (Expression<Player>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean arg1) {
        return "global time of player " + player.getSingle(e);
    }

    @Override
    @Nullable
    protected Number[] get(Event e) {
        return new Number[]{Autorank.getInstance().getAPI().getGlobalPlayTime(player.getSingle(e).getUniqueId())};
    }
}
