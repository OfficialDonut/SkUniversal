package us._donut_.skuniversal.bedwars;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import me.MineHome.Bedwars.Game.GameManager;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

@Name("Bedwars - Join Game")
@Description("Forces a player to join a Bedwars game.")
@Examples({"make player join bedwars game text-argument"})
public class EffJoinGame extends Effect {

    private Expression<Player> player;
    private Expression<String> game;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult p) {
        player = (Expression<Player>) e[0];
        game = (Expression<String>) e[1];
        return true;
    }
    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "make player " + player.toString(e, b) + " join Bedwars game " + game.toString(e, b);
    }

    @Override
    protected void execute(Event e) {
        GameManager.getGame(game.getSingle(e)).join(player.getSingle(e));
    }
}