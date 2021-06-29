package us.donut.skuniversal.parties.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.alessiodp.parties.api.interfaces.Party;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

import static us.donut.skuniversal.parties.PartiesHook.partiesAPI;

@Name("Parties - Remove Player from Party")
@Description("Removes a player from a party.")
@Examples({"remove player from the party named \"cool\""})
public class EffRemoveFromParty extends Effect {

    static {
        Skript.registerEffect(EffRemoveFromParty.class, "remove %player% from [the] party [(named|with name)] %string%");
    }

    private Expression<Player> player;
    private Expression<String> name;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult p) {
        player = (Expression<Player>) e[0];
        name = (Expression<String>) e[1];
        return true;
    }
    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "remove player " + player.toString(e, b) + "from party named " + name.toString(e, b);
    }

    @Override
    protected void execute(Event e) {
        if (player.getSingle(e) == null || name.getSingle(e) == null) return;
        Party party = partiesAPI.getParty(name.getSingle(e));
        if (party != null)
            party.removeMember(partiesAPI.getPartyPlayer(player.getSingle(e).getUniqueId()));
    }
}
