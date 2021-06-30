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
import me.mrCookieSlime.Slimefun.Objects.Category;
import org.bukkit.NamespacedKey;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import us.donut.skuniversal.slimefun.SlimefunHook;

import javax.annotation.Nullable;

@Name("Slimefun - Create Category")
@Description("Creates Slimefun category (it will not show up in the guide unless you create at least 1 item for it)")
@Examples("create slimefun category with ID \"cool_stuff\" with menu item dirt named \"Cool Stuff\" with priority 0")
public class EffCreateCategory extends Effect {

    static {
        Skript.registerEffect(EffCreateCategory.class, "create [a] [new] [Slimefun] category [with ID] %string% with [menu] item %itemstack% with (level|priority|tier) %integer%");
    }

    private Expression<String> id;
    private Expression<ItemStack> item;
    private Expression<Integer> level;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult p) {
        if (!ScriptLoader.isCurrentEvent(SkriptStartEvent.class)) {
            Skript.error("You can not use Slimefun create category effect in any event but on skript load.");
            return false;
        }
        id = (Expression<String>) e[0];
        item = (Expression<ItemStack>) e[1];
        level = (Expression<Integer>) e[2];
        return true;
    }
    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "create Slimefun category " + id.toString(e, b) + " with item " + item.toString(e, b) + " with level " + level.toString(e, b);
    }

    @Override
    protected void execute(Event e) {
        if (id.getSingle(e) == null || item.getSingle(e) == null || level.getSingle(e) == null) return;
        new Category(new NamespacedKey(Skript.getInstance(), id.getSingle(e).toLowerCase()), item.getSingle(e), level.getSingle(e)).register(SlimefunHook.ADDON);
    }
}
