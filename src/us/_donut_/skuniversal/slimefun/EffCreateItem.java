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
import me.mrCookieSlime.Slimefun.Lists.Categories;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;

@Name("Slimefun - Create Item")
@Description("Creates Slimefun item - Categories: https://pastebin.com/HF63gBBj | Recipe Types: https://pastebin.com/G5thupbG")
@Examples({"set {_recipe::*} to dirt, dirt, dirt, dirt, dirt, dirt, dirt, dirt, and dirt\n" +
        "create slimefun item dirt named \"Cool Dirt\" with lore \"Not your ordinary dirt!\" with id \"COOL_DIRT\" in category \"RESOURCES\" with recipe {_recipe::*} with recipe type \"ENHANCED_CRAFTING_TABLE\""})
public class EffCreateItem extends Effect {

    private Expression<ItemStack> item;
    private Expression<String> id;
    private Expression<String> category;
    private Expression<ItemStack> recipe;
    private Expression<String> recipeType;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult p) {
        if (!ScriptLoader.isCurrentEvent(ScriptEvent.class)) {
            Skript.error("You can not use Slimefun create item expression in any event but on script load.");
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
    public String toString(@Nullable Event e, boolean paramBoolean) {
        return "create Slimefun item";
    }

    @Override
    protected void execute(Event e) {
        if (item != null && id != null && category != null && recipe != null && recipeType != null) {
            Category actualCategory;
            try {
                actualCategory = (Category) Categories.class.getField(category.getSingle(e)).get(Category.class);
            } catch (NoSuchFieldException | IllegalAccessException exc) {
                actualCategory = Categories.MISC;
            }
            RecipeType actualRecipeType;
            try {
                actualRecipeType = (RecipeType) RecipeType.class.getField(recipeType.getSingle(e)).get(RecipeType.class);
            } catch (NoSuchFieldException | IllegalAccessException exc) {
                actualRecipeType = RecipeType.ENHANCED_CRAFTING_TABLE;
            }
            SlimefunItem slimefunItem = new SlimefunItem(actualCategory, item.getSingle(e), id.getSingle(e), actualRecipeType, recipe.getArray(e));
            slimefunItem.register();
        } else {
            Skript.error("Must provide a non-null value, please refer to the syntax");
        }
    }
}