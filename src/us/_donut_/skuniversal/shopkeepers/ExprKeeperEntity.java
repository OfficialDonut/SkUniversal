package us._donut_.skuniversal.shopkeepers;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.nisovin.shopkeepers.api.ShopkeepersAPI;
import com.nisovin.shopkeepers.api.shopkeeper.Shopkeeper;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

@Name("Shopkeepers - Shopkeeper ID of Entity")
@Description("Returns the shopkeeper ID of an entity.")
@Examples({"send \"%the shopkeeper ID of event-entity\""})
public class ExprKeeperEntity extends SimpleExpression<Integer> {

    private Expression<Entity> entity;

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Integer> getReturnType() {
        return Integer.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult pr) {
        entity = (Expression<Entity>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "shopkeeper ID of " + entity.toString(e, b);
    }

    @Override
    @Nullable
    protected Integer[] get(Event e) {
        Shopkeeper shopkeeper = ShopkeepersAPI.getShopkeeperRegistry().getShopkeeperByEntity(entity.getSingle(e));
        return new Integer[]{shopkeeper == null ? null : shopkeeper.getId()};
    }
}
