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
import io.github.thebusybiscuit.slimefun4.core.researching.Research;
import org.bukkit.NamespacedKey;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

@Name("Slimefun - Create Research")
@Description("Creates Slimefun research.")
@Examples({"create new slimefun research with ID \"cool_research\" and 2048 with name \"Cool Dirt Research\" with level 25"})
public class EffCreateResearch extends Effect {

    static {
        Skript.registerEffect(EffCreateResearch.class, "create [a] [new] [Slimefun] research [with ID] %string% and %integer% (named|with name) %string% with (cost|level) %integer%");
    }

    private Expression<String> stringID;
    private Expression<Integer> numID;
    private Expression<String> name;
    private Expression<Integer> level;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult p) {
        if (!ScriptLoader.isCurrentEvent(SkriptStartEvent.class)) {
            Skript.error("You can not use Slimefun create research effect in any event but on skript load.");
            return false;
        }
        stringID = (Expression<String>) e[0];
        numID = (Expression<Integer>) e[1];
        name = (Expression<String>) e[2];
        level = (Expression<Integer>) e[3];
        return true;
    }
    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "create Slimefun research with ID " + stringID.toString(e, b);
    }

    @Override
    protected void execute(Event e) {
        if (stringID.getSingle(e) == null || numID.getSingle(e) == null || name.getSingle(e) == null || level.getSingle(e) == null) return;
        new Research(new NamespacedKey(Skript.getInstance(), stringID.getSingle(e)), numID.getSingle(e), name.getSingle(e), level.getSingle(e)).register();
    }
}
