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
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import org.bukkit.Material;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import us.donut.skuniversal.slimefun.SlimefunHook;

import javax.annotation.Nullable;

@Name("Slimefun - Create Item")
@Description("Creates Slimefun item - Categories: https://pastebin.com/HF63gBBj | Recipe Types: https://pastebin.com/G5thupbG")
@Examples({"set {_recipe::*} to dirt, dirt, dirt, dirt, dirt, dirt, dirt, dirt, and dirt\n" +
        "create slimefun item dirt named \"Cool Dirt\" with lore \"Not your ordinary dirt!\" with id \"COOL_DIRT\" in category \"RESOURCES\" with recipe {_recipe::*} with recipe type \"ENHANCED_CRAFTING_TABLE\""})
public class EffCreateItem extends Effect {

    static {
        Skript.registerEffect(EffCreateItem.class, "create [a] [new] [Slimefun] item %itemstack% with id %string% in category %string% with recipe %itemstacks% with recipe type %string%");
    }

    private Expression<ItemStack> item;
    private Expression<String> id;
    private Expression<String> category;
    private Expression<ItemStack> recipe;
    private Expression<String> recipeType;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult p) {
        if (!ScriptLoader.isCurrentEvent(SkriptStartEvent.class)) {
            Skript.error("You can not use Slimefun create item effect in any event but on skript load.");
            return false;
        }
        item = (Expression<ItemStack>) e[0];
        id = (Expression<String>) e[1];
        category = (Expression<String>) e[2];
        recipe = (Expression<ItemStack>) e[3];
        recipeType = (Expression<String>) e[4];
        return true;
    }
    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "create Slimefun item " + item.toString(e, b) + " with id " + id.toString(e, b);
    }

    @Override
    protected void execute(Event e) {
        if (item.getSingle(e) == null || id.getSingle(e) == null || category.getSingle(e) == null || recipe.getSingle(e) == null || recipeType.getSingle(e) == null) return;
        RecipeType slimefunRecipeType;
        try {
            slimefunRecipeType = (RecipeType) RecipeType.class.getField(recipeType.getSingle(e)).get(null);
        } catch (NoSuchFieldException | IllegalAccessException exc) {
            slimefunRecipeType = RecipeType.ENHANCED_CRAFTING_TABLE;
        }
        ItemStack[] recipeItems = recipe.getArray(e);
        for (int i = 0; i < recipeItems.length; i++) {
            if (recipeItems[i].getType() == Material.AIR) {
                recipeItems[i] = null;
            }
        }
        new SlimefunItem(SlimefunHook.getCategory(category.getSingle(e).toLowerCase()), new SlimefunItemStack(id.getSingle(e), item.getSingle(e)), slimefunRecipeType, recipeItems).register(SlimefunHook.ADDON);
    }
}