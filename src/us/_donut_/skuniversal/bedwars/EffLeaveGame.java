package us._donut_.skuniversal.bedwars;

import ch.njol.skript.Skript;
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

@Name("Bedwars - Leave Game")
@Description("Forces a player to leave a Bedwars game.")
@Examples({"force player to leave bedwars game (player's bedwars game)"})
public class EffLeaveGame extends Effect {

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
    public String toString(@Nullable Event e, boolean paramBoolean) {
        return "make player " + player.getSingle(e) + " leave Bedwars game " + game.getSingle(e);
    }

    @Override
    protected void execute(Event e) {
        if (player.getSingle(e) != null) {
            if (GameManager.getGame(game.getSingle(e)) != null) {
                GameManager.getGame(game.getSingle(e)).leave(player.getSingle(e));
            } else {
                Skript.error("Must provide a Bedwars game, please refer to the syntax");
            }
        } else {
            Skript.error("Must provide a player, please refer to the syntax");
        }
    }
}