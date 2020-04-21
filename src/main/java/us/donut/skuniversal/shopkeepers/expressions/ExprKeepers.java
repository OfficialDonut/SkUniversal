package us.donut.skuniversal.shopkeepers.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.nisovin.shopkeepers.api.shopkeeper.Shopkeeper;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

import static us.donut.skuniversal.shopkeepers.ShopkeepersHook.*;

@Name("Shopkeepers - All Shopkeepers")
@Description("Returns the names of all shopkeepers.")
@Examples({"send \"%the IDs of all shopkeepers\""})
public class ExprKeepers extends SimpleExpression<Integer> {

    static {
        Skript.registerExpression(ExprKeepers.class, Integer.class, ExpressionType.SIMPLE, "[(all [[of] the]|the)] [IDs of all [the]] [existing] [shop]keepers");
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends Integer> getReturnType() {
        return Integer.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult pr) {
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "all the shopkeepers";
    }

    @Override
    @Nullable
    protected Integer[] get(Event e) {
        return shopkeeperRegistry.getAllShopkeepers().stream().map(Shopkeeper::getId).toArray(Integer[]::new);
    }
}
