package us.donut.skuniversal.skywars_daboross.conditions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import us.donut.skuniversal.skywars_daboross.SkyWarsDaborossHook;

import javax.annotation.Nullable;

@Name("SkyWars (Daboross) - Is Queue Full")
@Description("Checks if the game queue is full.")
@Examples({"if the skywars game queue is full:"})
public class CondQueueFull extends Condition {

    static {
        Skript.registerCondition(CondQueueFull.class, "[the] [SkyWars] [game] queue is full",
                "[the] [SkyWars] [game] queue is(n't| not) full");
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult pr) {
        setNegated(matchedPattern == 1);
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "queue is full";
    }

    @Override
    public boolean check(Event e) {
        return SkyWarsDaborossHook.skyWars.getGameQueue().isQueueFull();
    }
}
