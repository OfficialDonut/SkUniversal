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

@Name("Parties - Create Party")
@Description("Creates a party.")
@Examples({"create party named \"cool\" with leader player"})
public class EffCreateParty extends Effect {

    private Expression<Player> player;
    private Expression<String> name;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult p) {
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
        new PartiesAPI().createParty(player.getSingle(e), name.getSingle(e));
    }
}
