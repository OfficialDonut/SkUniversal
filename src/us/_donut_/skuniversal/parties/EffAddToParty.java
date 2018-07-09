package us._donut_.skuniversal.parties;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.alessiodp.parties.utils.api.PartiesAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

@Name("Parties - Add Player to Party")
@Description("Adds a player to a party.")
@Examples({"add player to the party named \"cool\""})
public class EffAddToParty extends Effect {

    private Expression<Player> player;
    private Expression<String> name;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult p) {
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
        new PartiesAPI().addPlayerInParty(player.getSingle(e), name.getSingle(e));
    }
}
