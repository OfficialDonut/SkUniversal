package us.donut.skuniversal.slimefun.effects;

import ch.njol.skript.ScriptLoader;
import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.events.bukkit.ScriptEvent;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.mrCookieSlime.Slimefun.Objects.Research;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

@Name("Slimefun - Add Research")
@Description("Adds Slimefun research to an item.")
@Examples({"add the slimefun research with id 2048 to the slimefun item named  \"COOL_DIRT\""})
public class EffAddResearch extends Effect {

    static {
        Skript.registerEffect(EffAddResearch.class, "add [the] [Slimefun] research [with ID] %integer% to [the] [Slimefun] [item] [(named|with name)] %string%");
    }

    private Expression<Integer> id;
    private Expression<String> item;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult p) {
        if (!ScriptLoader.isCurrentEvent(ScriptEvent.class)) {
            Skript.error("You can not use Slimefun add research expression in any event but on script load.");
            return false;
        }
        id = (Expression<Integer>) e[0];
        item = (Expression<String>) e[1];
        return true;
    }
    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "add Slimefun research " + id.toString(e, b) + " to item " + item.toString(e, b);
    }

    @Override
    protected void execute(Event e) {
        if (id.getSingle(e) == null || item.getSingle(e) == null) return;
        Research research = Research.getByID(id.getSingle(e));
        research.addItems(SlimefunItem.getByID(item.getSingle(e)));
        research.register();
    }
}
