package us.donut.skuniversal.cannons.expressions;

import at.pavlov.cannons.cannon.CannonManager;
import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

@Name("Cannons - Cannons of Player")
@Description("Returns the IDs of the cannons a player has created.")
@Examples({"send \"%player% has built %cannons of player%\""})
public class ExprCannonsOfPlayer extends SimpleExpression<String> {

    static {
        Skript.registerExpression(ExprCannonsOfPlayer.class, String.class, ExpressionType.COMBINED,
                "[(all [[of] the]|the)]] [IDs of [the]] cannons [of] %offlineplayer%",
                "[all of] [[the] IDs of]] %offlineplayer%'s cannons");
    }

    private Expression<OfflinePlayer> player;

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
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult pr) {
        player = (Expression<OfflinePlayer>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "the cannons of player " + player.toString(e, b);
    }

    @Override
    @Nullable
    protected String[] get(Event e) {
        if (player.getSingle(e) == null) return null;
        return CannonManager.getCannonList().values().stream()
                .filter(cannon -> cannon.getOwner().equals(player.getSingle(e).getUniqueId()))
                .map(cannon -> cannon.getUID().toString())
                .toArray(String[]::new);
    }

}
