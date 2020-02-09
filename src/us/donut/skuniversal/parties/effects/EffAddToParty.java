package us.donut.skuniversal.parties.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

import static us.donut.skuniversal.parties.PartiesHook.*;

@Name("Parties - Add Player to Party")
@Description("Adds a player to a party.")
@Examples({"add player to the party named \"cool\""})
public class EffAddToParty extends Effect {

    static {
        Skript.registerEffect(EffAddToParty.class, "add %player% to [the] party [(named|with name)] %string%");
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
        return "add player " + player.toString(e, b) + "to party named " + name.toString(e, b);
    }

    @Override
    protected void execute(Event e) {
        if (player.getSingle(e) == null || name.getSingle(e) == null) return;
        partiesAPI.getParty(name.getSingle(e)).addMember(partiesAPI.getPartyPlayer(player.getSingle(e).getUniqueId()));
    }
}
