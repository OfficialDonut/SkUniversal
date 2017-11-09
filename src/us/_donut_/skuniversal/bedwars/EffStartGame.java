package us._donut_.skuniversal.bedwars;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.MineHome.Bedwars.Game.GameManager;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

public class EffStartGame extends Effect {

    private Expression<String> game;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult p) {
        game = (Expression<String>) e[0];
        return true;
    }
    @Override
    public String toString(@Nullable Event e, boolean paramBoolean) {
        return "start Bedwars game " + game.getSingle(e);
    }

    @Override
    protected void execute(Event e) {
        if (GameManager.getGame(game.getSingle(e)) != null) {
            GameManager.getGame(game.getSingle(e)).start();
        } else {
            Skript.error("Must provide a Bedwars game, please refer to the syntax");
        }
    }
}