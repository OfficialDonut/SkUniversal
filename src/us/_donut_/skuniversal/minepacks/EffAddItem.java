package us._donut_.skuniversal.minepacks;

import at.pcgamingfreaks.MinePacks.MinePacks;
import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import javax.annotation.Nullable;

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
    public String toString(@Nullable Event e, boolean paramBoolean) {
        return "add item " + item.getSingle(e) + " to backpack of player " + player.getSingle(e);
    }

    @Override
    protected void execute(Event e) {
        if (player.getSingle(e) != null) {
            if (item.getSingle(e) != null) {
                MinePacks mp = MinePacks.getInstance();
                mp.DB.getBackpack(player.getSingle(e)).getInventory().addItem(item.getSingle(e));
                mp.DB.getBackpack(player.getSingle(e)).save();
            } else {
                Skript.error("Must provide an item, please refer to the syntax");
            }
        } else {
            Skript.error("Must provide a player, please refer to the syntax");
        }
    }
}
