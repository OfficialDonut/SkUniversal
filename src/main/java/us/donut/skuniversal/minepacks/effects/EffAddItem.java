package us.donut.skuniversal.minepacks.effects;

import at.pcgamingfreaks.Minepacks.Bukkit.API.Backpack;
import at.pcgamingfreaks.Minepacks.Bukkit.API.Callback;
import ch.njol.skript.Skript;
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

import static us.donut.skuniversal.minepacks.MinePacksHook.*;

@Name("MinePacks - Add Item")
@Description("Adds item to backpack of player.")
@Examples({"add dirt to backpack of player"})
public class EffAddItem extends Effect {

    static {
        Skript.registerEffect(EffAddItem.class,
                "add %itemstack% to [the] (back|mine)pack of %player%",
                "add %itemstack% to %player%'s (back|mine)pack");
    }

    private Expression<ItemStack> item;
    private Expression<Player> player;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult p) {
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
        if (player.getSingle(e) == null || item.getSingle(e) == null) return;
        minePacks.getBackpack(player.getSingle(e), new Callback<Backpack>() {
            @Override
            public void onResult(Backpack backpack) {
                backpack.getInventory().addItem(item.getSingle(e));
                backpack.save();
            }
            @Override
            public void onFail() {}
        });
    }
}
