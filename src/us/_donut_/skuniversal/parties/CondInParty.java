package us._donut_.skuniversal.parties;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.alessiodp.parties.utils.api.PartiesAPI;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

@Name("Parties - Is Player in Party")
@Description("Checks if a player is in a party.")
@Examples({"if player is in a party:"})
public class CondInParty extends Condition {

    private Expression<OfflinePlayer> player;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult pr) {
        player = (Expression<OfflinePlayer>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "player is in a party";
    }

    @Override
    public boolean check(Event e) {
        PartiesAPI parties = new PartiesAPI();
        return parties.haveParty(player.getSingle(e).getUniqueId());
    }
}
