package us._donut_.skuniversal.bedwars;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import javax.annotation.Nullable;
import me.MineHome.Bedwars.Game.GameManager;

@Name("Bedwars - Is Playing")
@Description("Checks if a player is in a Bedwars game.")
@Examples({"if {_player} is playing in a Bedwars game:"})
public class CondInGame extends Condition {

    private Expression<Player> player;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult pr) {
        player = (Expression<Player>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "player " + player.getSingle(e) + " is in Bedwars game";
    }

    @Override
    public boolean check(Event e) {
        if (player.getSingle(e) != null) {
            return GameManager.inGame(player.getSingle(e));
        } else {
            Skript.error("Must provide a player, please refer to the syntax");
            return false;
        }
    }
}
