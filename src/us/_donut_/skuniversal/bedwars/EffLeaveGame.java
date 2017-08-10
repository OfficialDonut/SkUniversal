package us._donut_.skuniversal.bedwars;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.MineHome.Bedwars.Game.GameManager;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

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
    public String toString(@Nullable Event paramEvent, boolean paramBoolean) {
        return "make player join Bedwars game";
    }

    @Override
    protected void execute(Event e) {
        GameManager.getGame(game.getSingle(e)).leave(player.getSingle(e));
    }
}
