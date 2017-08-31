package us._donut_.skuniversal.parties;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.alessiodp.parties.Parties;
import com.alessiodp.parties.objects.ThePlayer;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

public class ExprPartyOfPlayer extends SimpleExpression<String> {

    private Expression<OfflinePlayer> player;

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
        player = (Expression<OfflinePlayer>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean arg1) {
        return "party of player";
    }

    @Override
    @Nullable
    protected String[] get(Event e) {
        ThePlayer partyPlayer = Parties.getInstance().getPlayerHandler().getThePlayer(player.getSingle(e));
        return new String[]{Parties.getInstance().getPlayerHandler().getPartyFromThePlayer(partyPlayer).getName()};
    }
}
