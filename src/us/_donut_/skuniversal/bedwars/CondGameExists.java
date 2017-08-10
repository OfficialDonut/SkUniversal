package us._donut_.skuniversal.bedwars;

import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.MineHome.Bedwars.Game.GameManager;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

public class CondGameExists extends Condition {

    private Expression<String> game;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult pr) {
        game = (Expression<String>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "Bedwars game exists";
    }

    @Override
    public boolean check(Event e) {
        return GameManager.existGame(game.getSingle(e));
    }
}
