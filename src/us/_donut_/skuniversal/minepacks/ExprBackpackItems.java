package us._donut_.skuniversal.minepacks;

import at.pcgamingfreaks.MinePacks.MinePacks;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExprBackpackItems extends SimpleExpression<ItemStack> {

    private Expression<Player> player;

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends ItemStack> getReturnType() {
        return ItemStack.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult pr) {
        player = (Expression<Player>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean arg1) {
        return "contents of player's backpack";
    }

    @Override
    @Nullable
    protected ItemStack[] get(Event e) {
        MinePacks mp = MinePacks.getInstance();
        List<ItemStack> items = new ArrayList<>(Arrays.asList(mp.DB.getBackpack(player.getSingle(e)).getInventory().getStorageContents()));
        return items.toArray(new ItemStack[items.size()]);
    }
}