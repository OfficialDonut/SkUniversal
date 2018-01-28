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

@Name("Bitcoin - Top Players")
@Description("Returns the players in order of highest balance.")
@Examples({"send \"%the top bitcoin players%\""})
public class ExprBitcoinTopPlayers extends SimpleExpression<OfflinePlayer> {

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends OfflinePlayer> getReturnType() {
        return OfflinePlayer.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult pr) {
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean arg1) {
        return "the top bitcoin players";
    }

    @Override
    @Nullable
    protected OfflinePlayer[] get(Event e) {
        return Bitcoin.getAPI().getTopPlayers().toArray(new OfflinePlayer[Bitcoin.getAPI().getTopPlayers().size()]);
    }
}
