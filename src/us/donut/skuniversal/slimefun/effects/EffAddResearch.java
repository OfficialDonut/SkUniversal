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
import io.github.thebusybiscuit.slimefun4.core.researching.Research;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import org.bukkit.event.Event;
import us.donut.skuniversal.slimefun.SlimefunHook;

import javax.annotation.Nullable;

@Name("Slimefun - Add Research")
@Description("Adds Slimefun research to an item.")
@Examples({"add the slimefun research with ID \"cool_research\" to the slimefun item \"COOL_DIRT\""})
public class EffAddResearch extends Effect {

    static {
        Skript.registerEffect(EffAddResearch.class, "add [the] [Slimefun] research [with ID] %string% to [the] [Slimefun] [item] [with ID] %string%");
    }

    private Expression<String> id;
    private Expression<String> item;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult p) {
        if (!ScriptLoader.isCurrentEvent(ScriptEvent.class)) {
            Skript.error("You can not use Slimefun add research expression in any event but on script load.");
            return false;
        }
        id = (Expression<String>) e[0];
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
        Research research = SlimefunHook.getResearch(id.getSingle(e));
        if (research != null) {
            research.addItems(SlimefunItem.getByID(item.getSingle(e)));
            research.register();
        }
    }
}
