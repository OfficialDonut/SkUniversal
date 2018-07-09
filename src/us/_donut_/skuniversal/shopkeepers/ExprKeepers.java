package us._donut_.skuniversal.shopkeepers;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.nisovin.shopkeepers.Shopkeeper;
import com.nisovin.shopkeepers.ShopkeepersPlugin;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

@Name("Shopkeepers - All Shopkeepers")
@Description("Returns the names of all shopkeepers.")
@Examples({"send \"%the names of all shopkeepers\""})
public class ExprKeepers extends SimpleExpression<String> {

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult pr) {
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "all the shopkeepers";
    }

    @Override
    @Nullable
    protected String[] get(Event e) {
        return ShopkeepersPlugin.getInstance().getAllShopkeepers().stream().map(Shopkeeper::getName).toArray(String[]::new);
    }
}
