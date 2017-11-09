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
    public String toString(@Nullable Event e, boolean paramBoolean) {
        return "add player " + player.getSingle(e) + "to party named " + name.getSingle(e) ;
    }

    @Override
    protected void execute(Event e) {
        if (player.getSingle(e) != null) {
            if (name.getSingle(e) != null) {
                PartiesAPI parties = new PartiesAPI();
                parties.addPlayerInParty(player.getSingle(e), name.getSingle(e));
            } else {
                Skript.error("Must provide a string, please refer to the syntax");
            }
        } else {
            Skript.error("Must provide a player, please refer to the syntax");
        }
    }
}
