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

@Name("Bedwars - Is Game Running")
@Description("Checks if a Bedwars game is running.")
@Examples({"if bedwars game text-argument running:"})
public class CondGameRunning extends Condition {

    static {
        Skript.registerCondition(CondGameRunning.class,
                "[the] [Bedwars] game [(named|with name)] %string% is [currently] (running|active)",
                "[the] [Bedwars] game [(named|with name)] %string% is(n't| not) [currently] (running|active)");
    }

    private Expression<String> game;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult pr) {
        game = (Expression<String>) e[0];
        setNegated(matchedPattern == 1);
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "Bedwars game " + game.toString(e, b) + " is running";
    }

    @Override
    public boolean check(Event e) {
        if (game.getSingle(e) == null) return isNegated();
        return GameManager.getGame(game.getSingle(e)).isRunning() != isNegated();
    }
}