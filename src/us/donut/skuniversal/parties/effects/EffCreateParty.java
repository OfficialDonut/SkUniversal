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

import static us.donut.skuniversal.parties.PartiesHook.partiesAPI;

@Name("Parties - Create Party")
@Description("Creates a party.")
@Examples({"create party named \"cool\" with leader player"})
public class EffCreateParty extends Effect {

    static {
        Skript.registerEffect(EffCreateParty.class, "(create|make) [a] party [(named|with name)] %string% with leader %player%");
    }

    private Expression<Player> player;
    private Expression<String> name;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult p) {
        name = (Expression<String>) e[0];
        player = (Expression<Player>) e[1];
        return true;
    }
    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "create party named " + name.toString(e, b) + " with leader " + player.toString(e, b);
    }

    @Override
    protected void execute(Event e) {
        if (player.getSingle(e) == null || name.getSingle(e) == null) return;
        partiesAPI.createParty(name.getSingle(e), partiesAPI.getPartyPlayer(player.getSingle(e).getUniqueId()));
    }
}
