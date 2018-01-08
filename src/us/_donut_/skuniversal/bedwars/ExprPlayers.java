package us._donut_.skuniversal.bedwars;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import me.MineHome.Bedwars.Game.GameManager;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import javax.annotation.Nullable;
import java.util.List;

@Name("Bedwars - Players in Game")
@Description("Returns a list of all players in a Bedwars game.")
@Examples({"loop players in bedwars game {_game}:",
		"\tforce loop-player to leave bedwars game {_game}"})
public class ExprPlayers extends SimpleExpression<Player> {

    private Expression<String> game;

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends Player> getReturnType() {
        return Player.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult pr) {
        game = (Expression<String>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean arg1) {
        return "players in Bedwars game " + game.getSingle(e);
    }

    @Override
    @Nullable
    protected Player[] get(Event e) {
        if (GameManager.getGame(game.getSingle(e)) != null) {
            List<Player> players = GameManager.getGame(game.getSingle(e)).getPlayers();
            return players.toArray(new Player[players.size()]);
        } else {
            Skript.error("Must provide a Bedwars game, please refer to the syntax");
            return null;
        }
    }
}