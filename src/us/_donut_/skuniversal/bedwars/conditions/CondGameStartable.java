package us._donut_.skuniversal.bedwars.conditions;

import ch.njol.skript.Skript;
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

@Name("Bedwars - Is Game Startable")
@Description("Checks if a game is able to start.")
@Examples({"if bedwars game \"Game1\" is startable:"})
public class CondGameStartable extends Condition {

    static {
        Skript.registerCondition(CondGameStartable.class,
                "[the] [Bedwars] game [(named|with name)] %string% is (startable|able to start)",
                "[the] [Bedwars] game [(named|with name)] %string% can be started",
                "[the] [Bedwars] game [(named|with name)] %string% is(n't| not) (startable|able to start)",
                "[the] [Bedwars] game [(named|with name)] %string% can('t|not) be started");
    }

    private Expression<String> game;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult pr) {
        game = (Expression<String>) e[0];
        setNegated(matchedPattern == 2 || matchedPattern == 3);
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "Bedwars game " + game.toString(e, b) + " is startable";
    }

    @Override
    public boolean check(Event e) {
        if (game.getSingle(e) == null) return isNegated();
        return GameManager.getGame(game.getSingle(e)).isStartable() != isNegated();
    }
}