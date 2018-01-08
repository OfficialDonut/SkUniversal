package us._donut_.skuniversal.bedwars;

import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import me.MineHome.Bedwars.Game.GameManager;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

@Name("Bedwars - Is Game Running")
@Description("Checks if a Bedwars game is running.")
@Examples({"if bedwars game text-argument running:"})
public class CondGameRunning extends Condition {

    private Expression<String> game;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult pr) {
        game = (Expression<String>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "Bedwars game " + game.getSingle(e) + " is running";
    }

    @Override
    public boolean check(Event e) {
        return GameManager.getGame(game.getSingle(e)).isRunning();
    }
}