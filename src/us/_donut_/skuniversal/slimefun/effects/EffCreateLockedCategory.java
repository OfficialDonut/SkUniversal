package us._donut_.skuniversal.slimefun.effects;

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
import me.mrCookieSlime.Slimefun.Lists.Categories;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.LockedCategory;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import us._donut_.skuniversal.slimefun.SlimefunHook;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

@Name("Slimefun - Create Locked Category")
@Description("Creates Slimefun locked category (it will not show up in the guide unless you create at least 1 item for it)")
@Examples("create locked slimefun category named \"Locked Cool Stuff\" with menu item dirt named \"Locked Cool Stuff\" with priority 0 with required categories \"RESOURCES\"")
public class EffCreateLockedCategory extends Effect {

    static {
        Skript.registerEffect(EffCreateLockedCategory.class, "create [a] [new] locked [Slimefun] category [(named|with name)] %string% with [menu] item %itemstack% with (level|priority) %integer% with required categor(y|ies) %strings%");
    }

    private Expression<String> name;
    private Expression<ItemStack> item;
    private Expression<Integer> level;
    private Expression<String> categories;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult p) {
        if (!ScriptLoader.isCurrentEvent(SkriptStartEvent.class)) {
            Skript.error("You can not use Slimefun create locked category effect in any event but on skript load.");
            return false;
        }
        name = (Expression<String>) e[0];
        item = (Expression<ItemStack>) e[1];
        level = (Expression<Integer>) e[2];
        categories = (Expression<String>) e[3];
        return true;
    }
    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "create Slimefun category " + name.toString(e, b) + " with item " + item.toString(e, b) + " with level " + level.toString(e, b) + " with required categories " + categories.toString(e, b);
    }

    @Override
    protected void execute(Event e) {
        if (name.getSingle(e) == null || item.getSingle(e) == null || level.getSingle(e) == null || categories.getSingle(e) == null) return;
        List<Category> requiredCategories = new ArrayList<>();
        for (String categoryString : categories.getArray(e)) {
            try {
                requiredCategories.add((Category) Categories.class.getField(categoryString).get(Category.class));
            } catch (NoSuchFieldException | IllegalAccessException exc) {
                if (SlimefunHook.customCategories.containsKey(categoryString.toLowerCase())) {
                    requiredCategories.add(SlimefunHook.customCategories.get(categoryString.toLowerCase()));
                }
            }
        }
        SlimefunHook.customCategories.put(name.getSingle(e).toLowerCase(), new LockedCategory(item.getSingle(e), level.getSingle(e), requiredCategories.toArray(new Category[0])));
    }
}
