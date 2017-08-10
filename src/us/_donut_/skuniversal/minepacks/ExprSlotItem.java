package us._donut_.skuniversal.minepacks;

import at.pcgamingfreaks.MinePacks.MinePacks;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import javax.annotation.Nullable;

public class ExprSlotItem extends SimpleExpression<ItemStack> {
    private MinePacks mp = MinePacks.getInstance();
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
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult pr) {
        slotNum = (Expression<Integer>) e[0];
        player = (Expression<Player>) e[1];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean arg1) {
        return "item in slot";
    }

    @Override
    @Nullable
    protected ItemStack[] get(Event e) {
        return new ItemStack[]{mp.DB.getBackpack(player.getSingle(e)).getInventory().getItem(slotNum.getSingle(e))};
    }

    @Override
    public void change(Event e, Object[] delta, Changer.ChangeMode mode){
        if (mode == Changer.ChangeMode.SET) {
            mp.DB.getBackpack(player.getSingle(e)).getInventory().setItem(slotNum.getSingle(e), (ItemStack)delta[0]);
            mp.DB.getBackpack(player.getSingle(e)).save();
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            return CollectionUtils.array(ItemStack.class);
        }
        return null;
    }
}
