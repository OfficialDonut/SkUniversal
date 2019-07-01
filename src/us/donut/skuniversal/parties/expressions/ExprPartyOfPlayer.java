package us.donut.skuniversal.parties.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.alessiodp.parties.objects.ThePlayer;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

import static us.donut.skuniversal.parties.PartiesHook.*;

@Name("Parties - Party of Player")
@Description("Returns the party of a player.")
@Examples({"send \"%the party of player%\""})
public class ExprPartyOfPlayer extends SimpleExpression<String> {

    static {
        Skript.registerExpression(ExprPartyOfPlayer.class,String.class, ExpressionType.COMBINED,
                "[[the] name of] [the] party of %offlineplayer%",
                "[[the] name of] %offlineplayer%'s party");
    }

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
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult pr) {
        player = (Expression<OfflinePlayer>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "party of player " + player.toString(e, b);
    }

    @Override
    @Nullable
    protected String[] get(Event e) {
        if (player.getSingle(e) == null) return null;
        ThePlayer partyPlayer = playerHandler.getThePlayer(player.getSingle(e));
        return new String[]{playerHandler.getPartyFromThePlayer(partyPlayer).getName()};
    }
}
