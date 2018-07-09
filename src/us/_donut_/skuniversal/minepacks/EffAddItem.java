package us._donut_.skuniversal.minepacks;

import at.pcgamingfreaks.MinePacks.MinePacks;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import javax.annotation.Nullable;

@Name("MinePacks - Add Item")
@Description("Adds item to backpack of player.")
@Examples({"add dirt to backpack of player"})
public class EffAddItem extends Effect {

    private Expression<ItemStack> item;
    private Expression<Player> player;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult p) {
        item = (Expression<ItemStack>) e[0];
        player = (Expression<Player>) e[1];
        return true;
    }
    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "add item " + item.toString(e, b) + " to backpack of player " + player.toString(e, b);
    }

    @Override
    protected void execute(Event e) {
        MinePacks mp = MinePacks.getInstance();
        mp.DB.getBackpack(player.getSingle(e)).getInventory().addItem(item.getSingle(e));
        mp.DB.getBackpack(player.getSingle(e)).save();
    }
}
