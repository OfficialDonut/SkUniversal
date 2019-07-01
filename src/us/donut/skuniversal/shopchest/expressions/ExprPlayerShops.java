package us.donut.skuniversal.shopchest.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import de.epiceric.shopchest.shop.Shop;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import us.donut.skuniversal.shopchest.ShopChestHook;

import javax.annotation.Nullable;
import java.util.Arrays;

@Name("ShopChest - All Shops of Player")
@Description("Returns the IDs of all shops of a  Player.")
@Examples({"send \"%all shops of player%\""})
public class ExprPlayerShops extends SimpleExpression<Number> {

    static {
        Skript.registerExpression(ExprPlayerShops.class, Number.class, ExpressionType.COMBINED,
                "[(all [[of] the]|the)] [IDs of all [the]] [ShopChest] shops of %offlineplayer%",
                "[all of] [[the] IDs of [all]] %offlineplayer%'s [ShopChest] shops");
    }

    private Expression<Player> player;

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends Number> getReturnType() {
        return Number.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult pr) {
        player = (Expression<Player>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "ids of all shops of player " + player.toString(e, b);
    }

    @Override
    @Nullable
    protected Number[] get(Event e) {
        if (player.getSingle(e) == null) return null;
        return Arrays.stream(ShopChestHook.shopUtils.getShops()).filter(shop -> shop.getVendor().equals(player.getSingle(e))).map(Shop::getID).toArray(Number[]::new);
    }
}
