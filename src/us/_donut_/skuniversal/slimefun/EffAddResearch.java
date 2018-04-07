package us._donut_.skuniversal.slimefun;

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

    private Expression<Integer> id;
    private Expression<String> item;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult p) {
        if (!ScriptLoader.isCurrentEvent(ScriptEvent.class)) {
            Skript.error("You can not use Slimefun add research expression in any event but on script load.");
            return false;
        }
        id = (Expression<Integer>) e[0];
        item = (Expression<String>) e[1];
        return true;
    }
    @Override
    public String toString(@Nullable Event e, boolean paramBoolean) {
        return "add Slimefun research";
    }

    @Override
    protected void execute(Event e) {
        if (id != null && item != null) {
            Research research = Research.getByID(id.getSingle(e));
            research.addItems(SlimefunItem.getByID(item.getSingle(e)));
            research.register();
        } else {
            Skript.error("Must provide a non-null value, please refer to the syntax");
        }
    }
}
