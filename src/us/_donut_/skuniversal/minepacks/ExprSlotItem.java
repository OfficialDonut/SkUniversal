package us._donut_.skuniversal.minepacks;

import at.pcgamingfreaks.MinePacks.MinePacks;
import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import javax.annotation.Nullable;

@Name("MinePacks - Backpack SLot")
@Description("Returns the item in a backpack slot.")
@Examples({"send \"%the item in slot 5 of backpack of player%\""})
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
        return "item in slot " + slotNum + " of backpack of player " + player.getSingle(e);
    }

    @Override
    @Nullable
    protected ItemStack[] get(Event e) {
        if (player.getSingle(e) != null) {
            if (slotNum != null) {
                return new ItemStack[]{mp.DB.getBackpack(player.getSingle(e)).getInventory().getItem(slotNum.getSingle(e))};
            } else {
                Skript.error("Must provide an integer, please refer to the syntax");
                return null;
            }
        } else {
            Skript.error("Must provide a player, please refer to the syntax");
            return null;
        }
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
