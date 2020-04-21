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
import com.nisovin.shopkeepers.api.shopkeeper.player.PlayerShopkeeper;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

import static us.donut.skuniversal.shopkeepers.ShopkeepersHook.*;

@Name("Shopkeepers - Shopkeepers of Player")
@Description("Returns the IDs of the shopkeepers of a player.")
@Examples({"send \"%the shopkeepers of player\""})
public class ExprKeepersOfPlayer extends SimpleExpression<Integer> {

    static {
        Skript.registerExpression(ExprKeepersOfPlayer.class, Integer.class, ExpressionType.COMBINED,
                "[(all [[of] the]|the)] [IDs of all [the]] [shop]keepers of %offlineplayer%",
                "[(all [[of] the]|the)] [IDs of [all]] %offlineplayer%'s [shop]keepers");
    }

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
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult pr) {
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
        if (player.getSingle(e) == null) return null;
        return shopkeeperRegistry.getAllShopkeepers()
                .stream()
                .filter(keeper -> keeper instanceof PlayerShopkeeper && ((PlayerShopkeeper) keeper).getOwnerUUID().equals(player.getSingle(e).getUniqueId()))
                .map(Shopkeeper::getId)
                .toArray(Integer[]::new);
    }
}