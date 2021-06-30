package us.donut.skuniversal.slimefun.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;

@Name("Slimefun - Slimefun Item")
@Description("Returns a Slimefun item.")
@Examples({"give slimefun item \"CHEESE\" to player"})
public class ExprSlimefunItem extends SimpleExpression<ItemStack> {

    static {
        Skript.registerExpression(ExprSlimefunItem.class, ItemStack.class, ExpressionType.COMBINED, "[the] Slimefun item [with ID] %string%");
    }

    private Expression<String> id;

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends ItemStack> getReturnType() {
        return ItemStack.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult pr) {
        id = (Expression<String>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean arg1) {
        return "the Slimefun item with ID " + id.getSingle(e);
    }

    @Override
    @Nullable
    protected ItemStack[] get(Event e) {
        if (id.getSingle(e) == null) return null;
        return new ItemStack[]{SlimefunItem.getByID(id.getSingle(e)).getItem()};
    }
}
