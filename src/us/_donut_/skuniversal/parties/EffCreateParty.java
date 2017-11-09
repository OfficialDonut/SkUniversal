package us._donut_.skuniversal.parties;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.alessiodp.parties.utils.api.PartiesAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

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
    public String toString(@Nullable Event e, boolean paramBoolean) {
        return "create party named " + name.getSingle(e) + " with leader " + player.getSingle(e);
    }

    @Override
    protected void execute(Event e) {
        if (player.getSingle(e) != null) {
            if (player.getSingle(e) != null) {
                PartiesAPI parties = new PartiesAPI();
                parties.createParty(player.getSingle(e), name.getSingle(e));
            } else {
                Skript.error("Must provide a string, please refer to the syntax");
            }
        } else {
            Skript.error("Must provide a player, please refer to the syntax");
        }
    }
}
