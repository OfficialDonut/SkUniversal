package us._donut_.skuniversal.minepacks;

import at.pcgamingfreaks.MinePacks.MinePacks;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import javax.annotation.Nullable;

@Name("MinePacks - Backpack Items")
@Description("Returns the items in a backpack.")
@Examples({"send \"%the items in the backpack of player%\""})
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
    public String toString(@Nullable Event e, boolean b) {
        return "contents of backpack of player " + player.toString(e, b);
    }

    @Override
    @Nullable
    protected ItemStack[] get(Event e) {
        MinePacks mp = MinePacks.getInstance();
        return mp.DB.getBackpack(player.getSingle(e)).getInventory().getStorageContents();
    }
}