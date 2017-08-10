package us._donut_.skuniversal.bedwars;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.MineHome.Bedwars.Game.GameManager;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import javax.annotation.Nullable;
import java.util.List;

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
        return "players in Bedwars game";
    }

    @Override
    @Nullable
    protected Player[] get(Event e) {
        List<Player> players = GameManager.getGame(game.getSingle(e)).getPlayers();
        return players.toArray(new Player[players.size()]);
    }
}
