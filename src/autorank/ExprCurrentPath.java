package us._donut_.skuniversal.autorank;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.armar.plugins.autorank.Autorank;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

public class ExprCurrentPath extends SimpleExpression<String> {

    private Expression<Player> player;

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
        player = (Expression<Player>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean arg1) {
        return "current path of player " + player.getSingle(e);
    }

    @Override
    @Nullable
    protected String[] get(Event e) {
        if (player.getSingle(e) != null) {
            return new String[]{Autorank.getInstance().getAPI().getActivePath(player.getSingle(e).getUniqueId()).getDisplayName()};
        } else{
            Skript.error("Must provide a player, please refer to the syntax");
            return null;
        }
    }
}
