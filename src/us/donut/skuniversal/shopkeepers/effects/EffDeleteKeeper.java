package us.donut.skuniversal.shopkeepers.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.nisovin.shopkeepers.api.shopkeeper.Shopkeeper;
import org.bukkit.event.Event;
import us.donut.skuniversal.shopkeepers.ShopkeepersHook;

import javax.annotation.Nullable;

@Name("Shopkeepers - Delete Shopkeeper")
@Description("Delete a shopkeeper.")
@Examples({"delete the shopkeeper named {shopkeepers::%player%}"})
public class EffDeleteKeeper extends Effect {

    static {
        Skript.registerEffect(EffDeleteKeeper.class, "(delete|remove) [the] [shop]keeper [with ID] %integer%");
    }

    private Expression<Integer> id;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult p) {
        id = (Expression<Integer>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "delete shopkeeper named " + id.toString(e, b);
    }

    @Override
    protected void execute(Event e) {
        Shopkeeper shopkeeper;
        if (id.getSingle(e) == null || (shopkeeper = ShopkeepersHook.shopkeeperRegistry.getShopkeeperById(id.getSingle(e))) == null) return;
        shopkeeper.delete();
    }
}