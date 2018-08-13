package us._donut_.skuniversal.shopkeepers;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import com.nisovin.shopkeepers.api.ShopkeepersAPI;
import com.nisovin.shopkeepers.api.shopkeeper.Shopkeeper;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

@Name("Shopkeepers - Shopkeeper Name")
@Description("Returns the name of a shopkeeper.")
@Examples({"send \"%the name of the shopkeeper with id 1\""})
public class ExprKeeperName extends SimpleExpression<String> {

    private Expression<Integer> id;

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult pr) {
        id = (Expression<Integer>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "name of shopkeeper with ID " + id.toString(e, b);
    }

    @Override
    @Nullable
    protected String[] get(Event e) {
        Shopkeeper shopkeeper = ShopkeepersAPI.getShopkeeperRegistry().getShopkeeperById(id.getSingle(e));
        return new String[]{shopkeeper == null ? null : shopkeeper.getName()};
    }

    @Override
    public void change(Event e, Object[] delta, Changer.ChangeMode mode){
        if (mode == Changer.ChangeMode.SET) {
            Shopkeeper shopkeeper = ShopkeepersAPI.getShopkeeperRegistry().getShopkeeperById(id.getSingle(e));
            if (shopkeeper != null)
                shopkeeper.setName((String) delta[0]);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            return CollectionUtils.array(String.class);
        }
        return null;
    }
}
