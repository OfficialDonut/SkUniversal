package us.donut.skuniversal.parties.conditions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.alessiodp.parties.api.interfaces.PartyPlayer;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

import static us.donut.skuniversal.parties.PartiesHook.*;

@Name("Parties - Is Player in Party")
@Description("Checks if a player is in a party.")
@Examples({"if player is in a party:"})
public class CondInParty extends Condition {

    static {
        Skript.registerCondition(CondInParty.class,
                "%offlineplayer% is in [a] party",
                "%offlineplayer% is(n't| not) in [a] party");
    }

    private Expression<OfflinePlayer> player;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult pr) {
        player = (Expression<OfflinePlayer>) e[0];
        setNegated(matchedPattern == 1);
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "player " + player.toString(e, b) + " is in a party";
    }

    @Override
    public boolean check(Event e) {
        if (player.getSingle(e) == null) return isNegated();
        PartyPlayer pp = partiesAPI.getPartyPlayer(player.getSingle(e).getUniqueId());
        return (pp != null
                && pp.getPartyName() != null
                && !pp.getPartyName().isEmpty() // Backward compatibility
        ) != isNegated();
    }
}
