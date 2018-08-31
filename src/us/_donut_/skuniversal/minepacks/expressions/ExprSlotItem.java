package us._donut_.skuniversal.minepacks.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import javax.annotation.Nullable;

import static us._donut_.skuniversal.minepacks.MinePacksHook.*;

@Name("MinePacks - Backpack SLot")
@Description("Returns the item in a backpack slot.")
@Examples({"send \"%the item in slot 5 of backpack of player%\""})
public class ExprSlotItem extends SimpleExpression<ItemStack> {

    static {
        Skript.registerExpression(ExprSlotItem.class, ItemStack.class, ExpressionType.COMBINED,
                "[[the] item in] slot %integer% of [the] (back|mine)pack of %player%",
                "[[the] item in] slot %integer% of %player%'s (back|mine)pack");
    }

    private Expression<Integer> slotNum;
    private Expression<Player> player;

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
        slotNum = (Expression<Integer>) e[0];
        player = (Expression<Player>) e[1];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "item in slot " + slotNum.toString(e, b) + " of backpack of player " + player.toString(e, b);
    }

    @Override
    @Nullable
    protected ItemStack[] get(Event e) {
        if (player.getSingle(e) == null || slotNum.getSingle(e) == null) return null;
        return new ItemStack[]{database.getBackpack(player.getSingle(e)).getInventory().getItem(slotNum.getSingle(e))};
    }

    @Override
    public void change(Event e, Object[] delta, Changer.ChangeMode mode){
        if (mode == Changer.ChangeMode.SET) {
            if (player.getSingle(e) == null) return;
            database.getBackpack(player.getSingle(e)).getInventory().setItem(slotNum.getSingle(e), (ItemStack)delta[0]);
            database.getBackpack(player.getSingle(e)).save();
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
        return (mode == Changer.ChangeMode.SET) ? CollectionUtils.array(ItemStack.class) : null;
    }
}
