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
import com.nisovin.shopkeepers.api.shopkeeper.player.PlayerShopkeeper;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

@Name("Shopkeepers - Shopkeepers of Player")
@Description("Returns the IDs of the shopkeepers of a player.")
@Examples({"send \"%the shopkeepers of player\""})
public class ExprKeepersOfPlayer extends SimpleExpression<Integer> {

    private Expression<OfflinePlayer> player;

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
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult pr) {
        player = (Expression<OfflinePlayer>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "shopkeepers of player " + player.toString(e, b);
    }

    @Override
    @Nullable
    protected Integer[] get(Event e) {
        return ShopkeepersAPI.getShopkeeperRegistry().getAllShopkeepers().stream().filter(keeper -> keeper instanceof PlayerShopkeeper && ((PlayerShopkeeper) keeper).getOwnerUUID().equals(player.getSingle(e).getUniqueId())).map(Shopkeeper::getId).toArray(Integer[]::new);
    }
}