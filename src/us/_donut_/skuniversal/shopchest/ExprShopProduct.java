package us._donut_.skuniversal.shopchest;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import de.epiceric.shopchest.shop.Shop;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;

@Name("ShopChest - Shop Product")
@Description("Returns the product of a shop.")
@Examples({"send \"%the product of shop with id (shop at player)%\""})
public class ExprShopProduct extends SimpleExpression<ItemStack> {

    private Expression<Number> id;

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
        id = (Expression<Number>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "the product of the shop with id " + id.toString(e, b);
    }

    @Override
    @Nullable
    protected ItemStack[] get(Event e) {
        Shop shop = ShopChestRegister.getShop(id.getSingle(e).intValue());
        return shop == null ? null : new ItemStack[]{shop.getProduct()};
    }
}