package us.donut.skuniversal.slimefun.effects;

import ch.njol.skript.ScriptLoader;
import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.events.bukkit.SkriptStartEvent;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.mrCookieSlime.Slimefun.Objects.Research;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

@Name("Slimefun - Create Research")
@Description("Creates Slimefun research.")
@Examples({"create new slimefun research with id 2048 with name \"Cool Dirt Research\" with level 25"})
public class EffCreateResearch extends Effect {

    static {
        Skript.registerEffect(EffCreateResearch.class, "create [a] [new] [Slimefun] research [with ID] %integer% (named|with name) %string% with (cost|level) %integer%");
    }

    private Expression<Integer> id;
    private Expression<String> name;
    private Expression<Integer> level;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult p) {
        if (!ScriptLoader.isCurrentEvent(SkriptStartEvent.class)) {
            Skript.error("You can not use Slimefun create research effect in any event but on skript load.");
            return false;
        }
        id = (Expression<Integer>) e[0];
        name = (Expression<String>) e[1];
        level = (Expression<Integer>) e[2];
        return true;
    }
    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "create Slimefun research with id " + id.toString(e, b);
    }

    @Override
    protected void execute(Event e) {
        if (name.getSingle(e) == null || id.getSingle(e) == null || level.getSingle(e) == null) return;
        Research research = new Research(id.getSingle(e), name.getSingle(e), level.getSingle(e));
        research.register();
    }
}
